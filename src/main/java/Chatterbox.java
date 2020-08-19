import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chatterbox {
    private static final String SEPARATOR = "++++++++++++++++++++++++++++++++++++++++++++++++++++++";
    private static final List<Task> ITEMS = new ArrayList<>();

    private static String format(String s) {
        return SEPARATOR + "\n" + s + "\n" + SEPARATOR;
    }

    private static void processInput(String input) throws ChatterboxException {
        if (input.strip().equals("")) {
            throw new ChatterboxException("Input cannot be empty.");
        }
        String command = input.split(" ")[0];
        if (input.equals("list")) {
            if (ITEMS.size() != 0) {
                StringBuilder fullList = new StringBuilder("\n");
                for (int i = 0; i < ITEMS.size(); i++) {
                    fullList.append(i + 1).append(". ").append(ITEMS.get(i)).append("\n");
                }
                System.out.println(format(fullList.toString()));
            } else {
                System.out.println(format("Your list is currently empty."));
            }
        } else if (command.equals("done")) {
            int taskNo = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskNo < 0 || taskNo >= ITEMS.size()) {
                throw new ChatterboxException("Invalid task number.");
            }
            Task t = ITEMS.get(taskNo);
            t.markDone();
            System.out.println(format("Nice! I've marked this task as done: \n" + t));
        } else if (command.equals("deadline") || command.equals("todo") || command.equals("event")) {
            if (!input.contains(" ") || input.substring(input.indexOf(' ')).strip().equals("")) {
                throw new ChatterboxException("The description of a " + command + " cannot be empty");
            }
            String contents = input.substring(input.indexOf(' '));
            switch (command) {
                case "deadline":
                    ITEMS.add(new Deadline(contents));
                    break;
                case "todo":
                    ITEMS.add(new ToDo(contents));
                    break;
                case "event":
                    ITEMS.add(new Event(contents));
                    break;
            }
            System.out.println(format("Got it. I've added this task: \n"
                    + ITEMS.get(ITEMS.size() - 1) + "\n"
                    + "Now you have " + ITEMS.size() + " tasks in the list"));
        } else {
            throw new ChatterboxException("Invalid command.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello I'm Chatterbox. What can I do for you?");

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        while (!input.equals("bye")) {
            try {
                processInput(input);
            } catch (ChatterboxException e) {
                System.out.println(format("☹ OOPS!!! " + e));
            }
            input = s.nextLine();
        }

        System.out.println(format("Goodbye! Hope to see you again soon!"));
    }
}

