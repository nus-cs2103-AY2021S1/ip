import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;

public class DataManager {
    private static final String filePath = "data/data.txt";

    public static ArrayList<Task> loadTaskFromFile() throws IOException {
        ArrayList<Task> listOfTask = new ArrayList<>();
        try {
            File f = new File(filePath);
            f.createNewFile();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] task = sc.nextLine().split("|");
                String type = task[0];
                boolean isDone = Integer.parseInt(task[1]) == 1;
                String description = task[2];

                if (type.equals("T")) {
                    listOfTask.add(new Todo(description, isDone));
                }

                if (type.equals("D")) {
                    LocalDate date = LocalDate.parse(task[3]);
                    listOfTask.add(new Deadline(description, isDone, date));
                }

                if (type.equals("E")) {
                    LocalDate time = LocalDate.parse(task[3]);
                    listOfTask.add(new Event(description, isDone, time));
                }
            }

        }catch (IOException e){
                System.out.println("\"Could not save file!" + e.getMessage());
            }
        return listOfTask;
    }

    public static void writeToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        StringBuilder stringToAppend = new StringBuilder();

        for (Task currentTask : taskList) {
            String currentLine = "";
            String isTaskDone = currentTask.checkDone() ? "1" : "0";
            String description = currentTask.getDescription();
            if (currentTask instanceof Todo) {
                currentLine += "T" + "|" + isTaskDone + "|" + description;
            }

            if (currentTask instanceof Deadline) {
                currentLine += "D" + isTaskDone + "|" + description + "|" + ((Deadline) currentTask).getDate();
            }

            if (currentTask instanceof Event) {
                currentLine += "E" + isTaskDone + "|" + description + "|" + ((Event) currentTask).getTime();
            }

            stringToAppend.append(currentLine).append("\n");

        }
        fw.write(stringToAppend.toString());
        fw.close();
    }
}
