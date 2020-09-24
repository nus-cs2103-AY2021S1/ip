package tickbot.ui.text;

import java.io.PrintStream;

public class TextStream extends PrintStream {
    TextStream() {
        super(System.out);
    }

    @Override
    public void println(String text) {
        super.println("  " + text);
    }
}
