package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Takes in user inputs and make sense of them.
 */
public class Parser {
    /**
     * Parses the inputs and calls the corresponding execution.
     *
     * @param tasklist The overall user's task list.
     */
    public static void parseUserInput(TaskList tasklist) {
        ArrayList<Task> tasks = tasklist.getTaskList();
        Scanner sc = new Scanner(System.in);
        String inputMsg = sc.nextLine();

        // The program will continue looping if the user didn't enter "bye" to terminate the program
        while (!inputMsg.equals("bye")) {
            if (inputMsg.equals("list")) {
                Ui.getAllTasksMsg(tasks);
            } else if (inputMsg.equals("help")) {
                Ui.helpMsg();
            } else if (inputMsg.startsWith("done")) {
                try {
                    int index = Integer.parseInt(inputMsg.split("done ")[1]);
                    TaskList.doneTask(index, tasks);
                } catch (IndexOutOfBoundsException e) {
                    // if the user doesn't type the index after the keyword "done"
                    Warnings.invalidDoneTaskIndex(tasks.size());
                } catch (NumberFormatException e) {
                    // if the user doesn't key in a valid index after keyword "done"
                    Warnings.invalidDoneTaskIndex(tasks.size());
                }
            } else if (inputMsg.startsWith("todo")) {
                String taskTitle;
                try {
                    taskTitle = inputMsg.split("todo ")[1];
                    TaskList.addNewTodoTask(taskTitle, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    // if the user doesn't type the the task description after the keyword "todo"
                    Warnings.invalidToDo();
                }
            } else if (inputMsg.startsWith("deadline")) {
                String taskTitle;
                String deadlineTime;
                try {
                    taskTitle = inputMsg.split("deadline ")[1].split(" /by ")[0];
                    deadlineTime = inputMsg.split("deadline ")[1].split(" /by ")[1];
                    TaskList.addNewDeadlineTask(taskTitle, deadlineTime, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    // if the user doesn't follow the correct format after the keyword "deadline"
                    Warnings.invalidDeadline();
                }
            } else if (inputMsg.startsWith("event")) {
                try {
                    String taskTitle = inputMsg.split("event ")[1].split(" /at ")[0];
                    String eventTime = inputMsg.split("event ")[1].split(" /at ")[1];
                    TaskList.addNewEventTask(taskTitle, eventTime, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    // if the user doesn't follow the correct format after the keyword "event"
                    Warnings.invalidEvent();
                }
            } else if (inputMsg.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(inputMsg.split("delete ")[1]);
                    TaskList.deleteTask(index, tasks);
                } catch (IndexOutOfBoundsException e) {
                    // if the user doesn't type the index after the keyword "delete"
                    Warnings.invalidDelete(tasks.size());
                } catch (NumberFormatException e) {
                    // if the user doesn't key in a valid index after keyword "delete"
                    Warnings.invalidDelete(tasks.size());
                }
            } else if (inputMsg.startsWith("find")) {
                String keyword = inputMsg.split("find ")[1];
                TaskList.findTask(keyword, tasks);
            } else {
                // if the user randomly enter any other commands which are not inside the command list
                Warnings.invalidInput();
            }
            // waiting for user to key in the next request
            inputMsg = sc.nextLine();
        }
        // say bye to the user
        Ui.byeMsg();
    }
}
