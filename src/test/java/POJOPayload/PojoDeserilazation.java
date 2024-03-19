package POJOPayload;


import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import PojoDeserilazationObjs.Api;
import PojoDeserilazationObjs.GetCourseDetails;
import PojoDeserilazationObjs.Mobile;
import PojoDeserilazationObjs.WebAutomation;


public class PojoDeserilazation {

	@JsonIgnoreProperties
	public static void main(String[] args)
	{
		String response = given().
				formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
				formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
				formParams("grant_type", "client_credentials").
				//formParams("scope", "trust").when().log().all().
				formParams("scope", "trust").when().
				post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").
				asString();

		//System.out.println(response);
		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");

		GetCourseDetails mGetCourseDetails = 
				given().queryParams("access_token", accessToken).
				//when().log().all().
				when().
				get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").
				as(GetCourseDetails.class);

		System.out.println("Instructor: "+mGetCourseDetails.getInstructor());
		System.out.println("URL: "+ mGetCourseDetails.getUrl());
		System.out.println("Services: "+ mGetCourseDetails.getServices());
		System.out.println("Expertise: "+ mGetCourseDetails.getExpertise());		
		System.out.println("Linkedin: "+ mGetCourseDetails.getLinkedIn());


		List <Api> mApiCoursesList = mGetCourseDetails.getcourses().getApi();
		List <Mobile> mMobileCoursesList = mGetCourseDetails.getcourses().getMobile();

		String[] ExpectedcourseTitlesArray = {"Selenium Webdriver Java","Cypress","Protractor"};
		
		ArrayList<String> mWebAutomationCoursesTitleArrayList= new ArrayList<String>();
		List <WebAutomation> mActWebAutomationCoursesList = mGetCourseDetails.getcourses().getWebAutomation();
			
		for (int mCount=0;mCount < mActWebAutomationCoursesList.size();mCount++) {
			// Get course title from mWebAutomationCourseList list
			// and store in mWebAutomationCoursesTitleArrayList String array list
			mWebAutomationCoursesTitleArrayList.add(mActWebAutomationCoursesList.
					get(mCount).getCourseTitle());
				
			System.out.println(mCount+ " Course Title: "+mActWebAutomationCoursesList.
					get(mCount).getCourseTitle()+
					" Course Price:"+mActWebAutomationCoursesList.get(mCount).getPrice()
					);
		}

		//Convert string Array to Arraylist - Arrays.asList(ExpectedcourseTitlesArray)
		Assert.assertTrue(mWebAutomationCoursesTitleArrayList.
				equals(Arrays.asList(ExpectedcourseTitlesArray)));
		
	
		for (int mCount=0;mCount < mApiCoursesList.size();mCount++)
		{
			System.out.println(mCount+ " Course Title: "+mApiCoursesList.
					get(mCount).getCourseTitle()+
					" Course Price:"+mApiCoursesList.get(mCount).getPrice()
					);
		}
		
		for (int mCount=0;mCount < mMobileCoursesList.size();mCount++)
		{
			System.out.println(mCount+ " Course Title: "+mMobileCoursesList.
					get(mCount).getCourseTitle()+
					" Course Price:"+mMobileCoursesList.get(mCount).getPrice()
					);
		}
	}	
}