package duke;

/**
 * Represents a Parser that parses user input.
 */
public class Parser {
    private String[] splitUserInput = new String[0];
    
    /**
     * Creates an instance of a parser.
     */
    public Parser() {
    }
    
    /**
     * Processes user input. 
     * @param userInput String to be processed.
     */
    public void parse(String userInput) {
        this.splitUserInput = userInput.strip().split(" ", 2);
    }
    

    /**
     * Returns user input String array that has been split.
     * @return String array.
     */
    public String[] getSplitUserInput() {
        return this.splitUserInput;
    }
}
