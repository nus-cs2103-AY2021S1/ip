package duke;

public class DukeExceptions {
    /**
     * Exception for scenario where an incomplete command is given
     */
    public static class IncompleteCommandException extends Exception {
        public IncompleteCommandException(String message) {
            super(message);
        }
    }

    /**
     * Exception for scenario where user attempts to do tasks manipulation on an empty task list
     */
    public static class NoUndoneTaskException extends Exception {
        public NoUndoneTaskException() {
            super();
        }

        public String toString() {
            return "Master the list is empty\n";
        }
    }

    /**
     * Exception for scenario where user attempts to delete a task on an empty task list
     */
    public static class NoTaskToDeleteException extends Exception {
        public NoTaskToDeleteException() {
            super();
        }

        public String toString() {
            return "Master the list is empty\n";
        }
    }

    /**
     * error message for when the user does not input a task number for task manipulation
     */
    public static String noIndexKeyedError() {
        return "Master please enter a task number so that I know which to handle.\n";
    }

    /**
     * error message when the user inputs an invalid task number for task manipulation
     */
    public static String printIndexSizeMismatchError() {
        return "Master that is not a valid task number.\n";
    }

    /**
     * error message for when the user tries to mark a task as done when the task list is empty
     */
    public static String printNoUndoneTaskError() {
        return "Master there is no task that is undone.\n";
    }

    /**
     * error message for when the user fails to input additional parameters for the command
     */
    public static String printIncompleteCommandError() {
        return "Master there is no description entered !\n";
    }

    /**
     * error message for when a user inputs an unrecognisable command
     */
    public static String printUnrecognizableCommandError() {
        return "I am sorry master but I do not understand that command.\n";
    }

    /**
     * error message for when the user attempts to delete a task from an empty task list
     */
    public static String printNoTaskToDeleteError() {
        return "I am sorry master but the task list is empty.\n";
    }

    /**
     * error message for user not inputting a date for a task with a deadline
     */
    public static String printNoDateInput() {
        return "Master Please enter the date for your task.\n";
    }

    /**
     * error message for user inputting wrong datetime format
     */
    public static String printIncorrectDateFormatError() {
        return "Master the input date should be dd-mm-yyyy hhmm !\n";
    }

}
