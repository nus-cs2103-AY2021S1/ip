import java.util.ArrayList;

public class Printer {

    public Printer() {
    }


    String GREETING = "     Hello! I'm Duke\n" +
            "     What can I do for you?";
    String GOODBYE = "     Bye. Hope to see you again soon!";
    String LINE = "    ____________________________________________________________";
    String GOT_IT = "     Got it. I've added this task: ";
    String WHITE_SPACE_SEVEN = "       ";

    protected void greeting() {
        String s = "";
        s = LINE + "\n" + GREETING + "\n" + LINE;
        System.out.println(s);
    }

    protected void farewell() {
        String s = "";
        s = LINE + "\n" + GOODBYE + "\n" + LINE;
        System.out.println(s);
    }

    protected void printListing(Listing l, int size) {
        String s = "";
        s = LINE + "\n" + GOT_IT + "\n" + WHITE_SPACE_SEVEN + l.toString() +
                "\n" + "     Now you have " + size + " tasks in the list." + "\n" + LINE;
        System.out.println(s);
    }

    protected void undefinedExceptionMessage() {
        System.out.println(LINE + "\n" + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                + "\n" + LINE);
    }

    protected void noDescriptionMessage(String s) {
        System.out.println(LINE + "\n" + "     ☹ OOPS!!! The description of a " + s + " cannot be empty."
                + "\n" + LINE);
    }

    protected void deleteMessage(int size, String listing) {
        System.out.println(LINE + "\n" + "     Noted. I've removed this task: \n" + WHITE_SPACE_SEVEN +
                listing + "\n" + "     Now you have " + size + " tasks in the list.\n" + LINE);
    }

    protected void doneMessage(String s) {
        System.out.println(LINE + "\n" + "     Nice! I've marked this task as done: \n" + "     "
                + s + "\n" + LINE);
    }

}
