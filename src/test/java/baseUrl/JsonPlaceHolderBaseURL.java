package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.runner.Request;

public class JsonPlaceHolderBaseURL {

    // https://jsonplaceholder.typicode.com

   protected RequestSpecification specJsonPlace;

    @Before
    public void setUp(){

      specJsonPlace =new RequestSpecBuilder()
                            .setBaseUri("https://jsonplaceholder.typicode.com")
                            .build();



    }
}
