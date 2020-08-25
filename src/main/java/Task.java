import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Task {
    protected Boolean isDone;
    protected String icon; //tick or cross
    protected String name;

    private final static String TICK = "O";
    private final static String CROSS = "X";
    public static ArrayList<Task> Tasks = new ArrayList<>();

    Task(String name) {
        this.name = name;
        this.isDone = false;
        this.icon = CROSS;
    }
    
    Task(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.icon = isDone ? TICK : CROSS;
    }

    public static void addTask(Task task) {
        Tasks.add(task);
    }
    
    public static void addTaskAndPrint(Task task) {
        Task.addTask(task);
        System.out.println(Duke.HORIZONTAL_LINE
                + "Got it. I've added this task: " + "\n"
                + task.toString() + "\n"
                + String.format("Now you have %s tasks in the list.", Tasks.size()) + "\n"
                + Duke.HORIZONTAL_LINE);
    }

    public static void deleteTask(int i) {
        Task task = Tasks.get(i - 1);
        Tasks.remove(i - 1);
        System.out.println(Duke.HORIZONTAL_LINE
                + "Noted. I've removed this task: " + "\n"
                + task.toString() + "\n"
                + String.format("Now you have %s tasks in the list.", Tasks.size()) + "\n"
                + Duke.HORIZONTAL_LINE);
    }

    public static void saveTasks() {
        File savedTasks = new File(".//.//.//savedTasks.txt");
        boolean exists = savedTasks.exists();
        try {
            if(exists) {
                BufferedWriter taskWriter = new BufferedWriter(new FileWriter(".//.//.//savedTasks.txt"));
                StringBuilder currentTasks = new StringBuilder();
                for(Task task: Tasks) {
                    currentTasks.append(task.encode());
                    currentTasks.append("\n");
                }
                taskWriter.write(currentTasks.toString());
                taskWriter.close();
            } else { //file does not exist, create new file
                savedTasks.createNewFile();
                System.out.println("New save file created");
                saveTasks();
            }
        } catch (IOException ex) {
            System.out.println(Duke.HORIZONTAL_LINE + "Error: " + ex + "\n" + Duke.HORIZONTAL_LINE);
        }
    }
    
    public static void loadTasks() {
        try {
            System.out.println(Duke.HORIZONTAL_LINE + "Reading saved file..." + "\n" + Duke.HORIZONTAL_LINE);
            BufferedReader taskReader = new BufferedReader(new FileReader(".//.//.//savedTasks.txt"));
            String line = taskReader.readLine();
            while(line != null) {
                processTask(line);
                line = taskReader.readLine();
            }
            Task.showTasks();
        } catch (FileNotFoundException e) {
            //Folder not yet created, do nothing
        } catch (IOException e) {
            System.out.println(Duke.HORIZONTAL_LINE + "Error: " + e + "\n" + Duke.HORIZONTAL_LINE);
        }
    }
    
    public static void showTasks() {
        System.out.println(Duke.HORIZONTAL_LINE + "Here are the tasks in your list:");
        int i = 1;
        for (Task task : Task.Tasks) {
            //print out task with numbering
            System.out.println(String.format("%s.", i) + task.toString());
            i++;
        }
        System.out.println(Duke.HORIZONTAL_LINE);
    }
    
    abstract String encode();
    
    private static void processTask(String line) {
        String[] task = line.split(" \\| ");
        Boolean isDone = task[1].equals("1");
        String taskName = task[2];
        String taskDateTime = null;
        if(task.length > 3) {
            taskDateTime = task[3];
        }
        if(line.startsWith("T")) {
            Todo newTodo = new Todo(taskName, isDone);
            Task.addTask(newTodo);
        } else if(line.startsWith("E")) {
            Event newEvent = new Event(taskName, isDone, taskDateTime);
            Task.addTask(newEvent);
        } else if(line.startsWith("D")) {
            Deadline newDeadline = new Deadline(taskName, isDone, taskDateTime);
            Task.addTask(newDeadline);
        } else {
            String message = "Save file on device corrupted";
            System.out.println(Duke.HORIZONTAL_LINE + "Error: " + message + "\n" + Duke.HORIZONTAL_LINE);
        }
    }

    public static void markDone(int i) {
        Task task = Task.Tasks.get(i - 1);
        task.setDone();
        System.out.println(Duke.HORIZONTAL_LINE + "Nice! I've marked this task as done:" +  "\n" +
                task.toString() + "\n" + Duke.HORIZONTAL_LINE);
    }

    public void setDone() {
        this.icon = TICK;
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", icon, name);
    }
}