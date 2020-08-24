package duke.classes;

import duke.exceptions.BlahException;
import duke.exceptions.DukeInvalidTimeException;
import duke.exceptions.EmptyDukeException;
import duke.tasks.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    public List<Task> todoList;

    public TaskList() {
        this.todoList = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.todoList = list;
    }

    public void storeTask(String item) throws BlahException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
        } else if (item.equals("blah")) {
            throw new BlahException();
        } else {
            this.todoList.add(new Task(item, count));
        }
    }


    public Todo storeTodo(String todo) throws EmptyDukeException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else if (todo.length() <= 4) {
            throw new EmptyDukeException("The description of your todo is empty.");
        } else {
            Todo curr = new Todo(todo.substring(5), count, false);
            todoList.add(curr);
            return curr;
        }
    }
    public Deadline storeDeadline(String deadline) throws EmptyDukeException, DukeInvalidTimeException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else if (deadline.length() <= 8) {
            throw new EmptyDukeException("The description of your deadline is empty.");
        } else {
            Deadline curr = new Deadline(deadline.substring(9), count, false);
            todoList.add(curr);
            return curr;
        }
    }
    public Event storeEvent(String event) throws EmptyDukeException, DukeInvalidTimeException {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
            return null;
        } else if (event.length() <= 5) {
            throw new EmptyDukeException("The description of your event is empty.");
        } else {
            Event curr = new Event(event.substring(6), count, false);
            todoList.add(curr);
            return curr;
        }
    }

    public Task markDone(String command) {
        int taskNo = Character.getNumericValue(command.charAt(5)) - 1;
        Task task = todoList.get(taskNo);
        task.isDone = true;
        return task;
    }

    public Task delete(String command, List<Task> list) {
        int taskNo = Character.getNumericValue(command.charAt(7)) - 1;
        Task task = list.remove(taskNo);
        for (int i = taskNo; i < list.size(); i++) {
            todoList.get(i).index = todoList.get(i).index - 1;
        }
        return task;
    }

    public List<Task> find(String query) throws DukeInvalidTimeException {

        query = query.substring(5);
        List<Task> queriedList = new ArrayList<>();

        for (Task task : this.todoList) {
            String description = task.description;
            String[] keywords;
            switch (task.type) {
                case TODO:
                    keywords = description.split("\\s");
                    for (String keyword : keywords) {
                        if (keyword.equals(query)) {
                            queriedList.add(new Todo(description, queriedList.size() + 1, task.isDone));
                        }
                    }
                    break;
                case EVENT:
                case DEADLINE:
                    // to retrieve just the activity
                    keywords = description.substring(0, description.indexOf("/") - 1).split("\\s");
                    for (String keyword : keywords) {
                        if (keyword.equals(query)) {
                            queriedList.add(task.type == TaskType.DEADLINE
                                    ? new Deadline(description, queriedList.size() + 1, task.isDone)
                                    : new Event(description, queriedList.size() + 1, task.isDone));
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return queriedList;
    }


}
