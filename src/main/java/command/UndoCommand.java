package command;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import storage.CommandStorage;
import task.Task;

/**
 * An UndoCommand object undo the previous user action.
 *
 * @author ameliatjy
 * @version 1.0
 * @since 2020-09-08
 */
public class UndoCommand extends Command {
    /**
     * Undo the previous action.
     *
     * @param inputMsg User's input message to the chat bot.
     * @param currList Current list of tasks.
     * @param ui Ui object relevant to the chat bot.
     * @param commandStorage CommandStorage object to store user commands.
     * @return String message indicating action has been undone.
     * @throws ParseException From load() method.
     * @throws IOException From load() method.
     * @throws DukeException If there were no actions taken in this session.
     */
    @Override
    public String execute(String inputMsg, TaskList currList, Ui ui, CommandStorage commandStorage)
            throws IOException, DukeException {
        ArrayList<String> pastCommands = commandStorage.load();
        int numberOfCommands = pastCommands.size();
        if (numberOfCommands <= 0) {
            throw new DukeException("There are no past actions in this session.");
        }

        String lastCommand = pastCommands.get(numberOfCommands - 1);
        String[] lastCommandSplit = lastCommand.split(" ");
        String actionType = lastCommandSplit[0];

        switch (actionType) {
        case "todo":
        case "deadline":
        case "event":
            DeleteCommand deleteTaskAddition = new DeleteCommand();
            int numberOfTasks = currList.getNumberOfTasks();
            String newMessage = "delete " + numberOfTasks;
            deleteTaskAddition.execute(newMessage, currList, ui, commandStorage);
            break;
        case "done": {
            int taskNumber = Integer.parseInt(lastCommandSplit[1]);
            Task currTask = currList.get(taskNumber - 1);
            currTask.markAsUncompleted();
            break;
        }
        case "delete": {
            int taskNumber = Integer.parseInt(lastCommandSplit[1]);
            currList.add(taskNumber - 1, commandStorage.getLastDeletedTask());
            break;
        }
        case "list":
        case "find":
        case "undo":
            throw new DukeException("Past action cannot be reverted.");
        default:
            throw new DukeException("Past command is an unrecognisable one!");
        }
        return ui.undoCommand(currList);
    }
}
