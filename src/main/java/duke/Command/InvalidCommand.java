package duke.Command;

import duke.Exception.DukeException;
import duke.Exception.InvalidInputException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class InvalidCommand extends Command {

    /**
     * Constructs a InvalidCommand object with the input specified
     * @param input User's input that is processed by the InvalidCommand Object
     */
    public InvalidCommand(String input) {
        super(input);
    }

    /**
     * Invokes the InvalidCommand object to feedback to the User that Duke does not recognise User's input
     * @param tasks TaskList that contains an ArrayList of Task
     * @param ui Ui object that interacts with User
     * @param storage Storage object that reads from/write to specified filePath
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("I'm sorry, but I don't know what that means! :-(");
    }

    /**
     * Returns false as InvalidCommand is not for termination
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
