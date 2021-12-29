package org.trianz.eagleaccess.tests.get;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class getWeatherInfo {

    @Test(description = "Verify if the response code is 200",enabled = true,dataProvider = "cityData",priority =1)
    public static void testResponsecode(String city){
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://demoqa.com/utilities/weather/city/"+city)
                .then()
                .extract().response();
        System.out.println("ResponseCode is " + response.getStatusCode());
        assertThat(response.statusCode()).isEqualTo(200);

        System.out.println("Response is " + response.asString());
    }
    @Test(description = "Verify if the temperature",enabled = true,dataProvider="cityTemperature",priority =0 )
    public static void testTemperature(String city,String Temperature){
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://demoqa.com/utilities/weather/city/"+city)
                .then()
                .extract().response();

        System.out.println("ResponseCode is " + response.getStatusCode());
        if(response.getStatusCode()==200){
            System.out.println("Response for " +city+"-"+ response.path("Temperature"));
            System.out.println("Response for " +city+"-"+Temperature);
            Map<Object, Object> test = response.jsonPath().getMap("");
            System.out.println(test);
            System.out.println(test.get("City")+" - "+test.get("Temperature"));
            assertThat(response.path("Temperature").toString()).isEqualTo("80 Degree celsius");

        }


    }
    @Test(description = "Verify if the temperature",enabled = true,dataProvider="cityTemperature",dependsOnMethods = "testTemperature")
    public static void testHumidity(String city,String Temperature) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://demoqa.com/utilities/weather/city/" + city)
                .then()
                .extract().response();
        System.out.println("ResponseCode is " + response.getStatusCode());
        if (response.getStatusCode() == 200) {
            System.out.println("Response for " + city + "-" + response.path("Temperature"));
            System.out.println("Response for " + city + "-" + Temperature);

            assertThat(response.path("Temperature").toString()).isEqualTo("80 Degree celsius");

        }

    }
        @DataProvider
    private static Object[][] cityData() {
        Object[][] data = new Object[][]{
                // Description  |  Channel Id  |  Expected status  |  Expected error
                new Object[]{"Chennai"},
                new Object[]{"Hydrabad"},
                new Object[]{"Bangalore"},
                new Object[]{"Delhi"},

        };
        return data;
    }
    @DataProvider
    private static Object[][] cityTemperature() {
        Object[][] data = new Object[][]{
                // Description  |  Channel Id  |  Expected status  |  Expected error
                new Object[]{"Chennai","96 Degree celsius"},
                new Object[]{"Hydrabad","3 Degree celsius"},
                new Object[]{"Bangalore","3 Degree celsius"},
                new Object[]{"Delhi","3 Degree celsius"},

        };
        return data;
    }
}
