package duke.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import duke.Bot;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

public class TaskListStorage {
    private String filepath;

    public TaskListStorage(String filepath) {
        this.filepath = filepath;
    }

    public TaskList load(Bot bot) {
        final String createNewListMessage = "I'll create a new list of tasks.";
        TaskList list;
        try {
            list = StorageHelper.open((s) -> deserializeTaskList(s), filepath);
            bot.sayLine(String.format( "Loaded tasks from %s.", filepath ));
        } catch (FileMissingException e) {
            bot.sayLine(String.format("Couldn't find the file %s. %s", filepath, createNewListMessage));
            list = new TaskList();
        } catch (FileReadingException e) {
            bot.sayLine(String.format("Couldn't read the file %s. %s", filepath, createNewListMessage));
            list = new TaskList();
        } catch (DeserializingException e) {
            bot.sayLine(String.format("I don't understand the data in %s. %s", filepath,
                    createNewListMessage));
            list = new TaskList();
        }
        list.connectStorage((taskList) -> {
                try {
                    StorageHelper.save(() -> serializeTaskList(taskList), filepath);
                } catch (FileWritingException e) {
                    // TODO show an error message. Need to make Duke implement Bot.
                }
        });
        return list;
    }

    private String serializeTaskList(TaskList tasklist) {
        return IntStream
            .range(0, tasklist.size())
            .mapToObj(i -> serializeTask(tasklist.get(i)))
            .collect(Collectors.joining("\n"));
    }

    private TaskList deserializeTaskList(String string) throws DeserializingException {
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
            tokens.add(((Deadline) task).getBy());
        }
        if (task instanceof Event) {
            tokens.set(0, "E");
            tokens.add(((Event) task).getAt());
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
            boolean isDone = isDoneString.equals("T") ? true : false;

            Task task;
            switch (tokens[0]) {
            case "T":
                task = new Task(description, isDone);
                break;
            case "D":
                String by = tokens[3];
                task = new Deadline(description, isDone, by);
                break;
            case "E":
                String at = tokens[3];
                task = new Event(description, isDone, at);
                break;
            default:
                throw new DeserializingException();
            }

            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DeserializingException();
        }
    }
}
