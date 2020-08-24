public class InvalidDateException extends DukeException {

    public static String DTF = "The following DateTimeFormat is valid:\n"
            + "{YYYY-MM-DD}T{HH:MM:SS}\n"
            + "{YYYY-MM-DD}T{HH:MM}\n"
            + "{YYYY-MM-DD}\n"
            + "{HH:MM:SS}\n"
            + "{HH:MM}";

    public InvalidDateException() {
        super("");
    }

    protected InvalidDateException(String msg) {
        super(msg + "\n" + DTF);
    }
}
