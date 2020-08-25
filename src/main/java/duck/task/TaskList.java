package duck.task;

import duck.Parser;
import duck.exception.DuckException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskList {
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
}
