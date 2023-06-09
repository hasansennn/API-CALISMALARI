package tests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_Get_ResponseBilgileriAssertion {

    @Test
    public void get01() {

        /*
         https://restful-booker.herokuapp.com/booking/9856 url’ine
        bir GET request gonderdigimizde donen Response’un,
        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK
        ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz.
         */


        // 1 - Gonderecegiz Request icin gerekli olan URL  ihtiyacimizi varsa Request Bady hazirla
        String url = "https://restful-booker.herokuapp.com/booking/10";

        // 2 - Eger soruda bize verilmisse Expected Data hazirla
        // 3 - Bize donen Response i Actual Data olarak kaydet
        Response response = given().when().get(url);
        //System.out.println(response);
        response.prettyPrint();

        //  4- Expected Data ile Actual Datayi karsilastrmamız yani Assertion yapmamiz gerek
        response.then().assertThat().statusCode(200).
        contentType("application/json; charset=utf-8").
        header("Server","Cowboy").
        statusLine("HTTP/1.1 200 OK");


    }
}
