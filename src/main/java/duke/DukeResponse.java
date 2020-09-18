package duke;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.ResetCommand;
import duke.exception.DukeException;

public class DukeResponse {
    protected String responseText;
    protected ImageType imageType;
    protected CommandType commandType;

    private DukeResponse(String responseText, ImageType imageType, CommandType commandType) {
        this.responseText = responseText;
        this.imageType = imageType;
        this.commandType = commandType;
    }

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
            response = ui.displayError(e.getMessage());
            responseImageType = e.getImageType();
            responseCommandType = CommandType.RESET;
            return new DukeResponse(response, responseImageType, responseCommandType);
        }
    }

    public String getResponseText() {
        return responseText;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public boolean isExitCommand() {
        return this.commandType.equals(CommandType.EXIT);
    }
}
