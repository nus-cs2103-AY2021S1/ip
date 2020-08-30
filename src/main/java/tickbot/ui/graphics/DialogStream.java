package tickbot.ui.graphics;

import java.io.OutputStream;
import java.io.PrintStream;

public class DialogStream extends PrintStream {
    private GraphicsUi ui;

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