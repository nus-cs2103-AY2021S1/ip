package tickbot.ui.graphics;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * The class to represent a dislog print stream.
 * <p>This stream output nothing to the output stream; instead it informs
 * the GUI to create a dialog with the output text.</p>
 */
public class DialogStream extends PrintStream {
    private GraphicsUi ui;

    /**
     * Initialize the dialog stream with the GUI it will be used in.
     * @param ui The GUI the stream will be used in.
     */
    public DialogStream(GraphicsUi ui) {
        super(new OutputStream() {
            public void write(int c) { } // do nothing
        });
        this.ui = ui;
    }

    @Override
    public void println(String text) {
        super.println(text);
        ui.writeDialog(text);
    }
}
