package org.trianz.eagleaccess.utils.rest;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class restGetHelper extends restBaseHelper {


    public static Map getEmployeeInfo(String id, String node){

        Response response =  given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users/"+id)
                .then()
                .extract().response();
        System.out.println(response.asString());
//        Map<Object, Object> restResponseMap = response.jsonPath().getMap("data");
        Map<Object, Object> restResponseMap = response.jsonPath().getMap(node);
        System.out.println(restResponseMap);
        return restResponseMap;
    }

    public static List<Map>  getPageInfo(String pageNumber){
        Response response =  given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users?page="+pageNumber)
                .then()
                .extract().response();
        System.out.println(response.asString());
        JsonPath jp = new JsonPath(response.asString());
        List<Map> dataValues = jp.getList("data");
        System.out.println(dataValues);
        return dataValues;
    }
}
