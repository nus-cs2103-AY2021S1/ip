package tickbot.ui.graphics;

/**
 * The class to represent a input dislog stream.
 * <p>This stream output nothing to the output stream; instead it informs
 * the GUI to create a dialog with the output text.</p>
 */
public class InputDialogStream extends DialogStream {
    private GraphicsUi ui;

    /**
     * Initializes the dialog stream with the GUI it will be used in.
     * @param ui The GUI the stream will be used in.
     */
    public InputDialogStream(GraphicsUi ui) {
        super();
        this.ui = ui;
    }

    @Override
    public void println(String text) {
        String prefixedText = "☞ " + text;
        super.println(prefixedText);
        ui.writeInputDialog(prefixedText);
    }
}
