package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class Output {
    public static String addedTaskMessage(Task task, TaskList tasks) {
        return "sure thing! i have added the following task to your list:\n    " +
                task + "\n" + tasks.numberOfTasks();
    }


    public static String clearedAllTasksMessage() {
        return "okie! all the tasks in your list have been cleared :-)";
    }


    public static String deletedTaskMessage(Task deletedTask, TaskList tasks) {
        return "of course! i have removed this task: \n    " +
                deletedTask + "\n" +
                tasks.numberOfTasks();
    }

    public static String markedTaskAsDoneMessage(Task deletedTask, TaskList tasks) {
        return "yay! i have marked this task as done: \n    " +
                deletedTask + "\n" +
                tasks.numberOfTasks();
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
        StringBuilder sb = new StringBuilder();

        if (matchingTasks.size() == 0) {
            sb.append("oh dear :-( there are no tasks matching the given search");
        } else {
            sb.append("got it! here are the matching tasks in your list:\n");

            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append(i+1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
        }
        return sb.toString().trim();
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
        StringBuilder sb = new StringBuilder();

        if (tasks.size() == 0) {
            sb.append("hurray! there are no tasks in your list");
        } else {
            sb.append("okies! here are the tasks in your list:\n");

            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i+1).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        return sb.toString().trim();
    }

    public static String loadingErrorMessage() {
        return "There was an error loading the file";
    }
}
