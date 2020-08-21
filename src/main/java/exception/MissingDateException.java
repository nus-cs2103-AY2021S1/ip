package exception;

public class MissingDateException extends Exception {
    @Override
    public String toString() {
        String lines = "____________________\n";
        return lines
                + "Hey, you need to tell me the date for this.\n"
                + lines;
    }
}
