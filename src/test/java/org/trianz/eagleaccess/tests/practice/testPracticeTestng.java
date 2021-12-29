package org.trianz.eagleaccess.tests.practice;

import org.testng.annotations.*;

public class testPracticeTestng {
    @BeforeSuite
    public static void beforeSuite() {
        System.out.println("Before Suite Got Executed");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Class Got Executed");
    }

    @BeforeTest
    public static void beforeTest() {
        System.out.println("Before Test Got Executed");
    }

    @BeforeMethod
    public static void beforeMethod() {
        System.out.println("Before Method Got Executed");
    }

    @Test
    public static void setup() {
        System.out.println("First Test Got Executed");
    }

    @Test
    public static void tearDown() {
        System.out.println("Second Test Got Executed");
    }

    @AfterMethod
    public static void afterMethod() {
        System.out.println("After Method Got Executed");
    }

    @AfterTest
    public static void afterTest() {
        System.out.println("After Test Got Executed");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After Class Got Executed");
    }

    @AfterSuite
    public static void afterSuite() {
        System.out.println("After Suite Got Executed");
    }
}
