package duke;

public class DukeExceptions {

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

    public static class NoTaskToDeleteException extends Exception {
        public NoTaskToDeleteException() {
            super();
        }

        public String toString() {
            return "Master the list is empty";
        }
    }

    public static void noIndexKeyedError() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "Master please enter a task number so that I know which to handle.\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    public static void printIndexSizeMismatchError() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "Master that is not a valid task number.\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    public static void printNoUndoneTaskError() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "Master there is no task that is undone.\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    public static void printIncompleteCommandError(String command) {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "Master there is no description for " + command + " !\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    public static void printUnrecognizableCommandError() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "I am sorry master but I do not understand that command.\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    public static void printNoTaskToDeleteError() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "I am sorry master but the task list is empty.\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    public static void printNoDateInput(String command) {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "Master Please enter the date for your " + command + " task.\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    public static void printIncorrectDateFormatError() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "Master the input date should be dd-mm-yyyy hhmm !\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

}
