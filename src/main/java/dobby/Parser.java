package dobby;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import dobby.task.Deadline;
import dobby.task.Event;
import dobby.task.Task;
import dobby.task.Todo;

/**
 * Parses the input given by the user and interacts with TaskList accordingly
 */
public class Parser {
    private static final String ALL_COMMANDS = "You can use the following commands in this chat bot:"
            + "\n  " + (Commands.TODO).getUsage()
            + "\n  " + (Commands.DEADLINE).getUsage()
            + "\n  " + (Commands.EVENT).getUsage()
            + "\n  " + (Commands.LIST).getUsage()
            + "\n  " + (Commands.DONE).getUsage()
            + "\n  " + (Commands.DELETE).getUsage()
            + "\n  " + (Commands.SCHEDULED).getUsage()
            + "\n  " + (Commands.FIND).getUsage()
            + "\n  " + (Commands.FINDTYPE).getUsage()
            + "\n  " + (Commands.BYE).getUsage();

    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the chat bot reply or the error message depending on the input
     * @param text text inputted by user
     * @return message reply to user based on input
     * @throws DobbyException based on conditions with customised message
     */
    public String getMessage(String text) throws DobbyException {
        String message = "";

        if (text.equalsIgnoreCase("bye")) { // bye command
            message = "Goodbye.\nHope to see you again soon!";
        } else if (text.startsWith("todo")) { // todo command
            try {
                text = text.substring(5).trim();
                Todo todo = new Todo(text);
                this.tasks.addToList(todo);
                message = "Great! I've added the following task:\n  " + todo.getDescription()
                        + String.format("\nNow you have %d task%s in the list.", (this.tasks).getSize(),
                        this.tasks.getSize() > 1 ? "s" : "");
            } catch (StringIndexOutOfBoundsException e) {
                throw new DobbyException("Incorrect usage of command.\nDescription cannot be empty. "
                        + "Please try again.\n  " + (Commands.TODO).getUsage());
            }
        } else if (text.startsWith("deadline")) { // deadline command
            String by = "";
            try {
                text = text.substring(9).trim();

                if (text.indexOf("/by") <= 1) { // empty description or /by missing
                    throw new DobbyException("Incorrect usage of command.\nDescription cannot be empty. "
                            + "Please try again.\n  " + (Commands.DEADLINE).getUsage());
                }

                by = text.substring(text.indexOf("/by") + 4).trim();
                text = text.substring(0, text.indexOf("/by") - 1).trim();
                Deadline deadline = new Deadline(text, by);

                if (by.substring(1 + by.lastIndexOf(' ')).length() > 4) {
                    throw new DobbyException("Incorrect usage of command.\n"
                            + "Details of time should be in 24hr format with only 4 digits. "
                            + "Please try again.\n  " + (Commands.DEADLINE).getUsage());
                }
                this.tasks.addToList(deadline);
                message = "Great! I've added the following task:\n  " + deadline.getDescription()
                        + String.format("\nNow you have %d task%s in the list.", (this.tasks).getSize(),
                        this.tasks.getSize() > 1 ? "s" : "");
            } catch (StringIndexOutOfBoundsException e) {
                if (text.startsWith("deadline") || text == null) { // description empty
                    throw new DobbyException("Incorrect usage of command.\nDescription cannot be empty. "
                            + "Please try again.\n  "
                            + (Commands.DEADLINE).getUsage());
                } else { // no deadline details specified
                    throw new DobbyException("Incorrect usage of command.\nDeadline details cannot be empty. "
                            + "Please try again.\n  "
                            + (Commands.DEADLINE).getUsage());
                }
            } catch (DateTimeParseException e) {
                throw new DobbyException("Incorrect usage of command.\nThe format of the date in incorrect. "
                        + "Please try again.\n  "
                        + (Commands.DEADLINE).getUsage());
            } catch (DobbyException e) { // empty description or /by missing
                return e.getMessage();
            }
        } else if (text.startsWith("event")) { // event command
            String at = "";
            try {
                text = text.substring(6).trim();

                if (text.indexOf("/at") <= 1) { // empty description or /at missing
                    throw new DobbyException("Incorrect usage of command.\nDescription cannot be empty. "
                            + "Please try again.\n  "
                            + (Commands.EVENT).getUsage());
                }

                at = text.substring(text.indexOf("/at") + 4);
                text = text.substring(0, text.indexOf("/at") - 1);
                Event event = new Event(text, at);

                if (at.substring(1 + at.lastIndexOf(' ')).length() > 4) {
                    throw new DobbyException("Incorrect usage of command.\n"
                            + "Details of time should be in 24hr format with only 4 digits. Please try again.\n  "
                            + (Commands.EVENT).getUsage());
                }

                this.tasks.addToList(event);
                message = "Great! I've added the following task:\n  " + event.getDescription()
                        + String.format("\nNow you have %d task%s in the list.", (this.tasks).getSize(),
                        this.tasks.getSize() > 1 ? "s" : "");
            } catch (StringIndexOutOfBoundsException e) {
                if (text.startsWith("event") || text == null) { // description empty
                    throw new DobbyException("Incorrect usage of command.\nDescription cannot be empty. "
                            + "Please try again.\n  " + (Commands.EVENT).getUsage());
                } else { // no schedule details specified
                    throw new DobbyException("Incorrect usage of command.\nSchedule details cannot be empty. "
                            + "Please try again.\n  " + (Commands.EVENT).getUsage());
                }
            } catch (DateTimeParseException e) {
                throw new DobbyException("Incorrect usage of command.\nThe format of the date in incorrect. "
                        + "Please try again.\n  " + (Commands.EVENT).getUsage());
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
                    throw new DobbyException("Incorrect usage of command.\n"
                            + "Task number must be within the correct range.\n  "
                            + (Commands.DONE).getUsage());
                }
                Task task = (this.tasks).getTask(index - 1);
                task.setDone();

                message = "Great! I've marked this task as done:\n  " + task.getDescription();
            } catch (DobbyException e) { // if index is out of range return message
                return e.getMessage();
            } catch (Exception e) { // missing number after done
                throw new DobbyException("Incorrect usage of command.\n"
                        + "Please enter a task number after done.\n  "
                        + (Commands.DONE).getUsage());
            }
        } else if (text.startsWith("delete")) { // delete command
            try {
                text = text.substring(6).trim();

                int index = Integer.parseInt(text);
                if ((this.tasks).getSize() < index) { // if index is out of range throw exception
                    throw new DobbyException("Incorrect usage of command.\n"
                            + "Task number must be within the correct range.\n  "
                            + (Commands.DELETE).getUsage());
                }
                Task task = tasks.getTask(index - 1);
                this.tasks.removeTask(index - 1);

                message = "Noted. I've removed this task:\n  " + task.getDescription();
            } catch (DobbyException e) { // if index is out of range return message
                return e.getMessage();
            } catch (Exception e) { // missing number after done
                throw new DobbyException("Incorrect usage of command.\n"
                        + "Please enter a task number after delete.\n  "
                        + (Commands.DELETE).getUsage());
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
                throw new DobbyException("Incorrect usage of command.\n"
                        + "The format of the date in incorrect. Please try again.\n  "
                        + (Commands.SCHEDULED).getUsage());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DobbyException("Incorrect usage of command.\n"
                        + "The format of the date in incorrect. Please try again.\n  "
                        + (Commands.SCHEDULED).getUsage());
            }
        } else if (text.startsWith("findtype")) {
            try {
                String type = text.substring(text.indexOf(' ') + 1);

                if (type.length() > 1) {
                    throw new DobbyException("Incorrect usage of command. "
                            + "Please try again.\n  " + (Commands.FINDTYPE).getUsage());
                }
                if (!(type.equalsIgnoreCase("T")
                        || type.equalsIgnoreCase("D")
                        || type.equalsIgnoreCase("E"))) {
                    throw new DobbyException("Incorrect usage of command.\n"
                            + "Type can be T, D, or E only. Please try again.\n  "
                            + (Commands.FINDTYPE).getUsage());
                }

                message = (this.tasks).findOfType(type);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DobbyException("Incorrect usage of command.\n"
                        + "Type required cannot be empty. Please try again.\n  "
                        + (Commands.FINDTYPE).getUsage());
            }
        } else if (text.startsWith("find")) {
            try {
                String keyword = (text.substring(text.indexOf(' '))).substring(1);

                if (keyword.indexOf(' ') >= 0) {
                    throw new DobbyException("Incorrect usage of command.\n"
                            + "You can only give a single word. Please try again.\n  "
                            + (Commands.FINDTYPE).getUsage());
                } else if (keyword.length() == 0) {
                    throw new DobbyException("Incorrect usage of command.\n"
                            + "Keyword required cannot be empty. Please try again.\n  "
                            + (Commands.FIND).getUsage());
                }

                message = (this.tasks).findWithKeyword(keyword);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DobbyException("Incorrect usage of command.\n"
                        + "Keyword required cannot be empty. Please try again.\n  "
                        + (Commands.FIND).getUsage());
            }
        } else if (text.equalsIgnoreCase("help")) {
            message = ALL_COMMANDS;
        } else { // unexpected input
            message = "Sorry that command is not supported. Please try again.\n" + ALL_COMMANDS;
            throw new DobbyException(message);
        }
        return message;
    }
}
