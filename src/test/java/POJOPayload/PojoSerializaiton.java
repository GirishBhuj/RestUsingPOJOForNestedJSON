package POJOPayload;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;

import PojoSerilazaitonObjs.AddPlacePojoObjs;
import PojoSerilazaitonObjs.location;
import io.restassured.RestAssured;

public class PojoSerializaiton {

	public static void main(String[] args) {
		location mLocation = new location();
		
		AddPlacePojoObjs mAddPlacePojoObjs = new AddPlacePojoObjs();

		mLocation.setLng(58.387795);
		mLocation.setLat(-44.4437366);

		mAddPlacePojoObjs.setLocation(mLocation);
		mAddPlacePojoObjs.setAccuracy(830);
		mAddPlacePojoObjs.setName("Sampda Kandve");
		mAddPlacePojoObjs.setPhone_number("0000000000");
		mAddPlacePojoObjs.setAddress("205, Rajas Society, Bhamburda,Pune");
		
		List <String> mTmpList = new ArrayList<String>();
		mTmpList.add("Teja");
		mTmpList.add("Kuhja");
		
		//	public void setTypes(List<String> types)
		mAddPlacePojoObjs.setTypes(mTmpList);
		mAddPlacePojoObjs.setWebsite("www.junanava.com");
		mAddPlacePojoObjs.setLanguage("Marathi");

		//Add address
		RestAssured.baseURI = "https://www.rahulshettyacademy.com";
				
		String mResponse = 
			given().log().all().queryParam("key", "qaclick123").
				body(mAddPlacePojoObjs).
				when().post("maps/api/place/add/json").
				then().assertThat().statusCode(200).
				extract().response().asString();
		
		System.out.println(mResponse);
	}
}
