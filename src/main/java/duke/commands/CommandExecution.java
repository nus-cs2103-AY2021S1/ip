package duke.commands;


import duke.exception.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;


/**
 * A class representing command execution.
 */
public class CommandExecution {

    /**
     * Executes the commands from the input.
     *
     * @param enumCommand the enumeration type of the input command.
     * @param instruction the string of input command.
     * @param tasks       the TaskList storing the list of tasks.
     * @return a string representing the command output.
     * @throws DukeException if the description or the datetime format of the task is illegal.
     */
    public static String executeCommand(EnumCommand enumCommand, String instruction, TaskList tasks)
            throws DukeException {
        Ui ui = new Ui();
        switch (enumCommand) {
        case TODO:
            ToDoCommand.executeCommand(instruction, tasks);
            return ui.addTaskAlert(tasks);

        case DEADLINE:
            DeadLineCommand.executeCommand(instruction, tasks);
            return ui.addTaskAlert(tasks);

        case EVENT:
            EventCommand.executeCommand(instruction, tasks);
            return ui.addTaskAlert(tasks);

        case BYE:
            return ui.farewell();

        case DONE:
            Task tempDone = DoneCommand.executeCommand(instruction, tasks);
            return ui.doneAlert(tempDone);

        case DELETE:
            Task tempDelete = DeleteCommand.executeCommand(instruction, tasks);
            return ui.deleteTaskAlert(tempDelete, tasks);

        case LIST:
            return ui.showList(tasks);

        case CHECK:
            TaskList occurings = CheckCommand.executeCommand(instruction, tasks);
            return ui.showList(occurings);

        case FIND:
            TaskList matches = FindCommand.executeCommand(instruction, tasks);
            return ui.findTaskAlert(matches);

        default:
            return "";
        }
    }
}
