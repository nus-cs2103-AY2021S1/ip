package main.java;

import java.util.ArrayList;

class TaskList {

    final ArrayList<Task> taskArrayList;

    TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    private boolean isEmpty() {
        return taskArrayList.size() == 0;
    }

    void addToList(Task addTask) {
        this.taskArrayList.add(addTask);
    }

    Task getTaskAt(int taskIndex) {
        return this.taskArrayList.get(taskIndex);
    }

    void deleteTaskAt(int taskIndex) {
        this.taskArrayList.remove(taskIndex);
    }

    public int getNumOfTasks() {
        return this.taskArrayList.size();
    }

    public String toString() {
        String textIndentation = "     ";
        if (this.isEmpty()) {
            return textIndentation + "Your task list is currently empty. YAY!!! :D";
        } else {
            StringBuilder output = new StringBuilder(textIndentation + "Here are the tasks in your list:\n");
            int listSize = this.taskArrayList.size();
            for (int i = 0; i < listSize; i++) {
                String eachTaskString = textIndentation + (i + 1) + ". " + this.taskArrayList.get(i) + "\n";
                output.append(eachTaskString);
            }
            return output.deleteCharAt(output.length()-1).toString();
        }
    }
}
