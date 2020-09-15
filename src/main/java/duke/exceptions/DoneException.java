package duke.exceptions;

/**
 * Represents the exception from the help function
 * when the function receives an inappropriate command
 */
public class DoneException extends DukeException{
    public DoneException(int currentTask, int totalTask){

        super("You do not have " + (currentTask + 1) + " tasks!\n"
                + "Choose a number less than equals to " + totalTask + "!");
    }

    @Override
    public String toString(){
        return "Error";
    }

}
