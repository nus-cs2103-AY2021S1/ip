package duke;

import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main program for Duke.
 */
public class Duke {
    TaskList userTaskList;

    public Duke() {
        ArrayList<Task> tasks;
        try {
            tasks = Storage.load();
        } catch (FileNotFoundException | Storage.InvalidSaveFileException exception) {
            tasks = new ArrayList<>();
        }
        userTaskList = new TaskList(tasks);
    }

    public String getResponse(String userInput, UserCommandType userCommandType)
            throws TaskList.InvalidIndexException, Parser.InvalidCommandException, IOException {
        switch (userCommandType) {
        case EXIT:
            return StringConstants.EXIT_MESSAGE;
        case LIST:
            return StringConstants.joinStrings(userTaskList.getTasksAsStrings());
        case DONE:
            int doneIndex = Parser.getTaskIndex(userInput);
            String[] doneMessages = userTaskList.markTaskDoneAtIndex(doneIndex);
            userTaskList.saveTaskList();
            return StringConstants.joinStrings(doneMessages);
        case DELETE:
            int deleteIndex = Parser.getTaskIndex(userInput);
            String[] deleteMessages = userTaskList.deleteTaskAtIndex(deleteIndex);
            userTaskList.saveTaskList();
            return StringConstants.joinStrings(deleteMessages);
        case TODO:
        case DEADLINE:
        case EVENT:
            String[] addMessages = userTaskList.addTask(userInput, userCommandType);
            userTaskList.saveTaskList();
            return StringConstants.joinStrings(addMessages);
        case FIND:
            String[] findMessages = userTaskList.find(userInput);
            return StringConstants.joinStrings(findMessages);
        default:
            return "Error";
        }
    }

}
