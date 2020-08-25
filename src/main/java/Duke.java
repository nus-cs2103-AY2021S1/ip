import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args)
            throws IndexOutOfBoundsException,
            NumberFormatException {
        
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        Feedbacks.logoMsg();
        Feedbacks.greetingMsg();
        String inputMsg = sc.nextLine();

        // The program will continue looping if the user didn't enter "bye" to terminate the program
        while (!inputMsg.equals("bye")) {
            if (inputMsg.equals("list")) {
                Feedbacks.getAllTasksMsg(tasks);
            } else if (inputMsg.equals("help")) {
                Feedbacks.helpMsg();
            } else if (inputMsg.startsWith("done")) {
                try {
                    int index = Integer.parseInt(inputMsg.split("done ")[1]);
                    Task.doneTask(index, tasks);
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
                    ToDos.addTodoTask(taskTitle, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    // if the user doesn't type the the task description after the keyword "todo"
                    Warnings.invalidToDo();
                }
            } else if (inputMsg.startsWith("deadline")) {
                String taskTitle;
                String deadlineTime;
                try {
                    taskTitle = inputMsg.split("deadline ")[1].split("/by ")[0];
                    deadlineTime = inputMsg.split("deadline ")[1].split("/by ")[1];
                    Deadlines.addDeadlineTask(taskTitle, deadlineTime, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    // if the user doesn't follow the correct format after the keyword "deadline"
                    Warnings.invalidDeadline();
                }
            } else if (inputMsg.startsWith("event")) {
                try {
                    String taskTitle = inputMsg.split("event ")[1].split("/at ")[0];
                    String eventTime = inputMsg.split("event ")[1].split("/at ")[1];
                    Events.addEventTask(taskTitle, eventTime, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    // if the user doesn't follow the correct format after the keyword "event"
                    Warnings.invalidEvent();
                }
            } else if (inputMsg.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(inputMsg.split("delete ")[1]);
                    Task.deleteTask(index, tasks);
                } catch (IndexOutOfBoundsException e) {
                    // if the user doesn't type the index after the keyword "delete"
                    Warnings.invalidDelete(tasks.size());
                } catch (NumberFormatException e) {
                    // if the user doesn't key in a valid index after keyword "delete"
                    Warnings.invalidDelete(tasks.size());
                }

            } else {
                // if the user randomly enter any other commands which are not inside the command list
                Warnings.invalidInput();
            }
            // waiting for user to key in the next request
            inputMsg = sc.nextLine();
        }
        // say bye to the user
        Feedbacks.byeMsg();
    }
}
