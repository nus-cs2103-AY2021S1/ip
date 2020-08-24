package main.java.Exception;

public class AnonymousException extends DukeException {
    String input;
    public AnonymousException(String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return super.toString() + " There are no commands that start with " +  this.input + "\n"
                + "      "+"Please type --help to see all the commands";
    }
}
