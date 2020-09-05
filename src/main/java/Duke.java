import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The main class with the chat bot dealing with the user's inputs.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final String BYE = "bye";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(Path.of(filePath));
        try {
            taskList = new TaskList(storage.showTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            System.exit(0);
        }
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage(Path.of("duke.txt"));
        try {
            taskList = new TaskList(storage.showTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            System.exit(0);
        }
    }

    public String getResponse(String input) throws DukeException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        String trimmed = input.trim();
        String first = trimmed.split(" ")[0].trim(); // checking the first word
        String last = input.substring(first.length()).trim(); // get rid of the first word

        switch (first) {
        case "done":
            int id = Integer.parseInt(last) - 1;
            String changed = taskList.getTask(id).getDescription();
            String type = taskList.getTask(id).getType();
            taskList.getTask(id).markAsDone();
            storage.saveTasks(taskList.getTasks());
            return ui.doneTask() + "[" + type + "][" + "\u2713" + "]" + changed + "\n";

        case "todo":
            try {
                Todo todo = Todo.makeToDo(last, false);
                taskList.addTask(todo);
                storage.saveTasks(taskList.getTasks());
                return ui.addTask() + todo.toString() + "\n" + ui.showNumberOfTasks(taskList.getTasks());
            } catch (DukeException e) {
                return "Todo cannot be empty";
            }

        case "deadline": {
            String job = last.split("/by")[0].trim();
            String time = last.split("/by")[1].trim();
            LocalDate date = LocalDate.parse(time);
            Deadline work = new Deadline(job + " (by: " + formatter.format(date) + ")", false, date);
            taskList.addTask(work);
            storage.saveTasks(taskList.getTasks());
            return ui.addTask() + work.toString() + "\n" + ui.showNumberOfTasks(taskList.getTasks());
        }

        case "event": {
            String job = last.split("/at")[0].trim();
            String time = last.split("/at")[1].trim();
            LocalDate date = LocalDate.parse(time);
            Event work = new Event(job + " (at: " + formatter.format(date) + ")", false, date);
            taskList.addTask(work);
            storage.saveTasks(taskList.getTasks());
            return ui.addTask() + work.toString() + "\n" + ui.showNumberOfTasks(taskList.getTasks());
        }

        case "find": {
            ArrayList<Task> findTasks = new ArrayList<>();
            for (Task task : taskList.getTasks()) {
                if (task.getDescription().contains(last)) {
                    findTasks.add(task);
                }
            }
            String found = "";
            int index = 1;
            for (Task tsk : findTasks) {
                String desc =  tsk.getDescription();
                String taskType = tsk.getType();
                String statusIcon = tsk.getStatusIcon();
                found = found + index + "." + "[" + taskType + "][" + statusIcon + "] " + desc + "\n";
                index++;
            }
            return ui.printMatchingTasks() + found;

        }

        case "delete": {
            int index = Integer.parseInt(last) - 1;
            String deleted = taskList.getTask(index).getDescription();
            String deletedType = taskList.getTask(index).getType();
            String status = taskList.getTask(index).getStatusIcon();
            Task removedTask = taskList.removeTask(index);
            storage.saveTasks(taskList.getTasks());

            return ui.removeTask() + "[" + deletedType + "][" + status + "] "
                    + deleted + "\n" + ui.showNumberOfTasks(taskList.getTasks());

        }

        case "list": {
            Iterator<Task> iter = taskList.getTasks().iterator();
            int index = 1;
            String message = "";
            while (iter.hasNext()) {
                Task currentTask = iter.next();
                String next = currentTask.getDescription();
                message = message + index + "." + "[" + currentTask.getType() + "]["
                        + currentTask.getStatusIcon() + "] " + next + "\n";
                index++;
            }
            return ui.showList() + message;
        }

        case BYE:
            return ui.exit();

        default:
            return ui.invalidInput();
        }
    }
}
