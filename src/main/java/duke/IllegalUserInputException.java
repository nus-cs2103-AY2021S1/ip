package duke;
public class IllegalUserInputException extends IllegalArgumentException{
    private String description;
    public IllegalUserInputException (String description) {
        super(description);
        this.description = description;
    }

    public String toString() {
        return this.description;
    }
}
