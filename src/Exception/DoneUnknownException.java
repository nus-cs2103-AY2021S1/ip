package Exception;

public class DoneUnknownException extends DoneException {
    @Override
    public String toString(){
        String s = "Please provide the number of the task to be marked\n";
        return s;
    }
}
