public class InvalidIndexException extends Exception {
    @Override
    public String toString() {
        String lines = "____________________\n";
        return lines
                + "Fool, there is no task associated with this number!\n"
                + lines;
    }
}
