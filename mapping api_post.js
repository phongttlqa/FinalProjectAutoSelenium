const ja = require("assert")
describe('empty spec', () => {
  it('passes', () => {
    cy.visit('https://tcinvest.tcbs.com.vn');
    // stub login api
    cy.intercept(
      {
        method: "POST",
        url: "authen/v1/login"
      }
      , //fake response
      {
        statusCode: 200,
        body: {"code":"203004","message":"username and password are not matched"}
      }
      ).as("login-api")
    // Login using UI
    cy.get(".username").type("105C888888");
    cy.get(".password").type("abc123");
    cy.get(".btn-login").click();
    // Wait login api then verify request body
    cy.wait("@login-api").then((interception) => {
      assertJson(interception.request.body,
          {
          "username": "105C888888",
          "password": "abc123",
          "device_info": ja.matchType('string')
        }
      );
    })
  })
})
const assertJson = function (actual, expected) {
  let is_equal = ja.isEqual(expected, actual);
  if (!is_equal) {
    cy.log(actual).then(() => {
      throw new Error("test fails");
    })
  }
}