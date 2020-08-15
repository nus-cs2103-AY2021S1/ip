public class ViscountException extends Exception {
    private static final String ERROR_MESSAGE = "Alas, I do apologise but I don't know what that means.";

    public ViscountException() {
        super();
    }

    @Override
    public String toString() {
        return ViscountException.ERROR_MESSAGE;
    }
}
