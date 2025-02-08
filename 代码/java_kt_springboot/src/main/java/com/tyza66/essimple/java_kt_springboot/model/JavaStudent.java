package com.tyza66.essimple.java_kt_springboot.model;

public class JavaStudent {
    String name;  // 或者直接把name public
    public JavaStudent(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
