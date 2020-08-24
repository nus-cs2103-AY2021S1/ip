public class InvalidArgumentException extends DukeException{
    private String arg;

    public InvalidArgumentException() {
        this.arg = "argument";
    }
    public InvalidArgumentException(String arg) {
        this.arg = arg;
    }

    @Override
    public String toString () {
        return String.format("Invalid %s bro", arg) ;
    }
}