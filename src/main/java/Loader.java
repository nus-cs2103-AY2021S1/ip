package main.java;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Loader {

    ArrayList<Task> taskArrayList;

    public Loader() {
        this.taskArrayList = new ArrayList<>();
    }

    public void fill() throws FileNotFoundException {
        File f = new File("./command.txt");
        Scanner sc = new Scanner(f);

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
                    taskArrayList.add(new Deadline(description, time, isDone));
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

    public ArrayList<Task> load() throws DukeException {
        try{
            fill();
        } catch (FileNotFoundException e) {
            throw new DukeException("Cannot find the file!!");
        }
        return taskArrayList;
    }
}
