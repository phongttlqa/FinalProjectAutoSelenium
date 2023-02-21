import data from "../fixtures/data_BE_1.json"
import obligation from "../fixtures/data_BE_2.json"
import methodlv2 from "../fixtures/data_BE_3.json"
import relationship from "../fixtures/data_BE_4.json"
import contact_status from "../fixtures/BE5.json"
import warning from "../fixtures/BE6.json"
import detail from "../fixtures/BE7.json"
import detail2 from "../fixtures/BE8.json"
import detail3 from "../fixtures/BE9.json"
import expand from "../fixtures/BE10.json"
// import 'cypress-xpath'

describe('DCM - Secured asset contract management', () => {
    beforeEach(() => {
        const username = 'maker'
        const password = '123456'
        const url = 'https://backend-sit.tcbs.com.vn/auth/login?returnUrl=%2Fcollateral%2Fsecurity-contract-management'
        const head_url = 'https://apibacksit.tcbs.com.vn'
        cy.intercept('GET', head_url + '/pluto/v1/collateral/contract/advance-search?contract-name**',
            { fixture: 'data_BE_1.json' }).as('api')

        cy.intercept('GET', head_url + '/pluto/v1/collateral/contract/obligation**',
            { fixture: 'data_BE_2.json' }).as('obligation')

        cy.intercept('GET', head_url + '/pluto/v1/collateral/templates/method-level2**',
            { fixture: 'data_BE_3.json' }).as('methodlv2')

        cy.intercept('GET', 'https://apipubsit.tcbs.com.vn/pluto/v1/collateral/attr-value/find-by-type?types=RELATIONSHIP_STATUS**',
            { fixture: 'data_BE_4.json' }).as('relationship')

        cy.intercept('GET', 'https://apipubsit.tcbs.com.vn/pluto/v1/collateral/attr-value/find-by-type?types=CONTRACT_STATUS**',
            { fixture: 'BE5.json' }).as('contact_status')

        cy.intercept('GET', 'https://apipubsit.tcbs.com.vn/pluto/v1/collateral/attr-value/find-by-type?types=WARNING_EXPRIED**',
            { fixture: 'BE6.json' }).as('warning')

        cy.intercept('GET', 'https://apibacksit.tcbs.com.vn/pluto/v1/collateral/contract/12065**',
            { fixture: 'BE7.json' }).as('detail')
        cy.intercept('GET', 'https://apibacksit.tcbs.com.vn/pluto/v1/collateral/contract/12005**',
            { fixture: 'BE8.json' }).as('detail2')
        cy.intercept('GET', 'https://apibacksit.tcbs.com.vn/pluto/v1/collateral/contract/11585**',
            { fixture: 'BE9.json' }).as('detail3')

        cy.intercept('GET', 'https://apibacksit.tcbs.com.vn/pluto/v1/collateral/contract/relation/**',
            { fixture: 'BE10.json' }).as('expand')

        cy.visit(url)
        cy.viewport(1700, 900)
        cy.get('input[formcontrolname="username"]').type(username)
        cy.get('input[formcontrolname="password"]').type(password)
        cy.get('button[class="btn-login"]').click()


    })

    xit('Veify mapping data - màn hình danh sách', () => { // Tên Nghĩa vụ
        cy.wait('@api', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"name")]//span)[2]').should("be.contain", data.data.at(1).name)
        cy.xpath('(//td[contains(@class,"obligations")]//span)[2]').should("be.contain", data.data.at(1).obligations)
        cy.xpath('(//td[contains(@class,"assetApplies")]//span)[2]').should("be.contain", data.data.at(1).assetApplies)
        cy.xpath('(//td[contains(@class,"blttApplies")]//span)[2]').should("be.contain", data.data.at(1).blttApplies)
        cy.xpath('(//td[contains(@class,"level1Codes")]//span)[2]').should("be.contain", data.data.at(1).level1Codes)
        // cy.xpath('(//td[contains(@class,"totalObligation")])[2]').should("be.contain", data.data.at(1).totalObligation)
        cy.xpath('(//td[contains(@class,"relationStatus")]//div)[2]').should("be.contain", data.data.at(1).relationStatus)
        cy.xpath('(//td[contains(@class,"contractStatus")])[2]').should("be.contain", data.data.at(1).contractStatus)
    })

    xit('Veify mapping data null - màn hình danh sách', () => { // Tên Nghĩa vụ
        cy.wait('@api', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"name")]//span)[1]').should("be.contain", "")
        cy.xpath('(//td[contains(@class,"obligations")]//span)[1]').should("be.contain", "")
        cy.xpath('(//td[contains(@class,"assetApplies")]//span)[1]').should("be.contain", "")
        cy.xpath('(//td[contains(@class,"blttApplies")]//span)[1]').should("be.contain", "")
        cy.xpath('(//td[contains(@class,"level1Codes")]//span)[1]').should("be.contain", "")
        cy.xpath('(//td[contains(@class,"totalObligation")])[1]').should("be.contain", "0")
        cy.xpath('(//td[contains(@class,"relationStatus")]//div)[1]').should("be.contain", "")
        cy.xpath('(//td[contains(@class,"contractStatus")])[1]').should("be.contain", "")
    })

    xit('Veify mapping multi data - màn hình danh sách', () => {
        cy.wait('@api', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"obligations")]//span)[3]').should("be.contain", data.data.at(2).obligations.at(0) + ' ,...')
        cy.xpath('(//td[contains(@class,"assetApplies")]//span)[3]').should("be.contain", data.data.at(2).assetApplies.at(0) + ' ,...')
        cy.xpath('(//td[contains(@class,"blttApplies")]//span)[3]').should("be.contain", data.data.at(2).blttApplies.at(0) + ' ,...')
        cy.xpath('(//td[contains(@class,"level1Codes")]//span)[3]').should("be.contain", data.data.at(2).level1Codes.at(0) + ' ,...')
    })

    xit('Veify mapping droplist obligation - Droplist Nghĩa Vụ', () => {
        cy.wait('@obligation', { timeout: 15000 })
        cy.xpath('(//mat-select)[1]').click()
        cy.xpath('(//mat-option)[1]').should("be.contain", obligation.at(0).obligationName)
        cy.xpath('(//mat-option)[2]').should("be.contain", obligation.at(1).obligationName)
        cy.xpath('(//mat-option)[3]').should("be.contain", obligation.at(2).obligationName)
    })

    xit('Veify mapping droplist method-level 2 - Loại tài sản lv2', () => {
        // đang có bug UI 
        //BE gom các bản ghi có cùng 'level1Id' vào 1 nhóm và hiển thị 'level1Name' làm tên nhóm
        //Chi tiết các nhóm hiển thị 'level2Name'
        // cy.wait('@methodlv2', { timeout: 15000 })
        cy.xpath('(//mat-select)[2]').click()
        cy.xpath('(//mat-option)[1]//preceding-sibling::div//child::span//span').should("be.contain", 'ALL')
        cy.xpath('(//mat-option)[1]').should("be.contain", methodlv2.at(0).level2Code)
        cy.xpath('(//mat-option)[2]').should("be.contain", methodlv2.at(1).level2Code)
        cy.xpath('(//mat-option)[3]').should("be.contain", methodlv2.at(2).level2Code)
        cy.xpath('(//mat-option)[3]').should("be.contain", methodlv2.at(3).level2Code)
    })

    xit('Veify mapping droplist relationship - Droplist Mối quan hệ', () => {
        cy.wait('@relationship', { timeout: 15000 })
        cy.xpath('(//mat-select)[3]').click()
        cy.xpath('(//mat-option)[1]').should("be.contain", 'Tất cả')
        cy.xpath('(//mat-option)[2]').should("be.contain", relationship.at(0).attrValues.at(0).name)
        cy.xpath('(//mat-option)[3]').should("be.contain", relationship.at(0).attrValues.at(1).name)
    })

    xit('Veify mapping droplist contract status - Droplist Trạng thái HĐ', () => {
        cy.wait('@contact_status', { timeout: 15000 })
        cy.xpath('(//mat-select)[4]').click()
        cy.xpath('(//mat-option)[1]').should("be.contain", 'Tất cả')
        cy.xpath('(//mat-option)[2]').should("be.contain", contact_status.at(0).attrValues.at(0).name)
        cy.xpath('(//mat-option)[3]').should("be.contain", contact_status.at(0).attrValues.at(1).name)
        cy.xpath('(//mat-option)[4]').should("be.contain", contact_status.at(0).attrValues.at(2).name)
    })

    xit('Veify mapping droplist warning expried - Droplist nội dung cảnh báo', () => {
        cy.wait('@warning', { timeout: 15000 })
        cy.xpath('(//mat-select)[5]').click()
        cy.xpath('((//mat-option)[1]//preceding-sibling::div//child::span)[1]').should("be.contain", 'ALL')
        cy.xpath('(//mat-option)[1]').should("be.contain", warning.at(0).attrValues.at(0).name)
        cy.xpath('(//mat-option)[2]').should("be.contain", warning.at(0).attrValues.at(1).name)
        cy.xpath('(//mat-option)[3]').should("be.contain", warning.at(0).attrValues.at(2).name)
    })

    xit('Veify mapping droplist Action', () => {
        cy.wait('@api', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"actions")]//button)[1]').click()
        cy.xpath('(//button[contains(@class,"menu-item")])[1]').should("be.contain", 'Xem chi tiết')
        cy.xpath('(//button[contains(@class,"menu-item")])[2]').should("be.contain", 'Định giá')
        cy.xpath('(//button[contains(@class,"menu-item")])[3]').should("be.contain", 'Bảo hiểm')
        cy.xpath('(//button[contains(@class,"menu-item")])[4]').should("be.contain", 'Điều chỉnh tài sản')
    })

    xit('Veify mapping detail screen - Màn hình chi tiết - Thông tin chung Null', () => {
        cy.wait('@api', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"actions")]//button)[1]').click()
        cy.xpath('(//button[contains(@class,"menu-item")])[1]').click()
        cy.wait('@detail', { timeout: 15000 })
        cy.xpath('(//div[contains(@class,"info-val")])[1]').should("be.contain", "--")
        cy.xpath('(//div[contains(@class,"info-val")])[2]').should("be.contain", "--")
        cy.xpath('(//div[contains(@class,"info-val")])[3]').should("be.contain", "--")
        cy.xpath('(//div[contains(@class,"info-val")])[4]').should("be.contain", "--")
        cy.xpath('(//div[contains(@class,"info-val")])[5]').should("be.contain", "--")
        cy.xpath('(//div[contains(@class,"info-val")])[6]').should("be.contain", "")
        cy.xpath('(//div[contains(@class,"info-val")])[7]').should("be.contain", "--")
    })

    xit('Veify mapping detail screen - Màn hình chi tiết - Thông tin chung Not Null', () => {
        cy.wait('@api', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"actions")]//button)[2]').click()
        cy.xpath('(//button[contains(@class,"menu-item")])[1]').click()
        cy.wait('@detail2', { timeout: 15000 })
        cy.xpath('(//div[contains(@class,"info-val")])[1]').should("be.contain", detail2.name)
        cy.xpath('(//div[contains(@class,"info-val")])[2]').should("be.contain", detail2.obligations)
        cy.xpath('(//div[contains(@class,"info-val")])[3]').should("be.contain", detail2.assetApplies)
        cy.xpath('(//div[contains(@class,"info-val")])[4]').should("be.contain", detail2.blttApplies)
        cy.xpath('(//div[contains(@class,"info-val")])[5]').should("be.contain", formatRelationship(detail2.relationStatus))
        cy.xpath('(//div[contains(@class,"info-val")])[6]').should("be.contain", detail2.description)
        cy.xpath('(//div[contains(@class,"info-val")])[7]').should("be.contain", detail2.warnings)
    })

    xit('Veify mapping detail screen - Màn hình chi tiết - Thông tin chung nhiều dữ liệu', () => {
        cy.wait('@api', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"actions")]//button)[3]').click()
        cy.xpath('(//button[contains(@class,"menu-item")])[1]').click()
        cy.wait('@detail3', { timeout: 15000 })
        cy.xpath('(//div[contains(@class,"info-val")])[1]').should("be.contain", detail3.name)
        cy.xpath('(//div[contains(@class,"info-val")])[2]').should("be.contain", detail3.obligations.at(0) + ', ' + detail3.obligations.at(1))
        cy.xpath('(//div[contains(@class,"info-val")])[3]').should("be.contain", detail3.assetApplies.at(0) + ', ' + detail3.assetApplies.at(1))
        cy.xpath('(//div[contains(@class,"info-val")])[4]').should("be.contain", detail3.blttApplies.at(0) + ', ' + detail3.blttApplies.at(1))
        cy.xpath('(//div[contains(@class,"info-val")])[5]').should("be.contain", formatRelationship(detail3.relationStatus))
        cy.xpath('(//div[contains(@class,"info-val")])[6]').should("be.contain", detail3.description.at(0) + ', ' + detail3.description.at(1))
        cy.xpath('(//div[contains(@class,"info-val")])[7]').should("be.contain", detail3.warnings.at(0) + ', ' + detail3.warnings.at(1))
    })

    xit('Veify mapping detail screen - Màn hình chi tiết - Cụm mối quan hệ Null', () => {
        cy.wait('@api', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"actions")]//button)[1]').click()
        cy.xpath('(//button[contains(@class,"menu-item")])[1]').click()
        cy.wait('@detail', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"relationName")])[1]//span').should("be.contain", "--")
        cy.xpath('(//td[contains(@class,"relationType")])[1]//span').should("be.contain", "--")
        cy.xpath('(//td[contains(@class,"assetBlttApplies")])[1]//span').should("be.contain", "--")
        cy.xpath('(//td[contains(@class,"guarantees")])[1]//span').should("be.contain", "--")
        cy.xpath('(//td[contains(@class,"obligations")])[1]//span').should("be.contain", "--")
        cy.xpath('(//td[contains(@class,"totalValuationValue")])[1]').should("be.contain", "0")
        cy.xpath('(//td[contains(@class,"totalMortgageValue")])[1]').should("be.contain", "0")
        cy.xpath('(//td[contains(@class,"totalGuaranteeValue")])[1]').should("be.contain", "0")
        cy.xpath('(//td[contains(@class,"totalObligationValue")])[1]').should("be.contain", "0")
        cy.xpath('(//div[contains(@class,"relation-status-text")])[1]').should("be.contain", "--")
    })

    xit('Veify mapping detail screen - Màn hình chi tiết - Cụm mối quan hệ Not Null', () => {
        cy.wait('@api', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"actions")]//button)[2]').click()
        cy.xpath('(//button[contains(@class,"menu-item")])[1]').click()
        cy.wait('@detail2', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"relationName")])[1]//span').should("be.contain", detail2.relations.at(0).relationName)
        cy.xpath('(//td[contains(@class,"assetBlttApplies")])[1]//span')
            .should("be.contain", detail2.relations.at(0).assetApplies + ', ' + detail2.relations.at(0).blttApplies)
        cy.xpath('(//td[contains(@class,"guarantees")])[1]//span').should("be.contain", detail2.relations.at(0).guarantees)
        cy.xpath('(//td[contains(@class,"guarantees")]//following-sibling::td[contains(@class,"obligations")])[1]')
            .should("be.contain", detail2.relations.at(0).obligations)
        cy.xpath('(//td[contains(@class,"totalValuationValue")])[1]')
            .should("be.contain", formartAmount(detail2.relations.at(0).totalValuationValue, 0, 3, ',', ''))
        cy.xpath('(//td[contains(@class,"totalMortgageValue")])[1]')
            .should("be.contain", formartAmount(detail2.relations.at(0).totalMortgageValue, 0, 3, ',', ''))
        cy.xpath('(//td[contains(@class,"totalGuaranteeValue")])[1]')
            .should("be.contain", formartAmount(detail2.relations.at(0).totalGuaranteeValue, 0, 3, ',', ''))
        cy.xpath('(//td[contains(@class,"totalObligationValue")])[1]')
            .should("be.contain", formartAmount(detail2.relations.at(0).totalObligationValue, 0, 3, ',', ''))
    })

    xit('Veify mapping detail screen - Màn hình chi tiết - Cụm mối quan hệ - relationType', () => {
        cy.wait('@api', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"actions")]//button)[2]').click()
        cy.xpath('(//button[contains(@class,"menu-item")])[1]').click()
        cy.wait('@detail2', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"relationType")])[1]//span').should("be.contain", formatRelationType(detail2.relations.at(0).relationType))
        cy.xpath('(//td[contains(@class,"relationType")])[2]//span').should("be.contain", formatRelationType(detail2.relations.at(1).relationType))
    })

    xit('Veify mapping detail screen - Màn hình chi tiết - Cụm mối quan hệ Not Null - relationStatus', () => {
        cy.wait('@api', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"actions")]//button)[2]').click()
        cy.xpath('(//button[contains(@class,"menu-item")])[1]').click()
        cy.wait('@detail2', { timeout: 15000 })
        cy.xpath('(//div[contains(@class,"relation-status-text")])[1]').should("be.contain", formatRelationship(detail2.relations.at(0).relationStatus))
        cy.xpath('(//div[contains(@class,"relation-status-text")])[1]').should("be.contain", formatRelationship(detail2.relations.at(0).relationStatus))
    })

    xit('Veify mapping detail screen - Màn hình chi tiết - Cụm mối quan hệ - Nhiều data', () => {
        cy.wait('@api', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"actions")]//button)[3]').click()
        cy.xpath('(//button[contains(@class,"menu-item")])[1]').click()
        cy.wait('@detail3', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"assetBlttApplies")])[1]//span')
            .should("be.contain", detail3.relations.at(0).assetApplies.at(0) + ', ' +
                detail3.relations.at(0).assetApplies.at(1) + ', ' +
                detail3.relations.at(0).blttApplies.at(0) + ', ' +
                detail3.relations.at(0).blttApplies.at(1))
        cy.xpath('(//td[contains(@class,"guarantees")])[1]//span').should("be.contain",
            detail3.relations.at(0).guarantees.at(0) + ', ' + detail3.relations.at(0).guarantees.at(1))
        cy.xpath('(//td[contains(@class,"guarantees")]//following-sibling::td[contains(@class,"obligations")])[1]').should("be.contain",
            detail3.relations.at(0).obligations.at(0) + ', ' + detail3.relations.at(0).obligations.at(1))
    })

    it('Veify mapping detail screen - Màn chi tiết khi expand các mối quan hệ', () => {
        cy.wait('@api', { timeout: 15000 })
        cy.xpath('(//td[contains(@class,"actions")]//button)[2]').click()
        cy.xpath('(//button[contains(@class,"menu-item")])[1]').click()
        cy.wait('@detail2', { timeout: 15000 })
        cy.xpath('(//div[contains(@class,"relation-icon")])[1]').click()
        cy.wait('@expand', { timeout: 15000 })
        // cy.xpath('(//td[contains(@class,"guarantees")])[1]//span').should("be.contain",detail3.relations.at(0).guarantees.at(0))
        // cy.xpath('(//td[contains(@class,"guarantees")]//following-sibling::td[contains(@class,"obligations")])[1]').should("be.contain",
        //     detail3.relations.at(0).obligations.at(0) + ', ' + detail3.relations.at(0).obligations.at(1))
    })


})


var formatRelationship = (data) => {
    if (data === null) return '--'
    switch (data) {
        case 'KHONG_DAP_UNG':
            return 'Không đáp ứng'
        case 'DAP_UNG':
            return 'Đáp ứng'
        default: return data
    }
}

var formatRelationType = (data) => {
    if (data === null) return '--'
    switch (data) {
        case 'BAO_LANH':
            return 'BLTT'
        case 'TAI_SAN':
            return 'Tài sản'
        default: return data
    }
}

var formartAmount = (data, n, x, s, c) => {
    if (data != "--") {
        var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\D' : '$') + ')',
            num = data.toFixed(Math.max(0, ~~n));
        return (c ? num.replace('.', c) : num).replace(new RegExp(re, 'g'), '$&' + (s || ','));
    }
    else return "--";
}
