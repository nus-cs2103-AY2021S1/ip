package duke.Command;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public final String input;

    /**
     * Constructs a Command object with the input specified
     * @param input User's input that is processed by the Command Object
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Invokes the Command object to process the User's request based on User's input
     * @param tasks TaskList that contains an ArrayList of Task
     * @param ui Ui object that interacts with User
     * @param storage Storage object that reads from/write to specified filePath
     * @throws DukeException if User's input is invalid
     */
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if User's request is to exit, false otherwise
     * @return true if User's request is to exit, false otherwise
     */
    abstract public boolean isExit();
}