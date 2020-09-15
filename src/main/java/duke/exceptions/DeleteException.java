package duke.exceptions;

/**
 * Represents the exception from the delete function
 * when the function receives a command in the wrong format.
 */
public class DeleteException extends DukeException{
    public DeleteException(int currentTask, int totalTask){

        super("You do not have " + (currentTask + 1) + " tasks!\n"
                + "Choose a number less than equals to " + totalTask + "!");
    }

    @Override
    public String toString(){
        return "Error";
    }

}
