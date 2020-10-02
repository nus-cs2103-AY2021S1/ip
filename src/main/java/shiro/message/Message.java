package shiro.message;

import shiro.task.Task;
import shiro.task.TaskList;

import java.util.ArrayList;

/**
 * a class to store all the messages output by Shiro
 */
public class Message {

    /**
     * returns a message to indicate that a task has been successfully added to the task list
     * @param task the task that was added to the task list
     * @param taskList the task list that the task was added to
     * @return the message indicating that the task was successfully added
     */
    public static String addedTaskMessage(Task task, TaskList taskList) {
        return "sure thing! i have added the following task to your list:\n    " +
                task + "\n" + Message.numberOfTasksMessage(taskList.size());
    }


    /**
     * returns a message to indicate that all the tasks in the list have been cleared
     * @return message to indicate that all the tasks in the list have been cleared
     */
    public static String clearedAllTasksMessage() {
        return "okie! all the tasks in your list have been cleared :-)";
    }

    /**
     * returns a message to indicate that the task has been deleted from the task list
     * @param deletedTask the task that was deleted
     * @param taskList the task list that the task was deleted from
     * @return the message indicating that the task was successfully deleted
     */
    public static String deletedTaskMessage(Task deletedTask, TaskList taskList) {
        return "of course! i have deleted this task from your list: \n    " +
                deletedTask + "\n" +
                Message.numberOfTasksMessage(taskList.size());
    }

    /**
     * returns a message to indicate that the task was successfully marked as done
     * @param doneTask the task that was marked as done
     * @param taskList the task list that the task belongs to
     * @return the message indicating that the task was successfully marked as done
     */
    public static String markedTaskAsDoneMessage(Task doneTask, TaskList taskList) {
        return "yay! i have marked this task as done: \n    " +
                doneTask + "\n" +
                Message.numberOfTasksMessage(taskList.size());
    }

    /**
     * returns a string representation of the welcome message
     * @return string representation of the welcome message
     */
    public static String welcomeMessage() {
        return "hello! i'm shiro :-)\n" +
                "what can i do for you today?\n" +
                "\n" +
                "more: type 'help' for more information";
    }

    /**
     * returns a string representation of the exit message
     * @return string representation of the exit message
     */
    public static String exitMessage() {
        return "byebye! i hope to see you again soon :-)";
    }

    /**
     * returns a string representation of the matching tasks
     * @param matchingTasks the list of matching tasks
     * @return string representation of the matching tasks
     */
    public static String matchingTasksMessage(ArrayList<Task> matchingTasks) {
        String string;
        if (matchingTasks.size() == 0) {
            string = "oh dear :-( there are no tasks matching your search";
        } else {
            string = "got it! here are the tasks matching your search:\n" +
                    Message.tasksAsStringMessage(matchingTasks);
        }
        return string;
    }

    /**
     * returns a string representation of the tasks on the given date
     * @param tasks the list of tasks on a given date
     * @return string representation of the tasks on the given date
     */
    public static String viewTasksOnDateMessage(ArrayList<Task> tasks) {
        String string;
        if (tasks.size() == 0) {
            string = "yay! there are no tasks on your given date";
        } else {
            string = "okies! here are the tasks on your given date:\n" +
                    Message.tasksAsStringMessage(tasks);
        }
        return string;
    }

    /**
     * returns a string with the available commands
     * @return string with the available commands
     */
    public static String helpMessage() {
        return "available commands are: \n  " +
                "1. list\n  " +
                "2. todo <description>\n  " +
                "3. deadline <description> /by <date>\n  " +
                "4. event <description> /at <date>\n  " +
                "5. done <task_number>\n  " +
                "6. delete <task_number>\n  " +
                "7. clear\n  " +
                "8. find <keyword(s)>\n  " +
                "9. view <date>\n " +
                "10. bye";
    }

    /**
     * returns a message indicating that the command was invalid
     * @return message indicating that the command was invalid
     */
    public static String invalidCommandMessage() {
        return "oops! im sorry, but i do not know what that means :-(";
    }

    /**
     * returns a string representation of all the tasks
     * @param tasks the list of tasks
     * @return string representation of all the tasks
     */
    public static String listAllTasksMessage(ArrayList<Task> tasks) {
        String string;
        if (tasks.size() == 0) {
            string = "hurray! there are no tasks in your list";
        } else {
            string = "okies! here are the tasks in your list:\n" +
                    Message.tasksAsStringMessage(tasks);
        }
        return string;
    }

    /**
     * outputs a string representation of the number of tasks that the array list contains
     * @return a string describing the number of tasks that the array list contains
     */
    public static String numberOfTasksMessage(int n) {
        return "you have [" + n + "] task(s) in your list";
    }

    /**
     * outputs a string indicating that there was an error loading the file
     * @return string indicating that there was an error loading the file
     */
    public static String loadingErrorMessage() {
        return "there was an error loading the file";
    }

    private static String tasksAsStringMessage(ArrayList<Task> tasks) {
        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += String.format("%d", i+1) + ". " +
                    tasks.get(i) + "\n";
        }
        return tasksString.trim();
    }
}
