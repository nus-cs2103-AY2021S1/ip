package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class InvalidCharacterCommand extends Command {

    private final String invalidChar;

    public InvalidCharacterCommand(String invalidChar) {
        this.invalidChar = invalidChar;
    }

    @Override
    public String[] execute(Storage storage, TaskList taskList, TaskList archive, Ui ui) {
        return ui.getInvalidCharacterStrings(invalidChar);
    }
}
