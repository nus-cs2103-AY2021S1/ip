package duke.exceptions;

// Exception for wrong help command format
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
