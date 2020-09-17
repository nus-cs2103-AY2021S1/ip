package duke.io;

import duke.gui.MainWindow;

/**
 * <code>OutputHandler</code> connected for display via GUI.
 */
public class OutputHandlerForGui extends OutputHandler {

    /**
     * GUI main window for buffer contents to be outputted into.
     */
    private MainWindow mainWindow;

    /**
     * Constructor that initializes and links <code>OutputHandlerForGui</code> to the GUI window.
     *
     * @param mainWindow GUI main window component.
     */
    public OutputHandlerForGui(MainWindow mainWindow) {
        super();
        this.mainWindow = mainWindow;
    }

    /**
     * Display a given string immediately via the GUI, skipping ahead of the buffered items.
     *
     * @param output String to be displayed immediately.
     */
    @Override
    public void printNow(String output) {
        mainWindow.handleDukeResponse(output + "\n");
    }

    /**
     * Display a given warning immediately via the GUI, skipping ahead of the buffered items.
     * GUI-based implementation of warning display is handled differently from a normal response.
     * @param warning Warning to be displayed.
     */
    @Override
    public void printWarning(String warning) {
        mainWindow.handleDukeWarning(warning);
    }

}
