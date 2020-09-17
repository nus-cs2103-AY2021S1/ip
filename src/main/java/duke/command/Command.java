package duke.command;

import duke.ImageType;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public abstract class Command {
    protected CommandType commandType;
    protected ImageType imageType;

    public Command(CommandType commandType) {
        this.commandType = commandType;
        this.imageType = ImageType.MAIN;
    }

    public Command(CommandType commandType, ImageType imageType) {
        this.commandType = commandType;
        this.imageType = imageType;
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public boolean isExitCommand() {
        return commandType.equals(CommandType.EXIT);
    }

    public abstract String execute(Ui ui, TaskList taskList) throws DukeException;
}
