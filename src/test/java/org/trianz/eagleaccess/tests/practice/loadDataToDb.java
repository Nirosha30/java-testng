package org.trianz.eagleaccess.tests.practice;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.trianz.eagleaccess.utils.sql.sqlBaseHelper;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class loadDataToDb extends sqlBaseHelper {

    public static void main(String[] args) throws SQLException {

        connectDb();

        String dropTable = "DROP TABLE employeeInfo";

        String sql = "CREATE TABLE employeeInfo " +
                "(id VARCHAR(255) not NULL, " +
                " email VARCHAR(255), " +
                " first_name VARCHAR(255), " +
                " last_name VARCHAR(255), " +
                " avatar VARCHAR(255), " +
                " page VARCHAR(255)," +
                " PRIMARY KEY (id))";

        Statement stmt = conn.createStatement();

        try{
            stmt.executeUpdate(dropTable);
        } catch (Exception e){
            System.out.println("Table Does not Exits");
        }
        stmt.executeUpdate(sql);

        for(int i=1;i<=2;i++){
            List<Map> dataValues = getResponse(String.valueOf(i));
            for(Map map:dataValues){
                String query = "INSERT INTO employeeInfo VALUES ('"+map.get("id")+"'," +
                        " '"+map.get("email")+"', '"+map.get("first_name")+"', " +
                        " '"+map.get("last_name")+"', '"+map.get("avatar")+"'," +
                        " '"+String.valueOf(i)+"')";
                stmt.executeUpdate(query);
            }
        }

        System.out.println("Records Inserted");
        closeDbConnect();

    }

    private static List<Map> getResponse(String pageNo) {
        Response response =  given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users?page="+pageNo)
                .then()
                .extract().response();

        JsonPath jp = new JsonPath(response.asString());
        List<Map> dataValues = jp.getList("data");
        return dataValues;
    }

}
