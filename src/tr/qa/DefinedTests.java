package tr.qa;

// Main.java will check each of these defined tests using a URL similar to this one:
//  http://pdxsasqa071.corp.ositax.com:8080/job/069 Determination 5.6.0.0 Browser Tests/lastBuild/api/xml

public enum DefinedTests {

	test1("UI_SiteWideNavigation_Test"),
	test2("UI_Allocations_Tests"),
	test3("UI_Zones_Tests");
	   
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
