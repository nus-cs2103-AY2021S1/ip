import java.util.Scanner;

public class UI {

    // Create the scanner object
    private final Scanner scanner;
    public UI() {

        //Create a new scanner object
        scanner = new Scanner(System.in);
    }

    public String wrapper(final String message) {
        //Wrapper when printing the error message
        return "Heh, you can't even type in a correct command\n"
                + message
                + "\nI'll give u a pity tip\n"
                + "Use 'help' for more information";
    }

    public void helpMessage() {
        System.out.println("Heh I guess I could help an insect like you:\n"
                + "- help                      : Get help for the commands\n"
                + "- todo (name)               : Adds a todo to the list\n"
                + "- event (name) /at (date)   : Adds an event at date\n"
                + "- deadline (name) /by (date): "
                + "Adds a deadline which expires by date\n"
                + "- delete (number)           : Removes a todo from the list\n"
        );
    }

    public String getInput() {

        //Declare type
        String input;

        // Take in input
        input = this.scanner.nextLine();

        //Return the input
        return input;
    }

    public void printEnd() {
        // Print the end message
        showLine();
        System.out.println("Clearly you were not worth my time.\n");
        showLine();
    }

    public void printIntro() {
        // Print the intro messages

        //Logo for the mascot
        String logo = "  _    _ _ _                   \n"
                + " | |  | | | |                  \n"
                + " | |  | | | |_ _ __ ___  _ __  \n"
                + " | |  | | | __| '__/ _ \\| '_ \\ \n"
                + " | |__| | | |_| | | (_) | | | |\n"
                + "  \\____/|_|\\__|_|  \\___/|_| |_|\n";

        //Print the intro
        System.out.println(logo);
        showLine();
        System.out.println("Hello lesser being, I am Ultron\n"
                + "What do you want?\n");
        showLine();
    }

    public void showLine() {
        System.out.println("_______________________________"
                + "_____________________________\n");
    }
}
