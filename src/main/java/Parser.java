public class Parser {
    public static Task parse(String input) throws ChatterboxException {
        // Get first word of input
        String command = (input + " ").split(" ")[0];

        if (!input.contains(" ") || input.substring(input.indexOf(' ')).strip().equals("")) {
            throw new ChatterboxException("The description of a " + command + " cannot be empty");
        }
        String contents = input.substring(input.indexOf(' ') + 1);
        switch (command) {
        case "deadline":
            return new Deadline(contents);
        case "todo":
            return new ToDo(contents);
        case "event":
            return new Event(contents);
        default:
            throw new ChatterboxException("Invalid command.");
        }
    }
}
