package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    private static boolean isExit = false;

    public static boolean getExitStatus() {
        return isExit;
    }

    /**
     * Takes in a single line of command. isExit becomes true when the String "bye" is entered,
     * prompting the main program to exit.
     * Program stores user inputs as Tasks and returns the list when the String "list" is entered.
     * Tasks are categorised into "todo", "deadline" (to specify "by") and "event"  (to specify "at").
     * When "done xx" is entered, Task xx in the list is marked as done.
     * When "delete xx" is entered, Task xx in the list is removed from the list.
     *
     * @param command input string from user
     */
    public static void parse(String command) {
        if (command.equals("bye")) {
            isExit = true;
        } else if (command.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < Duke.getTasks().size() + 1; i++) {
                System.out.println(i + ". " + Duke.getTasks().get(i - 1));
            }
        } else if (command.startsWith("done")) {
            if (command.length() == 4) {
                System.out.println(new DukeException("Hold up! Please specify " +
                        "which task is done.").getMessage());
            } else {
                int taskToMark = Integer.parseInt(command.substring(5)) - 1;
                Duke.getTasks().get(taskToMark).markAsDone();
                System.out.println("Task Accomplished! I've marked this " +
                        "task as done:");
                System.out.println(Duke.getTasks().get(taskToMark));
            }
        } else if (command.startsWith("delete")) {
            if (command.length() == 6) {
                System.out.println(new DukeException("Hold up! Please specify " +
                        "which task to delete.").getMessage());
            } else {
                int taskToDelete = Integer.parseInt(command.substring(7)) - 1;
                System.out.println("Alright! I've removed this task:");
                System.out.println(Duke.getTasks().remove(taskToDelete));
                System.out.println("Now you have " + Duke.getTasks().size()
                        + " tasks in your list.");
            }
        } else if (command.startsWith("find")) {
            if (command.length() == 4) {
                System.out.println(new DukeException("Hold up! Please specify keyword.").getMessage());
            } else {
                System.out.println("Here are the matching tasks in your list:");
                String keyword = command.substring(5);
                for (int i = 1; i < Duke.getTasks().size() + 1; i++) {
                    Task currentTask = Duke.getTasks().get(i - 1);
                    if (currentTask.toString().contains(keyword)) {
                        System.out.println(i + ". " + currentTask);
                    }
                }
            }
        } else {
            if (!command.startsWith("todo") && !command.startsWith("deadline")
                    && !command.startsWith("event")) {
                System.out.println(new DukeException("Sorry, I'm not sure what " +
                        "you mean by that :(").getMessage());
            } else {

                if (command.startsWith("todo")) {
                    if (command.length() == 4) {
                        System.out.println(new DukeException("Hold up! The " +
                                "description of todo cannot be empty...").getMessage());
                    } else {
                        System.out.println("Got it! I've added this task:");
                        Duke.getTasks().add(new ToDo(command.substring(5)));
                        System.out.println(Duke.getTasks().get(
                                Duke.getTasks().size() - 1));
                        System.out.println("Now you have " + Duke.getTasks().size()
                                + " tasks in your list.");
                    }

                } else if (command.startsWith("deadline")) {
                    if (command.length() == 8) {
                        System.out.println(new DukeException("Hold up! The " +
                                "description of deadline cannot be empty...").getMessage());
                    } else if (!command.contains("/by")) {
                        System.out.println(new DukeException("Please specify " +
                                "deadline using: /by (deadline)").getMessage());
                    } else {
                        System.out.println("Got it! I've added this task:");
                        int endIndex = command.indexOf("/by") - 1;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                "yyyy-MM-dd HHmm");
                        LocalDateTime dateTime = LocalDateTime.parse(
                                command.substring(endIndex + 5), formatter);
                        Duke.getTasks().add(new Deadline(command.substring(9, endIndex),
                                dateTime.format(DateTimeFormatter.ofPattern(
                                        "MMM d yyyy HHmm"))));
                        System.out.println(Duke.getTasks().get(Duke.getTasks().size() - 1));
                        System.out.println("Now you have " + Duke.getTasks().size()
                                + " tasks in your list.");
                    }

                } else {
                    if (command.length() == 5) {
                        System.out.println(new DukeException("Hold up! The " +
                                "description of event cannot be empty...").getMessage());
                    } else if (!command.contains("/at")) {
                        System.out.println(new DukeException("Please specify " +
                                "timing using: /at (timing)").getMessage());
                    } else {
                        System.out.println("Got it! I've added this task:");
                        int endIndex = command.indexOf("/at") - 1;
                        Duke.getTasks().add(new Event(command.substring(6, endIndex),
                                command.substring(endIndex + 5)));
                        System.out.println(Duke.getTasks().get(Duke.getTasks().size() - 1));
                        System.out.println("Now you have " + Duke.getTasks().size()
                                + " tasks in your list.");
                    }
                }
            }
        }
    }
}
