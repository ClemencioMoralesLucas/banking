package com.clemencio.morales.bean.test;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class BeanTester {

    public void testBean(final Object bean) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        final Map beanMap = PropertyUtils.describe(bean);
        final Map<String, Object> beanProperties = new HashMap<>();
        final Set<Map.Entry> entries = beanMap.entrySet();
        for (Map.Entry entry : entries) {
            final String property = (String) entry.getKey();
            final Object value = PropertyUtils.getSimpleProperty(bean, property);
            beanProperties.put(property, value);
        }
        BeanUtils.populate(bean, beanProperties);
        assertEquals(beanMap, PropertyUtils.describe(bean));
    }
}
