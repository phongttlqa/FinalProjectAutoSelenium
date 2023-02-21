describe('Example to demonstrate API Mocking in Cypress with TCi3', () => {

    it('Mock margin data',() => {
            //Dùng để fake API, thay đổi phương thức POST, GET, và tên file json tại đây
            // api booking : chứa danh sách trái phiếu
            cy.intercept('GET', 'https://apiextsit.tcbs.com.vn/bond-product/v1/bookings/iconnect?marketType=ICONNECT&action=SELL&cusType=PERSONAL&rateType=REINVEST&tradingDate=**', 
            { fixture: 'data.json' })

            // // check cái basicInfo.type = INDIVIDUAL/CORPORATE
            // cy.intercept('GET', 'https://apiextsit.tcbs.com.vn/profile-r/v2/tc3/get-profile/by-tcbsid/**', 
            // { fixture: 'data2.json' })

            // // check xem có phải NĐT chuyên nghiệp hay không
            // cy.intercept('GET', 'https://apiextsit.tcbs.com.vn/profile/v1/profiles/proTrader-check?date=**', 
            // { fixture: 'data3.json' })

            // api product
            cy.intercept('GET', 'https://apiextsit.tcbs.com.vn/bond-trading/v1/products?filter=channel%3Acus%2Cmarkettype%3Aiconnect&level=basic&order_by=code%28asc%29&excludePP=0**', 
            { fixture: 'data4.json' })
            cy.intercept('GET', 'https://apiextsit.tcbs.com.vn/bond-trading/v2/pricings/iconnect/iadvisor?action=BUY&productCode=**', 
            { fixture: 'data5.json' })
            // cy.intercept('GET', 'https://apiextsit.tcbs.com.vn/ifund/v2/tc3/balances/account/details?productCode=TCBF&tradingDate=03/01/2023&assetCode=FL_X1**', 
            // { fixture: 'data6.json' })
            
            // cy.intercept('GET', '**', { fixture: 'data5.json' })
            //Dùng để điều chỉnh độ phân giải của màn hình test
            // cy.viewport(390, 844)
            cy.viewport(1900, 969)
            //visit dùng để điều hướng tới url mong muốn
            // cy.visit('https://tcwealth-uat.tcbs.com.vn/')
            cy.visit('https://tcwealth-sit.tcbs.com.vn/home')
            //Bắt element nhập username và nhập vào số 105C
            cy.get('input[formcontrolname="username"]').type('105C727076');
            //Bắt element nhập password và nhập vào
            cy.get('input[formcontrolname="password"]').type('abc123');
            //Tắt popup widget homepage
            // cy.get('button[class="btn-login"]').click()
            cy.get('span[class="mat-button-wrapper"]').click()
            cy.wait(5000)
            cy.visit('https://tcwealth-sit.tcbs.com.vn/new-iconnect')
          })
        
  })