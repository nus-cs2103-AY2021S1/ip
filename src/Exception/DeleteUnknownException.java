package Exception;


public class DeleteUnknownException extends DukeException {
    @Override
    public String toString(){
        String s = "Please provide the number of the task to be deleted\n";
        return s;
    }
}
