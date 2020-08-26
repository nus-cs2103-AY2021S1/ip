package duke;

import duke.task.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class TaskList {

    public ArrayList<Task> list;
    public int count;

    public TaskList() {
        this.list = new ArrayList<>();
        this.count = 0;
    }
    public TaskList(Optional<Stream<String>> content) {
        this.list = new ArrayList<>();
        this.count = 0;
        if (content.isEmpty()) {
            //do nothing
        } else {
            content.get().forEach((line) -> {
                System.out.println("the thingy" + line);
                char startChar = line.charAt(0);
                boolean isDone = line.charAt(4) == '1';
                switch (startChar) {
                    case 'T': {
                        String description = line.substring(8);
                        ToDo taskToAdd = new ToDo(description, isDone);
                        this.list.add(taskToAdd);
                        this.count++;
                        break;
                    }
                    case 'D': {
                        String descriptionAndDeadline = line.substring(8);
                        int stringBreak = descriptionAndDeadline.indexOf('|');
                        String deadline = descriptionAndDeadline.substring(stringBreak + 2);
                        String description = descriptionAndDeadline.substring(0, stringBreak - 1);
                        Deadline taskToAdd = new Deadline(description, deadline, isDone);
                        this.list.add(taskToAdd);
                        this.count++;
                        break;
                    }
                    case 'E': {
                        String descriptionAndDate = line.substring(8);
                        int stringBreak = descriptionAndDate.indexOf('|');
                        String date = descriptionAndDate.substring(stringBreak + 2);
                        String description = descriptionAndDate.substring(0, stringBreak - 1);
                        Event taskToAdd = new Event(description, date, isDone);
                        this.list.add(taskToAdd);
                        this.count++;
                        break;
                    }
                }
            });
        }

    }

    public String addTask(Task task) {
        this.list.add(task);
        this.count++;
        return task.toString();
    }
    public String markTaskAsDone(int index) {
        this.list.get(index).markAsDone();
        return this.list.get(index).toString();
    }
    public String deleteTask(int index) {
        String representation = this.list.get(index).toString();
        this.list.remove(index);
        this.count--;
        return representation;
    }
    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        String representation = "";
        if (this.count > 0) {
            for (int i = 0; i < this.count - 1; i++) {
                representation += (i + 1 + ". " + list.get(i) + "\n");
            }
            representation += this.count + ". " + list.get(this.count - 1);
        }
        return representation;
    }
    public String fileText() {
        String representation = "";
        if (this.count > 0) {
            for (int i = 0; i < this.count - 1; i++) {
                representation += (list.get(i).fileText() + "\n");
            }
            representation += list.get(this.count - 1).fileText();
        }
        return representation;
    }
}
