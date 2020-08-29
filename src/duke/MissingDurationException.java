package duke;

public class MissingDurationException extends Exception{

    public MissingDurationException(String s) {
        super("☹ OOPS!!! The time of a " + s + " must be specified.");
    }

}
