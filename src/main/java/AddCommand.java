import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddCommand extends Command {

    private String taskType;
    private String taskDescription;

    public AddCommand(String input) {
        assert input != null;

        String[] splitInput = input.split(" ", 2);
        this.taskType = splitInput[0];
        this.taskDescription = splitInput[1].trim();
    }

    public String handle(String input, TaskManager taskManager, Storage fileHandler) throws IOException {

        if (this.taskType.equals("todo")) {
            //if the task is a todo task
            Todo todo = new Todo(this.taskDescription);
            taskManager.addTask(todo);
            fileHandler.writeToFile(taskManager);

            return "Got it. I've added this task:\n"
                    + todo + "\nNow you have " + taskManager.getNumTasks()
                    + " tasks in the list";

        } else if (this.taskType.equals("deadline")) {
            //if the task is a deadline task
            String task = this.taskDescription.replace("/by", "/");

            String e = DukeExceptionHandler.handleIncorrectDeadlineInputException(task);

            if (e != null) {
                return e;
            }

            String taskName = TextAndTaskConverter.getTaskName(task);
            LocalDate date = TextAndTaskConverter.getDate(task);

            Deadline deadline = new Deadline(taskName, date);
            taskManager.addTask(deadline);
            fileHandler.writeToFile(taskManager);

            return "Got it. I've added this task:\n"
                    + deadline + "\nNow you have " + taskManager.getNumTasks()
                    + " tasks in the list";

        } else {
            //if task is an event task
            String task = this.taskDescription.replace("/at", "/");

            String e = DukeExceptionHandler.handleIncorrectEventInputException(task);

            if (e != null) {
                return e;
            }

            String taskName = TextAndTaskConverter.getTaskName(task);
            LocalDate date = TextAndTaskConverter.getDate(task);
            LocalTime time = TextAndTaskConverter.getTime(task);

            Event event = new Event(taskName, date, time);
            taskManager.addTask(event);
            fileHandler.writeToFile(taskManager);

            return "Got it. I've added this task:\n"
                    + event + "\nNow you have " + taskManager.getNumTasks()
                    + " tasks in the list";
        }
    }
}
