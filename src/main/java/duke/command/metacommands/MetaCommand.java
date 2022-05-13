package duke.command.metacommands;


import duke.command.Command;

public class MetaCommand {
    private Command type;
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Command getType() {
        return type;
    }

    public void setType(Command type) {
        this.type = type;
    }
}
