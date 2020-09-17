package duke;

import java.security.spec.ECFieldF2m;

public class DukeExceptions {
    /**
     * Exception for scenario where an incomplete command is given
     */
    public static class InsufficientParametersException extends Exception {
        public InsufficientParametersException(String message) {
            super(message);
        }
    }

    public static class TaskIsDoneException extends Exception {
        public TaskIsDoneException(String message) {
            super(message);
        }
    }

    public static class AliasAlreadyExistException extends Exception {
        public AliasAlreadyExistException(String message) {
            super(message);
        }
    }

    public static class AliasDoesNotExistException extends  Exception {
        public AliasDoesNotExistException(String message) {
            super(message);
        }
    }


}
