package duke;

import java.util.Scanner;

/**
 * Ui class to deal with user's interaction
 */
public class Ui {
    private static final Scanner sc = new Scanner(System.in);
    /**
     * Returns next line of user input
     *
     * @return Next line of user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints message when it operates
     */
    public void showInfo() {
        System.out.println("Hello, I'm DukeQ");
        System.out.println("todo then your instructions e.g. todo read book");
        System.out.println("deadline then your instructions e.g. deadline by 2020-09-01");
        System.out.println("type 'event' followed by the description,");
        System.out.println("then '/at', then timing in yyyy-MM-dd format");
        System.out.println("done followed by the task number " + "marked it as done");
        System.out.println("type list to see the task list");
        System.out.println("type find to search by keywords for tasks");
        System.out.println("type bye to exit");
        System.out.println("type help to get a tutorial");
    }


    /**
     * Returns string containing list of existing tasks
     *
     * @param tasks List of tasks
     * @return String of tasks
     */
    public String printList(TaskList tasks) {
        String tasksString = "";
        if (tasks.size() == 0) {
            tasksString += "List is empty \n";
        } else {
            tasksString += "Tasks in the list: \n";
            for (int i = 0; i < tasks.size(); i++) {
                tasksString += ((i + 1) + ". " + tasks.get(i).toString() + "\n");
            }
        }
        return tasksString;
    }

    /**
     * Prints appropriate message for task depending on action type
     *
     * @param task Task to be printed
     * @param action Action type; determines format of text that is printed
     */
    public String printTask(Task task, ActionType action) {
        String taskDescription;
        switch(action) {
        case MARK_DONE:
            taskDescription = "Task marked complete: \n";
            break;
        case DELETE:
            taskDescription = "Task deleted: \n";
            break;
        default:
            taskDescription = "Added: \n";
        }
        taskDescription += task.toString();
        return taskDescription;
    }

    /**
     * Searches task with keywords and print them.
     *
     * @param tasks
     * @param input
     * @return tasks including keywords' description string.
     */
    public String search(TaskList tasks, String input) {
        int count = 1;
        String result = "Matching tasks: \n";
        for (Task task: tasks.getList()) {
            String taskString = task.toString();
            if (taskString.contains(input)) {
                result += (count + ". " + taskString + "\n");
                count++;
            }
        }
        return result;
    }

    /**
     * Prints total number of tasks
     *
     * @param tasks List of tasks
     * @return String with total number of tasks
     */
    public String printTotalTasks(TaskList tasks) {
        return "Total tasks: " + tasks.size();
    }


    /**
     * Prints goodbye message
     */
    public String bye() {
        return "Bye!!!";
    }
}
