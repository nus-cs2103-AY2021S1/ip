package exceptions;

public enum ErrorEncode {
    FileRead(0, "A I/O and Filereading Error has occured."),
    BadCommandGiven(1, "I cannot understand what that command means."),
    NoInputGiven(2, "There was no Input given for the task."),
    BadDateGiven(3 , "You did not give a valid date!"),
    BlankCommand(4,"No input command was given"),
    IndexError(5, "The index of the task is out of bounds"),
    UnknownError(99, "Something Exceptionally unexpected has happened! We will shut the application down")
    ;
    
    private final int code;
    private final String description;

    private ErrorEncode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    /**
     * Parse an error code into the Enum List for the given error message
     * @param code
     * @return
     */
    public static ErrorEncode parseCode(int code){
        ErrorEncode e = UnknownError;
        for (ErrorEncode i : ErrorEncode.values()){
            if (i.getCode() == code){
                e = i;
                break;
            }
        }
        return e;
    }
    @Override
    public String toString() {
        return description;
    }
}