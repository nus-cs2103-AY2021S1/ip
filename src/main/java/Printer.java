/**
 * The Printer object is in charge of printing out the various statements after TaskList.java and
 * Storage.java has completed their tasks.
 */

public class Printer {

    private static final String GREETING = "     Hello! I'm Duke\n"
            + "     What can I do for you?";
    private static final String GOODBYE = "     Bye. Hope to see you again soon!";
    private static final String LINE = "    ____________________________________________________________";
    private static final String GOT_IT = "     Got it. I've added this task: ";
    private static final String WHITE_SPACE_SEVEN = "       ";

    public Printer() {
    }

    /**
     * Prints the greeting message.
     */
    protected void greeting() {
        String s = "";
        s = LINE + "\n" + GREETING + "\n" + LINE;
        System.out.println(s);
    }

    /**
     * Prints the farewell message.
     */
    protected String farewell() {
        String s = "";
        s = LINE + "\n" + GOODBYE + "\n" + LINE;
        System.out.println(s);
        return s;
    }

    /**
     * Prints the listing size message.
     *
     * @param l    a listing
     * @param size the current size of the ArrayList in TaskList.java
     */
    protected String printListing(Listing l, int size) {
        String output = "";
        output = LINE + "\n" + GOT_IT + "\n" + WHITE_SPACE_SEVEN + l.toString() +
                "\n" + "     Now you have " + size + " tasks in the list." + "\n" + LINE;
        return output;
    }

    /**
     * Prints the undefinedExceptionMessage message.
     */
    protected String undefinedExceptionMessage() {
        String output = LINE + "\n" + "     ): OOPS!!! I'm sorry, but I don't know what that means :-("
                + "\n" + LINE;
        return output;
    }

    /**
     * Prints the noExceptionMessage message.
     *
     * @param s the type of event
     */
    protected String noDescriptionMessage(String s) {
        String output = (LINE + "\n" + "     ): OOPS!!! The description of a " + s + " cannot be empty."
                + "\n" + LINE);
        return output;
    }

    /**
     * Prints the undefinedExceptionMessage message.
     */
    protected String dateTimeParseExceptionMessage() {
        String output = (LINE + "\n"
                + "     ): OOPS!!! I'm sorry, but that date is invalid. Try using a new date in the format YYYY-MM-DD :-("
                + "\n" + LINE);
        return output;
    }

    /**
     * Prints the delete message
     *
     * @param size    the current size of the ArrayList in TaskList.java
     * @param listing the detail of the listing
     */
    protected String deleteMessage(int size, String listing) {
        String output = "";
        output = (LINE + "\n" + "     Noted. I've removed this task: \n" + WHITE_SPACE_SEVEN
                + listing + "\n" + "     Now you have " + size + " tasks in the list.\n" + LINE);
        return output;
    }

    /**
     * Prints the delete message
     *
     * @param size    the current size of the ArrayList in TaskList.java
     * @param listing the detail of the listing
     */
    protected String tagMessage(String tagDetails, String listing) {
        String output = "";
        output = (LINE + "\n" + "     Noted. I've tagged this task: \n" + WHITE_SPACE_SEVEN
                + listing + "\n" + "     with " + tagDetails + "!\n" + LINE);
        return output;
    }

    /**
     * Prints the assertion error message
     */
    protected String assertionErrorMessage() {
        String output = "";
        output = (LINE + "\n" + "     Assertion error detected \n" + WHITE_SPACE_SEVEN
                + "\n" + LINE);
        return output;
    }

    /**
     * Prints the assertion error message
     */
    protected String invalidDeleteNumberExceptionMessage() {//
        String output = "";
        output = (LINE + "\n" + "     Sorry, that number/character isn't valid! ): \n"
                + "try writing tag (number) (message) instead! \n"
                + WHITE_SPACE_SEVEN + "\n" + LINE);
        return output;
    }

    /**
     * Prints the assertion error message
     */
    protected String invalidTagExceptionMessage() {//
        String output = "";
        output = (LINE + "\n" + "     Sorry, that tag command isn't valid! ): \n"
                + WHITE_SPACE_SEVEN + "\n" + LINE);
        return output;
    }

    /**
     * Prints the done message
     *
     * @param s the detail of the listing
     */
    protected String doneMessage(String s) {
        String output = (LINE + "\n" + "     Nice! I've marked this task as done: \n" + "     "
                + s + "\n" + LINE);
        return output;
    }

}
