Feature: Issues management

@CreateIssue @regression @sanity
Scenario Outline: Create a Jira Issue
Given Request payload is ready with "<IssueType>" and "<Summary>"
When "CreateIssueAPI" is invoked to create issue
Then Response has Statuscode as "201"
And Response has issue created with "key" containing "RES"
And "GetIssueAPI" is invoked successfully with issueKey
#The 8th line can be also replaced by the below two lines
#And "jiraNum" from "self" field is fetched successfully
#And Details retrieved successfully using "jiraNum"

Examples: 
|IssueType|Summary|
|10001    |This is a story|
|10004    |This is a Bug  |

@ModifyTitle @regression
Scenario: Update the summary of issue created
Given Request payload is ready with "10004" and "Jira title updated"
When "ModifyIssueAPI" is invoked with "PUT" method
Then Response has Statuscode as "204"
And "GetIssueAPI" is invoked successfully with issueKey
And Response has "fields.summary" with value "Jira title updated"

@AddComment @regression
Scenario: Add a comment in the issue created
Given Request payload is ready with the comment "Again a sample comment"
When "ModifyIssueAPI" is invoked with "POST" method
Then Response has Statuscode as "201"
And Response has "body.content[0].content[0].text" with value "Again a sample comment"
And "commentID" from "self" field is fetched successfully
And Details retrieved successfully using "commentID"
Then Response has Statuscode as "200"

@DeleteComment @regression
Scenario: Delete the comment from the issue
Given Basic Request payload is ready
When DeleteIssueAPI is invoked to perform delete action
Then Response has Statuscode as "204"
And Details retrieved successfully using "commentID"
Then Response has Statuscode as "404"

@AddAttachment @regression @sanity
Scenario: Add an attachment to issue
Given Request payload is ready with attachment "Samplefile.xlsx"
When "ModifyIssueAPI" is invoked with "POST" method
Then Response has Statuscode as "200"
And Response has "filename" with value "[Samplefile.xlsx]"