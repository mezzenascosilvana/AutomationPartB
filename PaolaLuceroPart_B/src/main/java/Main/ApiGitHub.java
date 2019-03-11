package Main;

import java.io.IOException;
import java.util.ArrayList;

import GitHubHandling.GitHubHandlerRequest;
import GitHubHandling.GitHubHandlerResponse;
import Log.CreateLog;

public class ApiGitHub {

	public static CreateLog log = new CreateLog();

	static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
	static final String REQUESTURL = "https://api.github.com/search/repositories";

	public static void main(String[] args) throws IOException {
		
		GitHubHandlerRequest obj1 =new GitHubHandlerRequest();
		ArrayList<String> parameters = obj1.setParameters(args[0], args[1] );
		String newRequestUrl = REQUESTURL + "?q=" + parameters.get(0) + "&sort=stars&order=desc";
		System.out.println(REQUESTURL);
		log.createLog("RESPONSE: " + newRequestUrl);
		String result = obj1.getHttpResponse(newRequestUrl, USER_AGENT);
		GitHubHandlerResponse obj2 = new GitHubHandlerResponse();
		ArrayList<String> nameAndStarResult = obj2.getKeys(result, "ShowFirstResultName&Star");
		log.createLog("RESPONSE: " + nameAndStarResult.get(0));
		log.createLog("RESPONSE: " + nameAndStarResult.get(1));
		String lastReleaseTagResult = obj2.showLatestReleaseTag(result, USER_AGENT);
		log.createLog("RESPONSE SECOND URL: " + lastReleaseTagResult);
		System.out.println(lastReleaseTagResult);
		obj2.verifyLastedRelease(lastReleaseTagResult, parameters.get(1));
		
}}

	