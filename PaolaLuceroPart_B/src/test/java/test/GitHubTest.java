package test;



import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import GitHubHandling.GitHubHandlerRequest;
import GitHubHandling.GitHubHandlerResponse;
import Log.CreateLog;
import ScannerParameters.SetParameters;


public class GitHubTest {

	static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
	static final String REQUESTURL = "https://api.github.com/search/repositories";
	public static CreateLog log = new CreateLog();

    /***
     * 
     * @param browser
     * @throws IOException 
     */
	@BeforeTest
	public void setup() throws IOException {
		
		SetParameters obj3 =new SetParameters();
		ArrayList<String> parameters = obj3. setUpParameters( );
		String newRequestUrl = REQUESTURL + "?q=" + parameters.get(0) + "&sort=stars&order=desc";
		System.out.println(REQUESTURL);
		log.createLog("RESPONSE: " + newRequestUrl);
	    assertTrue(newRequestUrl!=null);
		getResponse(newRequestUrl);
	}
   
	/***
     * 
     * 
     * @throws IOException 
     * @throws InterruptedException
     */
	@Test 
	public void getResponse(String newRequestUrl) throws IOException {
		
		GitHubHandlerRequest obj1 =new GitHubHandlerRequest();
		String result = obj1.getHttpResponse(newRequestUrl, USER_AGENT);
	    assertTrue(result!=null);
		showFirstResultName_Star(result);
	}
	 /***
     * 
     * 
     * @throws IOException 
	 * @throws InterruptedException
     */
	@Test
	public void showFirstResultName_Star(String result) throws IOException {
		
		GitHubHandlerResponse obj2 = new GitHubHandlerResponse();
		ArrayList<String> nameAndStarResult = obj2.getKeys(result, "ShowFirstResultName&Star");
	    assertTrue(nameAndStarResult!=null);
		showLastReleaseTagResult(obj2, result);
		log.createLog("RESPONSE: " + nameAndStarResult.get(0));
		log.createLog("RESPONSE: " + nameAndStarResult.get(1));
		
	}
	
	 /***
     * 
     * 
     * @throws IOException 
	 * @throws InterruptedException
     */
	@Test
	public void showLastReleaseTagResult(GitHubHandlerResponse obj2, String result) throws IOException {
		
		String lastReleaseTagResult = obj2.showLatestReleaseTag(result, USER_AGENT);
	    assertTrue(lastReleaseTagResult!=null);
		log.createLog("RESPONSE SECOND URL: " + lastReleaseTagResult);	
		System.out.println(lastReleaseTagResult);
		verifyLastedReleaset(obj2,lastReleaseTagResult );
	}
	/**
	 * 
	 * @param obj2
	 * @param lastReleaseTagResult
	 * @throws IOException
	 */
	@Test
	public void verifyLastedReleaset(GitHubHandlerResponse obj2, String lastReleaseTagResult ) throws IOException {
		
		SetParameters obj3 =new SetParameters();
		ArrayList<String> parameters = obj3. setUpParameters( );
		assertTrue(obj2.verifyLastedRelease(lastReleaseTagResult, parameters.get(1)));
		
	}

}
