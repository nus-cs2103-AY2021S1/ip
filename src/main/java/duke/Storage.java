package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;

public class Storage {
    private String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String str = sc.nextLine();
                String[] command = str.split(" \\| ");
                Task task;

                switch (command[0]) {
                    case "T":
                        task = new ToDo(command[2]);
                        break;
                    case "D":
                        task = new Deadline(command[2], LocalDate.parse(command[3], 
                                DateTimeFormatter.ofPattern("MMM dd yyyy")));
                        break;
                    case "E":
                        task = new Event(command[2], LocalDate.parse(command[3], 
                                DateTimeFormatter.ofPattern("MMM dd yyyy")));
                        break;
                    default:
                        throw new DukeException("Failed to load tasks");
                }
                if (command[1].equals("1")) {
                    task.completeTask();
                }
                taskList.add(task);
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        }
    }
    
    public void write(TaskList taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (Task task : taskList.getList()) {
                writer.write(task.encode());
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks");
        }
    }
}
