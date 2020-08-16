public class InvalidTaskDoneException extends IllegalArgumentException {

    @Override
    public String toString() {
        return "OOPS! No such task exists!";
    }
}
