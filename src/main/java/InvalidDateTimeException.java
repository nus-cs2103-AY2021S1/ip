public class InvalidDateTimeException extends InvalidDateException {

    public static String DTF = "The following DateTimeFormat is valid:\n"
            + "{YYYY-MM-DD}T{HH:MM:SS}\n"
            + "{YYYY-MM-DD}T{HH:MM}\n"
            + "{YYYY-MM-DD}\n"
            + "{HH:MM:SS}\n"
            + "{HH:MM}";

    public InvalidDateTimeException() {
        super("" + DTF);
    }

    protected InvalidDateTimeException(String msg) {
        super(msg + "\n\n" + DTF);
    }
}