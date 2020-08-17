public class InvalidTaskIndexException extends Exception {

    @Override
    public String toString() {
        return "☹ OOPS!!! This task index does not exist in your list.";
    }

}
