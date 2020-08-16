import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<String> entryBook = new ArrayList<>();

    /**
     * Main control system which loops user input until user exits with the
     * command 'bye'.
     * @param args args supplied to main.
     */
    public static void main(String[] args) {
        String introduction =
                "Hi, I'm your Professor, Martin." +
                "\nWhat can I do for you?";
        System.out.println(introduction);
        Scanner sc = new Scanner(System.in);
        String input = "";

        // Loop program until command 'bye' is entered as input.
        while (true) {
            try {
                System.out.print("Enter input: ");
                input = sc.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye, see you soon. Exiting...");
                    break;
                } else if (input.equals("list")){
                    listEntries();
                } else {
                    addEntry(input);
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e.toString());
            }
        }
        sc.close();
    }

    /**
     * Lists entries currently stored in the system.
     */
    public static void listEntries() {
        int numEntries = entryBook.size();
        if (numEntries == 0) {
            System.out.println("No entries found.");
        } else {
            for (int i = 0; i < numEntries; i++) {
                System.out.println((i + 1) + ". " + entryBook.get(i));
            }
        }
    }

    /**
     * Stores entry into the ArrayList.
     * @param input String to be stored into the ArrayList.
     */
    public static void addEntry(String input) {
        entryBook.add(input);
        System.out.println("added: " + input);
    }
}
