package duke.main;

/**
 * Ui (User Interface) is used to interact with the user. It provides
 * function that will form the interface that the user will see.
 */
public class Ui {

    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        System.out.println("_".repeat(55));
    }

    /**
     * Displays the greeting message to the user.
     */
    public void showGreetingMessage() {
        System.out.println(" Hey there! I am Popi" + "\n"
                + " How can I help you?");
    }
}
