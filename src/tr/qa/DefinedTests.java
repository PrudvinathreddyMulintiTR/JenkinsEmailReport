package tr.qa;

// Main.java will check each of these defined tests using a URL similar to this one:
//  http://pdxsasqa071.corp.ositax.com:8080/job/069 Determination 5.6.0.0 Browser Tests/lastBuild/api/xml

public enum DefinedTests {

	test1("Rest_UserRolesImport"),
	test2("Rest_ListAndDeleteCompany"),
	test3("Rest_ListCounteriesAndUSstates"),
	test4("Rest_LookupServices"),
	test5("Rest_DeleteEstablishedAuthoritiesAndZones"),
	test6("Rest_DeleteRegistrations"),
	test7("Rest_GetAncestorCompanyList"),
	test8("Rest_Eumoss_Authorities"),
	test9("Rest_Add_Edit_Company_Groups_Eumoss"),
	test10("Rest_GetCompany_RelatedGroups"),
	test11("Rest_GetCompAncestor_RelatedGrps"),
	test12("Rest_GetAdminOptions"),
	test13("Rest_Add_Edit_Admin_Options"),
	test14("Rest_SampleRegularExpressions"),
	test15("Rest_AddRegistrations"),
	test16("Rest_ProductMappings"),
	test17("Rest_Save_Manage_List_Company"),
   	test18("Rest_Pagination_Filter"),
    test19("Rest_GetCustomerList"),
	test20("Rest_GetAuthorities"),
   	test21("Rest_Certificates"),
	test22("Rest_ModifyRegistration"),
    test23("Rest_ReferenceList"),
	test24("REST_OneSourceContent"),
	test25("REST_OneSourceContent_ConditionalMapping"),
	test26("REST_OneSourceContent_ProductGroupTabsCounts"),
	test27("Rest_CREATE_GET_DELETE_Allocation_Groups"),	
	test28("REST_CustomProductCategories"),
	test29("Rest_CustomProductCategories_ExemptMap_Copy_ProductGroups"),
	test30("Rest_AddEditGetDeleteConditionalMapping"),
	test31("Rest_AddEditGetDeleteConditionalMappingGroup"),
	test32("Rest_AddEditGetDeleteLicenseType"),
	test33("Rest_AddEditGetDeleteLicense"),
	test34("REST_OneSourceContent_Mapped"),
	test35("Rest_UsersAndRoles"),
	test36("Rest_ERPCodeMapping"),
	test37("REST_Zones_ZoneLevels"),
	test38("REST_Zones_Authorities"),
	test39("Rest_Zones-Search"),
	test40("Rest_UnitsOfMeasure_ConversionFactors"),
	test41("REST_StandardRatesAndFees"),
	test42("REST-CreateCustomRates"),
	test43("Rest_Get_StandardAuthority"),
	test44("Rest_CustomProductCategories_ImpactedCompanies"),
	test45("REST_UpdateCustomRates"),
	test46("Rest_AddEditCopyGetDeleteCustomAuthority"),
	test47("REST_CustomRatesandFees_ListandDetails"),
	test48("Rest_VATGroupRegistrations"),
	test49("Rest_ContributingAuthorites"),
	test50("Rest_HomePage"),
	test51("Rest_AddEditgetDelete_AuthorityType"),
	test52("REST_ExpireAllocation"),
	test53("Rest_LocationsAddDeleteUpdateView"),
	test54("Rest_ModifyEstablishedAuthorities"),
	test55("REST_RenameAllocation"),
	test56("Rest_RuleQualifiers"),
	test57("Rest_UOM_EndToEnd"),
	test58("Rest_CreateEditGetDeleteAllocation"),
	test59("Rest_AddEditGetDeleteCompanyVCTConfiguration"),
	test60("REST_OilAndGas_TransportationTypes");
	   
	private final String test;       

	private DefinedTests( String s ) {
		test = s;
	}

	public boolean equalsName( String otherName ) {
		return ( otherName == null )? false:test.equals( otherName );
	}

	public String toString() {
		return test;
	}

}
