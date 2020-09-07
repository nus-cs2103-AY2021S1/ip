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
    public String greet() {
        String greeting = "Duke: Hello! How may I help you?";
        System.out.println(greeting);
        return greeting;
    }

    /**
     * Says goodbye to the user.
     */
    public String goodbye() {
        String goodBye = "Duke: Bye. Hope to see you again!";
        System.out.println(goodBye);
        return goodBye;
    }

    /**
     * Says something to the user.
     *
     * @param something Text to be said to the user.
     */
    public String say(String something) {
        String thing = "Duke: " + something;
        System.out.println(thing);
        return thing;
    }

    /**
     * Brings up error message to the user.
     *
     * @param e The exception.
     */
    public String getError(Exception e) {
        String error = "Duke: Error! " + e.getMessage();
        System.out.println();
        return error;
    }
}