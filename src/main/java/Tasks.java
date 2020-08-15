package ip.src.main.java;

import java.util.ArrayList;

public class Tasks {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Layout layout = new Layout();

    public void addTask(String type, String [] arr) {
        Task task;
        String date = getInfo(arr)[0];
        String description = getInfo(arr)[1];

        switch(type) {
            case "todo":
                task =  new Todo(description);
                break;
            case "deadline":
                task = new Deadline(description, date);
                break;
            case "event":
                task = new Event(description, date);
                break;
            default:
                task = new Todo(description);
                break;
        }

        tasks.add(task);
        layout.printAddedMessage(task.toString(), tasks.size());
    }


    public void showTasks() {
        layout.printTaskList(tasks);
    }

    public void markDone(String i) {
        int index = Integer.parseInt(i);
        try {
            Task task = tasks.get(index - 1);
            task.markDone();
            layout.printMarkedDone(task);
        } catch (IndexOutOfBoundsException e) {
            layout.print("No task labelled " + i);
        }

    }

    public String [] getInfo(String [] arr) {
        boolean reached = false;
        String date = "";
        String description = "";
        for (int i = 1; i < arr.length; i ++) {
            if (reached) {
                date += arr[i] + " ";
            } else if (arr[i].equals("/by") || arr[i].equals("/at")) {
                reached = true;
            } else {
                description += arr[i] + " ";
            }
        }
        return new String[]{date, description};
    }

}
