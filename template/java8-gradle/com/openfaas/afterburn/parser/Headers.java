package com.openfaas.afterburn.parser;

import java.util.HashMap;
import java.util.Map;

public class Headers {
    private String method;
    private Map<String, String> value = new HashMap<>();

    public Headers(String rawString) {
        String[] parts = rawString.split("\n");
        method = parts[0].split(" ")[0];
        for (int i = 1; i < parts.length; i++) {
            String[] tokens = parts[i].split(":");
            value.put(tokens[0].trim(), tokens[1].trim());
        }
    }

    public String getMethod() {
        return method;
    }

    public int contentLength() {
        if (!value.containsKey("Content-Length"))
            throw new RuntimeException("Content Length was not found in the request");
        return Integer.valueOf(value.get("Content-Length"));
    }
}
