package duke.Exceptions;

// Exception for wrong done command format
public class DoneException extends DukeException{
    public DoneException(int currentTask, int totalTask){

        super("You do not have " + (currentTask + 1) + " tasks!\n"
                + "Choose a number less than equals to " + totalTask + "!");
    }

    public String toString(){
        return "Error";
    }

}
