package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.TaskList;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HelpCommand extends Command {

    public HelpCommand() {
        super();
        this.cmd = CMD.HELP;
    }

    @Override
    public String getResponse(TaskList tasklist, Storage storage) throws DukeException {
        String url = "https://kaitlynng.github.io/ip/";
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                Desktop desktop = java.awt.Desktop.getDesktop();
                URI userGuideSite = new URI(url);
                desktop.browse(userGuideSite);
            } else {
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("xdg-open " + url);
            }
        } catch (IOException | URISyntaxException e) {
            return "Bark. (Sorry, we couldn't open the website.)";
        }

        return "GOES HERE 4 MOAR INFO!!!!11!!\n" + url;
    }

    @Override
    public String toString() {
        return this.cmd.toString();
    }
}
