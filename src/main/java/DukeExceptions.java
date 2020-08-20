public class DukeExceptions {

    public static class UnrecognizableCommandException extends Exception {
        public UnrecognizableCommandException() {
            super("");
        }

        public String toString() {
            return "I am terribly sorry master but I do not recognise that command";
        }
    }

    public static class IncompleteCommandException extends Exception {
        public IncompleteCommandException(String commandName) {
            super(commandName);
        }

        public String toString() {
            return "Master the description of " + super.getMessage() + " cannot be empty!";
        }
    }

    public static class NoUndoneTaskException extends Exception {
        public NoUndoneTaskException() {
            super();
        }

        public String toString() {
            return "Master the list is empty";
        }
    }
}
