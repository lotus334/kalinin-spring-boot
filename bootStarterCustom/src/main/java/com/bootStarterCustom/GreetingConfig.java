package com.bootStarterCustom;

import java.util.HashMap;
import java.util.Map;

public class GreetingConfig {
    private final Map<String, String> map = new HashMap<>();
    public void put(String systemName, String value) {
        map.put(systemName, value);
    }

    public String getGreeting() {
        return map.get("USER_NAME") + " " + map.get("USER_MESSAGE");
    }
}