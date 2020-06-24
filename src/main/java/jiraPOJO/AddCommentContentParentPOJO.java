package jiraPOJO;

import java.util.List;

public class AddCommentContentParentPOJO {
	
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<AddCommentContentChildPOJO> getContent() {
		return content;
	}
	public void setContent(List<AddCommentContentChildPOJO> content) {
		this.content = content;
	}
	private List<AddCommentContentChildPOJO> content;

	
	


}
