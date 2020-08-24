package main.java;

public class BobNumberFormatException extends BobException {
    @Override
    public String getMessage() {
        return "Please provide the index of a task on the list to mark it as done or to delete it.\n"
                + "Here's the format: delete/done [index]";
    }
}
