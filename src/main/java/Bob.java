/*================== Welcome to BOB ==================*/
/*
                ██████╗░░█████╗░██████╗░
                ██╔══██╗██╔══██╗██╔══██╗
                ██████╦╝██║░░██║██████╦╝
                ██╔══██╗██║░░██║██╔══██╗
                ██████╦╝╚█████╔╝██████╦╝
                ╚═════╝░░╚════╝░╚═════╝░

            Also known as BERY ORDINARY BOT
*/
// Package dependancies
import java.util.*;

public class Bob {
    private Command command;
    private String output;
    // Print when user first starts the program
    public static String INTRO = "Hi, my name is BOB.\n" +
            "What can I do for you?";
    // Print when user exits the program
    public static String OUTRO = "See you soon!";

    private Bob (Command command, String output) {
        this.command = command;
        this.output = output;
    }

    // Command getter
    public Command getCommand() {
        return this.command;
    }
    // Output getter
    public String getOutput() {
        String divider = "========================================================\n";
        return divider + this.output + "\n" + divider;
    }

    // Initializes Bob to introduce itself
    public static Bob initializeBob() {
        return new Bob(Command.GREET, INTRO);
    }

    // Updates Bob with command
    public Bob updateBob(Command command, String output) {
        return new Bob(command, output);
    }

    // Updates Bob with next command based on user input
    public Bob nextCommand(String input) {
        if (input.toLowerCase().equals("bye")) {
            return updateBob(Command.EXIT, OUTRO);
        } else {
            return updateBob(Command.ECHO, input);
        }
    }

    // Checks if Bob has ceased
    public boolean hasExited() {
        return command == Command.EXIT;
    }


//    public String toString() {
//        return getOutput();
//    }

    public static void main(String[] args) {
        // Initializing stage
        Bob chatbot = initializeBob();
        // Output for greeting
        System.out.println(chatbot.getOutput());

        Scanner sc = new Scanner(System.in);

        while (!chatbot.hasExited()) {
            String userInput = sc.next();
            chatbot = chatbot.nextCommand(userInput);
            System.out.println(chatbot.getOutput());
        }
        sc.close();
    }
}
