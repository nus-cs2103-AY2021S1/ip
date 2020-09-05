package duke.io;

import java.util.ArrayList;

/**
 * Class for handling all output to display instructions.
 */
public class OutputHandler {

    private ArrayList<String> outputBuffer;

    public OutputHandler() {
        outputBuffer = new ArrayList<>();
    }

    /**
     * Prints desired output to display.
     *
     * @param output String to display.
     */
    public void storeOutput(String output) {
        this.outputBuffer.add(output);
    }

    public void printNow(String output) {
        System.out.println(output);
    }

    public boolean isEmpty() {
        return this.outputBuffer.isEmpty();
    }

    public void flush() {
        String stringToPrint = this.outputBuffer
                .stream()
                .reduce("", (x,y) -> x + "\n" + y);

        printNow(stringToPrint);
        this.outputBuffer.clear();
    }

}
