package service;

import exceptions.DukeException;
import exceptions.ServiceException;

import java.util.ArrayList;
import java.util.function.Function;

public class DukeService {
    private ArrayList<Task> tasks;

    public DukeService() {
        tasks = new ArrayList<>();
    }

    private String numberOfElementsAnnouncement() {
         return String.format("You have %d elements in the list", tasks.size());
    }

    public DukeResponse addTask(Task toAdd) {
        tasks.add(toAdd);
        return new DukeResponse("Added: " + toAdd + "\n" + numberOfElementsAnnouncement() + "\n");
    }

    public void addInitializedTasks(ArrayList<Task> otherTasks) {
        this.tasks.addAll(otherTasks);
    }

    public String[] getParsedTasks(Function<Task, String> parser) {
        String[] results = new String[this.tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            results[i] = parser.apply(tasks.get(i));
        }
        return results;
    }

    public DukeResponse deleteTask(int position) throws ServiceException {
        position--;
        if (position < 0 || position >= tasks.size()) {
            throw new ServiceException("Position to delete is not valid :(");
        }
        Task toRemove = tasks.get(position);
        tasks.remove(position);
        StringBuilder sb = new StringBuilder();
        sb.append("A task has been deleted: \n").append(toRemove).append("\n").append(numberOfElementsAnnouncement());
        return new DukeResponse(sb.toString());
    }

    public DukeResponse getAllJobs() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i));
            sb.append("\n");
        }
        return new DukeResponse(sb.toString());
    }

    public DukeResponse markAsDone(int position) {
        position--;
        if (position < 0 || position >= tasks.size()) {
            return new DukeResponse("Invalid number");
        }
        tasks.get(position).markAsDone();
        return new DukeResponse("Good job, you have finished this task!: \n" + tasks.get(position) + "\n");
    }
}
