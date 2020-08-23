import Task.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String LINE = "_______________________________________\n";
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) throws DukeException {
        // Opening
        String open = "_______________________________________ \n"
                + "Hello! I'm Duke \n"
                + "What can I do for you? \n"
                + "You can type 'help' to view commands \n"
                + "_______________________________________ \n";
        String line = "_______________________________________\n";
        System.out.println(open);

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String user_input = scanner.nextLine();
            String[] input_split = user_input.split(" ", 2);
            if (user_input.equals("bye")) {  // For exiting the program
                break;

            } else if (user_input.equals("list")){  // For viewing items in to do list
                handleList();

            } else if (input_split[0].equals("done")) {  // For marking items in the to do list as done
                try {
                    handleDone(input_split[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line + "Invalid input! Please specify which task you have completed! \n" + line);
                }

            } else if (input_split[0].equals("todo")) { // Add new todo
                try {
                    handleTodo(input_split[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line + "Invalid input! Please specify your todo description! \n" + line);
                }

            } else if (input_split[0].equals("deadline")) { // Add new deadline
                try {
                    handleDeadline(input_split[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line + "Invalid input! Please specify your deadline description and details! \n" + line);
                }

            } else if (input_split[0].equals("event")) { // Add new event
                try {
                    handleEvent(input_split[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line + "Invalid input! Please specify your event description and details! \n" + line);
                }

            } else if (input_split[0].equals("delete")) { // Delete task
                try {
                    handleDelete(input_split[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line + "Invalid input! Please specify which task you want to delete! \n" + line);
                }

            } else if (user_input.equals("help")) { // Additional help feature
                handleHelp();

            } else {
                System.out.println(line + "Invalid input! Please try again! \n" + line);
            }
        }

        // Closing
        scanner.close();
        String close = "_______________________________________ \n"
                + "Goodbye! See you soon! \n"
                + "_______________________________________ \n";
        System.out.println(close);
    }

    public static void handleList() {
        String output = "";
        for (int i = 1; i <= taskList.size(); i++) {
            output = output + i + ". " + taskList.get(i - 1) + "\n";
        }
        System.out.println(LINE + "Here are the tasks in your list: \n" + output + LINE);
    }

    public static void handleDone(String taskIdString) {
        int taskId = Integer.parseInt(taskIdString);
        if (taskId <=0 || taskId > taskList.size()) {
            System.out.println(LINE + "Invalid input! That task does not exist! \n" + LINE);
        } else {
            taskList.get(taskId - 1).setCompleted();
            System.out.println(LINE + "Nice! I've marked this task as done: \n"
                    + taskList.get(taskId - 1) + "\n" + LINE);
        }
    }

    public static void handleDelete(String taskIdString) {
        int taskId = Integer.parseInt(taskIdString);
        if (taskId <=0 || taskId > taskList.size()) {
            System.out.println(LINE + "Invalid input! That task does not exist! \n" + LINE);
        } else {
            int new_size = taskList.size() - 1;
            System.out.println(LINE + "Noted! I've deleted this task: \n"
                    + taskList.get(taskId - 1) + "\n"
                    + "Now you have " + new_size + " tasks in the list."
                    + "\n" + LINE);
            taskList.remove(taskId - 1);
        }
    }

    public static void handleTodo(String todoDescription) {
        taskList.add(new ToDo(todoDescription));
        String output = LINE + "Got it. I've added this task: \n"
                + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list."
                + "\n" + LINE;
        System.out.println(output);
    }

    public static void handleDeadline(String deadlineDetails) {
        String[] details = deadlineDetails.split(" /by ", 2);
        taskList.add(new Deadline(details[0], details[1]));
        String output = LINE + "Got it. I've added this task: \n"
                + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list."
                + "\n" + LINE;
        System.out.println(output);
    }

    public static void handleEvent(String eventDetails) {
        String[] details = eventDetails.split(" /at ", 2);
        taskList.add(new Event(details[0], details[1]));
        String output = LINE + "Got it. I've added this task: \n"
                + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list."
                + "\n" + LINE;
        System.out.println(output);
    }

    public static void handleHelp() {
        String output = LINE  + "These are my available commands: \n"
                + "list: View entire list of task \n"
                + "todo <desciption>: Add new todo to list \n"
                + "deadline <description> /by <date/time>: Add new deadline to list \n"
                + "event <description> /at <date/time>: Add new event to list \n"
                + "done <task id>: Sets task as completed \n"
                + "delete <task id>: Deletes task from list \n"
                + "bye: Exits program \n" + LINE;
        System.out.println(output);
    }
}
