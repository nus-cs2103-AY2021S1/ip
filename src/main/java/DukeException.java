public class DukeException extends Exception {

    protected DukeExceptionType exceptionType;
    protected String command;

    public DukeException(String e, DukeExceptionType exceptionType){
        super(e);
        this.exceptionType = exceptionType;
    }

    public DukeException(String e, DukeExceptionType exceptionType, String command){
        super(e);
        this.exceptionType = exceptionType;
        this.command = command;
    }

    @Override
    public String toString(){
        String error = "";
        switch (exceptionType){
            case EMPTY_TIME:
                error+= "Deadline/event task requires a time";
                break;
            case INVALID_TASK:
                error+= "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                break;
            case NO_DESCRIPTION:
                error+="this command cannot have an empty description";
                break;
            case WRONG_DESCRIPTION:
                error+= "Deadline must have task name followed by '/by' and event must be follow by '/at'";
                break;
        }
        return error;
    }
}
