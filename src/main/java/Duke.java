import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    static List<Task> tasks = new ArrayList<>();

    public static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");
    }

    public static void farewell() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        String userCommand = sc.nextLine();

        while (!userCommand.equals("bye")) {
            // Show list of tasks
            if (userCommand.equals("list")) {
                System.out.println("\t____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    int serialNumber = i + 1;
                    Task task = tasks.get(i);
                    System.out.println("\t" + serialNumber + "." + task);
                }
                System.out.println("\t____________________________________________________________\n");
                userCommand = sc.nextLine();
            }  else if (userCommand.contains("done")) {
                try {
                    // E.g given "done 1", we split to ["done", "1"]
                    String[] userCommandSplit = userCommand.split(" ");
                    // To prevent cases such as "done 1 7"
                    if (userCommandSplit.length != 2) {
                        System.out.println("\t____________________________________________________________");
                        System.out.println("Done command written incorrectly. Please check again.");
                        System.out.println("\t____________________________________________________________\n");
                        userCommand = sc.nextLine();
                    } else {
                        // Take serial number e.g 1 "done 1" and mark as done
                        int serialNumber = Integer.parseInt(userCommandSplit[1]);
                        Task doneTask = tasks.get(serialNumber - 1);
                        doneTask.markAsDone();
                        System.out.println("\t____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("\t" + doneTask);
                        System.out.println("\t____________________________________________________________\n");
                        userCommand = sc.nextLine();
                    }
                } catch (Exception e) { // To prevent garbage cases like "done1728371", "done"
                    System.out.println("\t____________________________________________________________");
                    System.out.println("Done command written incorrectly. Please check again.");
                    System.out.println("\t____________________________________________________________\n");
                    userCommand = sc.nextLine();
                }
            } else { // Add Task
                Task newTask = null;
                if (userCommand.contains("todo")) { // To Do
                    newTask = new Todo(userCommand);
                } else if (userCommand.contains("deadline")) { // Deadline
                    String[] userCommandSplit = userCommand.split(" /by ");
                    String description = userCommandSplit[0].split(" ", 2)[1];
                    String by = userCommandSplit[1];
                    newTask = new Deadline(description, by);
                } else if (userCommand.contains("event")) { // Event
                    String[] userCommandSplit = userCommand.split(" /at ");
                    String description = userCommandSplit[0].split(" ", 2)[1];
                    String at = userCommandSplit[1];
                    newTask = new Event(description, at);
                }

                // If a task is specified as a Task but not a Deadline / To Do / Event, throw an error
                if (newTask == null) {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("Please specify either a Deadline, To Do, or Event!");
                    System.out.println("\t____________________________________________________________\n");
                    userCommand = sc.nextLine();
                } else {
                    tasks.add(newTask);
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t\t" + newTask);
                    System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
                    System.out.println("\t____________________________________________________________\n");
                    userCommand = sc.nextLine();
                }
            }
        }
        farewell();
    }
}
