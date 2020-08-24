package main.java.Exception;

public class DukeDateTimeParserException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " You have to enter the date time format correctly";
    }
}
