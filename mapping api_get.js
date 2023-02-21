const ja = require("assert")
describe('empty spec', () => {
  it('passes', () => {
    cy.visit('https://backend-sit.tcbs.com.vn/auth/login?returnUrl=%2Fistock%2Fhft');
    cy.get('input[formcontrolname="username"]').type('maker');
    cy.wait(1000)
    cy.get('input[formcontrolname="password"]').type('123456');
    cy.get('button[class="btn-login"]').click()

    // stub login api 
    let account = {
      "custodyID": "105C000082",
      "accountNo": "0001000069",
      "aftype": "0080",
      "vsdStatus": "Y",
      "accountStatus": "A",
      "marginLimit": 999999,
      "isIA": "N",
      "bankName": "---",
      "bankAccount": null,
      "accountType": "copier",
      "fullName": "Son Nguyen Duc"
    }
    cy.intercept(
      {
        method: "GET",
        url: /customers\/105C000082\/accounts/
      }
      , 
      //fake response
      {
        statusCode: 200,
        body: [account]
      }
    ).as("get_accounts")

    cy.get('[formcontrolname="text105C"]').type('000082');
    cy.get("button").contains("TÌM KIẾM").click();
    
    // So sánh xem mapping đúng hay chưa
    cy.wait("@get_accounts");
    cy.get(".user-name").contains(account.fullName);    
    // cy.get('.user-name').should(($p) => {
    //   expect($p.first()).to.contain('Hello World')})
  })
})
