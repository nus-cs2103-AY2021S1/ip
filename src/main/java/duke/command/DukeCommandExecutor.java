package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;

public class DukeCommandExecutor implements CommandExecutor {
    private boolean hasExited = false;

    /**
     * Processes the command issued by user and passes it on to the relevant Command class for its execution.
     *
     *
     * @param in String command issued by user
     * @param taskList TaskList list that contains tasks added by the user
     * @param storage Storage object to help with updating the save file
     * @return String response message to user
     * @throws DukeException If the command is not formatted properly
     */
    public String execute(String in, TaskList taskList, Storage storage) throws DukeException {
        if (hasExited) {
            throw new InvalidCommandException("Program has already exited!");
        }

        CommandType cmdType = Parser.parseCmdWord(in);
        switch (cmdType) {
            case Exit:
                hasExited = true;
                return ExitCommand.execute();
            case List:
                return ListCommand.execute(taskList);
            case Done:
                return DoneCommand.execute(in, taskList);
            case Delete:
                return DeleteCommand.execute(in, taskList, storage);
            case Due:
                return DueCommand.execute(in, taskList);
            case Task:
                return TaskCommand.execute(in, taskList, storage);
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
}
