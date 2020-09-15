package duke.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This class is responsible for loading a TaskList from an existing file and creating TaskLists which can be saved to a
 * file.
 */
public class TaskListStorage {
    private String filepath;

    /**
     * Creates a new TaskListStorage which which read from and write to the specified file.
     *
     * @param filepath the path to the file where the TaskList should be loaded from and saved to.
     */
    public TaskListStorage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Tries to load an existing TaskList from a file (specified in the constructor). A new TaskList is created if an
     * existing file cannot be found or read, or if the file has an invalid format. The TaskList returned will attempt
     * to save to the file every time it is modified.
     *
     * @param ui the Ui this TaskList can interact with to display error or other messages to the user.
     * @return an existing or new TaskList which saves to the file every time it is modified.
     */
    public TaskList load(Ui ui) {
        final String createNewListMessage = "I'll create a new list of tasks.";
        TaskList list;
        try {
            list = StorageHelper.open(this::deserializeTaskList, filepath);
            ui.say(String.format("Loaded tasks from %s.", filepath));
        } catch (FileMissingException e) {
            ui.say(String.format("Couldn't find the file %s. %s", filepath, createNewListMessage));
            list = new TaskList();
        } catch (FileReadingException e) {
            ui.say(String.format("Couldn't read the file %s. %s", filepath, createNewListMessage));
            list = new TaskList();
        } catch (DeserializingException e) {
            ui.say(String.format("I don't understand the data in %s. %s", filepath, createNewListMessage));
            list = new TaskList();
        }
        list.connectStorage((taskList) -> {
            try {
                StorageHelper.save(() -> serializeTaskList(taskList), filepath);
            } catch (FileWritingException e) {
                ui.say(String.format("Couldn't save task list to %s!", filepath));
            }
        });
        return list;
    }

    private String serializeTaskList(TaskList tasklist) {
        return IntStream.range(0, tasklist.size()).mapToObj(i -> serializeTask(tasklist.get(i)))
                .collect(Collectors.joining("\n"));
    }

    private TaskList deserializeTaskList(String string) throws DeserializingException {
        if (string.isBlank()) {
            return new TaskList();
        }
        String[] taskStrings = string.split("\n");
        TaskList tasklist = new TaskList();
        for (String taskString : taskStrings) {
            tasklist.add(deserializeTask(taskString));
        }
        return tasklist;
    }

    private String serializeTask(Task task) {
        List<String> tokens = new ArrayList<>();
        tokens.add("T");
        tokens.add(task.getDescription());
        tokens.add(task.isDone() ? "T" : "F");
        if (task instanceof Deadline) {
            tokens.set(0, "D");
            tokens.add(serializeDate(((Deadline) task).getDate()));
        }
        if (task instanceof Event) {
            tokens.set(0, "E");
            Event event = (Event) task;
            tokens.add(serializeDate(event.getStart()));
            tokens.add(serializeDate(event.getEnd()));
        }
        return String.join("\t", tokens);
    }

    private Task deserializeTask(String string) throws DeserializingException {
        try {
            String[] tokens = string.split("\t");
            String description = tokens[1];
            String isDoneString = tokens[2];
            if (!isDoneString.equals("T") && !isDoneString.equals("F")) {
                throw new DeserializingException();
            }
            boolean isDone = isDoneString.equals("T");

            Task task;
            switch (tokens[0]) {
            case "T":
                task = new Task(description, isDone);
                break;
            case "D":
                Date date = deserializeDate(tokens[3]);
                task = new Deadline(description, isDone, date);
                break;
            case "E":
                Date start = deserializeDate(tokens[3]);
                Date end = deserializeDate(tokens[4]);
                task = new Event(description, isDone, start, end);
                break;
            default:
                throw new DeserializingException();
            }

            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DeserializingException();
        }
    }

    private String serializeDate(Date date) {
        return date.toInstant().toEpochMilli() + "";
    }

    private Date deserializeDate(String string) throws DeserializingException {
        try {
            return new Date(Long.parseLong(string));
        } catch (NumberFormatException e) {
            throw new DeserializingException();
        }
    }
}
