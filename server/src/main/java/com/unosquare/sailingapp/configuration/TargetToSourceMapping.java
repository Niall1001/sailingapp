package com.unosquare.sailingapp.configuration;


import org.modelmapper.PropertyMap;

public interface TargetToSourceMapping<T, U> {
    PropertyMap<T, U> mapFromTargetToSource();
}
