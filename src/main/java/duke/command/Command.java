package duke.command;

import duke.*;
import duke.exception.*;

public abstract class Command {

    protected String tag;
    protected String input;
    protected boolean isExit;

    public Command(String tag) {
        this.tag = tag;
        this.input = "";
        this.isExit = false;
    }

    public Command(String tag, String input) {
        this.tag = tag;
        this.input = input;
        this.isExit = false;
    }

    public Command(String tag, boolean isExit) {
        this.tag = tag;
        this.input = "";
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeInvalidListNumberInputException,
            DukeInvalidTaskTimeException,
            DukeInvalidTaskDescriptionException,
            DukeUnknownInputException,
            DukeLoadingErrorException;

}
