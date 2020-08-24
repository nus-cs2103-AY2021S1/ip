package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager(){
        this.tasks = new ArrayList<Task>();
    }

    public Task addToDo(String name){
        Task newTask = new ToDo(name);
        tasks.add(newTask);
        return newTask;
    }

    public Task addDeadLine(String name, String time){
        Task newTask = new Deadline(name,time);
        tasks.add(newTask);
        return newTask;
    }

    public Task addEvent(String name, String time){
        Task newTask = new Event(name, time);
        tasks.add(newTask);
        return newTask;
    }

    public int getTotalTask(){
        return this.tasks.size();
    }

    public void doTask(int index) throws TaskDoneException {
        this.tasks.get(index-1).setDone();
    }

    public String[] readSavedData(String data) {
        String[] result = data.split(" \\| ");
        for (int i = 0; i < result.length; i ++) {
            result[i] = result[i].trim();
        }
        return result;
    }

    private void createSavedTask(String[] args) throws BadFormatFileException {
        Task newTask;
        switch(args[0]) {
        case "T":
            newTask = addToDo(args[2]);
            break;
        case "D":
            newTask = addDeadLine(args[2], args[3]);
            break;
        case "E":
            newTask = addEvent(args[2], args[3]);
            break;
        default:
            newTask = null;
        }
        if (args[1].equals("1")) {
            try {
                newTask.setDone();
            } catch (TaskDoneException err) {
                System.out.println("Error in Saved Files");
            } catch (NullPointerException err) {
                throw new BadFormatFileException();
            }
        }
    }

    public void readFile(File path) {
        try {
            Scanner scanner = new Scanner(path);
            String nextLine;
            String[] readData;
            while (scanner.hasNext()) {
                nextLine = scanner.nextLine();
                readData = readSavedData(nextLine);
                createSavedTask(readData);
            }
        } catch (FileNotFoundException err) {
            System.out.println("File not found");
        } catch (BadFormatFileException err) {
            System.out.println("File is corrupted");
            tasks = new ArrayList<Task>();
        }
    }

    public String toSaveFormat() {
        String result = "";
        for(Task t: tasks) {
            result += t.toSaveFormat() + "\n";
        }
        return result;
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index-1);
    }

    public String getTaskStatus(int index) {
        return tasks.get(index-1).toString();
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= tasks.size(); i++) {
            output += i + "." + tasks.get(i-1).toString();
            if (i != tasks.size()) {
                output += "\n";
            }
        }
        return output;
    }
}
