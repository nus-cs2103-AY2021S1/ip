package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Holds the storage work of duke program.
 */
public class Storage {

    /**
     * File object of data file.
     */
    private File storageFile;

    /**
     * Initializes the storage object.
     *
     */
    public Storage() {
        String currentListName;
        try {
            File currentList = new File("./data/currentList.txt");
            if (!currentList.exists()) {
                File dir = new File(currentList.getParent());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                currentList.createNewFile();
            }
            Scanner fileReader = new Scanner(currentList);
            if (fileReader.hasNext()) {
                currentListName = fileReader.nextLine();
            } else {
                currentListName = "duke";
                currentListName = "./data/" + currentListName + ".txt";
                FileWriter fileWriter = new FileWriter(currentList);
                fileWriter.write(currentListName);
                fileWriter.close();
            }
            fileReader.close();

            File temp = new File(currentListName);
            if (!temp.exists()) {
                temp.createNewFile();
                storageFile = temp;
            } else {
                storageFile = temp;
            }
            assert storageFile.exists(); //Ensure the storage file exists.
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Loads data from data file.
     * Parses data to list of tasks.
     * If no data in data file, gives an empty list.
     *
     * @return List of stored tasks.
     */
    public List<Task> load() {
        List<Task> result = new ArrayList<>();
        Scanner storage;
        try {
            storage = new Scanner(this.storageFile);

            while (storage.hasNext()) {
                String type = storage.nextLine();
                boolean status = storage.nextLine().equals("1");
                String content = storage.nextLine();
                if (type.equals("T")) {
                    result.add(new Todo(status, content));
                } else if (type.equals("E")) {
                    String time = storage.nextLine().replace(" ", "");
                    result.add(new Event(status, content, time));
                } else if (type.equals("D")) {
                    String time = storage.nextLine().replace(" ", "");
                    result.add(new Deadline(status, content, time));
                }
            }
            storage.close();
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
        return result;
    }

    /**
     * Saves list of tasks into data file.
     *
     * @param taskList List of tasks.
     */
    public void save(List<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(storageFile);

            String data = "";
            for (int i = 0; i < taskList.size(); i++) {
                String temp;
                if (taskList.get(i) instanceof Todo) {
                    Todo holding = (Todo) taskList.get(i);
                    temp = "T\n"
                            + (holding.isDone() ? "1" : "0") + "\n"
                            + holding.getContent() + "\n";
                    data += temp;
                } else if (taskList.get(i) instanceof Event) {
                    Event holding = (Event) taskList.get(i);
                    temp = "E\n"
                            + (holding.isDone() ? "1" : "0") + "\n"
                            + holding.getContent() + "\n"
                            + holding.getTime() + "\n";
                    data += temp;
                } else if (taskList.get(i) instanceof Deadline) {
                    Deadline holding = (Deadline) taskList.get(i);
                    temp = "D\n"
                            + (holding.isDone() ? "1" : "0") + "\n"
                            + holding.getContent() + "\n"
                            + holding.getTime() + "\n";
                    data += temp;
                }
            }
            fileWriter.write(data);
            fileWriter.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Gives all the list file.
     *
     * @return A list with all the list file name.
     */
    public List<String> getAllLists() {
        List<String> files = new ArrayList<String>();
        try {
            File file = new File("./data");
            File[] tempList = file.listFiles();
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    files.add(tempList[i].getName());
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return files;
    }

    public String getCurrentList() {
        String currentListName = "";
        try {
            File currentList = new File("./data/currentList.txt");
            if (!currentList.exists()) {
                File dir = new File(currentList.getParent());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                currentList.createNewFile();
            }
            Scanner fileReader = new Scanner(currentList);
            if (fileReader.hasNext()) {
                currentListName = fileReader.nextLine();
            } else {
                currentListName = "duke";
                currentListName = "./data/" + currentListName + ".txt";
                FileWriter fileWriter = new FileWriter(currentList);
                fileWriter.write(currentListName);
                fileWriter.close();
            }
            fileReader.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return currentListName;
    }

    public void switchList(String filePath) {
        try {
            File currentList = new File("./data/currentList.txt");
            if (!currentList.exists()) {
                File dir = new File(currentList.getParent());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                currentList.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(currentList);
            fileWriter.write(filePath);
            this.storageFile = new File(filePath);
            fileWriter.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
