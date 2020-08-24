import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileClass {
    private static String HOME_DIRECTORY = System.getProperty("user.dir") + "/data/";
    private static String FILE_NAME = "duke.txt";
    static ArrayList<Task> taskList;

    public FileClass() {
        taskList = Task.taskList;
    }

    public void writeToFile(ArrayList<Task> taskList) {
        try {
            File f = new File(HOME_DIRECTORY + FILE_NAME);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists");
            }
            FileWriter fw = new FileWriter(HOME_DIRECTORY + FILE_NAME);
            StringBuilder textToAdd = new StringBuilder();
            for (Task task : taskList) {
                textToAdd.append(task.toFileString());
            }
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    public ArrayList<Task> loadTaskList() throws FileNotFoundException {
        File f = new File(HOME_DIRECTORY + FILE_NAME);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String type = sc.nextLine();
            boolean done = Boolean.parseBoolean(sc.nextLine());
            String desc = sc.nextLine();
            Task task;

            switch (type) {
            case "T":
                task = new Todo(desc);
                if (done) {
                    task.markAsDone();
                }
                if (sc.hasNext()) {
                    sc.nextLine();
                }
                break;
            case "D":
                task = new Deadline(desc, sc.nextLine());
                if (done) {
                    task.markAsDone();
                }
                if (sc.hasNext()) {
                    sc.nextLine();
                }
                break;
            case "E":
                task = new Event(desc, sc.nextLine());
                if (done) {
                    task.markAsDone();
                }
                if (sc.hasNext()) {
                    sc.nextLine();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
            }

            taskList.add(task);
        }
        return taskList;
    }
}

//    public static void main(String[] args) throws FileNotFoundException{
//        taskList = Task.taskList;
//        File f = new File(HOME_DIRECTORY + FILE_NAME);
//        Scanner sc = new Scanner(f);
//        while (sc.hasNext()) {
//            String type = sc.nextLine();
//            boolean done = Boolean.parseBoolean(sc.nextLine());
//            String desc = sc.nextLine();
//            Task task;
//
//            switch (type) {
//            case "T":
//                task = new Todo(desc);
//                if (done) {
//                    task.markAsDone();
//                }
//                if (sc.hasNext()) {
//                    sc.nextLine();
//                }
//                break;
//            case "D":
//                task = new Deadline(desc, sc.nextLine());
//                if (done) {
//                    task.markAsDone();
//                }
//                if (sc.hasNext()) {
//                    sc.nextLine();
//                }
//                break;
//            case "E":
//                task = new Event(desc, sc.nextLine());
//                if (done) {
//                    task.markAsDone();
//                }
//                if (sc.hasNext()) {
//                    sc.nextLine();
//                }
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + type);
//            }
//
//            taskList.add(task);
//        }
//
//        String message = "";
//        for (int i = 0; i < taskList.size(); i++) {
//            Task task = taskList.get(i);
//            message += (i + 1)
//                    + ". "
//                    + task
//                    +"\n";
//        }
//        System.out.println(message);
//    }
//}
