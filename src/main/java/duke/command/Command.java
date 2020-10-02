package duke.command;

import duke.ImageType;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a Command.
 */
public abstract class Command {
    /**
     * The type of command.
     */
    protected CommandType commandType;

    /**
     * The type of image.
     */
    protected ImageType imageType;

    /**
     * Creates a Command object.
     * @param commandType Type of command.
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
        this.imageType = ImageType.MAIN;
    }

    /**
     * Creates a command object with a specific image.
     * @param commandType Type of command.
     * @param imageType Type of image.
     */
    public Command(CommandType commandType, ImageType imageType) {
        this.commandType = commandType;
        this.imageType = imageType;
    }

    /**
     * Returns the type of the command.
     * @return Type of command.
     */
    public CommandType getCommandType() {
        return this.commandType;
    }

    /**
     * Returns the type of the image.
     * @return Type of image.
     */
    public ImageType getImageType() {
        return imageType;
    }

    /**
     * Executes an command.
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return Response after executing the command.
     * @throws DukeException When any of the child classes encounter an exception.
     */
    public abstract String execute(Ui ui, TaskList taskList) throws DukeException;
}
