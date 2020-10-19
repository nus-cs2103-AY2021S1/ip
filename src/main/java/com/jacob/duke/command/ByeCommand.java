package main.java.com.jacob.duke.command;
import main.java.com.jacob.duke.DukeList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.io.Storage;


public class ByeCommand implements Command {
    /**
     * Executes command for pre-determined ByeCommand.
     *
     * @param ui UI object to deal with program output.
     * @param dukeList Task List Representation.
     * @param storage Storage object to deal with interfacing with file system.
     */
    @Override
    public String execute(Ui ui, DukeList dukeList, Storage storage) {
        return ui.sayBye();
    }

    /**
     * Check if it is the bye Command
     *
     * @return true since it is.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
