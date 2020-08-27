import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    protected String filePath;
    
    public Storage(String path) {
        filePath = path;
    }

    public static String getFilePath() {
        try {
            boolean doesDataExist = new File("./data").exists();
            if (!doesDataExist) {
                new File("./data").mkdir();
                new File("./data/duke.txt").createNewFile();
            }
        } catch (IOException e){
            System.out.println("Something went wrong in creating files");
        }
        return "./data/duke.txt";
    }
    
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();

        try {
            ReadFile file = new ReadFile(filePath);
            String[] dataArr = file.openFile();
            for (int i = 0; i < dataArr.length; ++i) {
                tasks.add(Task.taskify(dataArr[i]));
            }
        } catch (Exception e) {
            System.out.println("Something went wrong in reading data...");
        }

        return tasks;
    }
    
    public void save(List<Task> tasks) {
        try {
            if (tasks.size() == 0) {
                WriteFile emptyData = new WriteFile(filePath);
                emptyData.writeToFile("");
            } else {
                WriteFile firstData = new WriteFile(filePath);
                firstData.writeToFile(tasks.get(0).toString());

                if (tasks.size() > 1) {
                    WriteFile appendData = new WriteFile(filePath, true);
                    for (int i = 1; i < tasks.size(); ++i) {
                        appendData.writeToFile(tasks.get(i).toString());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong in writing data...");
        }
    }
}
