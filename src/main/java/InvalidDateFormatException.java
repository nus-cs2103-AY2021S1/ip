class InvalidDateFormatException extends DukeException {
    private static String INCORRECT_DATE_MSG = "Sorry, I cannot understand the date, "
            + "try to format it in the format: yyyy-MM-dd";
    
    InvalidDateFormatException() {
        super(INCORRECT_DATE_MSG);
    }
}
