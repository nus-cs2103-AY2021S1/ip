package main.java;

public class DoneOutOfListException extends Exception {

    @Override
    public String getMessage() {
        return "Oh no! Task number cannot be zero or negative, please refer to your task list to find" +
            " the appropriate task number :)";
    }
}
