package org.trianz.eagleaccess.tests.get;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.trianz.eagleaccess.utils.rest.restGetHelper;
import org.trianz.eagleaccess.utils.sql.sqlGetHelper;
import org.trianz.eagleaccess.utils.helper.testHelper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class getEmployeeInfo {

    @Test(description = "Verify the response in Map with DB",enabled = true,dataProvider = "empInfo")
    public static void testResponseMap(String id) throws SQLException {
        Map<Object, Object> restResponseMap = restGetHelper.getEmployeeInfo(id,"data");
        Map<Object, Object> sqlResponseMap = sqlGetHelper.getEmployeeInfo(id);
        System.out.println("Rest Response is "+restResponseMap);
        System.out.println("SQL Response is "+sqlResponseMap);
        Assertions.assertThat(testHelper.compareMaps(restResponseMap, sqlResponseMap)).as("Comparing Emp Info").isEqualTo("equal");
    }

    @Test(description = "Verify the response in List<Map> with DB",enabled = true,dataProvider = "pageData")
    public static void testResponseListMap(String pageNumber) throws SQLException {
        List<Map> restResponseMap = restGetHelper.getPageInfo(pageNumber);
        List<HashMap> sqlResponseMap = sqlGetHelper.getEmployeeInfoByPage(pageNumber);
        System.out.println("Rest Response is "+restResponseMap);
        System.out.println("SQL Response is "+sqlResponseMap);
        Assertions.assertThat(testHelper.compareListOfMapsUsingKey(restResponseMap, sqlResponseMap,"id")).as("Comparing Emp Info").isEqualTo("equal");
    }

    @DataProvider
    private static Object[][] empInfo() {
        Object[][] data = new Object[][]{
                // id
                new Object[]{"1"},
                new Object[]{"2"},
                new Object[]{"3"},
                new Object[]{"4"},
                new Object[]{"5"},
                new Object[]{"6"},
                new Object[]{"7"},
        };
        return data;
    }

    @DataProvider
    private static Object[][] pageData() {
        Object[][] data = new Object[][]{
                // PageNumber
                new Object[]{"1"},
                new Object[]{"2"},
        };
        return data;
    }

}
