package main.java;

public class BobIncompleteEventDescriptionException extends BobException {
    public String getMessage(){
        return "The description for this event is incomplete. Please remember to include a brief description alongside the period of this event.\n"
                + "Here's the format: event [brief description] /at [period]";
    }
}
