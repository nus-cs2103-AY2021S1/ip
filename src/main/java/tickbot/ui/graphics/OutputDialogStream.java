package tickbot.ui.graphics;

/**
 * The class to represent a output dislog stream.
 * <p>This stream output nothing to the output stream; instead it informs
 * the GUI to create a dialog with the output text.</p>
 */
public class OutputDialogStream extends DialogStream {
    private GraphicsUi ui;

    /**
     * Initializes the dialog stream with the GUI it will be used in.
     * @param ui The GUI the stream will be used in.
     */
    public OutputDialogStream(GraphicsUi ui) {
        super();
        this.ui = ui;
    }

    @Override
    public void println(String text) {
        super.println(text);
        ui.writeOutputDialog(text);
    }
}
