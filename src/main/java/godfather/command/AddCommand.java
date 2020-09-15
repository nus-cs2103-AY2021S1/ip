package godfather.command;

import java.io.IOException;
import java.util.ArrayList;

import godfather.Storage;
import godfather.TaskList;
import godfather.enums.Message;
import godfather.exception.VitoException;
import godfather.ui.Ui;


/**
 * Adds Tasks to the TaskList and invokes appropriate UI messages about it
 */
public class AddCommand implements Command {
    private final String[] parsedInput;
    /*Indicates the type of task*/
    private final String commandTag;
    /**
     * Constructs an AddCommand Object.
     *
     * @param parsedInput Parser's output for the user input
     */
    public AddCommand(String[] parsedInput) {
        this.parsedInput = parsedInput;
        this.commandTag = parsedInput[0];
    }
    /**
     * Prints out a confirmation message of the command, adds the entry to TaskList and displays the current status of
     * the TaskList
     *
     * @param tasks Current TaskList
     * @param ui    Where the User shall receive messages about the command
     *
     * @throws godfather.exception.VitoException If we can't write onto the file after adding a task to TaskList
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws VitoException, IOException {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(Message.CONFIRMATION_MSG.getMsg());
        String reply = tasks.addEntry(this.parsedInput, this.commandTag);
        assert !reply.isEmpty() : "AddCommand.execute(): reply is somehow empty";
        lines.add(reply);
        lines.add(tasks.getCurrentStatus());
        ui.display(lines);
        Storage.save(tasks); // save upon addition because we want to do a write on every change
        return Command.listLinesToString(lines);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
