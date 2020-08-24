public class WrongUsageException extends DukeException{

    WrongUsageException(String cmdName, String cmdUse){
        this.errorMessage = String.format("Error: You used the command '%s' wrongly!" +
                "\nUsage: %s", cmdName, cmdUse);
    }
}
