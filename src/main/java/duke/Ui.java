package duke;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {

    /**
     * Formats the given input and prints out the text with relevant indentations and enclosed it with lines.
     *
     * @param text The String to be formatted.
     *
     * @return Formatted string.
     *
     */
    public String printReply(String text) {
        String line = "\t____________________________________________________________________________________";
        return line + "\n\t\t" + text.replaceAll("\\n", "\n\t\t") + "\n" + line;
    }

    /**
     * Returns the standard greeting message when the bot is first started.
     *
     * @return The message as string.
     */
    public String greet() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Returns the standard message when the bot is being ended.
     *
     * @return The message as string.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

}