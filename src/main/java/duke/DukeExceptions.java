package duke;

public class DukeExceptions {
    /**
     * Exception for scenario where an incomplete command is given.
     */
    public static class InsufficientParametersException extends Exception {
        public InsufficientParametersException() {
            super();
        }
    }

    /**
     * Exception for scenario where a task that is supposed to be mark done is already marked as done.
     */
    public static class TaskIsDoneException extends Exception {
        public TaskIsDoneException() {
            super();
        }
    }

    /**
     * Exception for scenario where alias to add already exist.
     */
    public static class AliasAlreadyExistException extends Exception {
        public AliasAlreadyExistException(String message) {
            super(message);
        }
    }

    /**
     * Exception for scenario where alias to delete does not exist.
     */
    public static class AliasDoesNotExistException extends  Exception {
        public AliasDoesNotExistException(String message) {
            super(message);
        }
    }


}
