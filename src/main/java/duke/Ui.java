package duke;

/**
 * <code>Ui</code> deals with all the interactions with the user. A <code>Ui</code> object contains all the methods
 * required to print out messages to users, specifically, it specifies a format of print in the <code>print</code>
 * method that takes in a <code>String</code> object and prints the string in the desired format to users.
 */
public class Ui {
    private static final String LINES = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";

    public void print (String message) {
        System.out.println(LINES + message + LINES);
    }

    public String printFormat (String message) {
        return LINES + message + LINES;
    }

    /**
     * Returns a string representation of a message to inform user that a new file has been successfully created.
     */
    public String fileCreationSuccess() {
        return " Woof! I am Yuki your assigned Task Manager!\n"
                + " I have just created a new file to store all your tasks!\n"
                + " If you ever get stuck, enter help and I will be here for you!\n"
                + " So... What is my first assignment?\n *Woof woof*\n";
    }

    /**
     * Returns a string representation of a message to welcome users back.
     */
    public String welcome() {
        return " Hello! I'm Yuki *Woof*\n What can I do for you?\n"
                + " Remember if you ever get stuck,\n enter help and I will be here for you!\n *Woof woof*\n";
    }

    /**
     * Returns a string representation of the total number of <code>Task</code> in the list.
     *
     * @param total the number of <code>Task</code> in a list
     * @return a string representation of the total number of <code>Task</code> in the list.
     */
    public String displayTotal(int total) {
        return " Now you have " + total + " tasks in the list. Keep going!!\n";
    }

}
