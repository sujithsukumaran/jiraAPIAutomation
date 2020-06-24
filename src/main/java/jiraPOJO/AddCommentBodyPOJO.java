package jiraPOJO;

import java.util.List;

public class AddCommentBodyPOJO {
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public List<AddCommentContentParentPOJO> getContent() {
		return contents;
	}
	public void setContent(List<AddCommentContentParentPOJO> contents) {
		this.contents = contents;
	}
	private String type;
	private int version;
	private List<AddCommentContentParentPOJO> contents;
	
	

}
