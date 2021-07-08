package com.suse.travelsales;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class quarkusTest {

    @Test
    public void testRandomEndpoint() {
        given()
          .when().get("/ts/random")
          .then()
             .statusCode(200);
    }

}