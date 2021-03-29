package com.jarvis.adminservice.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BeanUtilities {

    public static String[] getIgnorePropertyNames(Object source, String... ignoreProperties) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> ignoreNames = new HashSet<>();
        if (ignoreProperties != null) {
            ignoreNames.addAll(Arrays.asList(ignoreProperties));
        }
        for(java.beans.PropertyDescriptor pd : pds) {
            //check if value of this property is null then add it to the collection
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                ignoreNames.add(pd.getName());
            }
        }
        String[] result = new String[ignoreNames.size()];
        return ignoreNames.toArray(result);
    }
}
