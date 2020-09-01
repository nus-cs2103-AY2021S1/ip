package duke;

import duke.command.Command;

public class Response {
    private String responseString;
    private Command command;

    public Response(String responseString, Command command) {
        this.responseString = responseString;
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public String getResponseString() {
        return responseString;
    }
}
