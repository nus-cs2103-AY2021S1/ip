import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String LINE_BREAK = "   ____________________________________________________________";
    private static final String SPACE_BEFORE_TEXT = "    ";
    private static final String SPACE_FOR_ADDING = "     ";

    private static boolean isInteger (String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        List<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println(LINE_BREAK);
        System.out.println(SPACE_BEFORE_TEXT + "Hey, I'm Emilia \u2764 !" + "\n" +
                SPACE_BEFORE_TEXT + "What can I do for you?");
        System.out.println(LINE_BREAK);

        while (sc.hasNext()) {
            String input = sc.nextLine().trim().toLowerCase();
            System.out.println(LINE_BREAK);
            if (input.equals("bye")) {
                System.out.println(SPACE_BEFORE_TEXT + "Welcome back \u2764 !" + "\n" +
                        LINE_BREAK);
                break;

            }

            if (input.equals("list")) {
                System.out.println(SPACE_BEFORE_TEXT + "Please take a look at the tasks:");
                int count = 1;
                for (Task task : tasks) {
                    System.out.println(SPACE_BEFORE_TEXT + count + ". " + task);
                    count++;
                }
                System.out.println(LINE_BREAK);
                continue;
            }

            String[] process = input.split(" ", 2);
            if (process[0].equals("done") && isInteger(process[1])) {
                int index = Integer.parseInt(process[1]) - 1;
                try {
                    Task task = tasks.get(index);
                    task.markAsDone();
                    System.out.println(SPACE_BEFORE_TEXT + "Understood, I've marked this " +
                            "task as done:" + "\n" + SPACE_FOR_ADDING + task);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(SPACE_BEFORE_TEXT + "Sorry, I can't seem " +
                            "to find the task...");
                }
                continue;
            }

            String firstWord = process[0];
            Task current;
            try {
                switch (firstWord) {
                    case "todo":
                        current = new ToDo(process[1].trim());
                        break;
                    case "deadline": {
                        int byIndex = process[1].indexOf(" /by ");
                        if (byIndex == -1) {
                            throw new DukeException("better description");
                        }
                        String deadline = process[1].substring(byIndex + 4).trim();
                        String description = process[1].substring(0, byIndex).trim();
                        current = new Deadline(description, deadline);
                        break;
                    }
                    case "event": {
                        int atIndex = process[1].indexOf(" /at ");
                        if (atIndex == -1) {
                            throw new DukeException("better description");
                        }
                        String deadline = process[1].substring(atIndex + 4).trim();
                        String description = process[1].substring(0, atIndex).trim();
                        current = new Event(description, deadline);
                        break;
                    }
                    default:
                        throw new DukeException("nothing understood");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(SPACE_BEFORE_TEXT + "The description of " +
                        "a task cannot be empty!");
                continue;
            } catch (DukeException e) {
                String type = e.getMessage();
                if (type.equals("better description")) {
                    System.out.println(SPACE_BEFORE_TEXT + "I need a better " +
                            "description of the task such as the time or date!");
                } else if (type.equals("nothing understood")) {
                    System.out.println(SPACE_BEFORE_TEXT + "I don't understand you at all...");
                }
                continue;
            }

            tasks.add(current);
            System.out.println(SPACE_BEFORE_TEXT + "Understood! I've added this task: " +
                    "\n" + SPACE_FOR_ADDING + current);
            System.out.println(SPACE_BEFORE_TEXT + "You have " +
                    tasks.size() + " tasks in your list now!");
            System.out.println(LINE_BREAK);
        }
    }
}
