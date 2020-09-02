package Duke;

public class Parser {

    public enum Mode {
        LIST, BYE, DONE, DELETE, TODO, DEADLINE, EVENT, FIND;

    }

    public Mode mode(String input) throws DukeException {
        String command = input.split(" ")[0];
        switch (command) {
            case "todo":
                return Mode.TODO;
            case "deadline":
                return Mode.DEADLINE;
            case "event":
                return Mode.EVENT;
            case "bye":
                return Mode.BYE;
            case "done":
                return Mode.DONE;
            case "delete":
                return Mode.DELETE;
            case "list":
                return Mode.LIST;
            case "find":
                return Mode.FIND;
            default:
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public Task task(String input) throws DukeException {
        String command = input.split(" ")[0];
        switch (command) {
            case "todo":
                return toDo(input);
            case "deadline":
                return deadline(input);
            case "event":
                return event(input);
            default:
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but this is an invalid command :-(");
        }
    }

    private ToDo toDo(String input) throws DukeException {
        String[] inputSplit = input.split(" ");
        if (inputSplit.length < 2) {
            // Throw new error message that todo has no desc
            throw (new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty."));
        } else {
            String name = input.split("todo ")[1];
            return new ToDo(name);
        }
    }

    private Deadline deadline(String input) {
        String name = input.split(" /by ")[0].split("deadline ")[1];
        String by = input.split(" /by ")[1];
        return new Deadline(name, by);

    }

    private Event event(String input) {
        String name = input.split(" /at ")[0].split("event ")[1];
        String at = input.split(" /at ")[1];
        return new Event(name, at);

    }

    public int order(String input) {
        return Integer.parseInt(input.split(" ")[1]);
    }

    public String name(String input) {
        return input.split("find ")[1];
    }
}