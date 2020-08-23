import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    private TaskList list;

    protected Parser(){}

    protected Parser(TaskList list) {
        this.list = list;
    }

    protected Task parseFileData(String line) {
        char taskType = line.charAt(3);
        boolean isDone = line.charAt(6) == '\u2713';
        Task task = null;
        if (taskType == 'T') {
            task = new ToDo(line.substring(9));
        } else if (taskType == 'E') {
            int index = line.indexOf(" (at: ");
            String time = line.substring(index + 6, line.length() - 1);
            task = new Event(line.substring(9, index), time);
        } else if (taskType == 'D') {
            int index = line.indexOf(" (by: ");
            String date = line.substring(index + 6, line.length() - 1);
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d MMM yyyy"));
            task = new Deadline(line.substring(9, index), localDate);
        }

        if (isDone) {
            task.markDone();
        }
        return task;
    }

    protected void parseUserInput(String input) throws DukeException, IOException {
        System.out.print("\n");

            if (input.equals("list")) {
                System.out.println("Here are your tasks:");
                System.out.println(list);
            } else if (input.startsWith("done")) {
                list.markTaskDone(input);
            } else if (input.startsWith("todo")) {
                if (input.equals("todo")) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
                }
                ToDo toDoTask = new ToDo(input);
                list.addTask(toDoTask);
            } else if (input.startsWith("deadline")) {
                if (input.equals("deadline")) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
                }
                int index = input.indexOf("/by");
                String task = input.substring(9, index - 1);
                LocalDate deadline = LocalDate.parse(input.substring(index + 4),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Deadline deadlineTask = new Deadline(task, deadline);
                list.addTask(deadlineTask);
            } else if (input.startsWith("event")) {
                if (input.equals("event")) {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.\n");
                }
                int index = input.indexOf("/at");
                String task = input.substring(6, index - 1);
                String time = input.substring(index + 4);
                Event eventTask = new Event(task, time);
                list.addTask(eventTask);
            } else if (input.startsWith("delete")) {
                list.deleteTask(input);
            }
            else {
                //unrecognised command
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means.\n");
            }
    }
}
