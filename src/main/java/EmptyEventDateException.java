public class EmptyEventDateException extends IllegalArgumentException {

    @Override
    public String toString() {
        return "OOPS! The date / time of event cannot be empty!";
    }
}
