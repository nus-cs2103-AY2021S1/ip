import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class FileReader {

    public static ArrayList<Task> loadTaskFromFile() throws IOException {
        File f = new File("data/duke.txt");
        f.createNewFile();
        Scanner sc = new Scanner(f);
        ArrayList<Task> listOfTask = new ArrayList<>();
        while (sc.hasNext()) {
            String[] task = sc.nextLine().split("|");
            String type = task[0];
            boolean isDone = Integer.parseInt(task[1]) == 1;
            String description = task[2];

            if(type.equals("T")) {
                listOfTask.add(new Todo(description, isDone));
            }

            if(type.equals("D")) {
                String date = task[3];
                listOfTask.add(new Deadline(description, isDone, date));
            }

            if(type.equals("E")) {
                String time = task[3];
                listOfTask.add(new Event(description, isDone, time));
            }
        }

        return listOfTask;
    }

    public static void writeToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        String stringToAppend = "";

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

            stringToAppend += currentLine + "\n";

        }
        fw.write(stringToAppend);
        fw.close();
    }
}