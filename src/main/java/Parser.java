import java.io.IOException;
import java.util.Scanner;

public class Parser {
    /**
     * Returns appropriate task after processing userInput. Calls on ui to give user a response
     *
     * @param userInput
     * @return Task to be used by Duke instance
     * @throws ToDoException
     * @throws deadlineException
     * @throws eventException
     */
    public String processAddTaskInput(String userInput, TaskList taskList, Storage storage, Ui ui) throws DukeException, IOException {
        String taskType = userInput.split(" ")[0];
        Task thisTask = null;
        switch (taskType) {
            case "todo":
                thisTask = new Task(userInput.replace("todo ", ""));
                taskList.addTask(thisTask, storage);
                return ui.showAddTaskMessage(thisTask, taskList);
            case "deadline":
                thisTask = new Deadline(userInput);
                taskList.addTask(thisTask, storage);
                return ui.showAddTaskMessage(thisTask, taskList);
            case "event":
                thisTask = new Event(userInput);
                taskList.addTask(thisTask, storage);
                return ui.showAddTaskMessage(thisTask, taskList);
            default:
                throw new DukeException();
        }
    }

    /**
     * Processes userInput and calls on ui to give appropriate response to the user
     *
     * @param userInput
     * @param taskList
     * @throws IOException
     * @throws DukeException
     */
    public String processOtherActionInput(String userInput, TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        String[] splitUserInput = userInput.split(" ");
        String actionType = splitUserInput[0];
        if (splitUserInput[1].equals("")) throw new DukeException("Please enter an index/duration");
        switch (actionType) {
            case "done":
                int doneIndex = Integer.parseInt(userInput.replace("done ", ""));
                assert doneIndex >= 1 : "Index must be a positive value";
                taskList.taskCompleted(doneIndex, storage);
                return ui.showOtherActionMessage(userInput, taskList);
            case "delete":
                int indexDeleted = Integer.parseInt(userInput.replace("delete ", ""));
                TaskList duplicatedTaskList = TaskList.copy(taskList);
                assert indexDeleted >= 1 : "Index must be a positive value";
                taskList.deleteTask(indexDeleted, storage);
                return ui.showOtherActionMessage(userInput, duplicatedTaskList);
            case "find":
                return ui.showOtherActionMessage(userInput, taskList);
            case "duration":
                if (userInput.equals("duration")) throw new DurationException();
                int indexSettingDuration = Integer.parseInt(userInput.split(" ")[1]);
                assert indexSettingDuration >= 1 : "Index must be a positive value";
                taskList.setDuration(userInput, storage);
                return ui.showOtherActionMessage(userInput, taskList);
            default:
                throw new DukeException();
        }
    }
}
