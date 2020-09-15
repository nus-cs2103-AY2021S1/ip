package ekud.utils.ui;

/**
 * The UI Handler. Handles User Input and Output
 * through the Command Line Interface.
 */
public interface Ui {

    /**
     * Prints a given input as a <code>string</code>.
     *
     * @param object the object to print
     */
    void print(Object object);

    /**
     * Read an entire line as a <code>string</code>.
     *
     * @return the string read
     */
    String read();
}
