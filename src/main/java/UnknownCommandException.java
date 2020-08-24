
public class UnknownCommandException extends DukeException{

    UnknownCommandException(String unknown){
        this.errorMessage = String.format("Error: I dont know what '%s' means yet (;-;)" +
                " I'm still learning new commands! "
                + "Try using the 'help' command" +
                " to see what commands are there and their usage!", unknown);
    }
}
