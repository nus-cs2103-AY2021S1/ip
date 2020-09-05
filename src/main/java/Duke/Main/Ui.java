package duke.main;

import java.util.List;

import duke.errors.InvalidCommandException;
import duke.tasks.Task;

/**
 * The type Ui.
 */
public class Ui {
    /**
     * Show loading error.
     */
    public void showLoadingError() {
        System.out.println("☹ OOPS!!! An error occurred while loading from the .txt file");
    }

    /**
     * Gets the provided List
     *
     * @param lst List of Tasks
     * @return the list
     */
    public static String getList(List<Task> lst) {
        String s = "";
        for (int i = 1; i <= lst.size(); i++) {
            Task item = lst.get(i - 1);
            s += String.format("%d.%s", i, item);
            s += (i == lst.size()) ? "" : "\n";
        }
        return s;
    }

    /**
     * Parses String array into String
     *
     * @param strArr array of String
     * @return parsed String
     */
    public String textParser(String[] strArr) {
        String comText = "";
        for (int i = 1; i < strArr.length; i++) {
            comText += strArr[i] + " ";
        }
        return comText.trim();
    }

    /**
     * Takes the input from user, runs the relevant command
     *
     * @param sentence user input
     * @param tasks tasks handler to interact with database
     * @return output to acknowledge user's command
     */
    public String commandParser(String sentence, TaskList tasks) {
        String[] arr = sentence.split("\\s+");
        String command = arr[0];
        String comText = textParser(arr);

        String returnStr;
        try {
            switch (command) {
            case "todo":
                returnStr = Command.todoCommand(arr, comText, tasks);
                break;
            case "deadline":
                returnStr = Command.deadlineCommand(arr, comText, tasks);
                break;
            case "event":
                returnStr = Command.eventCommand(arr, comText, tasks);
                break;
            case "find":
                returnStr = Command.findCommand(comText, tasks);
                break;
            case "list":
                returnStr = Ui.getList(tasks.getList());
                break;
            case "done":
                returnStr = Command.doneCommand(arr, comText, tasks);
                break;
            case "delete":
                returnStr = Command.deleteCommand(arr, comText, tasks);
                break;
            case "bye":
                returnStr = "Bye. Hope to see you again soon!";
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (Exception e) {
            returnStr =  e.getMessage();
        }

        return returnStr;
    }
}
