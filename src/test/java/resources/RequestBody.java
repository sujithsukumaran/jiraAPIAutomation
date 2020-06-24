package resources;

import java.util.ArrayList;
import java.util.List;

import jiraPOJO.AddCommentBodyPOJO;
import jiraPOJO.AddCommentContentChildPOJO;
import jiraPOJO.AddCommentContentParentPOJO;
import jiraPOJO.AddCommentPOJO;
import jiraPOJO.CreateFieldPOJO;
import jiraPOJO.CreateIssuePOJO;
import jiraPOJO.CreateIssueTypePOJO;
import jiraPOJO.CreateProjectPOJO;

public class RequestBody {

	public static CreateIssuePOJO createIssueBody(String issueType, String summary)
	{
		CreateIssuePOJO cib = new CreateIssuePOJO();
		
		CreateFieldPOJO c= new CreateFieldPOJO();
		c.setSummary(summary);
		
		CreateIssueTypePOJO i = new CreateIssueTypePOJO();
		i.setId(issueType);
		c.setIssuetype(i);
		
		CreateProjectPOJO p = new CreateProjectPOJO();
		p.setKey("RES");
		c.setProject(p);
		
		cib.setFields(c);
		return cib;
	}
	
	
	public static AddCommentPOJO addCommentBody(String comment)
	{
         
         
         AddCommentContentChildPOJO contentChild = new AddCommentContentChildPOJO();
	     AddCommentContentParentPOJO contentParent = new AddCommentContentParentPOJO();
	     AddCommentBodyPOJO commentBody = new AddCommentBodyPOJO();
         AddCommentPOJO acpojo = new AddCommentPOJO();
	     
	     contentChild.setText(comment);
	     contentChild.setType("text");
	     
	     List<AddCommentContentChildPOJO> childToParentComment = new ArrayList<AddCommentContentChildPOJO>();
         childToParentComment.add(contentChild);
	     
	     contentParent.setType("paragraph");
	     contentParent.setContent(childToParentComment);
	     
	     List<AddCommentContentParentPOJO> parentToBodyComment = new ArrayList<AddCommentContentParentPOJO>();
	     parentToBodyComment.add(contentParent);
	     
	     commentBody.setType("doc");
	     commentBody.setVersion(1);
	     commentBody.setContent(parentToBodyComment);
	     
	     acpojo.setBody(commentBody);
		 return acpojo;
	}
}
