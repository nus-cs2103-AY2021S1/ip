import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskManager {

    private final List<Task> tasks;

    private boolean isExit = false;

    private Ui ui;

    public TaskManager(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    public TaskManager(List<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void handleInput(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        String command = inputArray[0];
        String errorMessage = "";
        try {
            if (command.equals("bye")) { // Exits while loop
                isExit = true;
                this.ui.showExitMessage();
            } else if (command.equals("list")) {
                this.ui.displayTasks(this.tasks);
            } else if (command.equals("done")) {
                this.markTaskAsDone(inputArray[1]);
            } else if (command.equals("todo")) {
                String description = inputArray[1];
                Task task = new Todo(description);
                this.addTask(task);
            } else if (command.equals("deadline")) {
                String[] deadline = inputArray[1].split("/by");
                String description = deadline[0].trim();
                String by = deadline[1].trim();
                LocalDate date = LocalDate.parse(by);
                Task task = new Deadline(description, date);
                this.addTask(task);
            } else if (command.equals("event")) {
                String[] event = inputArray[1].split("/at");
                String description = event[0].trim();
                String at = event[1].trim();
                LocalDate date = LocalDate.parse(at);
                Task task = new Event(description, date);
                this.addTask(task);
            } else if (command.equals("delete")) {
                deleteTask(inputArray[1]);
            } else {
                errorMessage = "Sorry! I don't know what that means...\n";
                throw new DukeException(errorMessage);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if (inputArray.length <= 1) { // Entered keyword without description/task number
                if (isTask(command)) {
                    errorMessage = "OOPS!!! Description of a task cannot be empty :(\n";
                } else if (command.equals("done") || command.equals("delete")) {
                    errorMessage = "Missing task number! " + "Please ensure to key in the task number :)\n";
                }
            } else { // Deadline/Event missing their respective keywords
                if (command.equals("deadline")) {
                    errorMessage = "Please indicate a deadline using the \"/by\" keyword.\n";
                } else if (command.equals("event")) {
                    errorMessage = "Please indicate a timing using the \"/at\" keyword.\n";
                }
            }
            throw new DukeException(errorMessage);
        } catch (DateTimeParseException e) {
            errorMessage = "Invalid date format! "
                    + "Please use the proper date format i.e. yyyy-MM-dd\n";
            throw new DukeException(errorMessage);
        }
    }

    private boolean isTask(String command) {
        return command.equals("todo") || command.equals("deadline") || command.equals("event");
    }

    private void addTask(Task task) {
        this.tasks.add(task);
        this.ui.showAddMessage(task, this.tasks.size());
    }

    private void deleteTask(String taskNumber) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            Task task = this.tasks.remove(index);
            this.ui.showDeleteMessage(task, this.tasks.size());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            // Invalid task number or number out of range
            String errorMessage = "Invalid task number! " + "Please enter a valid task number :)\n";
            throw new DukeException(errorMessage);
        }
    }

    private void markTaskAsDone(String taskNumber) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            Task task = this.tasks.get(index);
            task.markAsDone();
            this.ui.showDoneMessage(task);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            // Invalid task number or number out of range
            String errorMessage = "Invalid task number! " + "Please enter a valid task number :)\n";
            throw new DukeException(errorMessage);
        }
    }
}