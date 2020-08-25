package main.java.task;

import main.java.misc.Parser;
import main.java.misc.Storage;
import main.java.exception.InvalidArgumentException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> database = new ArrayList<>();

    public Task addTask(List<String> tokens) throws InvalidArgumentException {
        Task task;
        String datetimeString = tokens.get(2).trim();
        LocalDateTime datetime = (datetimeString.equals("null"))
                ? LocalDateTime.MIN
                : Parser.stringToTime(datetimeString);

        switch (tokens.get(0)) {
            case "todo":
                task = new Todo(tokens.get(1).trim());
                break;
            case "deadline":
                if (datetimeString.equals("null")) throw new InvalidArgumentException("Deadline's time cannot be empty");
                task = new Deadline(tokens.get(1).trim(), datetime);
                break;
            case "event":
                if (datetimeString.equals("null")) throw new InvalidArgumentException("Event's time cannot be empty");
                task = new Event(tokens.get(1).trim(), datetime);
                break;
            default:
                throw new Error("An unexpected error has occurred");
        }
        if (tokens.get(3).equals("1")) task.markAsDone();
        database.add(task);
        return task;
    }

    public Task getTask(int index) throws InvalidArgumentException {
        if (index <= 0 || index > database.size()) {
            throw new InvalidArgumentException("Invalid argument for the LIST command.");
        }
        return database.get(index - 1);
    }

    public void finishTask(int index) throws InvalidArgumentException {
        if (index <= 0 || index > database.size()) {
            throw new InvalidArgumentException("Out of range argument for DONE command.");
        }
        database.get(index - 1).markAsDone();
    }

    public Task removeTask(int index) throws InvalidArgumentException {
        if (index <= 0 || index > database.size()) {
            throw new InvalidArgumentException("Out of range argument for DELETE command.");
        }
        return database.remove(index - 1);
    }

    public void initialize() throws InvalidArgumentException {
        List<List<String>> data = Storage.readFile();
        for (List<String> tokens: data) {
            addTask(tokens);
        }
    }

    public void save() {
        Storage.writeFile(database);
    }

    public int count() { return database.size();}

    public void clearAll() {
        database.clear();
    }

    public List<String> printTasks() {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < database.size(); i++) {
            output.add((i + 1) + "." + database.get(i));
        }
        return output;
    }

    public List<String> findTasks(String query) {
        query = query.trim();
        List<String> output = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getDescription().contains(query)) {
                output.add(count + "." + database.get(i));
                count++;
            }
        }
        return output;
    }
}
