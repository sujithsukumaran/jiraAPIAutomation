package resources;

public enum APIResources {

	CreateIssueAPI("/rest/api/3/issue"),
	GetIssueAPI("/rest/api/3/issue/"),
	ModifyIssueAPI("/rest/api/3/issue/"),
	DeleteIssueAPI("/rest/api/3/issue/");
	
	private String APIrequest;

	APIResources(String APIRequest) {
		this.APIrequest=APIRequest;
		
	}
	
	public String resourceName()
	{
		return APIrequest;
	}
	
}
