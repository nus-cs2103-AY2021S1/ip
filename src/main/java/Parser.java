public class Parser {
    private Task task;
    private String commandType;
    private int index;

    public Parser(String command) {
        this.commandType = "undefined";
        try {
            if (command.equals("bye")) {
                this.commandType = "bye";
            } else if (command.equals("list")) {
                this.commandType = "list";
            } else if (command.startsWith("done")) {
                if (command.length() < 5) {
                    throw new DukeException(":( OOPS!!! Task index not found.");
                }
                this.commandType = "done";
                this.index = Integer.parseInt(command.substring(5)) - 1;
            } else if (command.startsWith("delete")) {
                if (command.length() < 7) {
                    throw new DukeException(":( OOPS!!! Task index not found.");
                }
                this.commandType = "delete";
                this.index = Integer.parseInt(command.substring(7)) - 1;
            } else {
                Task newTask = null;
                if (command.startsWith("todo")) {
                    if (command.length() < 5) {
                        throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
                    }
                    String description = command.substring(5);
                    newTask = new Todo(description);
                } else if (command.startsWith("deadline")) {
                    if (command.length() < 9) {
                        throw new DukeException(":( OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String rest = command.substring(9);
                    int by_position = rest.indexOf("/by ");
                    if (by_position == -1) {
                        throw new DukeException(":( OOPS!!! Deadline time specification not found.");
                    }
                    String description = rest.substring(0, by_position - 1);
                    String by = rest.substring(by_position + 4);
                    newTask = new Deadline(description, by);
                } else if (command.startsWith("event")) {
                    if (command.length() < 6) {
                        throw new DukeException(":( OOPS!!! The description of an event cannot be empty.");
                    }
                    String rest = command.substring(6);
                    int at_position = rest.indexOf("/at ");
                    if (at_position == -1) {
                        throw new DukeException(":( OOPS!!! Event time specification not found.");
                    }
                    String description = rest.substring(0, at_position - 1);
                    String at = rest.substring(at_position + 4);
                    newTask = new Event(description, at);
                }

                if (newTask == null) {
                    throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                this.commandType = "add";
                this.task = newTask;
            }
        } catch(DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getIndex() {
        return this.index;
    }

    public Task getTask() {
        return this.task;
    }

    public String getCommandType() {
        return this.commandType;
    }
}
