package duke;

import duke.task.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> list;

    /**
     * Construct a TaskList object
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Construct a TaskList object
     * @param list an ArrayList of Task
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Construction a TaskList object
     * @param reader a BufferedReader to read the ./data/main.java.duke.txt
     * @throws IOException for reading file
     */
    public TaskList(BufferedReader reader) throws IOException {
        if (reader == null) {
            list = new ArrayList<>();
        } else {
            list = readTextFile2List(reader);
        }
    }

    /**
     * Reads all the Tasks in ./data/main.java.duke.txt and puts all the Tasks in an ArrayList
     * @param reader a BufferedReader for reading ./data/main.java.duke.txt
     * @return an ArrayList of all Tasks in ./data/main.java.duke.txt
     * @throws IOException for reading file
     */
    public static ArrayList<Task> readTextFile2List(BufferedReader reader) throws IOException {
        assert reader != null : "BufferedReader passed into readTextFile2List is null";
        ArrayList<Task> list = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] part = line.split("\\|");
            if (part[0].equals("T")) {
                if (part.length > 3) {
                    list.add(new Todo(part[2], part[1].equals("1"), part[3]));
                } else {
                    list.add(new Todo(part[2], part[1].equals("1")));
                }
            } else if (part[0].equals("D")) {
                if (part.length > 4) {
                    list.add(Deadline.of(part[2], part[3], part[1].equals("1"), part[4]));
                } else {
                    list.add(Deadline.of(part[2], part[3], part[1].equals("1")));
                }
            } else {
                if (part.length > 4) {
                    list.add(Event.of(part[2], part[3], part[1].equals("1"), part[4]));
                } else {
                    list.add(Event.of(part[2], part[3], part[1].equals("1")));
                }
            }
        }
        return list;
    }

    /**
     * Gets to Task at the specified index
     * @param index the index of the Task
     * @return the Task at the specified index
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Prints all the Tasks in the TaskList object
     */
    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + list.get(i).toString());
        }
    }

    /**
     * Marks the Task at specified index as done
     * @param index the index of the Task
     * @return the Task that is marked as done
     */
    public Task markTaskDone(int index) {
        if (index > 0 && index <= list.size()) {
            list.get(index - 1).markAsDone();
            return list.get(index - 1);
        } else {
            return null;
        }
    }

    /**
     * Gets the size of the TaskList
     * @return the size of the TaskList
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Removes the Task at specified index
     * @param index the index of the Task
     * @return the removed Task
     */
    public Task delete(int index) {
        if (index > 0 && index <= list.size()) {
            return list.remove(index - 1);
        } else {
            return null;
        }
    }

    /**
     * Adds the Task to TaskList
     * @param task the Task to be added
     */
    public void add(Task task) {
        assert task != null : "Task passed into add method of TaskList class is null";
        list.add(task);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("o(=*T*=)m\nHere are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            res.append((i + 1)).append(" ").append(list.get(i).toString()).append("\n");
        }
        return res.toString();
    }

}
