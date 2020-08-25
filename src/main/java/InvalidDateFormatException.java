package main.java;

public class InvalidDateFormatException extends DukeException {
    private static String messageNoTime = "Entered date is in the wrong format. Please "
            + "specify in this format YYYY-MM-DD";
    private static String messageWithTime = "Entered date is in the wrong format. Please "
            + "specify in this format YYYY-MM-DD HH:MM";
    private boolean hasTime;

    public InvalidDateFormatException(boolean hasTime) {
        this.hasTime = hasTime;
    }

    @Override
    public String toString() {
        return !hasTime ? messageNoTime : messageWithTime;
    }
}
