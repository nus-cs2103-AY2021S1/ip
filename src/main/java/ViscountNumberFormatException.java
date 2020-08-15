public class ViscountNumberFormatException extends ViscountException {
    private String argument;
    private static final String ERROR_MESSAGE = "Alas, '%s' is not a valid task number.";

    public ViscountNumberFormatException(String argument) {
        super();
        this.argument = argument;
    }

    @Override
    public String toString() {
        return String.format(ViscountNumberFormatException.ERROR_MESSAGE, this.argument);
    }
}
