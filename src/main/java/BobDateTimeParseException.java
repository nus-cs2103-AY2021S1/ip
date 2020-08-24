package main.java;

public class BobDateTimeParseException extends BobException {
    @Override
    public String getMessage() {
        return "Please input dates and times in the correct format. The format is: "
                + "yyyy-MM-dd HHMM"
                + "Note: Events require a start date and time and an end date and time with the following format:"
                + "yyyy-MM-dd HHMM to yyyy-MM-dd HHMM";
    }
}
