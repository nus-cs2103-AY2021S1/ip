package duke;

import duke.task.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    protected String filepath;

    public Storage() {
        this.filepath = "";
    }

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public List<Task> load() throws IOException {
        File taskLocation = new File(filepath);
        FileReader fileReader = new FileReader(taskLocation);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        List<String> strings = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            strings.add(line);
        }
        fileReader.close();
        List<Task> tasks = buildTasks(strings);
        return tasks;
    }

    public static void saveTaskChanges(TaskList taskList) {
        File taskDir = new File("./data");
        if (!taskDir.exists()) {
            taskDir.mkdir();
        }
        File tasks = new File("./data/Tasks.txt");
        try {
            if (!tasks.exists()) {
                tasks.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("./data/Tasks.txt"));
            for (int j = 0; j < taskList.getTaskListSize(); j++) {
                writer.write(taskList.getTask(j).buildSaveString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Invalid file name");
        }
    }

    public static Task extractTask(String taskString) {
        String[] taskStringSplit = taskString.split("\\|");
        String taskType = taskStringSplit[0];
        String doneStatus = taskStringSplit[1];
        boolean isDone = doneStatus.equals("1") ? true : false;
        String info = taskStringSplit[2];
        if (taskType.equals("T")) {
            return new Todo(info, TaskType.TODO, isDone);
        } else {
            String[] infoSplit = info.split("/");
            String description = infoSplit[0];
            String[] dateAndTimeSplit = infoSplit[1].split(" ");
            LocalDate date = LocalDate.parse(dateAndTimeSplit[0]);
            if (taskType.equals("D")) {
                LocalTime time = LocalTime.parse(dateAndTimeSplit[1]);
                return new Deadline(description, TaskType.DEADLINE, isDone, date, time);
            } else {
                String[] timePeriod = dateAndTimeSplit[1].split("-");
                LocalTime timeStart = LocalTime.parse(timePeriod[0]);
                LocalTime timeEnd = LocalTime.parse(timePeriod[1]);
                return new Event(description, TaskType.EVENT, isDone, date, timeStart, timeEnd);
            }
        }
    }

    public static List<Task> buildTasks(List<String> strings) {
        List<Task> tasks = new ArrayList<>();
        for (String taskString : strings) {
            Task task = extractTask(taskString);
            tasks.add(task);
        }
        return tasks;
    }
}
