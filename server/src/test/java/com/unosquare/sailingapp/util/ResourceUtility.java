package com.unosquare.sailingapp.util;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;

public class ResourceUtility {



    public static String generateStringFromResource(final String path) {
        String resourceString = "";

        try {
            resourceString = Resources.toString(Resources.getResource(path), Charsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("Can not retrieve resource entity");
        }
        return resourceString;
    }
}
