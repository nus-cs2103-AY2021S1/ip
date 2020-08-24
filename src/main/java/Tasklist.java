package main.java;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasklist){
        this.list = tasklist;
    }

    public void deleteTask(String userinput){
        try {
            if (userinput.length() <= 6) {
                throw new DukeException();
            }
            int taskNumber = Integer.parseInt(userinput.substring(7)) - 1;
            if (taskNumber > list.size()) {
                throw new DukeArrayException();
            }
            Task taskDeleted = list.get(taskNumber);
            list.remove(taskNumber);
            System.out.println("I have removed the task:\n" + taskDeleted.stringify() + "\n" + "Now you have " +
                    list.size() + " tasks in the list.");
        } catch (DukeArrayException e) {
            System.out.println("Number cannot be longer than the list.");
        } catch (DukeException e) {
            System.out.println("Must include number after 'delete'");
        } catch (NumberFormatException e) {
            System.out.println("Must include number after 'delete'");
        }
    }
}
