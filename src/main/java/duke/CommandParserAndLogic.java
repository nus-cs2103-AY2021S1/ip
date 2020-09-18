package duke;

import duke.tasks.DeadLineTask;
import duke.tasks.EventTask;
import duke.tasks.TodoTask;
import duke.text.TextBuilder;
import duke.text.TextCacher;
import duke.text.TextStore;

import java.nio.file.Path;
import java.time.format.DateTimeParseException;

public class CommandParserAndLogic {

    TaskList taskList;
    Path pathToSave;
    String[] current;
    boolean hasEnded = false;

    public CommandParserAndLogic(TaskList taskList, Path path) {
        this.taskList = taskList;
        this.pathToSave = path;
    }

    /**
     * Splits the string and uses commandCheck method for further logic
     *
     * @param input a string input
     */
    public void handleInput(String input) {
        current = input.split(" ", 2);
        commandCheck(current[0]);
    }

    /**
     * Checks the string provided if it matches any known commands
     * Follows up by calling the relevant logic
     *
     * @param string input
     */
    private void commandCheck(String string) {
        switch (string) {
            case "!commands":
                TextCacher.cacheCommands();
                return;
            case "bye":
                byeLogic();
                return;
            case "list":
                listLogic();
                return;
            case "done":
                doneLogic();
                return;
            case "delete":
                deleteLogic();
                return;
            case "todo":
                toDoLogic();
                return;
            case "deadline":
                deadLineLogic();
                return;
            case "event":
                eventLogic();
                return;
            case "find" :
                findLogic();
                return;
            default:
                TextCacher.cacheCommandNotFoundError();
        }
    }

    private void byeLogic() {
        FileManager.saveList(taskList, pathToSave);
        this.hasEnded = true;
    }

    private void listLogic() {
        TextCacher.cacheTextStandard(
                TextBuilder.buildTaskListMsg(TextStore.listOutHeader, taskList.allTasks()));
    }

    private void doneLogic() {
        if (current.length == 1) {
            TextCacher.cacheTaskNumNotSpecifiedError();
        } else {
            try {
                TextCacher.cacheTextStandard(taskList.markDone(Integer.parseInt(current[1])));
            } catch (IndexOutOfBoundsException e) {
                TextCacher.cacheTaskNotFoundError();
            }
        }
    }

    private void toDoLogic() {
        if (current.length == 1) {
            TextCacher.cacheDescriptionNotFoundError();
        } else {
            TextCacher.cacheTextStandard(taskList.addTask(new TodoTask(current[1])));
        }
    }

    private void deadLineLogic() {
        if (current.length == 1) {
            TextCacher.cacheDescriptionNotFoundError();
        } else {
            String[] details = current[1].split("/by", 2);
            if (details.length == 1) {
                TextCacher.cacheTimeNotFoundError();
            } else {
                try {
                    String processInfo = taskList.addTask(new DeadLineTask(details[0], MyDateTime.parse(details[1])));
                    TextCacher.cacheTextStandard(processInfo);
                } catch (DateTimeParseException e) {
                    TextCacher.cacheDateTimeFormatError();
                }
            }
        }
    }

    private void eventLogic() {
        if (current.length == 1) {
            TextCacher.cacheDescriptionNotFoundError();
        } else {
            String[] details = current[1].split("/at", 2);
            if (details.length == 1) {
                TextCacher.cacheTimeNotFoundError();
            } else {
                try {
                    String processInfo = taskList.addTask(new EventTask(details[0], MyDateTime.parse(details[1])));
                    TextCacher.cacheTextStandard(processInfo);
                } catch (DateTimeParseException e) {
                    TextCacher.cacheDateTimeFormatError();
                }
            }
        }
    }

    private void deleteLogic() {
        if (current.length == 1) {
            TextCacher.cacheTaskNumNotSpecifiedError();
        } else {
            try {
                TextCacher.cacheTextStandard(taskList.deleteTask(Integer.parseInt(current[1])));
            } catch (IndexOutOfBoundsException e) {
                TextCacher.cacheTaskNotFoundError();
            }
        }
    }

    private void findLogic() {
        if (current.length == 1) {
            TextCacher.cacheNoSearchTermError();
        } else {
            TextCacher.cacheTextStandard(
                    TextBuilder.buildTaskListMsg(TextStore.searchListHeader, taskList.search(current[1])));
        }
    }
}
