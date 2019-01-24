package com.example.mytonghang.view;

public interface Myview<T> {
    void showList(T t);

    void showError(String error);
}
