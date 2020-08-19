import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Rawr! I'm Dino ><\n" + "Get started on your task list by entering a task!"
                + "\nTo see how to format your task, input 'format'"
                + "\nTo see your list of tasks, input 'list'."
                + "\n____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        boolean isBye = false;
        List<Task> tasks = new ArrayList<>();

        while (!isBye) {
            String input = scanner.nextLine();
            String[] inputWords = input.split(" ");
            if (input.equals("bye")) {
                // Dino says bye
                System.out.println("Rawr. Hope to see you again soon! ><"
                        + "\n____________________________________________________________");
                isBye = true;
            } else if (input.equals("format")) {
                System.out.println("Formats for the three task types Todo, Deadline and Event,"
                        + " are shown below.\n"
                        + "Todo: 'todo <task description>'"
                        + " (e.g., visit new theme park)\n"
                        + "Deadline: 'deadline <task description>"
                        + " /by <date/time>' (e.g., submit report by 11/10/2019 5pm)\n"
                        + "Event: 'event <task description>"
                        + " /at <date/start and end time>' "
                        + "(e.g., team project meeting on 2/10/2019 2-4pm)"
                        + "\n____________________________________________________________");
            } else if (input.equals("list")) {
                if(tasks.size() != 0) {
                    // Dino lists out all items in list
                    System.out.println("Dino lists your tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        int index = i + 1;
                        System.out.println(index + ". " + tasks.get(i));
                    }
                    System.out.println("To mark off a task after completion"
                            + ", input 'done <task number>'.");
                } else {
                    System.out.println("Rawr! Dino could not find any items in your task list."
                            + "\nGet started by entering a task. "
                            + "Formats for a task can be found by entering 'format'.");
                }
                System.out.println("____________________________________________________________");
            } else if (inputWords[0].equals("done") && inputWords.length == 2
                    && inputWords[1].matches("[0-9]+")) {
                // condition checks that user input is in the format "done X" where X is a numeric

                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber > tasks.size() || taskNumber < 1) {
                    // task number is not valid
                    System.out.println("Task " + taskNumber + " is not in your list of tasks!"
                            + "\n____________________________________________________________");
                } else {
                    // Dino marks task as done
                    int taskIndex = taskNumber - 1;
                    Task doneTask = tasks.get(taskIndex);
                    doneTask.markAsDone();
                    System.out.println("Great! Dino has marked " + "Task " + taskNumber
                            + " as done: \n" + doneTask
                            + "\n____________________________________________________________");
                }
            } else {
                // Dino adds task to list
                try {
                    switch (inputWords[0]) {
                    case "todo":
                        String task = input.substring(5);
                        Todo todo = new Todo(task);
                        tasks.add(todo);
                        System.out.println("Dino has added to your list of tasks: \n"
                                + todo
                                + "\nNumber of tasks in list: " + tasks.size()
                                + "\n____________________________________________________________");
                        break;
                    case "deadline":
                        String[] taskBy = input.substring(9)
                                .split("/by");
                        Deadline deadline = new Deadline(taskBy[0], taskBy[1]);
                        tasks.add(deadline);
                        System.out.println("Dino has added to your list of tasks: \n"
                                + deadline
                                + "\nNumber of tasks in list: " + tasks.size()
                                + "\n____________________________________________________________");
                        break;
                    case "event":
                        String[] eventAt = input.substring(6)
                                .split("/at");
                        Event event = new Event(eventAt[0], eventAt[1]);
                        tasks.add(event);
                        System.out.println("Dino has added to your list of tasks: \n"
                                + event
                                + "\nNumber of tasks in list: " + tasks.size()
                                + "\n____________________________________________________________");
                        break;
                    default:
                        System.out.println("Rawr! Dino could not add your task. "
                                + "Make sure your format is correct."
                                + "\nFormats to input a task can be found by entering 'format'."
                                + "\n____________________________________________________________");
                        break;
                    }
                } catch(IndexOutOfBoundsException e) {
                    // invalid task format entered
                    System.out.println("Rawr! Dino could not add your task. "
                            + "Make sure your format is correct."
                            + "\nFormats to input a task can be found by entering 'format'."
                            + "\n____________________________________________________________");
                }
            }
        }
    }
}