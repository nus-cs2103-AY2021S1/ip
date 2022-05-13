package duke;

import duke.ui.Cli;
import duke.utils.Constants;

public class DukeCli {
    /**
     * Launch Duke.
     */
    public static void launchDuke() {
        //there is no need to set default storage, here is just showcasing the usage of Cli
        Cli.getInstance().setStorage(Constants.DEFAULTSTORAGE).loop();
    }

}

