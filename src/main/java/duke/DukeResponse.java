package duke;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.ResetCommand;
import duke.exception.DukeException;

/**
 * Represents a DukeResponse.
 */
public class DukeResponse {

    /**
     * Text response to be displayed to the user.
     */
    protected String responseText;

    /**
     * Type of image associated with the response.
     */
    protected ImageType imageType;

    /**
     * Type of command associated with the response.
     */
    protected CommandType commandType;

    /**
     * Creates a DukeResponse object.
     * @param responseText Text response to be displayed.
     * @param imageType Type of image.
     * @param commandType Type of command.
     */
    private DukeResponse(String responseText, ImageType imageType, CommandType commandType) {
        this.responseText = responseText;
        this.imageType = imageType;
        this.commandType = commandType;
    }

    /**
     * Creates a DukeResponse with a specific input.
     * @param input Input by the user.
     * @param ui Ui associated with the duke object.
     * @param taskList Task list associated with the duke object.
     * @return
     */
    public static DukeResponse getDukeResponse(String input, Ui ui, TaskList taskList) {
        String response;
        ImageType responseImageType;
        CommandType responseCommandType;
        try {
            Command command = Parser.parse(input);
            Parser.setPrevCommand(command);
            response = command.execute(ui, taskList);
            responseImageType = command.getImageType();
            responseCommandType = command.getCommandType();
            return new DukeResponse(response, responseImageType, responseCommandType);
        } catch (DukeException e) {
            Parser.setPrevCommand(new ResetCommand());
            response = ui.displayError(e.getMessage()) + ui.printAdditionActionMessage();
            responseImageType = e.getImageType();
            responseCommandType = CommandType.RESET;
            return new DukeResponse(response, responseImageType, responseCommandType);
        }
    }

    /**
     * Returns the response text to be displayed.
     * @return Response text.
     */
    public String getResponseText() {
        return responseText;
    }

    /**
     * Returns the type of image.
     * @return Type of image.
     */
    public ImageType getImageType() {
        return imageType;
    }

    /**
     * Returns the type of command.
     * @return Type of command.
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Returns if the command is an exit command or not.
     * @return Either true or false.
     */
    public boolean isExitCommand() {
        return this.commandType.equals(CommandType.EXIT);
    }
}
