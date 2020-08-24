import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class FileReader {

    private final static String PATH = "duke.txt";
    private final static String EVENT_COMMAND = "E";
    private final static String TODO_COMMAND = "T";
    private final static String DEADLINE_COMMAND = "D";

    public static ArrayList<Task> getCurrentTasks() throws IOException, InvalidTimeException {
            File f = new File(PATH);
            if (f.createNewFile()) {
                return new ArrayList<>();
            }
            Scanner s = new Scanner(f);
            ArrayList<Task> taskList = new ArrayList<>();
            while (s.hasNextLine()) {
                String[] task = s.nextLine().split("#");
                String command = task[0];
                boolean isDone = task[1].equals("done");
                String description = task[2];

                if(command.equals(TODO_COMMAND)) {
                    taskList.add(new ToDo(description, isDone));
                }

                if(command.equals(EVENT_COMMAND)) {
                    LocalDateTime date = Time.getFormatedTime(task[3]);
                    taskList.add(new Event(description, isDone, date));
                }

                if(command.equals(DEADLINE_COMMAND)) {
                    LocalDateTime date = Time.getFormatedTime(task[3]);
                    taskList.add(new Deadline(description, isDone, date));
                }
            }

            return taskList;
    }

    public static void saveFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(PATH);
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            String currentLine = "";
            String isTaskDone = currentTask.isTaskDone() ? "done" : "notDone";
            String description = currentTask.getDescription();
            if (currentTask instanceof ToDo) {
                currentLine += "T#" + isTaskDone + "#" + description;
            }

            if (currentTask instanceof Event) {
                currentLine += "E#" + isTaskDone + "#" + description + "#" + ((Event) currentTask).getBy();
            }

            if (currentTask instanceof Deadline) {
                currentLine += "D#" + isTaskDone + "#" + description + "#" + ((Deadline) currentTask).getBy();
            }

            string.append(currentLine);

            if (i != taskList.size() -1) {
                string.append("\n");
            }
        }
        fw.write(string.toString());
        fw.close();
    }
}
