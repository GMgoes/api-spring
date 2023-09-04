package com.example.demo.models;

import java.util.HashMap;
import java.util.Map;

public enum Console {
    PS3("PS3"), PS4("PS4"), PS5("PS5"), XBOX("XBOX"), XBOXONE("XBOXONE"), PC("PC");

    private String value;

    private static final Map<String, Console> funcGetValue = new HashMap<>();

    static {
        for(Console console: Console.values()){
            funcGetValue.put(console.getValue(), console);
        }
    }

    Console(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Console functionGetValue(String valor){
        return funcGetValue.get(valor);
    }
}
