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
            tasks.add(task, "", TaskType.TODO);
            String text = "Added ToDo '" + task + "' to your list!";
            return text;
        } else {
            throw(DukeException.emptyDesc("todo"));
        }
    }

    private static String handleDeadline(TaskList tasks, String input) throws DukeException {
        String basePattern = "(deadline\\s)(.+)";
        String completePattern = "(deadline\\s)(.+)\\s(/by\\s)(.+)";
        String missingTaskPattern = "(deadline\\s)(/by)((\\s(.*))*)";
        if (input.trim().matches(basePattern)) {
            if (input.trim().matches(completePattern)) {
                String task = input.replaceAll(completePattern, "$2");
                String time = input.replaceAll(completePattern, "$4");
                tasks.add(task, time, TaskType.DEADLINE);
                String text = "Added Deadline '" + task + "' to your list!";
                return text;
            } else if (input.trim().matches(missingTaskPattern)) {
                throw(DukeException.missingTask());
            } else {
                throw(DukeException.missingTime("by"));
            }
        } else {
            throw(DukeException.emptyDesc("deadline"));
        }
    }

    private static String handleEvent(TaskList tasks, String input) throws DukeException {
        String basePattern = "(event\\s)(.+)";
        String completePattern = "(event\\s)(.+)\\s(/at\\s)(.+)";
        String missingTaskPattern = "(event\\s)(/at)((\\s(.*))*)";
        if (input.trim().matches(basePattern)) {
            if (input.trim().matches(completePattern)) {
                String task = input.replaceAll(completePattern, "$2");
                String time = input.replaceAll(completePattern, "$4");
                tasks.add(task, time, TaskType.EVENT);
                String text = "Added Event '" + task + "' to your list!";
                return text;
            } else if (input.trim().matches(missingTaskPattern)) {
                throw(DukeException.missingTask());
            } else {
                throw(DukeException.missingTime("at"));
            }
        } else {
            throw(DukeException.emptyDesc("event"));
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
        } else {
            return handleOthers();
        }
    }
}
