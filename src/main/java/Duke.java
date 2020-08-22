import java.util.ArrayList;
import java.util.List;

public class Duke {

    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String savePath = "data/duke.txt";
    private static final DukeSaver saver = new DukeSaver(savePath);

    private static final List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        saver.loadData(taskList);
        greet();
        while (true) {
            String response = DukeIn.prompt();
            try {
                handleResponse(response);
            } catch (DukeException ex) {
                DukeOut.print(ex.toString());
            }
        }
    }

    private static void handleResponse(String response) throws DukeException {
        String[] parsedResponse = response.split(" ", 2);
        Command command;
        try {
            command = Command.valueOf(parsedResponse[0].toUpperCase());
        } catch (IllegalArgumentException ex) {
            command = Command.INVALID;
        }
        String rest = parsedResponse.length == 1 ? null : parsedResponse[1];
        switch (command) {
        case BYE:
            saver.saveData(taskList);
            exit();
        case LIST:
            handleList();
            break;
        case DONE:
            handleDone(rest);
            break;
        case TODO:
            handleTodo(rest);
            break;
        case DEADLINE:
            handleDeadline(rest);
            break;
        case EVENT:
            handleEvent(rest);
            break;
        case DELETE:
            handleDelete(rest);
            break;
        case INVALID:
            throw new DukeException("Unrecognized command!");
        }
    }

    private static void handleList() {
        if (taskList.isEmpty()) {
            DukeOut.print("You have no tasks!");
            return;
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n    ");
        for (int i = 0; i < taskList.size(); ++i) {
            sb.append((i + 1) + "." + taskList.get(i) + "\n    ");
        }
        sb.setLength(sb.length() - 5);
        DukeOut.print(sb.toString());
    }

    private static void handleDone(String rest) throws DukeException {
        if (rest == null) {
            throw new DukeException("Specify a task!");
        }
        int taskDone;
        try {
            taskDone = Integer.parseInt(rest) - 1;
        } catch (NumberFormatException ex) {
            throw new DukeException("Specify a valid task number!");
        }
        if (taskDone < 0 || taskDone >= taskList.size()) {
            throw new DukeException("No such task!");
        }
        markTaskDone(taskDone);
        DukeOut.print("Nice! I've marked this task as done:\n      " + taskList.get(taskDone));
    }

    private static void handleTodo(String rest) throws DukeException {
        if (rest == null) {
            throw new DukeException("Description of a todo cannot be empty!");
        }
        addToList(new Todo(rest));
    }

    private static void handleDeadline(String rest) throws DukeException {
        if (rest == null) {
            throw new DukeException("Description of a deadline cannot be empty!");
        }
        String[] deadlineParsed = rest.split("/");
        if (deadlineParsed.length == 1) {
            throw new DukeException("Prefix the keyword 'by' with a forward slash!");
        }
        String deadlineName = deadlineParsed[0].trim();
        String[] byParsed = deadlineParsed[1].split(" ", 2);
        if (byParsed.length == 1) {
            throw new DukeException("Deadline due time cannot be empty!");
        }
        String by = byParsed[1];
        addToList(new Deadline(deadlineName, by));
    }

    private static void handleEvent(String rest) throws DukeException {
        if (rest == null) {
            throw new DukeException("Description of an event cannot be empty!");
        }
        String[] eventParsed = rest.split("/");
        if (eventParsed.length == 1) {
            throw new DukeException("Prefix the keyword 'at' with a forward slash!");
        }
        String eventName = eventParsed[0].trim();
        String[] atParsed = eventParsed[1].split(" ", 2);
        if (atParsed.length == 1) {
            throw new DukeException("Event time cannot be empty!");
        }
        String at = atParsed[1];
        addToList(new Event(eventName, at));
    }

    private static void handleDelete(String rest) throws DukeException {
        if (rest == null) {
            throw new DukeException("Specify a task!");
        }
        int deleteIndex;
        try {
            deleteIndex = Integer.parseInt(rest) - 1;
        } catch (NumberFormatException ex) {
            throw new DukeException("Specify a valid task number!");
        }
        if (deleteIndex < 0 || deleteIndex >= taskList.size()) {
            throw new DukeException("No such task!");
        }
        deleteFromList(deleteIndex);
    }

    private static void greet() {
        String greeting = "Hi! I am\n" + LOGO + "\n" + "What can I do for you?";
        System.out.println(greeting);
    }

    private static void exit() {
        String goodbye = "Bye! Hope to see you again!";
        DukeOut.print(goodbye);
        System.exit(0);
    }

    private static void addToList(Task task) {
        taskList.add(task);
        DukeOut.print("Got it. I've added this task:\n      " + task + "\n    " + taskSizeString());
    }

    private static void deleteFromList(int index) {
        Task taskToDelete = taskList.get(index);
        taskList.remove(index);
        DukeOut.print("Noted. I've removed this task:\n      " + taskToDelete + "\n    " + taskSizeString());
    }

    private static String taskSizeString() {
        return "Now you have " + taskList.size() + " task(s) in the list.";
    }

    private static void markTaskDone(int taskNum) {
        Task task = taskList.get(taskNum);
        task.markDone();
    }
}
