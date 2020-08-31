package duke;

public class DukeExceptions {
    /**
     * Exception for scenario where an incomplete command is given
     */
    public static class IncompleteCommandException extends Exception {
        public IncompleteCommandException(String commandName) {
            super(commandName);
        }

        public String toString() {
            return "Master the description of " + super.getMessage() + " cannot be empty!";
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
            return "Master the list is empty";
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
            return "Master the list is empty";
        }
    }

    /**
     * error message for when the user does not input a task number for task manipulation
     */
    public static void noIndexKeyedError() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "Master please enter a task number so that I know which to handle.\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    /**
     * error message when the user inputs an invalid task number for task manipulation
     */
    public static void printIndexSizeMismatchError() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "Master that is not a valid task number.\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    /**
     * error message for when the user tries to mark a task as done when the task list is empty
     */
    public static void printNoUndoneTaskError() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "Master there is no task that is undone.\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    /**
     * error message for when the user fails to input additional parameters for the command
     */
    public static void printIncompleteCommandError() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "Master there is no description entered !\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    /**
     * error message for when a user inputs an unrecognisable command
     */
    public static void printUnrecognizableCommandError() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "I am sorry master but I do not understand that command.\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    /**
     * error message for when the user attempts to delete a task from an empty task list
     */
    public static void printNoTaskToDeleteError() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "I am sorry master but the task list is empty.\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    /**
     * error message for user not inputting a date for a task with a deadline
     */
    public static void printNoDateInput() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "Master Please enter the date for your task.\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

    /**
     * error message for user inputting wrong datetime format
     */
    public static void printIncorrectDateFormatError() {
        System.out.println(
            "----------------------------------------------------------------------------\n"
            + "Master the input date should be dd-mm-yyyy hhmm !\n"
            + "----------------------------------------------------------------------------\n"
        );
    }

}
