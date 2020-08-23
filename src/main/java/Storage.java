import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final static String DIRECTORY_PATH = "data";
    private final static String STORAGE_PATH = "data/duke.txt";

    public Storage() throws IOException {
        File folderDirectory = new File(DIRECTORY_PATH);
        if (!folderDirectory.exists()) {
            folderDirectory.mkdir();
        }
        File txt = new File(STORAGE_PATH);
        if (!txt.exists()) {
            txt.createNewFile();
        }

    }

    private static void clearTasks() throws IOException {
        FileWriter storageWriter = new FileWriter(STORAGE_PATH, false);
        // replace the original content with an empty string
        storageWriter.write("");
        storageWriter.close();
    }

    public static void addTask(String task) throws IOException {
        File data = new File(STORAGE_PATH);
        FileWriter writer = new FileWriter(data, true);
        writer.write(task);
        writer.write('\n');
        writer.flush();
        writer.close();
    }

    public static void currentList(ArrayList<Task> ls) throws FileNotFoundException, IOException {
        File data = new File(STORAGE_PATH);
        FileReader fr = new FileReader(data);
        BufferedReader br = new BufferedReader(fr);
        String tasks = "";
        while (true) {
            tasks = br.readLine();
            if (tasks == null) {
                break;
            }
            String[] arrTasks = tasks.split(" ~ ");
            String typeOfTask = arrTasks[0];
            String isDone = arrTasks[1];
            String nameOfTask = arrTasks[2];
            if (typeOfTask.equals("T")) {
                if (isDone.equals("1")) {
                    Todo temp = new Todo(nameOfTask, true);
                    ls.add(temp);
                } else {
                    Todo temp = new Todo(nameOfTask, false);
                    ls.add(temp);
                }
            } else if (typeOfTask.equals("D")) {
                String date = arrTasks[3];
                if (isDone.equals("1")) {
                    Deadline temp = new Deadline(nameOfTask, true, date);
                    ls.add(temp);
                } else {
                    Deadline temp = new Deadline(nameOfTask, false, date);
                    ls.add(temp);
                }
            } else if (typeOfTask.equals("E")) {
                String date = arrTasks[3];
                if (isDone.equals("1")) {
                    Event temp = new Event(nameOfTask, true, date);
                    ls.add(temp);
                } else {
                    Event temp = new Event(nameOfTask, false, date);
                    ls.add(temp);
                }
            }
        }
    }

        public static void completeTask(int taskNo, int size) {
            try {
                File data = new File(STORAGE_PATH);
                FileReader fr = new FileReader(data);
                BufferedReader br = new BufferedReader(fr);
                ArrayList<String> tempArr = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    String task = br.readLine();
                    if (i == taskNo) {
                        String temp = task.replaceFirst("0", "1");
                        task = temp;
                    }
                    tempArr.add(task);
                }
                Storage.clearTasks();
                for (int i = 0; i < size; i++) {
                    Storage.addTask(tempArr.get(i));
                }
            } catch (IOException ee) {
                System.out.println(ee.getMessage());
            }
        }

        public static void deleteTask(int index, int size) {
            try {
                File data = new File(STORAGE_PATH);
                FileReader fr = new FileReader(data);
                BufferedReader br = new BufferedReader(fr);
                ArrayList<String> tempArr = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    String task = br.readLine();
                    if (i != index) {
                        tempArr.add(task);
                    }
                }
                Storage.clearTasks();
                for (int i = 0; i < size; i++) {
                    Storage.addTask(tempArr.get(i));
                }
            } catch (IOException ee) {
                System.out.println(ee.getMessage());
            }
        }

}
