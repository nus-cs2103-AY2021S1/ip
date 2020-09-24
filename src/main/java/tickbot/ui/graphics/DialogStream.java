package tickbot.ui.graphics;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * The class to represent a dislog print stream.
 * <p>This stream output nothing to the output stream; instead it informs
 * the GUI to create a dialog with the output text.</p>
 */
public class DialogStream extends PrintStream {

    /**
     * Initializes a dialog stream.
     */
    public DialogStream() {
        super(new OutputStream() {
            public void write(int c) { } // do nothing
        });
    }
}
