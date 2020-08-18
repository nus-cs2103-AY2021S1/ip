package service;

import java.util.ArrayList;

public class DukeService {
    private ArrayList<Task> tasks;

    public DukeService() {
        tasks = new ArrayList<>();
    }

    public DukeResponse addTask(String description) {
        tasks.add(new Task(description));
        return new DukeResponse("Added: " + description + "\n");
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
