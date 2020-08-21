package main.java;

public enum Command {
    LIST("list"), ADD_TODO("todo"), ADD_DEADLINE("deadline"), ADD_EVENT("event"), DONE("done"), DELETE("delete"), LIST_DATE("list date");

    private String type;

    private Command(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}