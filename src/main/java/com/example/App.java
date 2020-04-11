package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;


/**
 * Hello world!
 */
@RunWith(Parameterized.class)
public class App {
    @Parameterized.Parameter(0)
    public int valueA;

    @Parameterized.Parameter(1)
    public int valueB;

    @Parameterized.Parameter(2)
    public int expected;

    @Parameterized.Parameters(name = "{index}:sumOf({0}+{1})={2}")
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {1, 1, 2},
                {2, 6, 8},
                {18, 2, 20},
                {13, 15, 28},
                {1, 5, 6}
        });
    }

    @Test
    public void paramTest() {
        System.out.println(valueA);
    }
}
