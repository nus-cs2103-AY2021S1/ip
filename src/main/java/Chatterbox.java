import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chatterbox {
    private static final String SEPARATOR = "++++++++++++++++++++++++++++++++++++++++++++++++++++++";

    private static Storage store;
    private static List<Task> items = new ArrayList<>();

    private static String format(String s) {
        return SEPARATOR + "\n" + s + "\n" + SEPARATOR;
    }

    private static void processInput(String input) throws ChatterboxException, IOException {
        // Check if input is just whitespace
        if (input.strip().equals("")) throw new ChatterboxException("Input cannot be empty.");

        // Get first word of input
        String command = (input + " ").split(" ")[0];

        // Process command
        if (command.equals("list")) {
            if (items.size() != 0) {
                StringBuilder fullList = new StringBuilder("\n");
                for (int i = 0; i < items.size(); i++) {
                    fullList.append(i + 1).append(". ").append(items.get(i)).append("\n");
                }
                System.out.println(format(fullList.toString()));
            } else {
                System.out.println(format("Your list is currently empty."));
            }
        } else if (command.equals("done") || command.equals("delete")) {
            // Get the task number after the command, check if it is valid
            int taskNo;
            try {
                taskNo = Integer.parseInt(input.split(" ")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new ChatterboxException("Please enter a number after the command.");
            }
            if (taskNo < 0 || taskNo >= items.size()) {
                throw new ChatterboxException("Invalid task number.");
            }

            // Mark as done or delete based on the command
            if (command.equals("done")) {
                Task t = items.get(taskNo);
                t.setDone(true);
                System.out.println(format("Nice! I've marked this task as done: \n" + t));
            } else {
                Task t = items.remove(taskNo);
                System.out.println(format("Noted! I've removed this task from your list: \n"
                        + t + "\n"
                        + " Now you have " + items.size() + " tasks in the list."));
            }
            store.saveItems(items);
        } else if (command.equals("deadline") || command.equals("todo") || command.equals("event")) {
            Task t = Parser.parse(input);
            items.add(t);
            System.out.println(format("Got it. I've added this task: \n"
                    + t + "\n"
                    + "Now you have " + items.size() + " tasks in the list"));
            store.saveItems(items);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello I'm Chatterbox. What can I do for you?");

        Scanner s = new Scanner(System.in);

        store = new Storage();
        try {
            items = store.getItems();
        } catch (IOException e) {
            System.out.println(format("Failed to load the save file, starting afresh."));
        }

        String input = s.nextLine();
        while (!input.equals("bye")) {
            try {
                processInput(input);
            } catch (ChatterboxException | IOException e) {
                System.out.println(format("☹ OOPS!!! " + e));
            }
            input = s.nextLine();
        }

        System.out.println(format("Goodbye! Hope to see you again soon!"));
    }
}

