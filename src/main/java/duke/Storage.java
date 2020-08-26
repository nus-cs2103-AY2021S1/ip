package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter file = new FileWriter(filePath);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Todo) {
                s.append(String.format("T | %d | %s", 
                        tasks.get(i).getStatus() ? 1 : 0, 
                        tasks.get(i).getDescription()));
            } else if (tasks.get(i) instanceof Deadline) {
                s.append(String.format("D | %d | %s | %s", 
                        tasks.get(i).getStatus() ? 1 : 0, 
                        tasks.get(i).getDescription(), 
                        ((Deadline) tasks.get(i)).getDate()));
            } else if (tasks.get(i) instanceof Event) {
                s.append(String.format("E | %d | %s | %s", 
                        tasks.get(i).getStatus() ? 1 : 0, 
                        tasks.get(i).getDescription(), 
                        ((Event) tasks.get(i)).getTime()));
            }
            s.append("\n");
        }
        file.write(s.toString());
        file.close();
    }
    
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new DukeException("Failed to create file for storage.");
            }
        }
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String row = s.nextLine();
                String[] strArray = row.split(" \\| ");
                switch (strArray[0]) {
                case "T":
                    tasks.add(new Todo(strArray[2], 
                            "1".equals(strArray[1])));
                    break;
                case "D":
                    tasks.add(new Deadline(strArray[2], 
                            "1".equals(strArray[1]), 
                            LocalDate.parse(strArray[3])));
                    break;
                case "E":
                    tasks.add(new Event(strArray[2], 
                            "1".equals(strArray[1]), 
                            LocalDate.parse(strArray[3])));
                    break;
                default:
                    throw new DukeException("File in incorrect format.");
                }
                
            }
        } catch (IOException ex) {
            throw new DukeException("Error reading file.");
        }
        return tasks;
    }
}
