public class DukeException extends Exception {
    public enum Errors {
        UNKNOWN_COMMAND("sorry sir i dont understand your command sir\n"
                + "please enter again sir thank you sir"),
        TODO_EMPTY_DESCRIPTION("v sorry sir you need to give todo description sir"),
        EVENT_BAD_FORMAT("apology mr sir, i dont not understand your input\n"
                + "the format for new event is \"event DESCRIPTION /at DATE\" sir"),
        DEADLINE_BAD_FORMAT("very sorry sir, i cannot understand your input\n"
                + "the format for new deadline is \"deadline DESCRIPTION /by DATE\" sir"),
        DONE_OUT_OF_RANGE("sir that number is too many for the list sir please\n"
                + "choose a lower number for the list please sir"),
        DELETE_OUT_OF_RANGE("sir that number is too many for the list sir please\n"
                + "choose a lower number for the list please sir"),
        DATE_PARSE_ERROR("sir the date of format is wrong sir please try\n"
                + "\"yyyy-mm-dd\", eg \"2020-08-24\" sir");

        private final String message;
        Errors(String message) {
            this.message = message;
        }
        @Override
        public String toString() {
            return message;
        }
    }
    public DukeException(Errors error) {
        super(error.toString());
    }
}
