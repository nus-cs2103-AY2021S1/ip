package duke;

import java.util.Scanner;

import duke.tasks.Task;

public class Ui {

    /**
     * Shows welcome message upon starting Duke
     * @return welcome message
     */
    public String showWelcomeMessage() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append ("Hello from\n" + logo + "\n");
        sb.append ("Hello! I'm Duke ^_^\n");
        sb.append("What can I do for you??");
        System.out.println (sb);
        return sb.toString();
    }

    /**
     * Shows bye message when user enters bye
     * @return bye message
     */
    public String showByeMessage() {
        String byeMessage = "Bye!! See you again :)";
        System.out.println (byeMessage);
        return byeMessage;
    }
    /**
     * Shows message when a task is added to the task list
     * @param t task added to the list
     * @param number number of tasks in the list
     * @return message shown when a task is added
     */
    public String showAddTaskMessage(Task t, int number) {
        String addTaskMessage = "Got it! I've added this task:\n";
        addTaskMessage += (t.toString() + "\n");
        addTaskMessage += (String.format("Now you have %d tasks in the list \n", number));
        System.out.println (addTaskMessage);
        return addTaskMessage;
    }

    /**
     * Shows message when a task is removed from the task list
     * @param t task removed from list
     * @param number number of tasks in the list
     * @return message shown when a task is removed
     */
    public String showRemoveTaskMessage (Task t, int number) {
        String removeTaskMessage = "Noted. I've removed this task\n";
        removeTaskMessage += (t.toString() + "\n");
        removeTaskMessage += (String.format("Now you have %d tasks in the list \n", number));
        System.out.println (removeTaskMessage);
        return removeTaskMessage;
    }

    /**
     * Shows message when a task is marked as done
     * @param t task that is marked done
     * @return message shown when a task is marked done
     */
    public String showDoneMessage (Task t) {
        String doneMessage = "Nice! I've marked this task as done:\n";
        doneMessage += t;
        System.out.println(doneMessage);
        return doneMessage;
    }

    /**
     * Reads the user input line by line
     * @return user input
     */
    public String readUserInput() {
        Scanner sc = new Scanner (System.in);
        return sc.nextLine();
    }

    /**
     * Prints out all the tasks in the list
     * @param taskList list containing all the tasks
     * @return The list of tasks
     */
    public String showList(TaskList taskList) {
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            list += String.format("%d. %s \n", i + 1, taskList.get(i));
        }
        System.out.println (list);
        return list;
    }
    public void showLoadingError() {
        System.out.println ("Sorry, I could not retrieve your previous tasks \n");
    }

    /**
     * Shows message if user did not specify description of a task
     */
    public void showNoTaskInputException() {
        System.out.println ("Please specify a task description");
    }

    /**
     * Shows message if user did not specify time of a deadline or event
     */
    public void showNoTimeInputException() {
        System.out.println ("Please specify a time");
    }

    /**
     * Shows message if user tries to retrieve a task that does not exist
     */
    public void showNoTaskExistException() {
        System.out.println ("Task does not exist");
    }
}
