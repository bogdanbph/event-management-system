package org.example.eventsmanagementsystem.converter;

public interface ObjectConverter<T, U> {

    T convertSecondToFirst(U u);
    U convertFirstToSecond(T t);
}
