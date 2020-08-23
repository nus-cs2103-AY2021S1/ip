public class InvalidCommandException extends DukeException{
    public InvalidCommandException(String str) {
        super("Invalid Command! " + str);
    }
}
