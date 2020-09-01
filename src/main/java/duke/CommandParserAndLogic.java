package duke;

import duke.Tasks.DeadLineTask;
import duke.Tasks.EventTask;
import duke.Tasks.TodoTask;
import duke.TextStoreAndPrint.TextPrinter;

import java.time.format.DateTimeParseException;

public class CommandParserAndLogic {

    TaskList taskList;
    String[] current;

    public CommandParserAndLogic(TaskList taskList) {
        this.taskList = taskList;
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

    private boolean commandCheck(String string) {
        switch (string) {
            case "!commands":
                TextPrinter.printCommands();
                return true;
            case "bye":
                byeLogic();
                return true;
            case "list":
                taskList.printOut();
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
                deadlineLogic();
                return true;
            case "event":
                eventLogic();
                return true;
            case "find" :
                findLogic();
                return true;
            default:
                TextPrinter.printCommandNotFoundError();
                return false;
        }
    }

    private void byeLogic() {
        Ui.endUi();
    }

    private void doneLogic() {
        if (current.length == 1) {
            TextPrinter.printTaskNumNotSpecifiedError();
        } else {
            taskList.markDone(Integer.parseInt(current[1]));
        }
    }

    private void toDoLogic() {
        if (current.length == 1) {
            TextPrinter.printDescriptionNotFoundError();
        } else {
            taskList.addTask(new TodoTask(current[1]));
        }
    }

    private void deadlineLogic() {
        if (current.length == 1) {
            TextPrinter.printDescriptionNotFoundError();
        } else {
            String[] details = current[1].split("/by", 2);
            if (details.length == 1) {
                TextPrinter.printTimeNotFoundError();
            } else {
                try {
                    taskList.addTask(new DeadLineTask(details[0], MyDateTime.parse(details[1])));
                } catch (DateTimeParseException e) {
                    TextPrinter.printDateTimeFormatError();
                }
            }
        }
    }

    private void eventLogic() {
        if (current.length == 1) {
            TextPrinter.printDescriptionNotFoundError();
        } else {
            String[] details = current[1].split("/at", 2);
            if (details.length == 1) {
                TextPrinter.printTimeNotFoundError();
            } else {
                try {
                    taskList.addTask(new EventTask(details[0], MyDateTime.parse(details[1])));
                } catch (DateTimeParseException e) {
                    TextPrinter.printDateTimeFormatError();
                }
            }
        }
    }

    private void deleteLogic() {
        if (current.length == 1) {
            TextPrinter.printTaskNumNotSpecifiedError();
        } else {
            taskList.deleteTask(Integer.parseInt(current[1]));
        }
    }

    private void findLogic() {
        if (current.length == 1) {
            TextPrinter.printNoSearchTermError();
        } else {
            taskList.search(current[1]);
        }
    }
}
