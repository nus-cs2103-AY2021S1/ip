public class BobInvalidNumberException extends Exception {
    private final static String ERR =
            "Sorry but there is no valid task number. Please enter a valid task number.\n";



    public BobInvalidNumberException () {
        super (ERR);
    }

    @Override
    public String toString() {
        return "========================================================================================\n" +
                ERR +
                "========================================================================================\n";

    }
}
