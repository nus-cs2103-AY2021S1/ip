public class Parser {

    public String[] splitCommandAndDescription(String string) {
        return string.split(" ", 2);
    }

    public String[] splitDeadlineTime(String string) {
        return string.split("/by ", 2);
    }

    public String[] splitEventTime(String string) {
        return string.split("/at ", 2);
    }
}
