import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> taskList = new ArrayList<>();

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
                } else if (input.length() >= 4 && input.substring(0, 4).equals("done")) {
                    // For processing "done" command with the corresponding integer value.
                    String numString = input.substring(5);
                    int entryNum = Integer.parseInt(numString);
                    markEntryDone(entryNum);
                } else {
                    addEntry(input);
                }
            } catch (Exception e) {
                throw e;
            }
        }
        sc.close();
    }

    /**
     * Lists entries currently stored in the system.
     */
    public static void listEntries() {
        int numEntries = taskList.size();
        if (numEntries == 0) {
            System.out.println("No entries found.");
        } else {
            for (int i = 0; i < numEntries; i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    /**
     * Stores entry into the ArrayList.
     * @param input String to be stored into the ArrayList.
     */
    public static void addEntry(String input) {
        taskList.add(new Task(input));
        System.out.println("added: " + input);
    }

    /**
     * Given a particular entry number, mark that entry in the task list
     * as done.
     * @param entryNum The entry number to mark as done.
     */
    public static void markEntryDone(int entryNum) {
        try {
            if (entryNum < 0 || entryNum > taskList.size()) {
                throw new InputMismatchException("Entry number does not exist.");
            } else {
                Task t = taskList.get(entryNum - 1);
                t.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
