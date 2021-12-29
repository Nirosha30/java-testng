package org.trianz.eagleaccess.tests.post;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.trianz.eagleaccess.utils.rest.restGetHelper;
import org.trianz.eagleaccess.utils.sql.sqlGetHelper;

import java.sql.SQLException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class postEmployeeInfo {

    @Test(description = "Verify if the response with DB",enabled = true)
    public static void testPostEmployeeInfo() throws SQLException {

        String jsonBody = getPayload();
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(jsonBody)
                .post("https://reqres.in/api/users");
        System.out.println(response.getStatusCode());

    }

    public static String getPayload(){
        return "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
    }

}
