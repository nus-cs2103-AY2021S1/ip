package duke;

/**
 * An ui to send message to user.
 */
public class Ui {

    private StringBuilder stringBuilder;

    /**
     * Public getter of the welcome message.
     * @return A welcoming message
     */
    public String getWelcome() {
        return "Hello! I'm duke.Duke\nWhat can I do for you?";
    }

    /**
     * Returns a loading error message.
     * @return loading error message
     */
    public String showLoadingError() {
        return "Failed to load";
    }

    public void printMessage(String msg) {
        this.stringBuilder.append(msg).append("\n");
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }
}
