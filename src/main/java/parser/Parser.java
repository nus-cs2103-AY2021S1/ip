package parser;

import java.util.List;
import java.util.Scanner;

import exception.DukeException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import storage.Storage;
import task.TaskDescription;
import task.TaskList;
import task.tasks.Task;
import ui.UI;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Takes in user input and executes further actions depending on the command word.
     * Command words are list, find, todo, deadline, event, list, done, delete.
     */
    public static void run() {
        Scanner sc = new Scanner(System.in);
        String userCommand;

        // Load duke.txt into task list
        List<Task> tasks = Storage.loadTextIntoTaskList();
        TaskList tasklist = new TaskList(tasks);

        while (sc.hasNext()) {
            userCommand = sc.nextLine();
            if (userCommand.equals("bye")) {
                break;
            } else if (userCommand.equals("list")) {
                // Show list of tasks
                TaskDescription.showAllTask(tasklist.getTasks());
            } else if (userCommand.contains("done")) {
                // e.g done 1
                tasklist.markTaskDone(userCommand);
            } else if (userCommand.contains("todo")
                    || userCommand.contains("deadline")
                    || userCommand.contains("event")) {
                // Add Task, e.g todo work
                tasklist.addTask(userCommand);
            } else if (userCommand.contains("delete")) {
                tasklist.deleteTask(userCommand);
            } else if (userCommand.contains("find")) {
                tasklist.findTask(userCommand);
            } else {
                // If a task is specified as a Task but not a Deadline / To Do / Event, throw an error
                DukeException.genericTask();
            }
        }
    }

    /**
     * Takes in user input and executes further actions on JavaFX GUI depending on the command word.
     * Command words are list, find, todo, deadline, event, list, done, delete.
     *
     * @param userCommand list, find, todo, deadline, event, list, done, delete.
     * @return Kim Jong Duke's response.
     */
    public static String runForJavaFx(String userCommand) {
        // Load duke.txt into task list
        List<Task> tasks = Storage.loadTextIntoTaskList();
        TaskList tasklist = new TaskList(tasks);
        if (userCommand.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            return UI.farewellForJavaFx();
        } else if (userCommand.equals("list")) {
            // Show list of tasks
            return TaskDescription.showAllTask(tasklist.getTasks());
        } else if (userCommand.contains("done")) {
            // e.g done 1
            tasklist.markTaskDone(userCommand);
        } else if (userCommand.contains("todo")
                || userCommand.contains("deadline")
                || userCommand.contains("event")) {
            // Add Task, e.g todo work
            return tasklist.addTask(userCommand);
        } else if (userCommand.contains("delete")) {
            return tasklist.deleteTask(userCommand);
        } else if (userCommand.contains("find")) {
            return tasklist.findTask(userCommand);
        } else {
            // If a task is specified as a Task but not a Deadline / To Do / Event, throw an error
            return DukeException.genericTask();
        }
        return "";
    }
}
