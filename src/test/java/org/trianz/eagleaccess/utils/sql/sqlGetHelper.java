package org.trianz.eagleaccess.utils.sql;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class sqlGetHelper extends sqlBaseHelper {

    public static HashMap<String,String> employeedetails;
    public static List<HashMap> employeeinfo;

    public static Map getEmployeeInfo(String empid) throws SQLException {
        connectDb();
        String query="SELECT id, email, first_name, last_name, avatar " +
                "FROM employeeInfo where id ='"+empid+"'";
        Statement stmt = conn.createStatement();
        rs = stmt.executeQuery(query);

        while(rs.next()) {
            employeedetails=new HashMap<String,String>();
            employeedetails.put("id",rs.getString(1));
            employeedetails.put("email",rs.getString(2));
            employeedetails.put("first_name",rs.getString(3));
            employeedetails.put("last_name",rs.getString(4));
            employeedetails.put("avatar",rs.getString(5));
        }
        closeDbConnect();
        System.out.println(employeedetails);
        return employeedetails;
    }

    public static List<HashMap> getEmployeeInfoByPage(String pageNumber) throws SQLException {
        connectDb();
        String query="SELECT id, email, first_name, last_name, avatar " +
                "FROM employeeInfo where page ='"+pageNumber+"'";
        Statement stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        employeeinfo = new ArrayList<HashMap>();
        while(rs.next()) {
            employeedetails=new HashMap<String,String>();
            employeedetails.put("id",rs.getString(1));
            employeedetails.put("email",rs.getString(2));
            employeedetails.put("first_name",rs.getString(3));
            employeedetails.put("last_name",rs.getString(4));
            employeedetails.put("avatar",rs.getString(5));
            employeeinfo.add(employeedetails);
        }
        closeDbConnect();
        System.out.println(employeeinfo);
        return employeeinfo;
    }

}
