import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    protected String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public List<Task> load() throws IOException{

            File tasks = new File(filepath);
            FileReader fileReader = new FileReader(tasks);
            BufferedReader bufferedReader =new BufferedReader(fileReader);
            String line;
            List<String> stringArr = new ArrayList<>();
            while((line = bufferedReader.readLine())!=null)
            {
                stringArr.add(line);
            }
            fileReader.close();
            List<Task> taskArr = buildTaskArr(stringArr);
            return taskArr;


    }
    public static void saveTaskChanges(TaskList taskList) {
        File taskDir = new File("./data");
        if (!taskDir.exists()) {
            taskDir.mkdir();
        }
        File tasks = new File("./data/Tasks.txt");
        try {
            if(!tasks.exists()) {
                tasks.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("./data/Tasks.txt"));
            for (int j = 0; j < taskList.getTaskListSize(); j++) {
                writer.write(taskList.getTask(j).toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Invalid file name");

        }
    }

    public static List<Task> buildTaskArr(List<String> stringArr) {
        List<Task> taskArr = new ArrayList<>();
        for (String taskString : stringArr) {
            String[] taskStringSplit = taskString.split(" ");
            if (taskStringSplit.length > 1) {
                String[] descriptionSplit = taskStringSplit[0].split("]");
                String statusIcon = descriptionSplit[1].substring(1);
                boolean isDone = statusIcon.equals("\u2713") ? true : false;
                String taskDescription = descriptionSplit[2] + " ";
                LocalDate date = LocalDate.parse(taskStringSplit[2]);
                int timeStringLength = taskStringSplit[3].length();
                String timeString = taskStringSplit[3].substring(0, timeStringLength - 1);
                if (timeString.length() == 5) {
                    LocalTime time = LocalTime.parse(timeString);
                    Task deadline = new Deadline(taskDescription, TaskType.DEADLINE, isDone, date, time);
                    taskArr.add(deadline);
                } else {
                    LocalTime timeStart = LocalTime.parse(timeString.split("-")[0]);
                    LocalTime timeEnd = LocalTime.parse(timeString.split("-")[1]);
                    Task event = new Event(taskDescription, TaskType.DEADLINE, isDone, date, timeStart, timeEnd);
                    taskArr.add(event);
                }
            } else {
                String[] descriptionSplit = taskStringSplit[0].split("]");
                String statusIcon = descriptionSplit[1].substring(1);
                String taskDescription = descriptionSplit[2];
                boolean isDone = statusIcon.equals("\u2713") ? true : false;
                Task todo = new Todo(taskDescription, TaskType.TODO, isDone);
                taskArr.add(todo);
            }
        }
        return taskArr;
    }

}
