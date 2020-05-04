/**
 * Created by UC221101 on 5/1/2020.
 */

package tr.qa;

// Main.java will check each of these defined tests using a URL similar to this one:
//  http://c962stnapp.intqa.thomsonreuters.com:8080/job/QA_BuildFlow/lastBuild/api/xml

public enum SatTests {

    test1("UI_SAT_Allocations_Tests"),
    test2("UI_SAT_AllThemesVerificationPostImport_Test"),
    test3("UI_SAT_AuthorityOptions_Tests"),
    test4("UI_SAT_AuthorityTypes_Tests"),
    test5("UI_SAT_Certificate_Tests"),
    test6("UI_SAT_CertificateManager_Certificates_Tests"),
    test7("UI_SAT_CMNotifications_Tests"),
    test8("UI_SAT_CMportal_Tests"),
    test47("UI_SAT_Companies_Tests"),
    test9("UI_SAT_Company_Regression_Tests"),
    test10("UI_SAT_CompanyAdminRole_Tests"),
    test11("UI_SAT_ConditionalMapping_Tests"),
    test12("UI_SAT_ContributingAuthorities_Tests"),
    test13("UI_SAT_ContributingAuthoritiesFieldValidations_Tests"),
    test14("UI_SAT_CustomAuthorities_Tests"),
    test15("UI_SAT_customAuthorities_fieldValidation_Tests"),
    test16("UI_SAT_Customer_ExemptionCertificate_Tests"),
    test17("UI_SAT_Customer_Tests"),
    test18("UI_SAT_CustomFields_Tests"),
    test19("UI_SAT_CustomProducts_Tests"),
    test20("UI_SAT_CustomRateAndFees_Tests"),
    test21("UI_SAT_CustomRules_Tests"),
    test22("UI_SAT_DataManagerRole_Tests"),
    test23("UI_SAT_ERPCodeMappings_Tests"),
    test24("UI_SAT_ErrorMessageValidationTest.js"),
    test25("UI_SAT_ExemptReasons_Tests"),
    test26("UI_SAT_Licenses_Tests"),
    test27("UI_SAT_LicenseTypes_Tests"),
    test28("UI_SAT_Locations_Tests"),
    test29("UI_SAT_LocationSets_Tests"),
    test30("UI_SAT_LookupLists_Tests"),
    test31("UI_SAT_MandatoryFieldValidation_Tests"),
    test32("UI_SAT_ModelScenario_Tests"),
    test33("UI_SAT_OneSourceContent_Tests"),
    test34("UI_SAT_PowerUserRoles_Tests"),
    test35("UI_SAT_ResearchUserRole_Tests"),
    test36("UI_SAT_SiteWideNavigation_Test"),
    test37("UI_SAT_StandardMapping_Tests"),
    test38("UI_SAT_StandardRateAndFees_Tests"),
    test39("UI_SAT_TaxJurisdiction_Tests"),
    test40("UI_SAT_TaxJurisdictionIntl_Tests"),
    test41("UI_SAT_UnitsOfMesure_Tests"),
    test42("UI_SAT_UsersAndRoles_Tests"),
    test43("UI_SAT_VATGroupRegistrations_Tests"),
    test44("UI_SAT_ViewOnlyRoles_Tests"),
    test45("UI_SAT_Zones_Tests"),
    test46("UI_ThemesVerificationPostImportWithInheritance_Tests");

    private final String test;

    private SatTests( String s ) {
        test = s;
    }

    public String toString() {
        return test;
    }

}