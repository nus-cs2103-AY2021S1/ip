import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> todoList;

    public TaskList() {
        this.todoList = new ArrayList<>();
    }

    public TaskList(File savedFile) throws FileNotFoundException {
        Scanner s = new Scanner(savedFile);
        this.todoList = new ArrayList<>();

        while(s.hasNext()) {
            String[] taskInfo = s.nextLine().split(",",4);
            if(taskInfo[0].equals("T")) {

                if(taskInfo[1].equals("1")) {
                    Todos todo = new Todos(taskInfo[2], true);
                    this.todoList.add(todo);
                } else {
                    Todos todo = new Todos(taskInfo[2], false);
                    this.todoList.add(todo);
                }
            } else if (taskInfo[0].equals("D")) {
                if(taskInfo[1].equals("1")) {
                    Deadlines deadline = new Deadlines(taskInfo[2], taskInfo[3], true);
                    this.todoList.add(deadline);
                } else {
                    Deadlines deadline = new Deadlines(taskInfo[2], taskInfo[3], false);
                    this.todoList.add(deadline);
                }
            } else {
                if(taskInfo[1].equals("1")) {
                    Events event = new Events(taskInfo[2], taskInfo[3], true);
                    this.todoList.add(event);
                } else {
                    Events event = new Events(taskInfo[2], taskInfo[3], false);
                    this.todoList.add(event);
                }
            }
        }
    }

    public void addTask(Task task) {
        String path = "/Users/joshua/Desktop/ip/data/duke.txt";

        try {
            String s = formatString(task);

            if (this.todoList.size() == 0) {
                appendToFile(path, s);
            } else {
                appendToFile(path, System.lineSeparator() + s);
            }
            this.todoList.add(task);
            System.out.println("     Got it. I've added this task:" + "\n" + "       " + task.toString());
            System.out.println("     Now you have " + this.todoList.size() + " tasks in the list.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String formatString(Task task) {
        if(task instanceof Todos) {
            if (task.isDone == true) {
                String s = "T,1," + task.description;
                return s;
            } else {
                String s = "T,0," + task.description;
                return s;
            }
        } else if (task instanceof Deadlines) {
            if (task.isDone == true) {
                String s = "D,1," + task.description + "," + ((Deadlines) task).deadline;
                return s;
            } else {
                String s = "D,0," + task.description + "," + ((Deadlines) task).deadline;
                return s;
            }
        } else {
            if (task.isDone == true) {
                String s = "E,1," + task.description + "," + ((Events) task).at;
                return s;
            } else {
                String s = "E,0," + task.description + "," + ((Events) task).at;
                return s;
            }
        }
    }

    public Task get(int index) {
        return this.todoList.get(index);
    }

    public int getLength() {
        return this.todoList.size();
    }

    public void removeTask(int index) {
        String path = "/Users/joshua/Desktop/ip/data/duke.txt";
        try {
            Task removedTask = this.todoList.get(index);
            this.todoList.remove(index);
            System.out.println("     Noted. I've removed this task:" + "\n" + "      " + removedTask.toString());
            System.out.println("     Now you have " + this.todoList.size() + " tasks in the list.");

            String allTasks = formatString(this.todoList.get(0));

            for (int i = 1; i < this.todoList.size(); i++) {
                Task t = this.todoList.get(i);
                String s = formatString(t);
                allTasks = allTasks + "\n" + s;
            }
            writeToFile(path, allTasks);

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void listTasks() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < this.todoList.size(); i++) {
            int listNumber = i + 1;
            Task currentTask = todoList.get(i);
            String taskStatus = currentTask.getStatusIcon();
            System.out.println("     " + listNumber + "." + currentTask.toString());
        }
    }

    public void updateDone() {
        String path = "/Users/joshua/Desktop/ip/data/duke.txt";
        try {
            String allTasks = formatString(this.todoList.get(0));

            for (int i = 1; i < this.todoList.size(); i++) {
                Task t = this.todoList.get(i);
                String s = formatString(t);
                allTasks = allTasks + "\n" + s;
            }
            writeToFile(path, allTasks);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

}
