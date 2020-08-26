package duke.storage;

import java.util.Date;
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
        final String CREATE_NEW_LIST_MESSAGE = "I'll create a new list of tasks.";
        TaskList list;
        try {
            list = StorageHelper.open(this::deserializeTaskList, filepath);
            bot.sayLine(String.format( "Loaded tasks from %s.", filepath ));
        } catch (FileMissingException e) {
            bot.sayLine(String.format("Couldn't find the file %s. %s", filepath, CREATE_NEW_LIST_MESSAGE));
            list = new TaskList();
        } catch (FileReadingException e) {
            bot.sayLine(String.format("Couldn't read the file %s. %s", filepath, CREATE_NEW_LIST_MESSAGE));
            list = new TaskList();
        } catch (DeserializingException e) {
            bot.sayLine(String.format("I don't understand the data in %s. %s", filepath,
                    CREATE_NEW_LIST_MESSAGE));
            list = new TaskList();
        }
        list.connectStorage((taskList) -> {
            try {
                StorageHelper.save(() -> serializeTaskList(taskList), filepath);
            } catch (FileWritingException e) {
                bot.sayLine(String.format("Couldn't save task list to %s!", filepath));
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
