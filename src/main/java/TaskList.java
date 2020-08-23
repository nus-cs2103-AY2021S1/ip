import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TaskList {

    private final List<Task> list = new ArrayList<>();
    private final Storage storage;
    private static final String doneErrorMessage = "OOPS!!! Please choose a valid task index to mark as done.\n";
    private static final String deleteErrorMessage = "OOPS!!! Please choose a valid task index to delete.\n";

    TaskList(Storage storage) throws FileNotFoundException, DukeException {
        this.storage = storage;
        Scanner scanner = storage.load();
        while (scanner.hasNext()) {
            String taskString = scanner.nextLine();
            char taskType = taskString.charAt(3);
            boolean isDone = taskString.charAt(6) == '\u2713';
            if (taskType == 'T') {
                ToDo task = new ToDo("todo " + taskString.substring(9));
                if (isDone) {
                    task.markDone();
                }
                list.add(task);
            } else if (taskType == 'E') {
                int index = taskString.indexOf(" (at: ");
                String time = taskString.substring(index + 6, taskString.length() - 1);
                Event task = new Event("event " + taskString.substring(9, index) + " /at " + time);
                if (isDone) {
                    task.markDone();
                }
                list.add(task);
            } else if (taskType == 'D') {
                int index = taskString.indexOf(" (by: ");
                String date = taskString.substring(index + 6, taskString.length() - 1);
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d MMM yyyy"));
                date = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Deadline task = new Deadline("deadline " + taskString.substring(9, index) + " /by " + date);
                if (isDone) {
                    task.markDone();
                }
                list.add(task);
            }
        }
    }

    protected void addTask(Task task) throws IOException {
        list.add(task);
        System.out.println("Added: " + task + "\n");
        storage.saveTasks(toString());
    }

    protected void markTaskDone(String command) throws DukeException {
        try {
            int listIndex = Integer.parseInt(command.substring(5));
            Task task = list.get(listIndex - 1);
            task.markDone();
            System.out.printf("Hurray! %s is now done.\n", task.getTask());
            System.out.println(task + "\n");
            storage.saveTasks(toString());
        } catch (Exception error) {
            throw new DukeException(doneErrorMessage);
        }
    }

    protected void deleteTask(String command) throws DukeException {
        try {
            int listIndex = Integer.parseInt(command.substring(7));
            Task task = list.get(listIndex - 1);
            list.remove(listIndex - 1);
            System.out.printf("Okay %s has been deleted.\n", task.getTask());
            System.out.println(task);
            System.out.println("You now have " + list.size() + " tasks.\n");
            storage.saveTasks(toString());
        } catch (Exception error) {
            throw new DukeException(deleteErrorMessage);
        }
    }

    @Override
    public String toString() {
        String message = "";
        int count = 1;
        for (Task task : list) {
            message += String.format("%d.%s\n"
                    , count++, task);
        }
        return message;
    }
}
