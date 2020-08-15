// Package dependancies
import java.util.*;

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


public class Bob {
    private Command command;
    private String output;
    private ArrayList<String> list;

    /*================ Static fields ================*/

    // Print when user first starts the program
    public static String INTRO = "Hi, my name is BOB.\n" +
            "What can I do for you?";
    // Print when user exits the program
    public static String OUTRO = "See you soon!";

    private Bob (Command command, String output, ArrayList<String> list) {
        this.command = command;
        this.output = output;
        this.list = list;
    }

    /*================ Private methods ================*/

    // Adds item to list and returns readable String
    private String addItem(String item) {
        this.list.add(item);
        return  "\"" + item + "\" " + "is added to your list!";
    }

    // Converts list to readable String
    private String convertList() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += i + ". " + list.get(i) + "\n";
        }
        return output;
    }

    /*================ Public methods ================*/

    // Chatbot's current command getter
    public Command getCommand() {
        return this.command;
    }
    // Chatbot's current output getter
    public String getOutput() {
        String divider = "========================================================\n";
        return divider + this.output + "\n" + divider;
    }

    // Initializes Bob to introduce itself
    public static Bob initializeBob() {
        return new Bob(Command.GREET, INTRO, new ArrayList<String>());
    }

    // Updates Bob with command
    public Bob updateBob(Command command, String output) {
        return new Bob(command, output, this.list);
    }

    // Updates Bob with next command based on user input
    public Bob nextCommand(String input) {
        if (input.toLowerCase().equals("bye")) {
            return updateBob(Command.EXIT, OUTRO);
        } else if (input.toLowerCase().equals("list")) {
            return updateBob(Command.LIST, convertList());
        } else {
            return updateBob(Command.ADD, addItem(input));
        }
    }

    // Checks if Bob has ceased
    public boolean hasExited() {
        return command == Command.EXIT;
    }

    public static void main(String[] args) {
        // Initializing stage
        Bob chatbot = initializeBob();
        // Output for greeting
        System.out.println(chatbot.getOutput());

        Scanner sc = new Scanner(System.in);

        while (!chatbot.hasExited()) {
            String userInput = sc.nextLine();
            chatbot = chatbot.nextCommand(userInput);
            System.out.println(chatbot.getOutput());
        }

        sc.close();
    }
}
