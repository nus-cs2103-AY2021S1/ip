import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TaskManager {
    final String FILE_PATH = "../tasks.txt";
    private List<Task> tasks;

    public TaskManager() throws DukeException {
        this.tasks = new ArrayList<>();
        try {
            init();
        } catch (DukeException e) {
            throw new DukeException("Something went wrong when initialising the Task Manager.\nError: " + e.getMessage());
        }
    }

    private void init() throws DukeException {
        try {
            File f = new File(FILE_PATH);
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {
                String taskInfo = sc.nextLine();
                String[] arr = taskInfo.split(",");
                switch (arr[0]) {
                    case "T":
                        Todo t = new Todo(arr[2]);
                        if (arr[1].equals("1")) t.markDone();
                        tasks.add(t);
                        break;
                    case "D":
                        Deadline d = new Deadline(arr[2], arr[3]);
                        if (arr[1].equals("1")) d.markDone();
                        tasks.add(d);
                        break;
                    case "E":
                        Event e = new Event(arr[2], arr[3], arr[4]);
                        if (arr[1].equals("1")) e.markDone();
                        tasks.add(e);
                        break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File \"tasks.txt\" does not exist. Attempting to create one for you.");
            try {
                FileWriter fw = new FileWriter(FILE_PATH);
                fw.close();
                System.out.println("Successfully created file tasks.txt");
            } catch (IOException ioException) {
                System.out.println("Oops something went wrong: " + ioException.getMessage());
            }
        }
    }

    public void add(Task task) {
        tasks.add(task);
        save();
    }

    private void save() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, false);
            StringBuffer sb = new StringBuffer();
            for (Task task : tasks) {
                sb.append(task.saveText() + "\n");
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException ioException) {
            System.out.println("Oops something went wrong while writing to \"tasks.txt\": " + ioException.getMessage());
        }
    }

    public void listTasks() {
        int i = 1;
        System.out.println("*Here are all your tasks");
        for (Task task: tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
    }

    public void markDone(int taskNum) {
        tasks.get(taskNum - 1).markDone();
        save();
    }

    public void deleteTask(int taskNum) {
        Task task = tasks.remove(taskNum - 1);
        System.out.println(String.format("Successfully removed the following task:\n %s", task));
        System.out.println(String.format("You have a total of %d tasks left", tasks.size()));
        save();
    }
}