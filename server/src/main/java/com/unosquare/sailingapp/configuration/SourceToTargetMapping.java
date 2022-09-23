package com.unosquare.sailingapp.configuration;

import org.modelmapper.PropertyMap;

public interface SourceToTargetMapping<T, U> {
    PropertyMap<T, U> mapFromSourceToTarget();
}
