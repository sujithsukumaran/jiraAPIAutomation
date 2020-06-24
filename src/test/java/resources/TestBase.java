package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import static org.hamcrest.core.IsEqual.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestBase {
	
	Properties prop = new Properties();
	FileInputStream fis;
	String baseURI;
	String auth;
	protected String projectName;
	String resourceName;
	String actualValue;
	private static RequestSpecification reqSpec;
	PrintStream requestFilter;
	PrintStream responseFilter;
	
	public RequestSpecification requestSpecification(String header, String value) throws IOException
	{
		
		fis = new FileInputStream("src/test/java/resources/data.properties");
		prop.load(fis);
		baseURI = prop.getProperty("baseURI");
		auth = prop.getProperty("Auth");
		projectName=prop.getProperty("JIRAProjectName");
		if(reqSpec==null || header.contains("X-Atlassian-Token"))
		{
		
			if(reqSpec==null)
			{
			requestFilter = new PrintStream(new FileOutputStream("src/test/java/resources/requestlog.txt"));
			responseFilter = new PrintStream(new FileOutputStream("src/test/java/resources/responselog.txt"));
			}
			if(header.contains("X-Atlassian-Token"))
			{
			requestFilter = new PrintStream(new FileOutputStream("src/test/java/resources/attachmentrequestlog.txt"));
			responseFilter = new PrintStream(new FileOutputStream("src/test/java/resources/attachmentresponselog.txt"));
			}
	    	reqSpec = new RequestSpecBuilder().setBaseUri(baseURI)
	    			.addHeader(header, value)
	    			.addFilter(RequestLoggingFilter.logRequestTo(requestFilter))
	    			.addFilter(ResponseLoggingFilter.logResponseTo(responseFilter))
	    			.addHeader("Authorization", auth).build();
		}
	    return reqSpec;
	}
	
	public ResponseSpecification responseSpecification()
	{
		ResponseSpecification respSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		return respSpec;
	}
	
	public String getResourceName(String APIName)
	{
    	APIResources resource=APIResources.valueOf(APIName);
    	resourceName=resource.resourceName();
		return resourceName;
		
	}
	
	public String getKeyValue(Response resp, String keyName)
	{
	    actualValue=resp.getBody().jsonPath().get(keyName).toString();
		return actualValue;
		
	}

}
