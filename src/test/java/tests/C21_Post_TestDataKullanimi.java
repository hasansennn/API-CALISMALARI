package test;

import baseURL.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataHerokuApp;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C21_Post_TestDataKullanimi extends HerokuAppBaseUrl {

    /*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip
    bir POST request gonderdigimizde donen response’un status kodunu ve id haric
    body'sinin asagidaki gibi oldugunu test edin.

	Request body
	      {
	      "firstname" : "Ali",
	      "lastname" : “Bak",
	      "totalprice" : 500,
	      "depositpaid" : false,
	      "bookingdates" : {
                      "checkin" : "2021-06-01",
                      "checkout" : "2021-06-10"
                        },
	      "additionalneeds" : "wi-fi"
	       }

	Expected Body
	{
    "bookingid":24,
    "booking":{
            "firstname":"Ali",
            "lastname":"Bak",
            "totalprice":500,
            "depositpaid":false,
            "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                            },
            "additionalneeds":"wi-fi"
               }
    }
     */

    @Test
    public void post01(){

        // 1 - Url ve request Body hazirla

        specHerokuApp.pathParam("pp1","booking");

        TestDataHerokuApp testDataHerokuApp = new TestDataHerokuApp();

        JSONObject reqBody = testDataHerokuApp.reqBodyJson();

        // 2 - Expected Data hazirla

        JSONObject expBody = testDataHerokuApp.expBodyJson();

        // 3 - Response'i kaydet

        Response response = given()
                .spec(specHerokuApp)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .post("/{pp1}");

        response.prettyPeek();

        // 4 - Assertion

        JsonPath resJP = response.jsonPath();

        assertEquals( testDataHerokuApp.basariliStatusCode, response.getStatusCode() );
        assertEquals( expBody.getJSONObject("booking").get("firstname") , resJP.get("booking.firstname") );
        assertEquals( expBody.getJSONObject("booking").get("lastname")  , resJP.get("booking.lastname") );
        assertEquals( expBody.getJSONObject("booking").get("totalprice")  , resJP.get("booking.totalprice") );
        assertEquals( expBody.getJSONObject("booking").get("depositpaid")  , resJP.get("booking.depositpaid") );
        assertEquals( expBody.getJSONObject("booking").get("additionalneeds")  , resJP.get("booking.additionalneeds") );
        assertEquals( expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin")  ,
                resJP.get("booking.bookingdates.checkin") );
        assertEquals( expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout")  ,
                resJP.get("booking.bookingdates.checkout") );

    }

}