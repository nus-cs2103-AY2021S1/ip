import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFile() throws IOException {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        if(file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while((line = br.readLine()) != null) {
                String[] strArr = line.split(" , ");
                switch(strArr[0]) {
                    case "T":
                        taskList.add(new Todo(strArr[1], strArr[2]));
                        break;
                    case "D":
                        taskList.add(new Deadline(strArr[1], strArr[2], LocalDate.parse(strArr[3])));
                        break;
                    case "E":
                        taskList.add(new Event(strArr[1], strArr[2], LocalDate.parse(strArr[3])));
                        break;
                }
            }
        } else {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return taskList;
    }

    public void writeFile(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for(Task task : tasks) {
            writer.write(task.saveString() + "\n");
        }
        writer.close();
    }
    
}
