package duke;

public class Parser {
    private String sanitisedUserInput = "";
    private String[] splitUserInput = new String[0];
    
    public Parser() {}
    
    public String sanitiseInput(String input) {
        return input.stripLeading();
    }
    
    public void parse(String userInput) {
        this.splitUserInput = userInput.split(" ", 2);
        this.sanitisedUserInput = userInput;
        if (this.splitUserInput[0].isBlank()) {
            this.sanitisedUserInput = sanitiseInput(userInput);
            this.splitUserInput = this.sanitisedUserInput.split(" ", 2);
        }
    }
    
    public String getSanitisedUserInput() {
        return this.sanitisedUserInput;
    }
    
    public String[] getSplitUserInput() {
        return this.splitUserInput;
    }
}
