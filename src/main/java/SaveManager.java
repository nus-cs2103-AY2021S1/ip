import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class SaveManager {

    private Path saveFilePath;

    public SaveManager(Path saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public TaskManager load() throws DukeSaveDataException{

        try {
            TaskManager taskManager = new TaskManager();
            BufferedReader br = Files.newBufferedReader(this.saveFilePath);
            String currLine = br.readLine();
            while (currLine != null) {
                taskManager.storeTask(this.loadTaskFromSave(currLine));
                currLine = br.readLine();
            }
            return taskManager;
        } catch (NoSuchFileException e) {
            try {
                Files.createDirectories(this.saveFilePath.getParent());
                Files.createFile(this.saveFilePath);
            } catch (IOException e2) {
                throw new DukeSaveDataException("Unable to load from save file or create new save file");
            }
            throw new DukeSaveDataException("Unable to load from save file - " + e.toString());
        } catch (IOException e) {
            throw new DukeSaveDataException("Unable to load from save file - " + e.toString());
        }
    }

    public void save(TaskManager taskManager) throws DukeSaveDataException{
        final String[] saveData = {""};
        taskManager.forEach(task -> saveData[0] += this.toSaveFormat(task.convertToHashMap()));

        try {
            BufferedWriter bw = Files.newBufferedWriter(this.saveFilePath);
            bw.write(saveData[0]);
            bw.close();
        } catch (IOException e) {
            throw new DukeSaveDataException("Unable to save to file at <" + this.saveFilePath + ">");
        }
    }

    public String keyValueToString(String key, String value) {
        return "{\"" + key + "\":\"" + value + "\"}";
    }

    public String toSaveFormat(HashMap<String, String> objectData) {
        HashMap<String, String> copyData = new HashMap<>(objectData);
        ArrayList<String> saveStrings = new ArrayList<>();
        saveStrings.add(this.keyValueToString("type", copyData.get("type")));

        //remove "type" and iterate through the other key,value pairs in input HashMap data
        copyData.remove("type");
        copyData.forEach((key, value) -> saveStrings.add(this.keyValueToString(key, value)));

        //convert ArrayList to String and output
        return saveStrings.toString() + "\n";
    }

    public HashMap<String, String> loadMapFromSave(String objectData) {
        String copyData = objectData.substring(2, objectData.length()-2);
        String[] paramsList = copyData.split("}, \\{");

        HashMap<String, String> paramsMap = new HashMap<>();
        for (int i = 0; i < paramsList.length; i++) {
            String[] keyValPair = paramsList[i]
                    .substring(1, paramsList[i].length() - 1)
                    .split("\":\"");
            paramsMap.put(keyValPair[0], keyValPair[1]);
        }

        return paramsMap;
    }

    public Task loadTaskFromMap(HashMap<String, String> params) throws DukeSaveDataException{
        String type = params.get("type");
        String name = params.get("name");
        boolean isDone = params.get("done").equals("true");
        if (type.equals("Task")) {
            return new Task(name, isDone);
        } else if (type.equals("ToDo")) {
            return new ToDo(name, isDone);
        } else if (type.equals("Deadline")) {
            return new Deadline(name, params.get("deadline"), isDone);
        } else if (type.equals("Event")) {
            return new Event(name, params.get("when"), isDone);
        } else {
            throw new DukeSaveDataException("Save Data Error: " + params.toString());
        }
    }

    public Task loadTaskFromSave(String objectData) throws DukeSaveDataException{
        return this.loadTaskFromMap(this.loadMapFromSave(objectData));
    }

}
