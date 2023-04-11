package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class C07_Get_BodyTekrarlardanKurtulma {

    /*
                 https://restful-booker.herokuapp.com/booking/10 url’ine
                 bir GET request gonderdigimizde donen Response’un,
                 status code’unun 200,
                 ve content type’inin application-json,
                 ve response body’sindeki
                     "firstname“in,"Jim",
                     ve "lastname“in, "Wilson",
                     ve "totalprice“in, 609,
                     ve "depositpaid“in,false,
                     ve "additionalneeds“in,"Breakfast"
                 oldugunu test edin
          */
    @Test
    public void get01() {
        // 1 - Request icin gerekli url hazirla
        String url = "https://restful-booker.herokuapp.com/booking/10";
        // 2 - Expected Data hazirla
        // 3 - Response'i kaydet
        Response response = given().when().get(url);
        response.prettyPrint();
        // 4 - Assertion
       /* response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", Matchers.equalTo("Sally"),
                        "lastname", Matchers.equalTo("Smith"),
                        "totalprice", Matchers.equalTo(843),
                        "depositpaid", Matchers.equalTo(true),
                        "additionalneeds", Matchers.equalTo("Breakfast")
                  );
        */


        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname",equalTo("Sally"),
                        "lastname", equalTo("Smith"),
                        "totalprice", equalTo(843),
                        "depositpaid", equalTo(true),
                        "additionalneeds", equalTo("Breakfast")
                );
    }
}