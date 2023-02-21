describe('Example to demonstrate API Mocking in Cypress with TCi3', () => {

    it('Mock margin data',() => {
            //Dùng để fake API, thay đổi phương thức POST, GET, và tên file json tại đây
            // cy.intercept('GET', '**el/getSimulateEL**', { fixture: 'data.json' })assets
            
            cy.intercept('GET', 'https://apibacksit.tcbs.com.vn/pluto/v1/collateral/contract/advance-search?contract-name=**', { fixture: 'data_BE_1.json' })
            cy.intercept('GET', 'https://apibackuat.tcbs.com.vn/ifund-backend/v1/conf/list/detail?mainCode=ENABLE_DISTRIBUTION_PARTLY_BY_RATIO_FLAG**', { fixture: 'data_BE_2.json' })
            // //

            cy.intercept('GET', 'https://apibackuat.tcbs.com.vn/hft-krema/v1/backend-orders?custodyCd&accountNo&tradePlace&symbol&execType&matchType&orderId=&orStatus&isDisposal&isGovBond&pageSize=20&pageIndex=0&userId=101**', { fixture: 'data_BE_2.json' })
            cy.intercept('GET', 'https://apibacksit.tcbs.com.vn/dcm-front/b/v1/book-build/pp/by-bond/**', { fixture: 'data_BE_4.json' })
            cy.intercept('GET', 'https://apibacksit.tcbs.com.vn/hft-krema/v1/backend-orders?custodyCd&accountNo&tradePlace&symbol&execType&matchType&orderId=&orStatus&isDisposal&isGovBond&pageSize**', { fixture: 'BE5.json' })
            // // cy.intercept('POST', 'https://apibacksit.tcbs.com.vn/tcbs-venus/v1/deal-blotter/all?page=0&size=20**', { fixture: 'BE6.json' })
            // cy.intercept('GET', 'https://apibacksit.tcbs.com.vn/tcbs-venus/v1/deal-blotter/cd/detail/12330**', { fixture: 'BE7.json' })
            // cy.intercept('GET', 'https://apibacksit.tcbs.com.vn/tcbs-venus/v1/deal-blotter/onshore-adjustment-detail?instanceId=10972**', { fixture: 'BE8.json' })
            // cy.intercept('GET', 'https://apibacksit.tcbs.com.vn/tcbs-venus/v1/deal-blotter/onshore-settlement-detail?instanceId=12796**', { fixture: 'BE9.json' })
            // cy.intercept('GET', 'https://apibacksit.tcbs.com.vn/tcbs-venus/v1/deal-blotter/loan/onshore/detail/12739**', { fixture: 'BE10.json' })
            
    
            //Dùng để điều chỉnh độ phân giải của màn hình test
            cy.viewport(1900, 969)
            //visit dùng để điều hướng tới url mong muốn
            cy.visit('https://backend-sit.tcbs.com.vn/auth/login?returnUrl=%2Fifund%2Fcross-check%2Fresult-matching')
            cy.wait(1000)
            //Bắt element nhập username và nhập vào số 105C
            cy.get('input[formcontrolname="username"]')
              .type('maker');
            cy.wait(1000)
            //Bắt element nhập password và nhập vào
            cy.get('input[formcontrolname="password"]')
              .type('123456');
            //Tắt popup widget homepage
            cy.get('button[class="btn-login"]').click()
            // cy.get('span[class="mat-button-wrapper"]').click()
            cy.wait(3000)
            // cy.get('input[formcontrolname="total_debt"]')
            // .type('123456789');
            // cy.get('input[formcontrolname="ticker"]')
            // .type('TCB');
            // cy.get('input[formcontrolname="value"]')
            // .type('45000');
            // cy.get('mat-select[formcontrolname="exchange"]').click()
            // cy.wait(1000)
            // cy.get('mat-option[id="mat-option-0"]').click()
            // cy.wait(1000)
            // cy.get('button[class="btn-pricing mat-button mat-button-base"]').click()
          })
        
  })