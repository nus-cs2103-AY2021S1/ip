package service;

import java.util.ArrayList;

public class DukeService {
    private ArrayList<String> jobs;

    public DukeService() {
        jobs = new ArrayList<>();
    }

    public DukeResponse addJob(String newJob) {
        jobs.add(newJob);
        return new DukeResponse("Added: " + newJob + "\n");
    }

    public DukeResponse getAllJobs() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jobs.size(); i++) {
            sb.append(i + 1).append(". ").append(jobs.get(i));
            sb.append("\n");
        }
        return new DukeResponse(sb.toString());
    }
}
