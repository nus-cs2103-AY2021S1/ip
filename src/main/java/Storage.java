package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * An object to save all the task in the file.
 */
public class Storage {
    String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * A function to load all the task saved in the file.
     * @return a list of the task which is saved in the file.
     * @throws IOException
     */
    public List<Task> load() throws IOException {
        File f = new File(this.filePath);
        LinkedList<Task> result = new LinkedList<>();
        if(f.exists()) {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String temp = sc.nextLine();
                String[] res = temp.split(" # ");
                if (res[0].equals("T")) {
                    boolean isDone = false;
                    if(res[1].equals("1")) {
                        isDone = true;
                    }
                    Task cur = new Task(res[2], isDone);
                    result.add(cur);
                } else if (res[0].equals("D")) {
                    boolean isDone = false;
                    if (res[1].equals("1")) {
                        isDone = true;
                    }
                    String datePattern = "dd/MM/yyyy HH:mm";
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
                    LocalDateTime date =LocalDateTime.parse(res[3],dateFormatter);
                    Task cur = new Deadline(res[2], isDone, date);
                    result.add(cur);
                } else {
                    boolean isDone = false;
                    if (res[1].equals("1")) {
                        isDone = true;
                    }
                    String datePattern = "dd/MM/yyyy HH:mm";
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
                    LocalDateTime date =LocalDateTime.parse(res[3],dateFormatter);
                    Task cur = new Event(res[2], isDone, date);
                    result.add(cur);
                }
            }
            sc.close();
            return result;
        } else {
            f.createNewFile();
            throw new IOException();
        }
    }

    /**
     * A function to save all the list of task to the filepath.
     * @param task the list of the task.
     * @throws IOException
     */
    public void saveFile(TaskList task) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        List<Task> currentList = task.getTaskList();
        int len = currentList.size();
        for (int i = 0; i < len;i++) {
            Task current = currentList.get(i);
            fw.write(current.writeToFile());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

}
