package main.java;

public class Parser {

    public static String parse(Task task) {
        String taskName = task.getTaskName();
        String isDone = task.isDone() ? "1" : "0";

        String parsed = null;

        if (task instanceof ToDoTask) {
            parsed = "T | " + isDone + " | " + taskName + "\n";
        } else if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            String date = deadlineTask.getDate();
            parsed = "D | " + isDone + " | " + taskName + " | " + date + "\n";
        } else if (task instanceof EventTask) { // task instanceof EventTask
            EventTask eventTask = (EventTask) task;
            String time = eventTask.getTime();
            parsed = "E | " + isDone + " | " + taskName + " | " + time + "\n";
        }

        return parsed;
    }
}
