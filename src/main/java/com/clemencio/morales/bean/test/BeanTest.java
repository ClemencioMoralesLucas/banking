package com.clemencio.morales.bean.test;

import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;

public abstract class BeanTest<T> {

    @Test
    public void testBean() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(getBean());
    }

    protected abstract T getBean();
}
