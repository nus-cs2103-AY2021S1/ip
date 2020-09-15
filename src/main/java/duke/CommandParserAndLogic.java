package duke;

import duke.tasks.DeadLineTask;
import duke.tasks.EventTask;
import duke.tasks.TodoTask;
import duke.text.TextCacher;

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
     * @return true if it is an know command, else false
     */
    private boolean commandCheck(String string) {
        switch (string) {
            case "!commands":
                TextCacher.cacheCommands();
                return true;
            case "bye":
                byeLogic();
                return true;
            case "list":
                listLogic();
                return true;
            case "done":
                doneLogic();
                return true;
            case "delete":
                deleteLogic();
                return true;
            case "todo":
                toDoLogic();
                return true;
            case "deadline":
                deadLineLogic();
                return true;
            case "event":
                eventLogic();
                return true;
            case "find" :
                findLogic();
                return true;
            default:
                TextCacher.cacheCommandNotFoundError();
                return false;
        }
    }

    private void byeLogic() {
        FileManager.saveList(taskList, pathToSave);
        this.hasEnded = true;
    }

    private void listLogic() {
        TextCacher.cacheTextStandard(taskList.listOutTasks());
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
                    TextCacher.cacheTextStandard(taskList.addTask(new DeadLineTask(details[0], MyDateTime.parse(details[1]))));
                } catch (DateTimeParseException e) {
//                    System.out.println(e.getMessage());
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
                    TextCacher.cacheTextStandard(taskList.addTask(new EventTask(details[0], MyDateTime.parse(details[1]))));
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
            TextCacher.cacheTextStandard(taskList.search(current[1]));
        }
    }
}
