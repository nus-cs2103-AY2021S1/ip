package duke.storage;

import duke.exceptions.TaskListTranslatorException;
import duke.parsers.DukeDateTimeParser;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskListTranslator {

    public static TaskList decode(List<String> lines) throws TaskListTranslatorException {

        try {
            TaskList taskList = new TaskList();
            for (String line : lines) {
                Task task = null;
                String[] parsed = line.split(" \\| ");
                switch (parsed[0]) {
                case "T":
                    task = new Todo(parsed[2]);
                    break;
                case "D":
                    task = new Deadline(parsed[2], DukeDateTimeParser.parse(parsed[3]));
                    break;
                case "E":
                    task = new Event(parsed[2], DukeDateTimeParser.parse(parsed[3]));
                    break;
                default:
                    break;
                }
                if (parsed[1].equals("1")) {
                    assert task != null;
                    task.markAsDone();
                }
                taskList.addTask(task);
            }
            return taskList;

        } catch (IndexOutOfBoundsException e) {
            throw new TaskListTranslatorException();
        }
    }

    public static List<String> encode(TaskList taskList) {
        ArrayList<String> strings = new ArrayList<>();
        List<Task> tasks = taskList.getTasks();
        for (Task task : tasks) {
            strings.add(task.toFileString());
        }
        return strings;
    }
}
