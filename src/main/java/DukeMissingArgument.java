public class DukeMissingArgument extends ArrayIndexOutOfBoundsException {
    private final String DESCRIPTION;

    DukeMissingArgument(String description) {
        this.DESCRIPTION = description;
    }
    @Override
    public String toString() {
        return "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The description of " + DESCRIPTION +" cannot be empty.\n" +
                "    ____________________________________________________________";
    }
}
