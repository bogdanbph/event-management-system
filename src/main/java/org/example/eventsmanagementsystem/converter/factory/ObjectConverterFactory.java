package org.example.eventsmanagementsystem.converter.factory;

import org.example.eventsmanagementsystem.converter.ObjectConverter;

public interface ObjectConverterFactory<T, U> {

    ObjectConverter<T, U> createConverter();
}
