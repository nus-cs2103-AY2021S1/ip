package main.java;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Represents the local storage of the task list
 */
public class Storage {

    ArrayList<Task> taskArrayList;
    String filePath;

    public Storage(String filePath) {
        this.taskArrayList = new ArrayList<>();
        this.filePath = filePath;
    }

    /**
     * fills the arraylist with the tasks in the hard disk
     * @throws IOException
     */
    public void fill() throws IOException {
        File f = new File(filePath);
        Scanner sc;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            f.createNewFile();
            sc = new Scanner(f);
        }

        while (sc.hasNext()) {
            String next = sc.nextLine();
            String[] token = next.split("\\|");
            String first = token[0];
            boolean isDone = (Integer.parseInt(token[1])) == 1? true: false;

            switch(first) {
                case "T": {
                    String description = token[2];
                    taskArrayList.add(new Todo(description, isDone));
                    break;
                }
                case "D": {
                    String description = token[2];
                    String time = token[3];
                    LocalDate date = LocalDate.parse(time);
                    taskArrayList.add(new Deadline(description, date, isDone));
                    break;
                }
                case "E": {
                    String description = token[2];
                    String time = token[3];
                    taskArrayList.add(new Event(description, time, isDone));
                    break;
                }
            }
        }
    }

    /**
     * loads the task list from local hard disk
     * @return the arraylist that contains the tasks loaded from the local hard disk
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        try{
            fill();
        } catch (IOException e) {
            throw new DukeException("Cannot find the file!!");
        }
        return taskArrayList;
    }
}
