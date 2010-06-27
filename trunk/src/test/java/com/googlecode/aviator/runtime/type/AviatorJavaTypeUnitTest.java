package com.googlecode.aviator.runtime.type;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.googlecode.aviator.exception.ExpressionRuntimeException;


public class AviatorJavaTypeUnitTest {
    private enum OperatorType {
        Add,
        Sub,
        Mult,
        Div,
        Mod
    }


    @Test
    public void testAddWithNumber() {
        doArthOpJavaTypeNumber(OperatorType.Add);
    }


    @Test
    public void testSubWithNumber() {
        doArthOpJavaTypeNumber(OperatorType.Sub);
    }


    @Test
    public void testMultWithNumber() {
        doArthOpJavaTypeNumber(OperatorType.Mult);
    }


    @Test
    public void testDivWithNumber() {
        doArthOpJavaTypeNumber(OperatorType.Div);
    }


    @Test
    public void testModWithNumber() {
        doArthOpJavaTypeNumber(OperatorType.Mod);
    }


    public void doArthOpJavaTypeNumber(OperatorType operatorType) {
        AviatorJavaType byteType = new AviatorJavaType("byteType");
        AviatorJavaType shortType = new AviatorJavaType("shortType");
        AviatorJavaType intType = new AviatorJavaType("intType");
        AviatorJavaType longType = new AviatorJavaType("longType");
        AviatorJavaType floatType = new AviatorJavaType("floatType");
        AviatorJavaType doubleType = new AviatorJavaType("doubleType");

        Map<String, Object> env = new HashMap<String, Object>();
        env.put("byteType", (byte) 4);
        env.put("shortType", (short) 4);
        env.put("intType", 4);
        env.put("longType", (long) 4);
        env.put("floatType", 4.4f);
        env.put("doubleType", 4.4d);

        AviatorNumber n1 = AviatorNumber.valueOf(3);
        AviatorNumber n2 = AviatorNumber.valueOf(3.3f);

        switch (operatorType) {
        case Add:
            assertEquals(7, byteType.add(n1, env).getValue(null));
            assertEquals(7, shortType.add(n1, env).getValue(null));
            assertEquals(7, intType.add(n1, env).getValue(null));
            assertEquals(7, longType.add(n1, env).getValue(null));
            assertEquals(7.4, (Double) floatType.add(n1, env).getValue(null), 0.001);
            assertEquals(7.4, (Double) doubleType.add(n1, env).getValue(null), 0.001);
            assertEquals(8L, byteType.add(intType, env).getValue(null));
            assertEquals(8L, shortType.add(longType, env).getValue(null));
            assertEquals(8.4, (Double) floatType.add(byteType, env).getValue(null), 0.001);
            assertEquals(8.8, (Double) doubleType.add(floatType, env).getValue(null), 0.001);
            assertEquals(7.7, (Double) n2.add(doubleType, env).getValue(null), 0.001);
            break;
        case Sub:
            assertEquals(1, byteType.sub(n1, env).getValue(null));
            assertEquals(1, shortType.sub(n1, env).getValue(null));
            assertEquals(1, intType.sub(n1, env).getValue(null));
            assertEquals(1, longType.sub(n1, env).getValue(null));
            assertEquals(1.4, (Double) floatType.sub(n1, env).getValue(null), 0.001);
            assertEquals(1.4, (Double) doubleType.sub(n1, env).getValue(null), 0.001);
            assertEquals(0L, byteType.sub(intType, env).getValue(null));
            assertEquals(0L, shortType.sub(longType, env).getValue(null));
            assertEquals(0.4, (Double) floatType.sub(byteType, env).getValue(null), 0.001);
            assertEquals(0.0, (Double) doubleType.sub(floatType, env).getValue(null), 0.001);
            assertEquals(-1.1, (Double) n2.sub(doubleType, env).getValue(null), 0.001);
            break;

        case Mult:
            // 4 4.4 3 3.3
            assertEquals(12, byteType.mult(n1, env).getValue(null));
            assertEquals(12, shortType.mult(n1, env).getValue(null));
            assertEquals(12, intType.mult(n1, env).getValue(null));
            assertEquals(12, longType.mult(n1, env).getValue(null));
            assertEquals(13.2, (Double) floatType.mult(n1, env).getValue(null), 0.001);
            assertEquals(13.2, (Double) doubleType.mult(n1, env).getValue(null), 0.001);
            assertEquals(16L, byteType.mult(intType, env).getValue(null));
            assertEquals(16L, shortType.mult(longType, env).getValue(null));
            assertEquals(17.6, (Double) floatType.mult(byteType, env).getValue(null), 0.001);
            assertEquals(19.36, (Double) doubleType.mult(floatType, env).getValue(null), 0.001);
            assertEquals(14.52, (Double) n2.mult(doubleType, env).getValue(null), 0.001);
            break;
        case Div:
            assertEquals(1, byteType.div(n1, env).getValue(null));
            assertEquals(1, shortType.div(n1, env).getValue(null));
            assertEquals(1, intType.div(n1, env).getValue(null));
            assertEquals(1, longType.div(n1, env).getValue(null));
            assertEquals(1.466667, (Double) floatType.div(n1, env).getValue(null), 0.001);
            assertEquals(1.466667, (Double) doubleType.div(n1, env).getValue(null), 0.001);
            assertEquals(1L, byteType.div(intType, env).getValue(null));
            assertEquals(1L, shortType.div(longType, env).getValue(null));
            assertEquals(1.1, (Double) floatType.div(byteType, env).getValue(null), 0.001);
            assertEquals(1.0, (Double) doubleType.div(floatType, env).getValue(null), 0.001);
            assertEquals(0.75, (Double) n2.div(doubleType, env).getValue(null), 0.001);
            break;
        case Mod:
            assertEquals(1, byteType.mod(n1, env).getValue(null));
            assertEquals(1, shortType.mod(n1, env).getValue(null));
            assertEquals(1, intType.mod(n1, env).getValue(null));
            assertEquals(1, longType.mod(n1, env).getValue(null));
            assertEquals(1.4, (Double) floatType.mod(n1, env).getValue(null), 0.001);
            assertEquals(1.4, (Double) doubleType.mod(n1, env).getValue(null), 0.001);
            assertEquals(0L, byteType.mod(intType, env).getValue(null));
            assertEquals(0L, shortType.mod(longType, env).getValue(null));
            assertEquals(0.4, (Double) floatType.mod(byteType, env).getValue(null), 0.001);
            System.out.println(4.4 % 4.40000001);
            // assertEquals(0.0, (Double)
            // doubleType.mod(floatType).getValue(null),
            // 0.001);
            assertEquals(3.3, (Double) n2.mod(doubleType, env).getValue(env), 0.001);
            break;

        }
    }


    @Test
    public void testAddJavaString() {
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("a", 300);
        env.put("b", 1.001);
        env.put("c", 3);
        env.put("s", "+3=303");
        env.put("h1", "hello ");
        env.put("h2", 'h');
        env.put("w1", "world");
        env.put("w2", "ello world");

        AviatorJavaType a = new AviatorJavaType("a");

        assertEquals("300+3=303", a.add(new AviatorJavaType("s"), env).getValue(null));
        assertEquals(301.001, a.add(new AviatorJavaType("b"), env).getValue(null));

        AviatorJavaType s = new AviatorJavaType("h1");
        assertEquals("hello world", s.add(new AviatorJavaType("w1"), env).getValue(null));

        AviatorJavaType ch = new AviatorJavaType("h2");
        assertEquals("hello world", ch.add(new AviatorJavaType("w2"), env).getValue(null));

        assertEquals("hello 3", s.add(new AviatorJavaType("c"), env).getValue(env));
    }


    private Map<String, Object> createEnvWith(String name, Object obj) {
        Map<String, Object> env = new HashMap<String, Object>();
        if (name != null) {
            env.put(name, obj);
        }
        env.put("true", Boolean.TRUE);
        env.put("false", Boolean.FALSE);
        return env;
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void addJavaBooleanWithNumber() {
        AviatorJavaType a = new AviatorJavaType("true");
        a.add(AviatorNumber.valueOf(3), createEnvWith(null, null));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void addJavaNumberWithJavaBoolean() {
        AviatorJavaType a = new AviatorJavaType("a");
        a.add(new AviatorJavaType("true"), createEnvWith("a", 4));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void subJavaBooleanWithNumber() {
        AviatorJavaType a = new AviatorJavaType("true");
        a.sub(AviatorNumber.valueOf(3), createEnvWith(null, null));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void subJavaNumberWithJavaBoolean() {
        AviatorJavaType a = new AviatorJavaType("a");
        a.sub(new AviatorJavaType("true"), createEnvWith(null, null));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void multJavaBooleanWithNumber() {
        AviatorJavaType a = new AviatorJavaType("true");
        a.mult(AviatorNumber.valueOf(3), createEnvWith(null, null));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void multJavaNumberWithJavaBoolean() {
        AviatorJavaType a = new AviatorJavaType("a");
        a.mult(new AviatorJavaType("true"), createEnvWith("a", 4));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void divJavaBooleanWithNumber() {
        AviatorJavaType a = new AviatorJavaType("true");
        a.div(AviatorNumber.valueOf(3), createEnvWith(null, null));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void divJavaNumberWithJavaBoolean() {
        AviatorJavaType a = new AviatorJavaType("a");
        a.div(new AviatorJavaType("true"), createEnvWith("a", 4));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void modJavaBooleanWithNumber() {
        AviatorJavaType a = new AviatorJavaType("true");
        a.mod(AviatorNumber.valueOf(3), createEnvWith(null, null));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void modJavaNumberWithJavaBoolean() {
        AviatorJavaType a = new AviatorJavaType("a");
        a.mod(new AviatorJavaType("true"), createEnvWith("a", 4));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void modJavaBooleanWithJavaNumber() {
        AviatorJavaType a = new AviatorJavaType("true");
        a.mod(new AviatorJavaType("b"), createEnvWith("b", 3));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void addJavaBooleanWithJavaNumber() {
        AviatorJavaType a = new AviatorJavaType("true");
        a.add(new AviatorJavaType("b"), createEnvWith("b", 3));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void subJavaBooleanWithJavaNumber() {
        AviatorJavaType a = new AviatorJavaType("true");
        a.sub(new AviatorJavaType("b"), createEnvWith("b", 3));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void multJavaBooleanWithJavaNumber() {
        AviatorJavaType a = new AviatorJavaType("true");
        a.mult(new AviatorJavaType("b"), createEnvWith("b", 3));
    }


    @Test(expected = ExpressionRuntimeException.class)
    public void divJavaBooleanWithJavaNumber() {
        AviatorJavaType a = new AviatorJavaType("true");
        a.div(new AviatorJavaType("b"), createEnvWith("b", 3));
    }


    @Test
    public void compareWithAviatorType() {

        AviatorJavaType intType = new AviatorJavaType("intType");
        AviatorJavaType doubleType=new AviatorJavaType("doubleType");
        AviatorJavaType boolType = new AviatorJavaType("boolType");
        AviatorJavaType stringType = new AviatorJavaType("stringType");
        AviatorJavaType charType = new AviatorJavaType("charType");
        AviatorJavaType dateType = new AviatorJavaType("dateType");

        Map<String, Object> env = new HashMap<String, Object>();
        env.put("intType", 3);
        env.put("boolType", Boolean.FALSE);
        env.put("stringType", "hello");
        env.put("charType", 'c');
        env.put("dateType", new Date());
        env.put("doubleType", 3.4);
        

        AviatorNumber number = AviatorNumber.valueOf(3.4);
        AviatorBoolean bool = AviatorBoolean.TRUE;
        AviatorPattern pattern = new AviatorPattern("\\d+");
        AviatorString string = new AviatorString("hello");

        assertTrue(intType.compare(number,env) < 0);
        assertEquals(0, doubleType.compare(number,env));
        try {
            intType.compare(bool,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }
        try {
            intType.compare(pattern,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }
        try {
            intType.compare(string,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }

        try {
            stringType.compare(number,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }
        try {
            stringType.compare(bool,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }
        try {
            stringType.compare(pattern,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }
        assertEquals(0, stringType.compare(string,env));

        try {
            charType.compare(number,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }

        try {
            charType.compare(bool,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }

        try {
            charType.compare(pattern,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }
        assertTrue(charType.compare(string,env) < 0);

        try {
            dateType.compare(number,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }
        try {
            dateType.compare(bool,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }
        try {
            dateType.compare(string,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }
        try {
            dateType.compare(pattern,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }

        try {
            boolType.compare(number,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }
        try {
            boolType.compare(string,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }
        try {
            boolType.compare(pattern,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {

        }

        assertTrue(boolType.compare(bool,env) < 0);
    }


    @Test
    public void compareWithJavaType() {

        AviatorJavaType intType = new AviatorJavaType("intType");
        AviatorJavaType boolType = new AviatorJavaType("boolType");
        AviatorJavaType stringType = new AviatorJavaType("stringType");
        AviatorJavaType charType = new AviatorJavaType("charType");
        AviatorJavaType dateType = new AviatorJavaType("dateType");

        Map<String, Object> env = new HashMap<String, Object>();
        env.put("intType", 3);
        env.put("boolType", Boolean.FALSE);
        env.put("stringType", "hello");
        env.put("charType", 'c');
        env.put("dateType", new Date());

        assertEquals(0, intType.compare(intType,env));
        try {
            intType.compare(boolType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            intType.compare(stringType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            intType.compare(charType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            intType.compare(dateType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }

        try {
            boolType.compare(intType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        assertEquals(0, boolType.compare(boolType,env));
        try {
            boolType.compare(stringType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            boolType.compare(charType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            boolType.compare(dateType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }

        try {
            stringType.compare(intType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            stringType.compare(boolType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        assertEquals(0, stringType.compare(stringType,env));
        try {
            stringType.compare(dateType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        assertTrue(stringType.compare(charType,env) > 0);

        // char

        try {
            charType.compare(intType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }

        try {
            charType.compare(boolType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        assertTrue(charType.compare(stringType,env) < 0);
        assertEquals(0, charType.compare(charType,env));
        try {
            charType.compare(dateType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }

        try {
            dateType.compare(intType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            dateType.compare(boolType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            dateType.compare(stringType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            dateType.compare(charType,env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        assertEquals(0, dateType.compare(dateType,env));

    }


    @Test
    public void testNot() {
        AviatorJavaType intType = new AviatorJavaType("intType");
        AviatorJavaType boolType = new AviatorJavaType("boolType");
        AviatorJavaType stringType = new AviatorJavaType("stringType");
        AviatorJavaType charType = new AviatorJavaType("charType");
        AviatorJavaType dateType = new AviatorJavaType("dateType");

        Map<String, Object> env = new HashMap<String, Object>();
        env.put("intType", 3);
        env.put("boolType", Boolean.FALSE);
        env.put("stringType", "hello");
        env.put("charType", 'c');
        env.put("dateType", new Date());

        assertEquals(Boolean.TRUE, boolType.not(env).getValue(env));
        try {
            intType.not(env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            stringType.not(env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            charType.not(env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            dateType.not(env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
    }


    @Test
    public void testNeg() {
        AviatorJavaType intType = new AviatorJavaType("intType");
        AviatorJavaType boolType = new AviatorJavaType("boolType");
        AviatorJavaType stringType = new AviatorJavaType("stringType");
        AviatorJavaType charType = new AviatorJavaType("charType");
        AviatorJavaType dateType = new AviatorJavaType("dateType");

        Map<String, Object> env = new HashMap<String, Object>();
        env.put("intType", 3);
        env.put("boolType", Boolean.FALSE);
        env.put("stringType", "hello");
        env.put("charType", 'c');
        env.put("dateType", new Date());

        assertEquals(-3L, intType.neg(env).getValue(env));
        try {
            boolType.neg(env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            stringType.neg(env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            charType.neg(env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
        try {
            dateType.neg(env);
            Assert.fail();
        }
        catch (ExpressionRuntimeException e) {
        }
    }
}
