import java.util.Scanner;

/**
 * Keeps track of user input. A <code>Ui</code> object contains a <code>Scanner</code> object.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the user input and return it.
     *
     * @return user input.
     */
    public String receiveInput() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println("Duke: Hello! How may I help you?");
    }

    /**
     * Says goodbye to the user.
     */
    public void goodbye() {
        System.out.println("Duke: Bye. Hope to see you again!");
    }

    /**
     * Says something to the user.
     *
     * @param something Text to be said to the user.
     */
    public void say(String something) {
        System.out.println("Duke: " + something);
    }

    /**
     * Brings up error message to the user.
     *
     * @param e The exception.
     */
    public void getError(Exception e) {
        System.out.println("Duke: Error! " + e.getMessage());
    }
}