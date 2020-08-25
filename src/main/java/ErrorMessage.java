public enum ErrorMessage {
    UNKNOWN_FUNCTION("The function does not exist."),
    TASK_EXCEED_RANGE("The task number provided is out of range."),
    EMPTY_DESCRIPTION("The description of the function cannot be empty."),
    EXCESS_DESCRIPTION("The function should be followed by a single number only.");

    private final String error;

    ErrorMessage(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return "Error: " + this.error;
    }

}