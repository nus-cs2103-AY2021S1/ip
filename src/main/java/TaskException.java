public class TaskException extends Exception{

    TaskException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
