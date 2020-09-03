package willy.exceptions;

/**
 * Handles the errors that may arise in the process of using the bot.
 */
public class WillyException extends Exception {
    private String style = "\t______________________________________________________________\n";
    private String error;

    public WillyException(String errorMessage) {
        super(errorMessage);
        this.error = errorMessage;
    }

    public String getStyle() {
        return style;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return style + "\t" + error + "\n" + style;
    }
}
