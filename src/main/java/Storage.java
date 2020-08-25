import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    
    Storage(String filePath) {
        this.filePath = filePath;    
    }
    
    public ArrayList<Task> load() throws DukeException{
        File f = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String nextTask = sc.nextLine();
                String[] splitted = nextTask.split("\\s+", 3);
                String type = splitted[0];
                boolean isDone = splitted[1].equals("D");
                Task newTask;
                if (type.equals("T")) {
                    String description = splitted[2];
                    newTask = new ToDo(description);
                } else {
                    int timeIdx = splitted[2].indexOf(" /time");
                    String description = splitted[2].substring(0, timeIdx);
                    String time = splitted[2].substring(timeIdx + 7);
                    if (type.equals("D")) {
                        newTask = new Deadline(description, time);
                    } else {
                        newTask = new Event(description, time);
                    }
                }
                if (isDone) {
                    newTask.markAsDone();
                }
                tasks.add(newTask);
            }
            sc.close();
        } catch (FileNotFoundException e){
            try {
                String DIRECTORY_PATH = filePath.substring(0, filePath.length() - 8);
                File directory = new File(DIRECTORY_PATH);
                if (!directory.exists()) {
                    directory.mkdir();
                }
                f.createNewFile();
            } catch (IOException e1) {
                throw new DukeException("");
            }
        }
        return tasks;
    }

    public void write(TaskList tasks) {
        try {
            File f = new File(filePath);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            FileWriter fw = new FileWriter(filePath, true);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String type, description, time, status;
                description = task.getDescription();
                status = task.isDone() ? "D" : "ND";
                time = task.getTime().equals("") ? "" : "/time " + task.getTime();
                if (task instanceof ToDo) {
                    type = "T";
                } else if (task instanceof Deadline) {
                    type = "D";
                } else {
                    type = "E";
                }
                String dataPresentation = type + " " + status + " " + description + " " + time + "\n";
                fw.write(dataPresentation);
            }
            fw.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
