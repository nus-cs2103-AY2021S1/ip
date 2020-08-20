import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static final String delete_task_number_missing = "Please enter a valid task number";
    private static final String dash = ("\u2500").repeat(5);
    private static final String greeting = "Hello! I'm Duke \n" +
            "What can I do for you?";
    private static final String task_read = "Here are the tasks in your list: \n";
    private static final String task_added = "Got it. I've added this task: \n";
    private static final String task_completed = "Nice! I've marked this task as complete. \n";
    private static final String farewell = "Bye. Hope to see you again soon.";
    private static final String task_index_out_of_bounds = "That task does not exist.";
    private static final String task_number_format = "Please enter the task number in numerals.";
    public static final String task_no_description = "Invalid, no task description provided.";
    public static final String task_invalid_type = "Invalid, not an accepted task type";
    public static final String task_list_number = "\nNow you have %d tasks in the list";
    public static final String task_removed = "Noted. I've removed this task";
    private boolean running = true;
    private List<Task> taskList = new ArrayList<>(100);

    public Duke() {
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        Duke servingDuke = new Duke();
        System.out.println(servingDuke.constructMessage(greeting));
        while (servingDuke.canRun()) {
            String input = sc.nextLine();
            servingDuke.respondToInput(input);
        }
    }

    private void respondToInput(String rawInput) {
        //Remove leading and trailing whitespaces
        String input = rawInput.trim();
        String[] breakdown = input.split(" ", 2);
        if (!input.isEmpty()) {
            if (input.equals("bye")) {
                running = false;
                System.out.println(
                        constructMessage(farewell));
            } else if (input.equals("list")) {
                System.out.println(
                        constructMessage(task_read + printAsString(taskList)));
            } else if (breakdown[0].equals("delete")) {
                deleteTask(breakdown);
            } else if (breakdown[0].equals("done")) {
                markTaskAsDone(breakdown);
            } else {
                addTask(breakdown);
            }
        }
    }

    private void deleteTask(String[] breakdown) {
        try {
            int taskNumber = Integer.parseInt(breakdown[1]);
            Task deletedTask = taskList.get(taskNumber - 1);
            taskList.remove(taskNumber - 1);
            String count_text = String.format(task_list_number, taskList.size());
            System.out.println(constructMessage(
                    task_removed + deletedTask + count_text

            ));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(constructMessage(delete_task_number_missing));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(constructMessage(task_index_out_of_bounds));
        } catch (NumberFormatException e) {
            System.out.println(constructMessage(task_number_format));
        }
    }

    private void addTask(String[] breakdown) {
        try {
            String taskType = breakdown[0];
            if (!(taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event"))) {
                throw new DukeException(taskType);
            }
            String description = breakdown[1];
            Task newTask = null;
            switch (taskType) {
                case "todo":
                    newTask = new Todo(description);
                    break;
                case "deadline":
                    String[] deadlineDetails = description.split("/by", 2);
                    String deadlineDesc = deadlineDetails[0].trim();
                    String deadlineTime = deadlineDetails[1].trim();
                    newTask = new Deadline(deadlineDesc, deadlineTime);
                    break;
                case "event":
                    String[] eventDetails = description.split("/at", 2);
                    String eventDesc = eventDetails[0].trim();
                    String eventTime = eventDetails[1].trim();
                    newTask = new Event(eventDesc, eventTime);
                    break;
            }
            taskList.add(newTask);
            String count_text = String.format(task_list_number, taskList.size());
            System.out.println(constructMessage(
                    task_added + newTask.toString() + count_text
            ));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(constructMessage(task_no_description));
        } catch (DukeException e) {
            System.err.println(constructMessage(e.toString()));
        }
    }

    private void markTaskAsDone(String[] breakdown) {
        try {
            int taskNumber = Integer.parseInt(breakdown[1]);
            Task completedTask = taskList.get(taskNumber - 1);
            completedTask.markDone();
            System.out.println(constructMessage(
                    task_completed + completedTask.toString()
            ));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(constructMessage(task_index_out_of_bounds));
        } catch (NumberFormatException e) {
            System.out.println(constructMessage(task_number_format));
        }
    }

    private String printAsString(List<Task> taskList) {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            res.append(i)
                    .append(". ")
                    .append(taskList.get(i - 1));
            if (i != taskList.size()) {
                res.append("\n");
            }
        }
        return res.toString();
    }

    private boolean canRun() {
        return running;
    }

    public String constructMessage(String message) {
        return dash + "\n" + message + "\n" + dash;
    }
}
