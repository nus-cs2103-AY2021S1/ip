package duke.core;

public class Result {
    private String message;
    private boolean isContinuing;

    public Result(String message, boolean isContinuing) {
        this.message = message;
        this.isContinuing = isContinuing;
    }

    public String getMessage() {
        return message;
    }

    public boolean isContinuing() {
        return isContinuing;
    }
}
