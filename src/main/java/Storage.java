import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void store(List<Task> taskList) {

        File outFile;
        Writer out;

        try{
            outFile = new File(filePath);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);

            for (int i = 0; i < taskList.size(); ++i) {
                Task task = taskList.get(i);
                out.write(
                        task.toStore() + "\n"
                );
            }

            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> load() {
        List<Task> taskList = new ArrayList<>();
        FileReader fileReader;
        BufferedReader bufferedReader;

        try{
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                if(!line.isBlank()) {
                    taskList.add(parseTask(line));
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Task parseTask(String line) {
        String[] commands = line.split(" \\| ");
        boolean isDone = commands[1].equals("1");
        switch (commands[0]) {
            case "T": {
                return new Todo(isDone, commands[2]);
            }
            case "D": {
                return new Deadline(isDone, commands[2], commands[3]);
            }
            default: {
                return new Event(isDone, commands[2], commands[3]);
            }
        }
    }
}