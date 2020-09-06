package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class Output {
    public static String addedTaskMessage(Task task, TaskList tasks) {
        return "sure thing! i have added the following task to your list:\n    " +
                task + "\n" + Output.numberOfTasksMessage(tasks.size());
    }


    public static String clearedAllTasksMessage() {
        return "okie! all the tasks in your list have been cleared :-)";
    }


    public static String deletedTaskMessage(Task deletedTask, TaskList tasks) {
        return "of course! i have removed this task: \n    " +
                deletedTask + "\n" +
                Output.numberOfTasksMessage(tasks.size());
    }

    public static String markedTaskAsDoneMessage(Task deletedTask, TaskList tasks) {
        return "yay! i have marked this task as done: \n    " +
                deletedTask + "\n" +
                Output.numberOfTasksMessage(tasks.size());
    }

    public static String welcomeMessage() {
        return "hello! i'm shiro :-)\n" +
                "what can i do for you today?\n" +
                "\n" +
                "more: type 'help' for more information";
    }

    public static String exitMessage() {
        return "byebye! i hope to see you again soon :-)";
    }

    public static String matchingTasksMessage(ArrayList<Task> matchingTasks) {
        String string;
        if (matchingTasks.size() == 0) {
            string = "oh dear :-( there are no tasks matching your search";
        } else {
            string = "got it! here are the tasks matching your search:\n" +
                    Output.tasksAsStringMessage(matchingTasks);
        }
        return string;
    }

    public static String helpMessage() {
        return "available commands are: \n    " +
                "1. todo <todo_description>\n    " +
                "2. deadline <deadline_description> /by <date>\n    " +
                "3. event <event_description> /at <date>\n    " +
                "4. done <task_number>\n    " +
                "5. delete <task number>\n    " +
                "6. list\n    " +
                "7. clear\n    " +
                "8. find <keyword>\n    " +
                "9. bye";
    }

    public static String invalidCommandMessage() {
        return "oops! im sorry, but i do not know what that means :-(";
    }

    public static String listAllTasksMessage(ArrayList<Task> tasks) {
        String string;
        if (tasks.size() == 0) {
            string = "hurray! there are no tasks in your list";
        } else {
            string = "okies! here are the tasks in your list:\n" +
                    Output.tasksAsStringMessage(tasks);
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

    public static String loadingErrorMessage() {
        return "there was an error loading the file";
    }

    public static String tasksAsStringMessage(ArrayList<Task> tasks) {
        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += String.format("%d", i+1) + ". " +
                    tasks.get(i) + "\n";
        }
        return tasksString.trim();
    }
}
