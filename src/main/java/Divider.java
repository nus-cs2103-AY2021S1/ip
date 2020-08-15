public class Divider {
    private static final String DIVIDER = "――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――";

    public Divider() {

    }

    public String wrapInDivider(String message) {
        return (DIVIDER + "\n " + message + "\n" + DIVIDER);
    }

}
