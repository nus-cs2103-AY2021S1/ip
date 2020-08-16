public class Divider {
    private static final String DIVIDER = "____________________________________________________________";
    public Divider() {

    }

    public String wrapInDivider(String message) {
        return ("\t" + DIVIDER + "\n\t " + message + "\n\t" + DIVIDER);
    }

}
