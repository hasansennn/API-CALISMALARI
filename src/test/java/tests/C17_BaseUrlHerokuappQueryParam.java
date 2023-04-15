package tests;

import baseURL.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.codehaus.groovy.control.io.ReaderSource;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class C17_BaseUrlHerokuappQueryParam extends HerokuAppBaseUrl {

    // Class icinde 3 Test metodu olusturun ve asagidaki testleri yapin

    /*
        1-  https://restful-booker.herokuapp.com/booking endpointine bir GET
        request gonderdigimizde donen response’un status code’unun 200 oldugunu
        ve Response’ta 33071 id'ye sahip bir booking oldugunu test edin
     */

     /*
        2- https://restful-booker.herokuapp.com/booking endpointine gerekli
        Query parametrelerini yazarak “firstname” degeri “Eric” olan rezervasyon
        oldugunu test edecek bir GET request gonderdigimizde, donen response’un
        status code’unun 200 oldugunu ve “Eric” ismine sahip en az bir booking oldugunu test edin
    */

    /*
        3- https://restful-booker.herokuapp.com/booking endpointine gerekli Query
         parametrelerini yazarak “firstname” degeri “Jim” ve “lastname” degeri
         “Jackson” olan rezervasyon oldugunu test edecek bir GET request gonderdigimizde,
         donen response’un status code’unun 200 oldugunu ve “Jim Jackson” ismine sahip
         en az bir booking oldugunu test edin.
    */

    @Test
    public void get01(){
        /*
        1-  https://restful-booker.herokuapp.com/booking endpointine bir GET
        request gonderdigimizde donen response’un status code’unun 200 oldugunu
        ve Response’ta 17 bookingid'ye sahip bir booking oldugunu test edin
     */
        // 1 - Url hazirla

        specHerokuApp.pathParam("pp1","booking");


        // 2 - Expected Data hazirla

        // 3 - Response'i kaydet

        Response response = given().spec(specHerokuApp).when().get("/{pp1}");

        response.prettyPrint();

        // 4 - Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("bookingid", hasItem(17));
    }
    @Test
    public void get02(){
/*
        2- https://restful-booker.herokuapp.com/booking endpointine gerekli
        Query parametrelerini yazarak “firstname” degeri “Eric” olan rezervasyon
        oldugunu test edecek bir GET request gonderdigimizde, donen response’un
        status code’unun 200 oldugunu ve “Eric” ismine sahip en az bir booking oldugunu test edin
    */
        // 1 - Url hazirla

        specHerokuApp.pathParam("a","booking").queryParam("firstname","Eric");

        // 2 - Expected Data

        // 3 - Response'i kaydet

        Response response = given().spec(specHerokuApp).when().get("/{a}");

        response.prettyPrint();

        // 4 - Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("bookingid",hasSize(1));
    }
    @Test
    public void get03(){
/*
        3- https://restful-booker.herokuapp.com/booking endpointine gerekli Query
         parametrelerini yazarak “firstname” degeri “Jim” ve “lastname” degeri
         “Jackson” olan rezervasyon oldugunu test edecek bir GET request gonderdigimizde,
         donen response’un status code’unun 200 oldugunu ve “Jim Jackson” ismine sahip
         en az bir booking oldugunu test edin.
    */

        // 1 - Url hazirla

        specHerokuApp
                .pathParam("pp1","booking")
                .queryParams("firstname","Jim","lastname","Jackson");

        // 2 - Expected Data

        // 3 - Response'i kaydet

        Response response = given().spec(specHerokuApp).when().get("/{pp1}");

        response.prettyPrint();

        // 4 - Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("bookingid",hasSize(3));

    }
}