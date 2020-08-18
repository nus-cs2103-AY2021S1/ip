import java.util.ArrayList;
import java.util.Scanner;

public class Alice {
    private static final String prompt = "\n > ";
    private final ArrayList<Task> tasks;

    public Alice() {
        tasks = new ArrayList<>();
    }

    public String reply(String input) {
        String[] arr = input.split(" ", 2);
        try {
            if (arr.length == 1) {
                return frame_output(process_cmd(arr[0], "")) + prompt;
            }
            return frame_output(process_cmd(arr[0], arr[1])) + prompt;
        } catch (AliceException ex) {
            return frame_output(ex.getMessage()) + prompt;
        }
    }

    private String process_cmd(String cmd, String params) throws AliceException {
        if (cmd.equals("list") && params.isBlank()) {
            // Display task list
            return "Here are the tasks in your list:\n" + getTaskList();
        } else if (cmd.equals("done")) {
            // Mark as done
            return markTaskAsDone(params);
        } else if (cmd.equals("delete")) {
            // Delete
            return deleteTask(params);
        } else if (cmd.equals("todo")) {
            // Add to-do
            return addTodo(params);
        } else if (cmd.equals("deadline")) {
            // Add deadline
            return addDeadline(params);
        } else if (cmd.equals("event")) {
            // Add event
            return addEvent(params);
        } else if (cmd.isBlank()) {
            // Empty command
            throw new AliceException("Please give me something to do. T_T");
        } else {
            // Invalid command
            throw new AliceException("That command is not in my dictionary!");
        }
    }

    private String markTaskAsDone(String s_index) throws AliceException {
        try {
            int index = Integer.parseInt(s_index) - 1;
            tasks.get(index).markAsDone();
            return "Great work! I've marked this task as done:\n    " + tasks.get(index);
        } catch (NumberFormatException e) {
            throw new AliceException("That is not a valid number.");
        } catch (IndexOutOfBoundsException e) {
            throw new AliceException("That task number is not in the list.");
        }
    }

    private String deleteTask(String s_index) throws AliceException {
        try {
            int index = Integer.parseInt(s_index) - 1;
            Task removed = tasks.remove(index);
            return "Roger. I've removed this task from your list:\n    "
                    + removed
                    + "\nNow you have " + tasks.size() + " task in your list";
        } catch (NumberFormatException e) {
            throw new AliceException("That is not a valid number.");
        } catch (IndexOutOfBoundsException e) {
            throw new AliceException("That task number is not in the list.");
        }
    }

    private String addTodo(String details) throws AliceException {
        if (!details.isBlank()) {
            return addTask(new Todo(details));
        } else {
            throw new AliceException("The todo description cannot be left empty.");
        }
    }

    private String addDeadline(String details) throws AliceException {
        String[] desc_date = details.split(" /by ", 2);
        if (desc_date.length == 2 && !desc_date[1].isBlank()) {
            return addTask(new Deadline(desc_date[0], desc_date[1]));
        } else if (details.isBlank()) {
            // Empty description
            throw new AliceException("The deadline description cannot be left empty.");
        } else if (details.endsWith("/by")) {
            // Empty date
            throw new AliceException("You cannot create an deadline without the date.");
        } else {
            // No /by marker
            throw new AliceException("I can't find the date. Did you forget to add '/by'?");
        }
    }

    private String addEvent(String details) throws AliceException {
        String[] desc_date = details.split(" /at ", 2);
        if (desc_date.length == 2 && !desc_date[1].isBlank()) {
            return addTask(new Event(desc_date[0], desc_date[1]));
        } else if (details.isBlank()) {
            // Empty event description
            throw new AliceException("The event description cannot be left empty.");
        } else if (details.endsWith("/at")) {
            // Empty start-end time
            throw new AliceException("You cannot create an event without the start and end time.");
        } else {
            // No /at marker
            throw new AliceException("I can't find the start/end time. Did you forget to add '/at'?");
        }
    }

    private String addTask(Task t) {
        tasks.add(t);
        return "Roger. I've added the task to your list:\n    " + t
                + "\nNow you have " + tasks.size() + " task in your list";
    }

    private String frame_output(String s) {
        return "____________________________________________________________\n"
                + s
                + "\n____________________________________________________________";
    }

    private String getTaskList() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i + 1).append(". ").append(tasks.get(i));
            if (i < tasks.size() - 1) {
                s.append("\n");
            }
        }
        return s.toString();
    }

    public String greet() {
        String logo = " _____  _     _____ _____  _____\n" +
                "/  _  \\| |   |_   _/  __ \\|  ___|\n" +
                "| |_| || |     | | | /  \\/| |__\n" +
                "|  _  || |     | | | |    |  __|\n" +
                "| | | || |_____| |_| \\__/\\| |___\n" +
                "\\_| |_/\\_____/\\___/ \\____/\\____/\n";

        return logo +
                "\nHello! I'm Alice\n" +
                "How can I help you today?\n" +
                "____________________________________________________________" + prompt;
    }

    public String sayGoodbye() {
        return frame_output("Goodbye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Alice alice = new Alice();
        System.out.print(alice.greet());

        Scanner sc = new Scanner(System.in);

        String input;
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.print(alice.sayGoodbye());
                return;
            }
            System.out.print(alice.reply(input));
        }
    }
}
