import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private TaskList tasks;

    private static final String ALL_COMMANDS = "\n    You can use the following commands in this chat bot:"
            + (Commands.TODO).getUsage()
            + (Commands.DEADLINE).getUsage()
            + (Commands.EVENT).getUsage()
            + (Commands.LIST).getUsage()
            + (Commands.DONE).getUsage()
            + (Commands.DELETE).getUsage()
            + (Commands.SCHEDULED).getUsage()
            + (Commands.BYE).getUsage();

    public Parser (TaskList tasks) {
        this.tasks = tasks;
    }
    // Returns the chat bot reply or the error message depending on the input
    public String getMessage(String text) throws DobbyException {
        String message = "";

        if (text.equalsIgnoreCase("bye")) { // bye command
            message = "\n    Goodbye. Hope to see you again soon!\n    ";
        } else if (text.startsWith("todo")) { // todo command
            try {
                text = text.substring(5).trim();
                Todo todo = new Todo(text);
                (this.tasks).addToList(todo);
                message = "\n    Great! I've added the following task:\n      " + todo.getDescription() +
                        String.format("\n    Now you have %d tasks in the list.\n    ", (this.tasks).getSize());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                        + (Commands.TODO).getUsage() + "\n    ");
            }
        } else if (text.startsWith("deadline")) { // deadline command
            String by = "";
            try {
                text = text.substring(9).trim();
                if (text.indexOf("/by") <= 1) { // empty description or /by missing
                    throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                            + (Commands.DEADLINE).getUsage() + "\n    ");
                }
                by = text.substring(text.indexOf("/by") + 4).trim();
                text = text.substring(0, text.indexOf("/by") - 1).trim();
                Deadline deadline = new Deadline(text, by);
                if (by.substring(1 + by.lastIndexOf(' ')).length() > 4) {
                    throw new DobbyException("\n    Incorrect usage of command." +
                            "\n    Details of time should be in 24hr format with only 4 digits. Please try again."
                            + (Commands.DEADLINE).getUsage() + "\n    ");
                }
                (this.tasks).addToList(deadline);
                message = "\n    Great! I've added the following task:\n      " + deadline.getDescription() +
                        String.format("\n    Now you have %d task%s in the list.\n    ",
                                (this.tasks).getSize(), (this.tasks).getSize() > 1 ? "s" : "");
            } catch (StringIndexOutOfBoundsException e) {
                if (text.startsWith("deadline") || text == null) { // description empty
                    throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                            + (Commands.DEADLINE).getUsage()  + "\n    ");
                } else { // no deadline details specified
                    throw new DobbyException("\n    Incorrect usage of command. Deadline details cannot be empty. Please try again."
                            + (Commands.DEADLINE).getUsage()  + "\n    ");
                }
            } catch (DateTimeParseException e) {
                throw new DobbyException("\n    Incorrect usage of command. The format of the date in incorrect. Please try again."
                        + (Commands.DEADLINE).getUsage()  + "\n    ");
            } catch (DobbyException e) { // empty description or /by missing
                return e.getMessage();
            }
        } else if (text.startsWith("event")) { // event command
            String at = "";
            try {
                text = text.substring(6).trim();
                if (text.indexOf("/at") <= 1) { // empty description or /at missing
                    throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                            + (Commands.EVENT).getUsage()  + "\n    ");
                }
                at = text.substring(text.indexOf("/at") + 4);
                text = text.substring(0, text.indexOf("/at") - 1);
                Event event = new Event(text, at);
                if (at.substring(1 + at.lastIndexOf(' ')).length() > 4) {
                    throw new DobbyException("\n    Incorrect usage of command." +
                            "\n    Details of time should be in 24hr format with only 4 digits. Please try again."
                            + (Commands.EVENT).getUsage()  + "\n    ");
                }
                (this.tasks).addToList(event);
                message = "\n    Great! I've added the following task:\n      " + event.getDescription() +
                        String.format("\n    Now you have %d task%s in the list.\n    ",
                                (this.tasks).getSize(), (this.tasks).getSize() > 1 ? "s" : "");
            } catch (StringIndexOutOfBoundsException e) {
                if (text.startsWith("event") || text == null) { // description empty
                    throw new DobbyException("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                            + (Commands.EVENT).getUsage()  + "\n    ");
                } else { // no schedule details specified
                    throw new DobbyException("\n    Incorrect usage of command. Schedule details cannot be empty. Please try again."
                            + (Commands.EVENT).getUsage()  + "\n    ");
                }
            } catch (DateTimeParseException e) {
                throw new DobbyException("\n    Incorrect usage of command. The format of the date in incorrect. Please try again."
                        + (Commands.EVENT).getUsage()  + "\n    ");
            } catch (DobbyException e) { // empty description or /at missing
                return e.getMessage();
            }
        } else if (text.equalsIgnoreCase("list")) { // list command
            message = (this.tasks).getListedTasks();
        } else if (text.startsWith("done")) { // done command
            try {
                text = text.substring(4).trim();
                int index = Integer.parseInt(text);
                if ((this.tasks).getSize() < index) { // if index is out of range throw exception
                    throw new DobbyException("\n    Incorrect usage of command. Task number must be within the correct range."
                            + (Commands.DONE).getUsage() + "\n    ");
                }
                Task task = (this.tasks).getTask(index - 1);
                task.setDone();
                message = "\n    Great! I've marked this task as done:\n      " + task.getDescription() + "\n    ";
            } catch (DobbyException e) { // if index is out of range return message
                return e.getMessage();
            } catch (Exception e) { // missing number after done
                throw new DobbyException("\n    Incorrect usage of command. Please enter a task number after done."
                        + (Commands.DONE).getUsage() + "\n    ");
            }
        } else if (text.startsWith("delete")) { // delete command
            try {
                text = text.substring(6).trim();
                int index = Integer.parseInt(text);
                if ((this.tasks).getSize() < index) { // if index is out of range throw exception
                    throw new DobbyException("\n    Incorrect usage of command. Task number must be within the correct range."
                            + (Commands.DELETE).getUsage() + "\n    ");
                }
                Task task = tasks.getTask(index - 1);
                (this.tasks).removeTask(index - 1);
                message = "\n    Noted. I've removed this task:\n      " + task.getDescription() + "\n    ";
            } catch (DobbyException e) { // if index is out of range return message
                return e.getMessage();
            } catch (Exception e) { // missing number after done
                throw new DobbyException("\n    Incorrect usage of command. Please enter a task number after delete."
                        + (Commands.DELETE).getUsage() + "\n    ");
            }
        } else if (text.startsWith("scheduled")) {
            try {
                String dt = text.substring(text.indexOf(' ') + 1);
                String day = dt.substring(0, dt.indexOf('/'));
                String month = dt.substring(dt.indexOf('/') + 1, dt.lastIndexOf('/'));
                String year = dt.substring(dt.lastIndexOf('/') + 1);
                LocalDate parsedDate = LocalDate.parse(year + "-" + month + "-" + day);
                message = (this.tasks).getScheduledTasks(parsedDate);
            } catch (DateTimeParseException e) {
                throw new DobbyException("\n    Incorrect usage of command. The format of the date in incorrect. Please try again."
                        + (Commands.SCHEDULED).getUsage()  + "\n    ");
            } catch (StringIndexOutOfBoundsException e) {
                throw new DobbyException("\n    Incorrect usage of command. The format of the date in incorrect. Please try again."
                        + (Commands.SCHEDULED).getUsage() + "\n    ");
            }
        } else { // unexpected input
            message =  "\n    Sorry that command is not supported. Please try again." + ALL_COMMANDS + "\n    ";
            throw new DobbyException(message);
        }
        return message;
    }
}
