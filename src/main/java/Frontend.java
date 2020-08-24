// Frontend.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

public class Frontend {

    private final String name;

    public Frontend(String name) {
        this.name = name;
    }

    public void greet() {
        println("Hello, I'm %s", this.name);
        println("What can I do for you?");
    }

    public void println(String fmt, Object... args) {
        System.out.printf(fmt, args);
        System.out.println();
    }

    public void beginLog() {

        println("");
    }

    public void endLog() {

        println("--------------------------------------");
    }
}
