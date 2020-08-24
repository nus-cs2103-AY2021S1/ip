public class EmptyArgumentException extends DukeException {
    private String arg;

    public EmptyArgumentException() {
        this.arg = "Arguments";
    }
    public EmptyArgumentException(String arg) {
        this.arg = arg;
    }

    @Override
    public String toString () {
        return String.format("%s cant be empty bro", arg) ;
    }
}