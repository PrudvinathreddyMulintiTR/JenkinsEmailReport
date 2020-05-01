/**
 * Created by UC221101 on 5/1/2020.
 */

package tr.qa;

// Main.java will check each of these defined tests using a URL similar to this one:
//  http://c962stnapp.intqa.thomsonreuters.com:8080/job/QA_BuildFlow/lastBuild/api/xml

public enum SatTests {

    test1("UI_AddCompany_Tests"),
    test2("UI_Allocations_Tests"),
    test3("UI_AuthorityOptions_Tests"),
    test4("UI_AuthorityOptions_Tests"),
    test5("UI_Certificate_Test"),
    test6("UI_CertificateManager_Certificates_Tests"),
    test7("UI_CMNotifications_Tests"),
    test8("UI_CMportal_Tests"),
    test9("UI_Company_Regression_Tests"),
    test10("UI_CompanyAdminRole_Tests"),
    test11("UI_ConditionalMapping_Tests"),
    test12("UI_ContributingAuthorities_Tests"),
    test13("UI_ContributingAuthoritiesFieldValidations_Tests"),
    test14("UI_CustomAuthorities_Tests"),
    test15("UI_CustomAuthoritiesFieldValidation"),
    test16("UI_Customers_ExemptionCertificate_Tests"),
    test17("UI_Customers_Tests"),
    test18("UI_CustomFields_Tests"),
    test19("UI_CustomProducts_Tests"),
    test20("UI_CustomRateAndFees_Tests"),
    test21("UI_CustomRules_Tests"),
    test22("UI_DataManagerRole_Tests"),
    test23("UI_ERPCodeMappings_Tests"),
    test24("UI_ErrorMessageValidationTest.js"),
    test25("UI_ExemptReasons_Tests"),
    test26("UI_Licenses_Tests"),
    test27("UI_LicenseTypes_Tests"),
    test28("UI_Locations_Tests"),
    test29("UI_LocationSets_Tests"),
    test30("UI_LookupLists_Tests"),
    test31("UI_Mandatory_field_level_Validation_Tests"),
    test32("UI_ModelScenario_Tests"),
    test33("UI_OneSourceContent_Tests"),
    test34("UI_PowerUserRoles_Tests"),
    test35("UI_ResearchUserRole_Tests"),
    test36("UI_SiteWideNavigation_Test"),
    test37("UI_StandardMapping_Tests"),
    test38("UI_StandardRateAndFees_Tests"),
    test39("UI_TaxJurisdiction_Tests"),
    test40("UI_TaxJurisdictionIntl_Tests"),
    test41("UI_UnitsOfMesure_Tests"),
    test42("UI_UsersAndRoles_Tests"),
    test43("UI_VATGroupRegistrations_Tests"),
    test44("UI_ViewOnlyRoles_Tests"),
    test45("UI_Zones_Tests");

    private final String test;

    private SatTests( String s ) {
        test = s;
    }

    public String toString() {
        return test;
    }

}