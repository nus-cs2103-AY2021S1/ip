import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Parser {
    private static String printList(TaskList tasks) throws DukeException {
        try {
            return tasks.listContents();
        } catch (DukeException e) {
            throw(e);
        }
    }

    private static String printDone(TaskList tasks, String input) throws DukeException {
        try {
            String pattern = "(done\\s)(.+)";
            if (input.trim().matches(pattern)) {
                String number = input.substring(5);
                int index = parseInt(number) - 1;
                String task = tasks.done(index);
                return "Marked this task as done:\n" + task;
            } else {
                throw(DukeException.emptyDesc("done"));
            }
        } catch (NumberFormatException e) {
            throw(DukeException.typeMismatch("done"));
        } catch (IndexOutOfBoundsException e) {
            throw(DukeException.outOfBounds());
        }
    }

    private static String handleToDo(TaskList tasks, String input) throws DukeException {
        String pattern = "(todo\\s)(.+)";
        if (input.trim().matches(pattern)) {
            String task = input.replaceAll(pattern, "$2");
            tasks.add(task, LocalDateTime.now(), TaskType.TODO);
            return "Added ToDo '" + task + "' to your list!";
        } else {
            throw(DukeException.emptyDesc("todo"));
        }
    }

    private static String handleDeadline(TaskList tasks, String input) throws DukeException {
        String basePattern = "(deadline\\s)(.+)";
        String almostCompletePattern = "(deadline\\s)(.+)\\s(/by\\s)(.+)";
        String datePattern = "(\\d\\d\\d\\d-[01]\\d-[0123]\\d)\\s";
        String timePattern = "([012]\\d)([012345]\\d)";
        String completePattern = "(deadline\\s)(.+)\\s(/by\\s)"+ datePattern + timePattern;
        String missingTaskPattern = "(deadline\\s)(/by)((\\s(.*))*)";
        try {
            if (input.trim().matches(basePattern)) {
                if (input.trim().matches(almostCompletePattern)) {
                    if (input.trim().matches(completePattern)) {
                        String task = input.replaceAll(completePattern, "$2");
                        LocalDateTime dateTime = extractDateTime(input, completePattern);
                        if (LocalDateTime.now().isBefore(dateTime)) {
                            tasks.add(task, dateTime, TaskType.DEADLINE);
                            return "Added Deadline '" + task + "' to your list!";
                        } else {
                            throw(DukeException.pastDateTime());
                        }
                    } else {
                        throw(DukeException.wrongDateTimeFormat());
                    }
                } else if (input.trim().matches(missingTaskPattern)) {
                    throw(DukeException.missingTask());
                } else {
                    throw(DukeException.missingTime("by"));
                }
            } else {
                throw(DukeException.emptyDesc("deadline"));
            }
        } catch (DateTimeParseException e) {
            throw(DukeException.invalidDateTime());
        }
    }

    private static String handleEvent(TaskList tasks, String input) throws DukeException {
        String basePattern = "(event\\s)(.+)";
        String almostCompletePattern = "(event\\s)(.+)\\s(/at\\s)(.+)";
        String datePattern = "(\\d\\d\\d\\d-\\d\\d-\\d\\d)\\s";
        String timePattern = "(\\d\\d)(\\d\\d)";
        String completePattern = "(event\\s)(.+)\\s(/at\\s)"+ datePattern + timePattern;
        String missingTaskPattern = "(event\\s)(/at)((\\s(.*))*)";
        try {
            if (input.trim().matches(basePattern)) {
                if (input.trim().matches(almostCompletePattern)) {
                    if (input.trim().matches(completePattern)) {
                        String task = input.replaceAll(completePattern, "$2");
                        LocalDateTime dateTime = extractDateTime(input, completePattern);
                        if (LocalDateTime.now().isBefore(dateTime)) {
                            tasks.add(task, dateTime, TaskType.EVENT);
                            return "Added Event '" + task + "' to your list!";
                        } else {
                            throw(DukeException.pastDateTime());
                        }
                    } else {
                        throw(DukeException.wrongDateTimeFormat());
                    }
                } else if (input.trim().matches(missingTaskPattern)) {
                    throw(DukeException.missingTask());
                } else {
                    throw(DukeException.missingTime("at"));
                }
            } else {
                throw(DukeException.emptyDesc("event"));
            }
        } catch (DateTimeParseException e) {
            throw(DukeException.invalidDateTime());
        }
    }

    private static String handleOthers() throws DukeException {
        throw(DukeException.unknownCommand());
    }

    private static String delete(TaskList tasks, String input) throws DukeException {
        try {
            String pattern = "(delete\\s)(.+)";
            if (input.trim().matches(pattern)) {
                String number = input.substring(7);
                int index = parseInt(number) - 1;
                String task = tasks.delete(index);
                return "Deleted this task:\n" + task;
            } else {
                throw(DukeException.emptyDesc("delete"));
            }
        } catch (NumberFormatException e) {
            throw(DukeException.typeMismatch("delete"));
        } catch (IndexOutOfBoundsException e) {
            throw(DukeException.outOfBounds());
        }
    }

    public static LocalDateTime extractDateTime(String input, String completePattern) {
        String date = input.replaceAll(completePattern, "$4");
        String hours = input.replaceAll(completePattern, "$5");
        String minutes = input.replaceAll(completePattern, "$6");
        String time = hours + ":" + minutes;
        return LocalDateTime.parse(date + "T" + time);
    }

    private static String handleDueIn(TaskList tasks, String input) throws DukeException {
        try {
            String basePattern = "(due in\\s)(.+)";
            String hourPattern = "(due in\\s)(\\d+)\\s(hours)";
            String dayPattern = "(due in\\s)(\\d+)\\s(days)";
            if (input.trim().matches(basePattern)) {
                if (input.trim().matches(hourPattern)) {
                    long hours = parseInt(input.replaceAll(hourPattern, "$2"));
                    return tasks.extractDueTasksHours(hours);
                } else if (input.trim().matches(dayPattern)) {
                    long days = parseInt(input.replaceAll(dayPattern, "$2"));
                    return tasks.extractDueTasksDays(days);
                } else {
                    throw(DukeException.wrongDueInFormat());
                }
            } else {
                throw(DukeException.emptyDesc("due in"));
            }
        } catch (NumberFormatException e) {
            throw(DukeException.typeMismatch("due in"));
        } catch (IndexOutOfBoundsException e) {
            throw(DukeException.outOfBounds());
        }
    }

    private static String printHelp() {
        String help = "These are the available commands:\n" +
                "bye - exits the program\n" +
                "deadline <description> /by <due date and time> - " +
                "adds a deadline with the given description and due date to the task list\n" +
                "delete <task number> - deletes the task corresponding to the number from the task list\n" +
                "done <task number> - marks the task corresponding to the number as done\n" +
                "event <description> /at <due date and time> - " +
                "adds an event with the given description and due date to the task list\n" +
                "help - shows this list of commands\n" +
                "list - shows the contents of the task list\n" +
                "todo <description> - adds a todo task with the given description to the task list";
        return help;
    }

    public static String parseInput(TaskList tasks, String input) throws DukeException {
        if (input.trim().equals("help")) {
            return printHelp();
        } else if (input.trim().equals("list")) {
            return printList(tasks);
        } else if (input.startsWith("done")) {
            return printDone(tasks, input);
        } else if (input.startsWith("todo")) {
            return handleToDo(tasks, input);
        } else if (input.startsWith("deadline")) {
            return handleDeadline(tasks, input);
        } else if (input.startsWith("event")) {
            return handleEvent(tasks, input);
        } else if (input.startsWith("delete")) {
            return delete(tasks, input);
        } else if (input.startsWith("due in")) {
            return handleDueIn(tasks, input);
        } else {
            return handleOthers();
        }
    }
}
