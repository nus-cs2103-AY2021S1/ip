package duke.io;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Class for handling all output to display instructions.
 * A buffering functionality is present to combine messages that should be sent together from various sources.
 */
public class OutputHandler {

    /**
     * Buffer containing messages to be sent.
     */
    private ArrayList<String> outputBuffer;

    /**
     * Default constructor for <code>OutputHandler</code> with a new empty buffer.
     */
    public OutputHandler() {
        outputBuffer = new ArrayList<>();
    }

    /**
     * Stores desired output to be displayed in <code>outputBuffer</code>.
     *
     * @param output String to be display.
     */
    public void storeOutput(String output) {
        this.outputBuffer.add(output);
    }

    /**
     * Display a given string immediately, skipping ahead of the buffered items.
     * Uses <code>System.out.println</code> as a default display method.
     *
     * @param output String to be displayed immediately.
     */
    public void printNow(String output) {
        System.out.println(output);
    }

    /**
     * Display a given warning immediately using <code>printNow()</code>
     * @param warning Warning to be displayed.
     */
    public void printWarning(String warning) {
        printNow(warning);
    }

    /**
     * Checks if <code>outputBuffer</code> is empty.
     *
     * @return Whether <code>outputBuffer</code> is empty.
     */
    public boolean isEmpty() {
        return this.outputBuffer.isEmpty();
    }

    /**
     * Concatenates messages in buffer then displays them.
     * Buffer is then cleared.
     * No displaying occurs if buffer is empty.
     */
    public void flush() {

        if (this.isEmpty()) {
            return;
        }

        // Concatenate messages in buffer
        Optional<String> stringToPrint = this.outputBuffer
                .stream()
                .reduce((x, y) -> x + "\n" + y);

        assert stringToPrint.isPresent();

        printNow(stringToPrint.get());

        this.outputBuffer.clear();

        assert this.isEmpty();
    }

}
