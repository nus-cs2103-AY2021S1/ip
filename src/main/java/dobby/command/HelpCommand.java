package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;

public class HelpCommand implements Command {

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        assert text.equals("help") : "Help command must be help";
        String userGuide = "https://sagarsureka.github.io/ip/";
        return "Please look at the user guide at: " + userGuide;
    }
}
