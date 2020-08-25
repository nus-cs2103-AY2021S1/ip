import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String horizontal = "________________________________" + "\n";
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void action() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    String bye = "Bye. Hope to see you again soon!" + "\n";
                    System.out.println(horizontal + bye + horizontal);
                    break;
                } else if (input.equals("list")) {
                    System.out.println(horizontal + "Here are the tasks in your list:" + "\n");
                    int counter = 1;
                    for (Task task : taskList) {
                        System.out.println(counter + "." + task.toString());
                        counter++;
                    }
                    System.out.println(horizontal);
                } else if (input.startsWith("done")) {
                    String[] number = input.split("done ");
                    int num = Integer.parseInt(number[1]);
                    Task task = taskList.get(num - 1);
                    task.markAsDone();
                    saveTasks();
                    System.out.println(horizontal + "Nice! I've marked this task as done:" + "\n" +
                            task.toString() + "\n" + horizontal);
                } else if (input.startsWith("delete")) {
                    String[] number = input.split("delete ");
                    int num = Integer.parseInt(number[1]);
                    Task task = taskList.get(num - 1);
                    taskList.remove(task);
                    saveTasks();
                    System.out.println(horizontal + "Noted. I've removed this task:" + "\n" +
                            task.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list." + "\n" + horizontal);
                } else {
                    saveTasks();
                    addTaskAndPrint(input, taskList);
                }
            } catch (DukeException | IOException e) {
                System.out.println(horizontal + "Oops!!! " + e.getMessage() + "\n" + horizontal);
            }
        }
    }

    public static void greet() {
        load();
        String hello = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(horizontal + hello + horizontal);
    }

    public static void addTaskAndPrint(String input, ArrayList<Task> list) throws DukeException {
        if (input.startsWith("todo")) {
            String[] array = input.split("todo ");
            if (array.length == 1) {
                throw new DukeException("The description of a todo cannot be empty!");
            } else {
                String des = array[1];
                Task todo = new ToDos(des);
                taskList.add(todo);
                System.out.println(horizontal + "Got it. I've added this task:" + "\n" + todo.toString() + "\n" +
                        "Now you have " + list.size() + " tasks in the list." + "\n" + horizontal);
            }
        } else if (input.startsWith("deadline")) {
            String[] array = input.split("deadline ");
            if (array.length == 1) {
                throw new DukeException("The description of a deadline cannot be empty!");
            } else {
                String[] arr = array[1].split(" /by ");
                String des = arr[0];
                if (arr.length == 1) {
                    throw new DukeException("The deadline of the task cannot be empty!");
                } else {
                    String due = arr[1];
                    Task dl = new Deadline(des, due);
                    taskList.add(dl);
                    System.out.println(horizontal + "Got it. I've added this task:" + "\n" + dl.toString() + "\n" +
                            "Now you have " + list.size() + " tasks in the list." + "\n" + horizontal);
                }
            }
        } else if (input.startsWith("event")) {
            String[] array = input.split("event");
            if (array.length == 1) {
                throw new DukeException("The description of an event cannot be empty!");
            } else {
                String[] arr = array[1].split(" /at ");
                String des = arr[0];
                if (arr.length == 1) {
                    throw new DukeException("The date of the event cannot be empty!");
                } else {
                    String date = arr[1];
                    Task event = new Events(des, date);
                    taskList.add(event);
                    System.out.println(horizontal + "Got it. I've added this task:" + "\n" + event.toString() + "\n" +
                            "Now you have " + list.size() + " tasks in the list." + "\n" + horizontal);
                }
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
    }

    public static void addTask(Task task) {
        taskList.add(task);
    }

    public static void saveTasks() throws IOException {
        File savedFile = new File(".//SAVED-TASKS.txt");
        boolean exists = savedFile.exists();
        BufferedWriter writer = new BufferedWriter(new FileWriter(".//SAVED-TASKS.txt"));
        StringBuilder tasks = new StringBuilder();

        if (exists) {
            for (Task task : taskList) {
                tasks.append(task.toSaveString());
                tasks.append("\n");
            }
            writer.write(tasks.toString());
            writer.close();
        } else {
            savedFile.createNewFile();
            saveTasks();
            System.out.println("File does not exist!");
        }

    }

    public static void load() {
        File savedFile = new File(".//SAVED-TASKS.txt");
        boolean exists = savedFile.exists();
        try {
            if (exists) {
                BufferedReader br = new BufferedReader(new FileReader(savedFile));
                String line = br.readLine();
                while (line != null) {
                    loadTasks(line);
                    line = br.readLine();
                }
            } else {
                System.out.println(horizontal + "foesuh9feoisfjse" + "\n" + horizontal);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.out.println("IOException!");

        }
    }

    public static void loadTasks(String line) {
        String[] tasks = line.split(" \\| ");
        String task = tasks[0];
        Boolean isDone = tasks[1].equals("1");
        String desc = tasks[2];
        String date = null;
        if (tasks.length > 3) {
            date = tasks[3];
        }
        if (task.equals("T")) {
            Task newTask = new ToDos(desc, isDone);
            addTask(newTask);
            System.out.println(newTask.toString());
        } else if (task.equals("E")) {
            Task newTask = new Events(desc, date, isDone);
            addTask(newTask);
            System.out.println(newTask.toString());
        } else if (task.equals("D")) {
            Task newTask = new Deadline(desc, date, isDone);
            addTask(newTask);
            System.out.println(newTask.toString());
        }
    }



    public static void main(String[] args) {
        greet();
        action();

        //String logo = " ____        _        \n"
        //      + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
    }
}

