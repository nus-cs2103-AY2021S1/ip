package duke;

import duke.DukeCommandType;
import duke.DukeExceptionType;

public class DukeException extends Exception {

    protected DukeExceptionType exceptionType;
    protected DukeCommandType commandType;

    public DukeException(String e, DukeExceptionType exceptionType) {
        super(e);
        this.exceptionType = exceptionType;
    }

    public DukeException(String e, DukeExceptionType exceptionType, DukeCommandType commandType) {
        super(e);
        this.exceptionType = exceptionType;
        this.commandType = commandType;
    }

    @Override
    public String toString(){
        String error = "";
        switch (exceptionType){
        case EMPTY_LIST:
            error += "    YOUR LIST IS EMPTY :-(\n";
            break;
        case UNKNOWN:
            error += "    I DON'T KNOW WHAT YOU MEAN :-(\n";
            break;
        case WRONG_FORMAT:
            switch (commandType) {
            case DEADLINE:
                error += "    ERROR IN ADDING DEADLINE: WRONG FORMAT\n    Format: deadline <description> /by <datetime>\n";
                break;
            case EVENT:
                error += "    ERROR IN ADDING EVENT: WRONG FORMAT\n    Format: event <description> /at <datetime>\n";
                break;
            }
            break;
        case MISSING_DESCRIPTION:
            switch (commandType) {
            case TODO:
                error += "    ERROR IN ADDING TODO: MISSING DESCRIPTION\n";
                break;
            case DEADLINE:
                error += "    ERROR IN ADDING DEADLINE: MISSING DESCRIPTION\n";
                break;
            case EVENT:
                error += "    ERROR IN ADDING EVENT: MISSING DESCRIPTION\n";
                break;
            }
            break;
        case MISSING_TIMING:
            switch (commandType){
            case DEADLINE:
                error += "    ERROR IN ADDING DEADLINE: MISSING DUE DATE\n";
                break;
            case EVENT:
                error += "    ERROR IN ADDING EVENT: MISSING SCHEDULED DATE\n";
                break;
            }
            break;
        case INVALID_INDEX:
            switch (commandType){
            case DONE:
                error += "    ERROR IN MARKING TASK DONE: INVALID INDEX\n";
                break;
            case DELETE:
                error += "    ERROR IN DELETING TASK: INVALID INDEX\n";
                break;
            }
            break;
        }
        return error;
    }

    public static void wrongTimeFormat() {
        System.err.println("    ERROR IN ADDING DEADLINE: WRONG FORMAT\n    Format: YYYY-MM-DD\n");
    }
}
