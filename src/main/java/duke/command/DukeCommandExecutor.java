package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.TaskList;

public class DukeCommandExecutor implements CommandExecutor {
    private boolean hasExited = false;

    /**
     * Processes the command issued by user and passes it on to the relevant Command class for its execution.
     *
     *
     * @param in String command issued by user
     * @param taskList TaskList list that contains tasks added by the user
     * @return String response message to user
     * @throws DukeException If the command is not formatted properly
     */
    public String execute(String in, TaskList taskList) throws DukeException {
        if (hasExited) {
            throw new InvalidCommandException("Program has already exited!");
        }

        CommandType cmdType = CommandParser.parseCmdWord(in);
        switch (cmdType) {
        case Delete:
            return DeleteCommand.execute(in, taskList);
        case Done:
            return DoneCommand.execute(in, taskList);
        case Due:
            return DueCommand.execute(in, taskList);
        case Exit:
            hasExited = true;
            return ExitCommand.execute();
        case Find:
            return FindCommand.execute(in, taskList);
        case List:
            return ListCommand.execute(taskList);
        case Task:
            return TaskCommand.execute(in, taskList);
        default: // Invalid
           throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns true if the "bye" command has been issued else returns false.
     *
     * @return boolean should the program exit
     */
    public boolean shouldExit() {
        return hasExited;
    }

    public void loadSaveString(String in, TaskList taskList) throws DukeException {
        TaskCommand.loadSavedTasks(in, taskList);
    }
}
