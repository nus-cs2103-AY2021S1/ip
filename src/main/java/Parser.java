public class Parser {
    public static int isDoneCommand(String cmd, int count) throws InvalidCommandException {
        if (cmd.startsWith("done ")) {
            if (cmd.length() < 6) {
                throw new InvalidCommandException("\u2639 OOPS!!! The task to mark as done cannot be empty.");
            }
            try {
                int n = Integer.parseInt(cmd.substring(5));
                if (n < 1) {
                    throw new InvalidCommandException("\u2639 OOPS!!! The task index should be a positive integer.");
                } else if (n > count) {
                    throw new InvalidCommandException("\u2639 OOPS!!! The task index does not exist.");
                }
                return n;
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("\u2639 OOPS!!! The task index should be a number.");
            }
        } else {
            return -1;
        }
    }

    public static int isDeleteCommand(String cmd, int count) throws InvalidCommandException {
        if (cmd.startsWith("delete ")) {
            if (cmd.length() < 8) {
                throw new InvalidCommandException("\u2639 OOPS!!! The task to mark to delete cannot be empty.");
            }
            try {
                int n = Integer.parseInt(cmd.substring(7));
                if (n < 1) {
                    throw new InvalidCommandException("\u2639 OOPS!!! The task index should be a positive integer.");
                } else if (n > count) {
                    throw new InvalidCommandException("\u2639 OOPS!!! The task index does not exist.");
                }
                return n;
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("\u2639 OOPS!!! The task index should be a number.");
            }
        } else {
            return -1;
        }
    }

    public static Task generate(String cmd) throws InvalidCommandException {
        if (cmd.startsWith("todo")) {
            if (cmd.length() < 5) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            } else if (cmd.charAt(4) != ' ') {
                throw new InvalidCommandException("Do you mean 'todo " + cmd.substring(4) + "'");
            } else if (cmd.length() < 6) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            }
            return new ToDo(cmd.substring(5));
        } else if (cmd.startsWith("deadline")) {
            if (cmd.length() < 9) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
            } else if (cmd.charAt(8) != ' ') {
                throw new InvalidCommandException("Do you mean 'deadline " + cmd.substring(8) + "'");
            } else if (cmd.length() < 10) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
            }
            String description = cmd.substring(9);
            int s = description.indexOf("/by");
            if (s == -1) {
                throw new InvalidCommandException("\u2639 OOPS!!! Time should be specified");
            }
            if (description.length() - s < 4) {
                throw new InvalidCommandException("The time specification cannot be empty.");

            }
            String time = description.substring(s + 4);
            description = description.substring(0, s - 1);
            return new Deadline(description, time);
        } else if (cmd.startsWith("event")) {
            if (cmd.length() < 6) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a event cannot be empty.");
            } else if (cmd.charAt(5) != ' ') {
                throw new InvalidCommandException("Do you mean 'event " + cmd.substring(5) + "'");
            } else if (cmd.length() < 7) {
                throw new InvalidCommandException("\u2639 OOPS!!! The description of a event cannot be empty.");
            }
            String description = cmd.substring(6);
            int s = description.indexOf("/at");
            if (s == -1) {
                throw new InvalidCommandException("\u2639 OOPS!!! Time should be specified");
            }
            if (description.length() - s < 4) {
                throw new InvalidCommandException("The time specification cannot be empty.");

            }
            String time = description.substring(s + 4);
            description = description.substring(0, s - 1);
            return new Event(description, time);
        } else {
            throw new InvalidCommandException();
        }
    }

    public static Input getType(String input, int count) throws InvalidCommandException {
        if (input.equals("bye")) {
            return Input.BYE;
        } else if (input.equals("list")) {
            return Input.LIST;
        } else if (input.startsWith("delete ")) {
            return Input.DELETE;
        } else if (input.startsWith("done ")) {
            return Input.DONE;
        } else if (input.startsWith("happens on ")) {
            return Input.HAPPENS;
        } else {
            return Input.TASK;
        }
    }
}
