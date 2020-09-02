package duke.exceptions;

// Exception for wrong delete command format
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
