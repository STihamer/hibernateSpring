package com.in28minutes.junit;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssertTest {
    @BeforeAll
    public static void beforeAll(){
        System.out.println("Before All");
    }

    @AfterAll
    public static void AfterAll(){
        System.out.println("After All");
    }

   @BeforeEach
   public void before(){
       System.out.println("Before Each");
   }
    @AfterEach
    public void after(){
        System.out.println("After Each");
    }
    @Test
    public void test(){
        Integer number = 10;
        Integer number1 = 2;
         Integer ex = null;
         int[] array1 = {1,2,3};
         int[] array2 = {1,2,3};
        //assertEquals(1,1);
       // assertTrue(number!=number1);
        //assertNotNull();
        //assertNull(ex);
        assertArrayEquals(array1, array2);
        System.out.println("Test");
    }

    @Test
    public void test2(){
        Integer number = 10;
        Integer number1 = 2;
        Integer ex = null;
        int[] array1 = {1,2,3};
        int[] array2 = {1,2,3};
        //assertEquals(1,1);
        // assertTrue(number!=number1);
        //assertNotNull();
        //assertNull(ex);
        //assertArrayEquals(array1, array2);
        System.out.println("Test2");
    }
}
