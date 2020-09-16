package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Main program for Duke.
 */
public class Duke {
    private final TaskList userTaskList;

    /**
     * Initialise duke and load tasks.
     */
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
            return saveTaskListAndJoinMessages(userTaskList.getTasksAsStrings());
        case DONE:
            int doneIndex = Parser.getTaskIndex(userInput);
            return saveTaskListAndJoinMessages(userTaskList.markTaskDoneAtIndex(doneIndex));
        case DELETE:
            int deleteIndex = Parser.getTaskIndex(userInput);
            return saveTaskListAndJoinMessages(userTaskList.deleteTaskAtIndex(deleteIndex));
        case TODO:
        case DEADLINE:
        case EVENT:
            return saveTaskListAndJoinMessages(userTaskList.addTask(userInput, userCommandType));
        case FIND:
            return saveTaskListAndJoinMessages(userTaskList.find(userInput));
        default:
            return "Error: Unrecognised command type";
        }
    }

    private String saveTaskListAndJoinMessages(String[] messages) throws IOException {
        userTaskList.saveTaskList();
        return StringConstants.joinStrings(messages);
    }

}
