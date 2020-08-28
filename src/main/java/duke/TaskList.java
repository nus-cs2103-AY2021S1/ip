package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public String summarize() {
        String all_tasks = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = tasks.get(i);
            all_tasks += String.format("%d.%s\n", i+1, t.toString());
        }
        return all_tasks;
    }

    public String mark_done(int index) {
        if (!this.tasks.get(index).isDone()) {
            this.tasks.get(index).done();
            return String.format("Nice! I've marked this task as done:\n[✓] %s\n", this.tasks.get(index).getName());
        } else {
            return String.format("Task %d already done!\n", index);
        }
    }

    public String deleteTask(int index) {
        if (tasks.size() <= index) {
            return "No such task\n";
        }
        Task t = this.tasks.get(index);
        this.tasks.remove(index);
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.\n", t.toString(), this.tasks.size());
    }

    public String addTask(Task t) {
        tasks.add(t);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n", t.toString(), this.tasks.size());
    }



    public String findTasksWith(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            matchName(tasks.get(i), keyword, matches);
        }
        
        // make String
        String output_msg = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < matches.size(); i++) {
            Task t = matches.get(i);
            output_msg += String.format("%d.%s\n", i+1, t.toString());
        }
        return output_msg;
    }

    public static void matchName(Task t, String keyword, ArrayList<Task> matches) {
        if (t.containsKeyword(keyword)) {
            matches.add(t);
        }
    }

}