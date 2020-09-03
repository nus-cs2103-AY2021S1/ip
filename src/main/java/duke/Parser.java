package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {

    private static Ui ui;

    /**
     * Constructor which takes in a UI and initialises its ui field member.
     *
     * @param ui a UI for the Duke object
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Decides on the relevant actions to execute based on the input of the user.
     *
     * @param taskList TaskList object that contains a list of tasks
     * @param filePath the path location of the load or save file
     * @throws DukeException thrown if the Duke program does not recognise user input
     * @throws IOException produced by failed or interrupted I/O operations
     */
    public static void interact(TaskList taskList, String filePath) throws DukeException, IOException {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                ui.exit();
                break;
            } else if (line.equals("list")) {
                ui.list(taskList);
            } else {
                String[] arrOfStr = line.split(" ", 0);
                String identifier = arrOfStr[0];
                if (identifier.equals("done")) {
                    // mark as done
                    int index = Integer.parseInt(arrOfStr[1]) - 1;
                    ui.markDone(taskList, index);
                    Storage.updateFile(filePath, taskList);
                } else if (identifier.equals("delete")) {
                    // delete
                    int index = Integer.parseInt(arrOfStr[1]) - 1;
                    taskList.delete(index);
                } else if (identifier.equals("find")) {
                    // find
                    String textToMatch = arrOfStr[1];
                    ui.findMatching(taskList, textToMatch);
                } else {
                    // add to list
                    if ((identifier.equals("todo") || identifier.equals("deadline")
                            || identifier.equals("event")) && arrOfStr.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a " + identifier + " cannot be empty.");
                    } else {
                        Task task;
                        if (identifier.equals("todo")) {
                            String[] newArrOfStr = line.split(" ", 2);
                            task = new Todo(newArrOfStr[1]);
                        } else if (identifier.equals("deadline")) {
                            String[] firstSplit = line.split(" /by ", 2);
                            String[] secondSplit = firstSplit[0].split(" ", 2);
                            String description = secondSplit[1];
                            String date = firstSplit[1];
                            String[] dateSplit = date.split(" ", 0);

                            if (dateSplit.length > 1) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                                LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
                                task = new Deadline(description, localDateTime);
                            } else {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                                LocalDate localDate = LocalDate.parse(date, formatter);
                                task = new Deadline(description, localDate);
                            }
                        } else if (identifier.equals("event")) {
                            String[] firstSplit = line.split(" /at ", 2);
                            String by = firstSplit[1];
                            String[] secondSplit = firstSplit[0].split(" ", 2);
                            String description = secondSplit[1];
                            task = new Event(description, by);
                        } else {
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                        taskList.add(task);
                    }
                }
            }
        }
    }

    /**
     * Alternative interact method for GUI.
     * Decides on the relevant actions to execute based on the input of the user.
     *
     * @param input user input to be parsed
     * @param taskList TaskList object that contains a list of tasks
     * @param filePath the path location of the load or save file
     * @return a String to be displayed in a DialogBox
     * @throws IOException produced by failed or interrupted I/O operations
     */
    public static String interact(String input, TaskList taskList, String filePath) throws IOException {
        if (input.equals("bye")) {
            return ("Bye. Hope to see you again soon!");
        } else if (input.equals("list")) {
            String output = "";
            if (taskList.getTasks().size() == 0) {
                output += "You have no tasks in your list!";
            } else {
                output += "Here are the tasks in your list:\n";
                for (Task task : taskList.getTasks()) {
                    int index = taskList.getTasks().indexOf(task) + 1;
                    output += (index + "." + task + "\n");
                }
            }
            return output;
        } else {
            String[] arrOfStr = input.split(" ", 0);
            String identifier = arrOfStr[0];
            if (identifier.equals("done")) {
                // mark as done
                int index = Integer.parseInt(arrOfStr[1]) - 1;
                Task oldTask = taskList.getTasks().get(index);
                Task newTask = oldTask.markAsDone();
                taskList.replace(oldTask, newTask);
                Storage.updateFile(filePath, taskList);
                String output = "Nice! I've marked this task as done:\n";
                output += (newTask.getStatusIcon() + " " + newTask.description);
                return output;
            } else if (identifier.equals("delete")) {
                // delete
                int index = Integer.parseInt(arrOfStr[1]) - 1;
                Task task = taskList.getTasks().get(index);
                taskList.delete(index);
                List<Task> tasks = taskList.getTasks();
                String output = ("Noted. I've removed this task:\n");
                output += (task + "\n");
                output += ("Now you have " + (tasks.size() != 1
                        ? tasks.size() + " tasks in the list."
                        : tasks.size() + " task in the list."));
                return output;
            } else if (identifier.equals("find")) {
                // find
                String textToMatch = arrOfStr[1];
                String output = ("Here are the matching tasks in your list:\n");
                int index = 1;
                for (Task task : taskList.getTasks()) {
                    String description = task.getDescription();
                    if (description.contains(textToMatch)) {
                        output += (index + "." + task + "\n");
                        index++;
                    }
                }
                return output;
            } else {
                // add to list
                if ((identifier.equals("todo") || identifier.equals("deadline")
                        || identifier.equals("event")) && arrOfStr.length < 2) {
                    return ("☹ OOPS!!! The description of a " + identifier + " cannot be empty.");
                } else {
                    Task task;
                    if (identifier.equals("todo")) {
                        String[] newArrOfStr = input.split(" ", 2);
                        task = new Todo(newArrOfStr[1]);
                    } else if (identifier.equals("deadline")) {
                        String[] firstSplit = input.split(" /by ", 2);
                        String[] secondSplit = firstSplit[0].split(" ", 2);
                        String description = secondSplit[1];
                        String date = firstSplit[1];
                        String[] dateSplit = date.split(" ", 0);

                        if (dateSplit.length > 1) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                            LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
                            task = new Deadline(description, localDateTime);
                        } else {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                            LocalDate localDate = LocalDate.parse(date, formatter);
                            task = new Deadline(description, localDate);
                        }
                    } else if (identifier.equals("event")) {
                        String[] firstSplit = input.split(" /at ", 2);
                        String by = firstSplit[1];
                        String[] secondSplit = firstSplit[0].split(" ", 2);
                        String description = secondSplit[1];
                        task = new Event(description, by);
                    } else {
                        return ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    taskList.add(task);
                    List<Task> tasks = taskList.getTasks();
                    String output = ("Got it. I've added this task:\n");
                    output += (task + "\n");
                    output += ("Now you have " + (tasks.size() != 1
                            ? tasks.size() + " tasks in the list."
                            : tasks.size() + " task in the list."));
                    return output;
                }
            }
        }
    }
}
