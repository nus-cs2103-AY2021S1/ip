public class InvalidCommandException extends Exception {
    @Override
    public String toString() {
        String lines = "____________________\n";
        return lines
                + "I do not know what you mean, care to try again?\n"
                + lines;
    }
}
