package duke;

/**
 * <code>Ui</code> deals with all the interactions with the user. A <code>Ui</code> object contains all the methods
 * required to print out messages to users, specifically, it specifies a format of print in the <code>print</code>
 * method that takes in a <code>String</code> object and prints the string in the desired format to users.
 */
public class Ui {
    private static final String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";

    public void print (String message) {
        System.out.println(lines + message + lines);
    }

    public String printFormat (String message) {
        return lines + message + lines;
    }

    /**
     * Returns a string representation of a message to inform user that a new file has been successfully created.
     */
    public String fileCreationSuccess() {
        return " Woof! I am Yuki your assigned Task Manager!\n"
                + " I have just created a new file to store all your tasks!\n"
                + " So... What is my first assignment? *Woof woof*\n";
    }

    /**
     * Returns a string representation of a message to welcome users back.
     */
    public String welcome() {
        return " Hello! I'm Yuki *Woof*\n What can I do for you? *Woof woof*\n";
    }

    /**
     * Returns a string representation of the total number of <code>Task</code> in the list.
     *
     * @param i the number of <code>Task</code> in a list
     * @return a string representation of the total number of <code>Task</code> in the list.
     */
    public String displayTotal(int i) {
        return " Now you have " + i + " tasks in the list. Keep going!!\n";
    }

}
