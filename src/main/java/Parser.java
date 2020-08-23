import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    private TaskList list;

    protected Parser(){}

    protected Parser(TaskList list) {
        this.list = list;
    }

    protected Task parseFileData(String line) throws DukeException {
        char taskType = line.charAt(3);
        boolean isDone = line.charAt(6) == '\u2713';
        Task task = null;
        if (taskType == 'T') {
            task = new ToDo("todo " + line.substring(9));
        } else if (taskType == 'E') {
            int index = line.indexOf(" (at: ");
            String time = line.substring(index + 6, line.length() - 1);
            task = new Event("event " + line.substring(9, index) + " /at " + time);
        } else if (taskType == 'D') {
            int index = line.indexOf(" (by: ");
            String date = line.substring(index + 6, line.length() - 1);
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d MMM yyyy"));
            date = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            task = new Deadline("deadline " + line.substring(9, index) + " /by " + date);
        }

        if (isDone) {
            task.markDone();
        }
        return task;
    }

    protected void parseUserInput(String input) {
        System.out.print("\n");
        try {
            if (input.equals("list")) {
                System.out.println("Here are your tasks:");
                System.out.println(list);
            } else if (input.startsWith("done")) {
                list.markTaskDone(input);
            } else if (input.startsWith("todo")) {
                ToDo toDoTask = new ToDo(input);
                list.addTask(toDoTask);
            } else if (input.startsWith("deadline")) {
                Deadline deadlineTask = new Deadline(input);
                list.addTask(deadlineTask);
            } else if (input.startsWith("event")) {
                Event eventTask = new Event(input);
                list.addTask(eventTask);
            } else if (input.startsWith("delete")) {
                list.deleteTask(input);
            }
            else {
                //unrecognised command
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means.\n");
            }
        } catch (DukeException | IOException error) {
            System.out.println(error.getMessage());
        }
    }
}
