package com.bootStarterCustom;

public class Greeter {

    public Greeter(GreetingConfig greetingConfig) {
        this.greetingConfig = greetingConfig;
    }
    private final GreetingConfig greetingConfig;

    public String greet() {
        return greetingConfig.getGreeting();
    }
}
