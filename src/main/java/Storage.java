import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try { 
            File file = new File("taskList.txt");
            if (!file.createNewFile()) { // file already exists
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String data = fileScanner.nextLine();
                    Task newTask = readFiles(data);
                    list.add(newTask);
                }
                fileScanner.close();
            }
            return list;
        } catch (IOException e) {
            throw new DukeException("ERROR: file not found.");
        }
    }
    
    public void save(TaskList list) {
        try {
            FileWriter fileWriter = new FileWriter("taskList.txt");
            for (Task task : list.taskList) {
                fileWriter.write(task.saveTask() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    public Task readFiles(String data) throws DukeException {
        if (data.startsWith("T")) {
            String description = data.substring(8);
            Todo todo = new Todo(description);
            todo.isDone = data.charAt(4) == '1';
            return todo;
            
        } else if (data.startsWith("E")) {
            int index = data.lastIndexOf("|");
            String description = data.substring(8, index - 1);
            String eventDate = data.substring(index + 2);
            try {
                LocalDate date = LocalDate.parse(eventDate);
                Event event = new Event(description, date);
                event.isDone = data.charAt(4) == '1';
                return event;
            } catch (DateTimeParseException e) {
                throw new DukeException("Please key in the date in the format YYYY-MM-DD");
            }

        } else if (data.startsWith("D")) {
            int index = data.lastIndexOf("|");
            String description = data.substring(8, index - 1);
            String deadlineDate = data.substring(index + 2);
            try {
                LocalDate date = LocalDate.parse(deadlineDate);
                Deadline deadline = new Deadline(description, date);
                deadline.isDone = data.charAt(4) == '1';
                return deadline;
            } catch (DateTimeParseException e) {
                throw new DukeException("Please key in the date in the format YYYY-MM-DD");
            }
            
        } else {
            throw new DukeException("ERROR: unknown task");
        }
    }
}