public class BobEmptyTaskException extends Exception {
    private final static String ERR =
            "Sorry, I can't guess your task that well. Please enter a description for your task.\n";


    public BobEmptyTaskException () {
        super (ERR);
    }

    @Override
    public String toString() {
        return "===========================================================================================\n" +
                ERR +
                "===========================================================================================\n";

    }
}
