package gel;

import gel.task.Deadline;
import gel.task.Event;
import gel.task.Task;
import gel.task.Todo;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Storage {

    String filePath;
    String directoryPath;

    public Storage(String filePath) {
        int fileIndex = filePath.lastIndexOf("/");
        this.directoryPath = filePath.substring(0, fileIndex);
        this.filePath = filePath;
    }

    public void checkFileExistence() throws IOException {
        File dataDir = new File(directoryPath);
        if (!dataDir.exists()) {
            boolean bool = dataDir.mkdir();
            if (bool) {
                System.out.println("data directory successfully created");
            } else {
                System.out.println("Failed to create data directory");
            }
        }
        File file = new File(filePath);
        boolean newFileCreated = file.createNewFile();
    }

    public void updateFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);
        List<Task> listOfTask = taskList.getListOfTask();
        for (Task task : listOfTask) {
            String isDone;
            if (task.isDone()) {
                isDone = "1,";
            } else {
                isDone = "0,";
            }
            if (task instanceof Todo) {
                bw.write("T," + isDone + task.getDescription());
                bw.newLine();
            } else if (task instanceof Event) {
                bw.write("E," + isDone + task.getDescription() + "," + ((Event) task).getAt());
                bw.newLine();
            } else if (task instanceof Deadline) {
                bw.write("D," + isDone + task.getDescription() + "," + ((Deadline) task).getBy());
                bw.newLine();
            }
        }
        bw.close();
        fw.close();
    }

    public TaskList load(Ui ui) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        TaskList taskList = new TaskList(ui);
        while (sc.hasNextLine()) {
            String description = sc.nextLine();
            String[] desArr = description.split(",");
            switch (desArr[0]){
            case "T":
                taskList.addTodoFromFile(desArr[2], Integer.parseInt(desArr[1]));
                break;
            case "D":
                taskList.addDeadlineFromFile(desArr[2], desArr[3], Integer.parseInt(desArr[1]));
                break;
            case "E":
                taskList.addEventFromFile(desArr[2], desArr[3], Integer.parseInt(desArr[1]));
                break;
            }
        }
        return taskList;
    }
}
