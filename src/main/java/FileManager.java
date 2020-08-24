import java.io.*;
import java.nio.Buffer;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    public static void checkFileExistence() throws IOException {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            boolean bool = dataDir.mkdir();
            if (bool) {
                System.out.println("data directory successfully created");
            } else {
                System.out.println("Failed to create data directory");
            }
        }
        File file = new File("data/gel.txt");
        boolean newFileCreated = file.createNewFile();
    }

    public static void updateFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter("data/gel.txt");
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

    public static TaskList readTaskList() throws FileNotFoundException {
        File file = new File("data/gel.txt");
        Scanner sc = new Scanner(file);
        TaskList taskList = new TaskList();
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
