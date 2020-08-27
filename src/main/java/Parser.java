public class Parser {

    public static String parse(String command) {
        String trimmed = command.trim();
        String first = trimmed.split(" ")[0].trim();

        if (first.equals("todo") || first.equals("deadline") || first.equals("event") || first.equals("list")
                || first.equals("done") || first.equals("delete") || first.equals("bye")) {
            return command;

        } else {
            return " ";
        }
    }
}
