public class BobEmptyDateException extends Exception {
    private final static String ERR =
            "Sorry, I can't guess your date that well. Please enter a date.\n";


    public BobEmptyDateException () {
        super (ERR);
    }

    @Override
    public String toString() {
        return "=====================================================================\n" +
                ERR +
               "=====================================================================\n";

    }
}
