import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        Feedbacks.logoMsg();
        Feedbacks.greetingMsg();
        String inputMsg = sc.nextLine();

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
                    Warnings.invalidDoneTaskIndex(tasks.size());
                } catch (NumberFormatException e) {
                    Warnings.invalidDoneTaskIndex(tasks.size());
                }
            } else if (inputMsg.startsWith("todo")) {
                String taskTitle;
                try {
                    taskTitle = inputMsg.split("todo ")[1];
                    ToDos.addTodoTask(taskTitle, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Warnings.invalidToDo();
                }
            } else if (inputMsg.startsWith("deadline")) {
                String taskTitle;
                String deadlineTime;
                try {
                    taskTitle = inputMsg.split("deadline ")[1].split("/by")[0];
                    deadlineTime = inputMsg.split("deadline ")[1].split("/by")[1];
                    Deadlines.addDeadlineTask(taskTitle, deadlineTime, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Warnings.invalidDeadline();
                }
            } else if (inputMsg.startsWith("event")) {
                try {
                    String taskTitle = inputMsg.split("event ")[1].split("/at")[0];
                    String eventTime = inputMsg.split("event ")[1].split("/at")[1];
                    Events.addEventTask(taskTitle, eventTime, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Warnings.invalidEvent();
                }
            } else if (inputMsg.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(inputMsg.split("delete ")[1]);
                    Task.deleteTask(index, tasks);
                } catch (IndexOutOfBoundsException e) {
                    Warnings.invalidDelete(tasks.size());
                } catch (NumberFormatException e) {
                    Warnings.invalidDelete(tasks.size());
                }

            } else {
                Warnings.invalidInput();
            }
            inputMsg = sc.nextLine();
        }
        Feedbacks.byeMsg();
    }
}
