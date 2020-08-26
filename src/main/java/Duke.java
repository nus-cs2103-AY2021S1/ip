import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void messagePrint(String msg) {
//      Add indentation for new lines
        msg = msg.replace("\n", "\n    ");
        msg = ("    ____________________________________________________________\n"
                + "    " + msg + "\n"
                + "    ____________________________________________________________\n");
        System.out.printf(msg);
    }

    public static void main(String[] args) throws DukeException {
        String currentDirectory = System.getProperty("user.dir");
        DukeFile file = new DukeFile(currentDirectory + "/data/duke.txt");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        if (file.getExisted()) {
            taskList = file.getTaskList();
        }
        messagePrint(
                "Hello! I'm Duke\n" +
                "What can I do for you?");

        while (scanner.hasNext()) {
            try {
                String msg = scanner.nextLine();
                if (msg.equals("bye")) {
                    messagePrint("Bye. Hope to see you again soon!");
                    break;

                    //          PRINTING LIST
                } else if (msg.equals("list")) {
                    String listMessage = "";
                    for (int i = 0; i < taskList.size(); i++) {
                        listMessage += (i + 1) + ". " + taskList.get(i).toString();
                        // If is not last object, add a new line at the end of the item
                        if (i != taskList.size() - 1) {
                            listMessage += "\n";
                        }
                    }
                    messagePrint(listMessage);

                    //          UPDATING STATUS OF EVENTS
                } else if (msg.matches("^done \\d+$")) {
                    int updateTaskIndex = Integer.valueOf(msg.substring(5, msg.length())) - 1;
                    if (updateTaskIndex >= taskList.size() || updateTaskIndex <= 0) {
                        throw new DukeException(DukeExceptionType.TASK_NOT_FOUND);
                    }
                    Task taskToUpdate = taskList.get(updateTaskIndex);
                    taskToUpdate.updateStatus(true);
                    taskList.set(updateTaskIndex, taskToUpdate);
                    String completedMessage = "Nice! I've marked this task as done:\n" + "  " + taskList.get(updateTaskIndex).toString();
                    messagePrint(completedMessage);
                    file.write(taskList);

                } else if (msg.matches("^delete \\d+$")) {
                    int updateTaskIndex = Integer.valueOf(msg.substring(7, msg.length())) - 1;
                    if (updateTaskIndex >= taskList.size() || updateTaskIndex <= 0) {
                        throw new DukeException(DukeExceptionType.TASK_NOT_FOUND);
                    }
                    Task taskToUpdate = taskList.get(updateTaskIndex);
                    taskList.remove(updateTaskIndex);
                    String deletedMessage = "Noted. I've removed this task:\n" +
                           "  " + taskToUpdate.toString() + "\n" +
                                "Now you have " + taskList.size() + " tasks in the list.";
                    messagePrint(deletedMessage);
                    file.write(taskList);

                    //          CREATING NEW TASKS
                } else {

                    //              DEADLINES
                    Task newTask;
                    if (msg.matches("^deadline \\S.* /by \\S.*$")) {
                        int byIndex = msg.indexOf("/by");
                        String task = msg.substring(9, byIndex); //Number 9 = starting index of deadline string.
                        String date = msg.substring(byIndex + 4, msg.length());
                        newTask = new Deadline(task, date);

                    //              EVENTS
                    } else if (msg.matches("^event \\S.* /at \\S.*$")) {
                        int atIndex = msg.indexOf("/at");
                        String task = msg.substring(6, atIndex); //Number 6 = starting index of event string.
                        String date = msg.substring(atIndex + 4, msg.length());
                        newTask = new Event(task, date);

                        //              TODOS
                    } else if (msg.matches("^todo \\S.*$")) {
                        String task = msg.substring(5, msg.length()); //Number 5 = starting index of todo string.
                        newTask = new ToDo(task);

//                        Checks for empty TODO
                    } else if (msg.matches("^todo\\s*$")) {
                        throw new DukeException(DukeExceptionType.EMPTY_TASK_TODO);

//                        Checks for empty TASK for event/deadline
                    } else if (
                            msg.matches("^event\\s/at.*$") ||
                                    msg.matches("^deadline\\s/by.*$") ||
                                    msg.matches("^event\\s*$") ||
                                    msg.matches("^deadline\\s*$"))
                    {
                        throw new DukeException(DukeExceptionType.EMPTY_TASK_EVENT_DEADLINE);

//                        Checks for empty DATE for event/deadline
                    } else if (msg.matches("^event .* /at\\s*$") ||
                            msg.matches("^deadline .* /by\\s*") ||
                            msg.matches("^event.*") ||
                            msg.matches("^deadline.*"))
                    {
                        throw new DukeException(DukeExceptionType.EMPTY_DATE);
                    }

                    else {
                        newTask = null;
                        throw new DukeException(DukeExceptionType.INVALID_INPUT);
                    }

                    taskList.add(newTask);
                    String newTaskMsg = String.format(
                            "Got it. I've added this task:\n" +
                                    "  %s\n" +
                                    "Now you have %d tasks in the list.", newTask.toString(), taskList.size());
                    messagePrint(newTaskMsg);
                    file.write(taskList);
                }
            } catch (DukeException e) {
                messagePrint(e.toString());
                continue;
            }
        }
    }
}
