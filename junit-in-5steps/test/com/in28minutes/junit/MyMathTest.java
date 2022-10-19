package com.in28minutes.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyMathTest {

    MyMath myMath = new MyMath();
    //MyMath.sum
    //1,2,3
    @Test
    void sum() {
        assertEquals(6, myMath.sum(new int[]{1, 2, 3}));
    }
    @Test
    void sumWithOneNUmber() {
        assertEquals(3, myMath.sum(new int[]{ 3}));
    }

}