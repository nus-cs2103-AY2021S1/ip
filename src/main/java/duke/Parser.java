package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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
                System.out.println(new DukeException("Hold up! Please specify "
                        + "which task is done.").getMessage());
            } else {
                int taskToMark = Integer.parseInt(command.substring(5)) - 1;
                Duke.getTasks().get(taskToMark).markAsDone();
                System.out.println("Task Accomplished! I've marked this "
                        + "task as done:");
                System.out.println(Duke.getTasks().get(taskToMark));
            }
        } else if (command.startsWith("delete")) {
            if (command.length() == 6) {
                System.out.println(new DukeException("Hold up! Please specify "
                        + "which task to delete.").getMessage());
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
                System.out.println(new DukeException("Sorry, I'm not sure what "
                        + "you mean by that :(").getMessage());
            } else {

                if (command.startsWith("todo")) {
                    if (command.length() == 4) {
                        System.out.println(new DukeException("Hold up! The "
                                + "description of todo cannot be empty...").getMessage());
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
                        System.out.println(new DukeException("Hold up! The "
                                + "description of deadline cannot be empty...").getMessage());
                    } else if (!command.contains("/by")) {
                        System.out.println(new DukeException("Please specify "
                                + "deadline using: /by (deadline)").getMessage());
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
                        System.out.println(new DukeException("Hold up! The "
                                + "description of event cannot be empty...").getMessage());
                    } else if (!command.contains("/at")) {
                        System.out.println(new DukeException("Please specify "
                                + "timing using: /at (timing)").getMessage());
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

    /**
     * Alternative parse method that works the same way, but used for GUI
     *
     * @param command user input
     * @param tasks list of tasks
     * @param storage storage of tasks
     * @param output duke's reply
     * @return duke's reply
     * @throws IOException if filePath does not exist
     */
    public static String parse(String command, TaskList tasks, Storage storage, String output) throws IOException {
        if (command.equals("bye")) {
            isExit = true;
            return "Goodbye, have a nice day :D";

        } else if (command.equals("list")) {
            output += "Here are the tasks in your list:" + "\n";
            for (int i = 1; i < tasks.size() + 1; i++) {
                output += i + ". " + tasks.get(i - 1) + "\n";
            }
            return output;

        } else if (command.startsWith("done")) {
            if (command.length() == 4) {
                return new DukeException("Hold up! Please specify "
                        + "which task is done.").getMessage();
            } else {
                int taskToMark = Integer.parseInt(command.substring(5)) - 1;
                tasks.get(taskToMark).markAsDone();
                output += "Task Accomplished! I've marked this "
                        + "task as done:\n";
                output += tasks.get(taskToMark);
                storage.refresh(tasks);
                return output;
            }

        } else if (command.startsWith("delete")) {
            if (command.length() == 6) {
                return new DukeException("Hold up! Please specify "
                        + "which task to delete.").getMessage();
            } else {
                int taskToDelete = Integer.parseInt(command.substring(7)) - 1;
                output += "Alright! I've removed this task:\n";
                output += tasks.remove(taskToDelete) + "\n";
                output += "Now you have " + tasks.size()
                        + " tasks in your list.";
                storage.refresh(tasks);
                return output;
            }

        } else if (command.startsWith("find")) {
            if (command.length() == 4) {
                return new DukeException("Hold up! Please specify keyword.").getMessage();
            } else {
                output += "Here are the matching tasks in your list:\n";
                String keyword = command.substring(5);
                for (int i = 1; i < tasks.size() + 1; i++) {
                    Task currentTask = tasks.get(i - 1);
                    if (currentTask.toString().contains(keyword)) {
                        output += i + ". " + currentTask + "\n";
                    }
                }
                return output;
            }

        } else {
            if (!command.startsWith("todo") && !command.startsWith("deadline")
                    && !command.startsWith("event")) {
                return new DukeException("Sorry, I'm not sure what "
                        + "you mean by that :(").getMessage();
            } else {

                if (command.startsWith("todo")) {
                    if (command.length() == 4) {
                        return new DukeException("Hold up! The "
                                + "description of todo cannot be empty...").getMessage();
                    } else {
                        output += "Got it! I've added this task:\n";
                        tasks.add(new ToDo(command.substring(5)));
                        output += tasks.get(
                                tasks.size() - 1) + "\n";
                        output += "Now you have " + tasks.size()
                                + " tasks in your list.";
                        storage.append(new ToDo(command.substring(5)));
                        return output;
                    }

                } else if (command.startsWith("deadline")) {
                    if (command.length() == 8) {
                        return new DukeException("Hold up! The "
                                + "description of deadline cannot be empty...").getMessage();
                    } else if (!command.contains("/by")) {
                        return new DukeException("Please specify "
                                + "deadline using: /by (deadline)").getMessage();
                    } else {
                        output += "Got it! I've added this task:\n";
                        int endIndex = command.indexOf("/by") - 1;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                "yyyy-MM-dd HHmm");
                        LocalDateTime dateTime = LocalDateTime.parse(
                                command.substring(endIndex + 5), formatter);
                        tasks.add(new Deadline(command.substring(9, endIndex),
                                dateTime.format(DateTimeFormatter.ofPattern(
                                        "MMM d yyyy HHmm"))));
                        output += tasks.get(tasks.size() - 1) + "\n";
                        output += "Now you have " + tasks.size()
                                + " tasks in your list.";
                        storage.append(new Deadline(command.substring(9, endIndex),
                                dateTime.format(DateTimeFormatter.ofPattern(
                                        "MMM d yyyy HHmm"))));
                        return output;
                    }

                } else {
                    if (command.length() == 5) {
                        return new DukeException("Hold up! The "
                                + "description of event cannot be empty...").getMessage();
                    } else if (!command.contains("/at")) {
                        return new DukeException("Please specify "
                                + "timing using: /at (timing)").getMessage();
                    } else {
                        output += "Got it! I've added this task:\n";
                        int endIndex = command.indexOf("/at") - 1;
                        tasks.add(new Event(command.substring(6, endIndex),
                                command.substring(endIndex + 5)));
                        output += tasks.get(tasks.size() - 1) + "\n";
                        output += "Now you have " + tasks.size()
                                + " tasks in your list.";
                        storage.append(new Event(command.substring(6, endIndex),
                                command.substring(endIndex + 5)));
                        return output;
                    }
                }
            }
        }
    }
}
