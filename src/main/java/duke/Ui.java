package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Ui class to deal with user interaction
 */
public class Ui {

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        System.out.println("    Hello, I'm Duke");
        System.out.println("    I can help you keep track of all your tasks! ☆*:.｡.o(≧▽≦)o.｡.:*☆");
        System.out.println("    How to add tasks to the list:");
        System.out.println("    ToDo - type 'todo' followed by the description");
        System.out.println("    Deadline - type 'deadline' followed by the description,");
        System.out.println("    then '/by', then due date in yyyy-MM-dd format");
        System.out.println("    Event - type 'event' followed by the description,");
        System.out.println("    then '/at', then timing in yyyy-MM-dd format");
        System.out.println("    Type 'done' followed by the task number " +
                "and I'll mark it as done");
        System.out.println("    Type 'list' to see the list");
        System.out.println("    Type 'find' followed by keyword to search for tasks");
        System.out.println("    Type 'bye' to exit");
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
            tasksString += "Items in list: \n";
            for (int i = 0; i < tasks.size(); i++) {
                tasksString += ((i + 1) + ". " + tasks.get(i).toString() + "\n");
            }
        }
        return tasksString;
    }

    /**
     * Returns appropriate message for task depending on action type
     *
     * @param task Task to be printed
     * @param action Action type; determines format of text that is printed
     * @return String containing task info
     */
    public String printTask(Task task, ActionType action) {
        String taskString = "";
        switch(action){
        case MARK_DONE:
            taskString += "Task marked complete: \n";
            break;
        case DELETE:
            taskString += "Task deleted: \n";
            break;
        default:
            taskString += "Added: \n";
        }
        taskString += task.toString();
        return taskString;
    }

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
    public String goodbye() {
        return "See you again soon (hopefully)! :>";
    }
}
