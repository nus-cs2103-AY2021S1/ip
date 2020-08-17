public class DukeException extends Exception {
    public enum Errors {
        UNKNOWN_COMMAND("sorry sir i dont recognize that command sir"),
        EMPTY_TODO_DESCRIPTION("v sorry sir you need to give todo description");
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
