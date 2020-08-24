package main.java;

public class BobIncompleteDeadlineDescriptionException extends BobException {
    @Override
    public String getMessage() {
        return "The description for this deadline is incomplete. Please remember to include a brief description alongside a due date.\n" +
                "Here's the format: deadline [brief description] /by [due date]";
    }
}
