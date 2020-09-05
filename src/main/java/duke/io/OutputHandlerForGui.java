package duke.io;

import com.sun.tools.javac.Main;
import duke.DukeGui;
import duke.MainWindow;

public class OutputHandlerForGui extends OutputHandler{

    private MainWindow mainWindow;

    public OutputHandlerForGui(MainWindow mainWindow) {
        super();
        this.mainWindow = mainWindow;
    }

    @Override
    public void printNow(String output) {
        mainWindow.handleDukeResponse(output + "\n");
    }

}
