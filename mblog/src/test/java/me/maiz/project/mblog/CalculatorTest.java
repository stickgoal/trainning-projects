package me.maiz.project.mblog;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;


public class CalculatorTest extends TestCase {

    @Test
    public void testSumException(){
        try {
            new Calculator().sum(-1, -1);
        }catch (IllegalArgumentException e){
            assertEquals(e.getMessage(),"参数错误");
        }
    }
    @Test
    public void testSum(){
        try {
            final double sum = new Calculator().sum(1, 1);
            assertEquals(sum,2.0);
        }catch (IllegalArgumentException e){
            fail();
        }
    }


}