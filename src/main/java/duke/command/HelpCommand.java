package duke.command;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

/**
 * Encapsulates data and methods specific to the Help command.
 */
public class HelpCommand extends Command {

    /**
     * Creates a new instance of the Help command class.
     */
    public HelpCommand() {
    }

    /**
     * Displays the different commands available and their syntax on the GUI.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list that is used by the instance of Duke.
     * @param ui UI object for the instance of Duke.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) {

        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Task list cannot be null";

        String helpUrl = "http://pr4aveen.github.io/ip";

        ui.printToConsole(String.format("Please visit %s for more information"
                + " on the commands available.", helpUrl));

        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI userGuideUrl = new URI(helpUrl);
            desktop.browse(userGuideUrl);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
