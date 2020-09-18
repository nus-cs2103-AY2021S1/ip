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
        return "GOES HERE 4 MOAR INFO!!!!11!!\n" + url;
    }

    @Override
    public String toString() {
        return this.cmd.toString();
    }
}

