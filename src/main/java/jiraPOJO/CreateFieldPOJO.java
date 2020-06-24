package jiraPOJO;

public class CreateFieldPOJO {
	
	private String summary;
	private CreateIssueTypePOJO issuetype;
	private CreateProjectPOJO project;
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public CreateIssueTypePOJO getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(CreateIssueTypePOJO issuetype) {
		this.issuetype = issuetype;
	}
	public CreateProjectPOJO getProject() {
		return project;
	}
	public void setProject(CreateProjectPOJO project) {
		this.project = project;
	}


}



