package ultron.ui;

public final class UI {

    /**
     * Store the scanner object.
     */
    private String string;

    /**
     * UI to handle all the displaying.
     */
    public UI() {
        setIntro();
    }

    /**
     * Returns the introduction string.
     * @return String introduction.
     */
    public static String getIntro() {
        return "Hello lesser being, I am Ultron\n"
            + "What do you want?\n";
    }

    /**
     * Prints a message.
     *
     * @param argument message to be printed.
     */
    public void print(final String argument) {
        System.out.println(argument);
    }

    /**
     * Get the message that is currently stored.
     */
    public String getMessage() {
        return getLine() + string + getLine();
    }

    /**
     * Set the message to be shown in gui.
     */
    public void setMessage(final String argument) {
        string = argument;
    }

    /**
     * Prints the end message to stdout.
     */
    public void printEnd() {
        print(getEnd());
    }

    /**
     * Set the intro message
     */
    public void setIntro() {
        setMessage(getIntro());
    }

    /**
     * Get the end message.
     * @return String End message.
     */
    private String getEnd() {
        return "Clearly you were not worth my time.\n";
    }

    /**
     * Get the new line separator.
     *
     * @return String newline seperator
     */
    private String getLine() {
        return "";
    }
}
