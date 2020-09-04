package duke.io;

import com.sun.tools.javac.Main;
import duke.DukeGui;
import duke.MainWindow;

public class OutputHandlerForGui extends OutputHandler{

    private MainWindow mainWindow;

    public OutputHandlerForGui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void print(String output) {
        mainWindow.handleDukeResponse(output + "\n");
    }
}
