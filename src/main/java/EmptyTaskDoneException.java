public class EmptyTaskDoneException extends IllegalArgumentException {

    @Override
    public String toString() {
        return "OOPS! Task done cannot be empty!";
    }
}
