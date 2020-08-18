import java.util.ArrayList;
import java.util.Scanner;
public class PersonalAssistant {
    private ArrayList<String> store;

    /**
     * Initialize personal assistant with a store for user input
     */
    public PersonalAssistant() {
        store = new ArrayList<String>();
    }

    public void run() {
        System.out.println("> What can I do for you?");
        getUserCommands();
    }

    /**
     * Gets user input from STDIN
     * If the input command is "bye"
     * Exit and echo a farewell message
     * Otherwise echoes the input
     */
    public void getUserCommands() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your command or \"bye\" to exit: ");
        String cmd = reader.nextLine();
        switch (cmd) {
            case "bye":
                System.out.println("Goodbye!");
                break;
            case "list":
                this.list();
                getUserCommands();
                break;
            default:
                store.add(cmd);
                System.out.println(cmd);
                System.out.println("\n");
                getUserCommands();
        }
    }

    public void list() {
        for (int i = 0; i < store.size(); i++) {
            String listText = String.format("%d. %s", i + 1, store.get(i));
            System.out.println(listText);
        }
    }
}
