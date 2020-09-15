package duke.exceptions;

/**
 * Represents the exception from the help function
 * when the function receives an inappropriate command
 */
public class HelpException extends DukeException{
    public HelpException(){
        super("Please key in an appropriate command after the \"help\"\n" +
                "Enter \"help\" to get the full list of commands \n");
    }

    @Override
    public String toString(){
        return "Error";
    }
}
