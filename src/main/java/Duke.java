import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke class
 */
public class Duke {

    public static void main(String[] args) throws DukeException {
//        Initialising components
        Parser parser = new Parser();
        Ui printer = new Ui();
        String currentDirectory = System.getProperty("user.dir");
        Storage file = new Storage(currentDirectory + "/data/duke.txt");
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        if (file.getExisted()) {
            taskList = new TaskList(file.getTaskList());
        }
        printer.messagePrint(
                "Hello! I'm Duke\n" +
                "What can I do for you?");

        while (scanner.hasNext()) {
            try {
                String msg = scanner.nextLine();
                if (parser.parse(msg) == Parser.Command.BYE) {
                    printer.messagePrint("Bye. Hope to see you again soon!");
                    break;

                    //          PRINTING LIST
                } else if (parser.parse(msg) == Parser.Command.LIST) {
                    String listMessage = "";
                    for (int i = 0; i < taskList.size(); i++) {
                        listMessage += (i + 1) + ". " + taskList.get(i).toString();
                        // If is not last object, add a new line at the end of the item
                        if (i != taskList.size() - 1) {
                            listMessage += "\n";
                        }
                    }
                    printer.messagePrint(listMessage);

                    //          UPDATING STATUS OF EVENTS
                } else if (parser.parse(msg) == Parser.Command.DONE) {
                    int updateTaskIndex = Integer.valueOf(msg.substring(5, msg.length())) - 1;
                    if (updateTaskIndex >= taskList.size() || updateTaskIndex <= 0) {
                        throw new DukeException(DukeExceptionType.TASK_NOT_FOUND);
                    }
                    Task taskToUpdate = taskList.get(updateTaskIndex);
                    taskToUpdate.updateStatus(true);
                    taskList.set(updateTaskIndex, taskToUpdate);
                    String completedMessage = "Nice! I've marked this task as done:\n" + "  " + taskList.get(updateTaskIndex).toString();
                    printer.messagePrint(completedMessage);
                    file.write(taskList.getList());

                } else if (parser.parse(msg) == Parser.Command.DELETE) {
                    int updateTaskIndex = Integer.valueOf(msg.substring(7, msg.length())) - 1;
                    if (updateTaskIndex >= taskList.size() || updateTaskIndex <= 0) {
                        throw new DukeException(DukeExceptionType.TASK_NOT_FOUND);
                    }
                    Task taskToUpdate = taskList.get(updateTaskIndex);
                    taskList.remove(updateTaskIndex);
                    String deletedMessage = "Noted. I've removed this task:\n" +
                           "  " + taskToUpdate.toString() + "\n" +
                                "Now you have " + taskList.size() + " tasks in the list.";
                    printer.messagePrint(deletedMessage);
                    file.write(taskList.getList());

                    //          CREATING NEW TASKS
                } else {

                    //              DEADLINES
                    Task newTask;
                    if (parser.parse(msg) == Parser.Command.DEADLINE) {
                        int byIndex = msg.indexOf("/by");
                        String task = msg.substring(9, byIndex); //Number 9 = starting index of deadline string.
                        String dateString = msg.substring(byIndex + 4, msg.length());
                        try {
                            LocalDate date = LocalDate.parse(dateString);
                            newTask = new Deadline(task, date);
                        } catch (DateTimeParseException e) {
                            newTask = new Deadline(task, dateString);
                        }

                        //              EVENTS
                    } else if (parser.parse(msg) == Parser.Command.EVENT) {
                        int atIndex = msg.indexOf("/at");
                        String task = msg.substring(6, atIndex); //Number 6 = starting index of event string.
                        String dateString = msg.substring(atIndex + 4, msg.length());
                        try {
                            LocalDate date = LocalDate.parse(dateString);
                            newTask = new Event(task, date);
                        } catch (DateTimeParseException e) {
                            newTask = new Event(task, dateString);
                        }

                        //              TODOS
                    } else if (parser.parse(msg) == Parser.Command.TODO) {
                        String task = msg.substring(5, msg.length()); //Number 5 = starting index of todo string.
                        newTask = new ToDo(task);

//                        Checks for empty TODO
                    } else if (parser.parse(msg) == Parser.Command.EMPTY_TASK_TODO) {
                        throw new DukeException(DukeExceptionType.EMPTY_TASK_TODO);

//                        Checks for empty TASK for event/deadline
                    } else if (parser.parse(msg) == Parser.Command.EMPTY_TASK_EVENT_DEADLINE) {
                        throw new DukeException(DukeExceptionType.EMPTY_TASK_EVENT_DEADLINE);

//                        Checks for empty DATE for event/deadline
                    } else if (parser.parse(msg) == Parser.Command.EMPTY_DATE) {
                        throw new DukeException(DukeExceptionType.EMPTY_DATE);
                    }

                    else {
                        newTask = null;
                        throw new DukeException(DukeExceptionType.INVALID_INPUT);
                    }

                    taskList = taskList.add(newTask);
                    String newTaskMsg = String.format(
                            "Got it. I've added this task:\n" +
                                    "  %s\n" +
                                    "Now you have %d tasks in the list.", newTask.toString(), taskList.size());
                    printer.messagePrint(newTaskMsg);
                    file.write(taskList.getList());
                }
            } catch (DukeException e) {
                printer.messagePrint(e.toString());
                continue;
            }
        }
    }
}
