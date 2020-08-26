import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Storage {
    private final String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public void save(TaskList taskList, Ui ui) {
        File savedTasks = new File(filePath);
        boolean exists = savedTasks.exists();
        try {
            if(exists) {
                BufferedWriter taskWriter = new BufferedWriter(new FileWriter(filePath));
                StringBuilder currentTasks = new StringBuilder();
                for(int i = 1; i <= taskList.size(); i++) {
                    currentTasks.append(taskList.getTask(i).encode());
                    currentTasks.append("\n");
                }
                taskWriter.write(currentTasks.toString());
                taskWriter.close();
            } else { //file does not exist, create new file
                boolean isCreated = savedTasks.createNewFile();
                if(isCreated) {
                    System.out.println("New save file created");
                } else {
                    ui.printError("Failed to create save file");
                }
                save(taskList, ui);
            }
        } catch (IOException ex) {
            ui.printError(ex);
        }
    }
    
    public void load(TaskList taskList, Ui ui) {
        ui.loadTasks();
        try {
            BufferedReader taskReader = new BufferedReader(new FileReader(filePath));
            String line = taskReader.readLine();
            while(line != null) {
                processTask(line, taskList, ui);
                line = taskReader.readLine();
            }
            ui.showTasks(taskList);
        } catch (FileNotFoundException e) {
            //Folder not yet created, do nothing
        } catch (IOException e) {
            System.out.println(Duke.HORIZONTAL_LINE + "Error: " + e + "\n" + Duke.HORIZONTAL_LINE);
        }
    }

    private static void processTask(String line, TaskList taskList, Ui ui) {
        String[] task = line.split(" \\| ");
        Boolean isDone = task[1].equals("1");
        String taskName = task[2];
        String taskDateTime = null;
        if(task.length > 3) {
            taskDateTime = task[3];
        }
        if(line.startsWith("T")) {
            Todo newTodo = new Todo(taskName, isDone);
            taskList.addTask(newTodo);
        } else if(line.startsWith("E")) {
            Event newEvent = new Event(taskName, isDone, taskDateTime);
            taskList.addTask(newEvent);
        } else if(line.startsWith("D")) {
            Deadline newDeadline = new Deadline(taskName, isDone, taskDateTime);
            taskList.addTask(newDeadline);
        } else {
            ui.printError("Save file on device corrupted");
        }
    }
}
