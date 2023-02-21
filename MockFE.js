describe('Mock FE', () => {

    it('Mock FE', () => {
        cy.intercept('GET', 'https://apiextuat.tcbs.com.vn/ipwbiz-front/v1/isave/cash/transaction-history?walletType=ISAVEPLUS&fromDate=**',
            { fixture: 'data3.json' })
        cy.intercept('GET', 'https://apiextuat.tcbs.com.vn/ipwbiz-front/v2/isave/cash/wrap**',
            { fixture: 'data4.json' })
        // cy.viewport(1900, 969)
        cy.viewport(367, 667)
        cy.visit('https://tcwealth-uat.tcbs.com.vn/home')
        cy.get('input[formcontrolname="username"]').type('105C727076')
        cy.get('input[formcontrolname="password"]').type('abc123');
        cy.get('span[class="mat-button-wrapper"]').click()
    })

})