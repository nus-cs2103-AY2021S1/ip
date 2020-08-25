import task.Deadline;
import task.Event;
import task.ToDo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Ui {
    public static final String LINE = "_______________________________________\n";

    public Ui() {

    }

    public static void handleList() {
        String output = "";
        for (int i = 1; i <= Duke.taskList.size(); i++) {
            output = output + i + ". " + Duke.taskList.get(i - 1) + "\n";
        }
        System.out.println(LINE + "Here are the tasks in your list: \n" + output + LINE);
    }

    public static void handleFilter(String date) throws DukeException {
        try {
            String output = "";
            String[] dateSplit = date.split("/", 3);
            String reformatedDate = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];
            LocalDate filterDate = LocalDate.parse(reformatedDate);
            for (int i = 1; i <= Duke.taskList.size(); i++) {
                if (Duke.taskList.get(i - 1).isDate(filterDate)) {
                    output = output + i + ". " + Duke.taskList.get(i - 1) + "\n";
                }
            }
            System.out.println(LINE + "Here are your task due on "
                    + filterDate.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ": \n" + output + LINE);
        } catch (DateTimeParseException e) {
            throw new DukeException(LINE + "Invalid input! Please enter a valid date! \n" + LINE);
        }
    }

    public static void handleDone(String taskIdString) throws DukeException {
        int taskId = Integer.parseInt(taskIdString);
        if (taskId <= 0 || taskId > Duke.taskList.size()) {
            throw new DukeException(LINE + "Invalid input! That task does not exist! \n" + LINE);
        } else {
            Duke.taskList.get(taskId - 1).setCompleted();
            System.out.println(LINE + "Nice! I've marked this task as done: \n"
                    + Duke.taskList.get(taskId - 1) + "\n" + LINE);
        }
    }

    public static void handleDelete(String taskIdString) {
        int taskId = Integer.parseInt(taskIdString);
        if (taskId <=0 || taskId > Duke.taskList.size()) {
            System.out.println(LINE + "Invalid input! That task does not exist! \n" + LINE);
        } else {
            int new_size = Duke.taskList.size() - 1;
            System.out.println(LINE + "Noted! I've deleted this task: \n"
                    + Duke.taskList.get(taskId - 1) + "\n"
                    + "Now you have " + new_size + " tasks in the list."
                    + "\n" + LINE);
            Duke.taskList.remove(taskId - 1);
        }
    }

    public static void handleTodo(String todoDescription) throws IOException {
        ToDo newToDo = new ToDo(todoDescription, false);
        Duke.taskList.add(newToDo);
        String output = LINE + "Got it. I've added this task: \n"
                + Duke.taskList.get(Duke.taskList.size() - 1) + "\n"
                + "Now you have " + Duke.taskList.size() + " tasks in the list."
                + "\n" + LINE;
        System.out.println(output);
    }

    public static void handleDeadline(String deadlineDetails) throws IOException {
        String[] details = deadlineDetails.split(" /by ", 2);
        Deadline newDeadline = new Deadline(details[0], details[1], false);
        Duke.taskList.add(newDeadline);
        String output = LINE + "Got it. I've added this task: \n"
                + Duke.taskList.get(Duke.taskList.size() - 1) + "\n"
                + "Now you have " + Duke.taskList.size() + " tasks in the list."
                + "\n" + LINE;
        System.out.println(output);
    }

    public static void handleEvent(String eventDetails) throws IOException {
        String[] details = eventDetails.split(" /at ", 2);
        Event newEvent = new Event(details[0], details[1], false);
        Duke.taskList.add(newEvent);
        String output = LINE + "Got it. I've added this task: \n"
                + Duke.taskList.get(Duke.taskList.size() - 1) + "\n"
                + "Now you have " + Duke.taskList.size() + " tasks in the list."
                + "\n" + LINE;
        System.out.println(output);
    }
}
