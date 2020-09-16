package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.ui.Ui;
import duke.ui.UiForGui;

class ResponseTest {
    private class CommandStub extends Command {
        public void execute(TaskList tasks, Ui ui, Storage storage) {
        }

        public String execute(TaskList tasks, UiForGui uiForGui, Storage storage) {
            return "method is working";
        }
    }

    @Test
    void getCommand() {
        CommandStub commandStub = new CommandStub();
        String responseString = "method is working";
        Response response = new Response(responseString, commandStub);
        assertEquals(response.getCommand(), commandStub);
    }

    @Test
    void getResponseString() {
        CommandStub commandStub = new CommandStub();
        String responseString = "method is working";
        Response response = new Response(responseString, commandStub);
        assertEquals(response.getResponseString(), responseString);
    }
}
