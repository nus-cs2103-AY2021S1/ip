package duck.task;

import duck.Parser;
import duck.exception.DuckException;


import java.io.Serializable;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TaskList implements Serializable {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public String[] getStatuses() {
        String[] statuses = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            statuses[i] = "" + (i + 1) + ". " + this.tasks.get(i).getStatus();
        }
        return statuses;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public int getLength() {
        return this.tasks.size();
    }

    public Task markDone(int index) throws DuckException {
        if (index > this.tasks.size()) {
            throw new DuckException("No such task with that number!");
        } else {
            Task task = this.tasks.get(index - 1);
            task.markDone();
            return task;
        }
    }

    public Task deleteTask(int index) throws DuckException {
        if (index > this.tasks.size()) {
            throw new DuckException("No such task with that number!");
        } else {
            Task task = this.tasks.get(index - 1);
            this.tasks.remove(index - 1);
            return task;
        }
    }

    public String[] getStatusesByDate(Optional<LocalDate> optionalDate) {

        return this.tasks.stream()
                .filter(TaskWithDate.class::isInstance)
                .map(TaskWithDate.class::cast)
                .sorted((task1, task2) -> task1.getDate().compareTo(task2.getDate()))
                .filter((task) -> {
                    if (optionalDate.isPresent()) {
                        return task.getDate().isBefore(optionalDate.get());
                    } else {
                        return true;
                    }

                })
                .map((task) -> task.getStatus())
                .toArray(String[]::new);
    }

    /**
     * Takes in a search input and parses for the description to filter by.
     * Filters the list of Tasks based on their description and returns the
     * statuses of those that match.
     *
     * @param input Input from user.
     * @return String array containing statuses of tasks that match.
     * @throws DuckException If description field is empty.
     */
    public String[] getStatusesByFind(String input) throws DuckException {
        String description = Parser.parseDescription(input);
        return this.tasks.stream()
                .filter((task) -> task.getDescription().contains(description))
                .map(Task::getStatus)
                .toArray(String[]::new);
    }
}
