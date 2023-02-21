import profiledto from "C:/Users/phongtt5/VisualPro/cypress/fixtures/profiledto.json"

const ja = require("assert");
// const { expect } = require("chai"); 

const { profile, info } = require("console");
describe('empty spec', () => {
  it('passes', () => {
    
    cy.visit('https://backend-sit.tcbs.com.vn/auth/login?returnUrl=%2Fusers%2Flist');
    cy.viewport(1600,900);
    cy.get('input[formcontrolname="username"]').type('wso2isadmin');
    cy.wait(1000)
    cy.get('input[formcontrolname="password"]').type('iUXq6wAUyVP9HEks');
    cy.get('button[class="btn-login"]').click()
    cy.wait(3000);
    //stub login api 

    cy.intercept('GET','**findCustomerOps?code105c=105C000082**', 
    { fixture: 'Backend/Customer/ManageCustomerFromTCinvest/profiledto.json'});

    cy.get('#mat-select-0 > .mat-select-trigger > .mat-select-arrow-wrapper').click();
    cy.get('#mat-option-0 > .mat-option-text').click();
    cy.get('#mat-input-2').type('105C000082');
    cy.get('form.ng-touched > .row > .button-container > .button-action').click();
    // So sánh xem mapping đúng hay chưa
    cy.wait(1000)
    //Trường hợp đúng
    cy.xpath('//div[text()="Họ và tên"]/following-sibling::div')
      .should("have.text", profiledto.profileDto.personalInfo.fullName);
    cy.xpath('//div[text()="Tên viết tắt"]/following-sibling::div')
      .should("have.text", replaceNullValue(profiledto.profileDto.personalInfo.acronym, '--'));
    
    cy.xpath('//div[text()="Giới tính"]/following-sibling::div')
    .should("have.text", mappingGender(profiledto.profileDto.personalInfo.gender));

    //Trường hợp sai
    cy.xpath('//div[text()="Tên viết tắt"]/following-sibling::div').should("have.text", profiledto.profileDto.personalInfo.fullName);

    
   //  cy.get(".user-name").contains(objectData.first);    
   //  cy.get('.user-name').should(($p) => {
   //    expect($p.first()).to.contain('Hello World')})
  })
})

var replaceNullValue = (jsonValue, defaultValue) => {
  return jsonValue === null ? defaultValue : jsonValue;
 }

var mappingGender = (gender) => {
  if(gender == "FEMALE")
   return "Nữ";
   else return "Nam";
}

