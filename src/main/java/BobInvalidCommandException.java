public class BobInvalidCommandException extends Exception {
    private final static String ERR = "Sorry boss, I am not smart enough to understand that. Please give me a valid instruction.\n";


    public BobInvalidCommandException () {
        super (ERR);
    }

    @Override
    public String toString() {
        return "================================================================================================\n" +
                ERR +
                "================================================================================================\n";
    }
}
