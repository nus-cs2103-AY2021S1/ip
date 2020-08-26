package duke;

/**
 * Represents a Parser that parses user input.
 */
public class Parser {
    private String sanitisedUserInput = "";
    private String[] splitUserInput = new String[0];
    
    /**
     * Creates an instance of a parser.
     */
    public Parser() {
    }

    /**
     * Returns String with trimmed leading white spaces.
     * @param input String to be trimmed of leading white spaces.
     * @return String that has been trimmed of leading white spaces.
     */

    public String sanitiseInput(String input) {
        return input.stripLeading();
    }

    /**
     * Processes user input. 
     * @param userInput String to be processed.
     */
    public void parse(String userInput) {
        this.splitUserInput = userInput.split(" ", 2);
        this.sanitisedUserInput = userInput;
        if (this.splitUserInput[0].isBlank()) {
            this.sanitisedUserInput = sanitiseInput(userInput);
            this.splitUserInput = this.sanitisedUserInput.split(" ", 2);
        }
    }

    /**
     * Returns user input String that has been trimmed of white spaces, if any.
     * @return Formatted String.
     */
    public String getSanitisedUserInput() {
        return this.sanitisedUserInput;
    }

    /**
     * Returns user input String array that has been split.
     * @return String array.
     */
    public String[] getSplitUserInput() {
        return this.splitUserInput;
    }
}
