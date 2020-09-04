import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private String saveFilePath;

    public Storage(String saveFilePath){
        this.saveFilePath = saveFilePath;
    }

    public void save(ArrayList<Task> taskList) throws IOException {
        File saveFile = new File(saveFilePath);
        if (!saveFile.exists()) {
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile();
        }
        FileWriter fw = new FileWriter(saveFilePath);
        for (Task task : taskList) {
            fw.write(task.toDataString() + "\n");
        }
        fw.close();
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(saveFilePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] taskLine = line.split("\\|");
            switch (taskLine[0]) {
            case("T"):
                ToDo todo = new ToDo(taskLine[2], Boolean.parseBoolean(taskLine[1]));
                taskList.add(todo);
                break;
            case("D"):
                LocalDate deadlineDate = LocalDate.parse(taskLine[3]);
                Deadline deadline = new Deadline(taskLine[2], Boolean.parseBoolean(taskLine[1]), deadlineDate);
                taskList.add(deadline);
                break;
            case("E"):
                LocalDate eventDate = LocalDate.parse(taskLine[3]);
                Event event = new Event(taskLine[2], Boolean.parseBoolean(taskLine[1]), eventDate);
                taskList.add(event);
            }
        }
        return taskList;
    }
}
