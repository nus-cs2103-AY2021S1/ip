package duke;

import duke.exception.DukeSaveDataException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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

    public TaskManager load() throws DukeSaveDataException {

        try {
            // Initialise variables
            TaskManager taskManager = new TaskManager();

            // Loop for reading line by line (i.e. task by task) from save file to taskManager
            BufferedReader br = Files.newBufferedReader(this.saveFilePath);
            String currLine = br.readLine();
            while (currLine != null) {
                taskManager.storeTask(this.loadTaskFromSave(currLine));
                currLine = br.readLine();
            }

            return taskManager;

        } catch (NoSuchFileException e) {
            // If unable to load file, attempts to create the file directory and a new, blank save file
            try {
                Files.createDirectories(this.saveFilePath.getParent());
                Files.createFile(this.saveFilePath);

            } catch (IOException e2) {
                // Throw DukeSaveDataException if unable to create a new save file
                throw new DukeSaveDataException("Unable to load from save file or create new save file");
            }

            // Throw DukeSaveDataException for not being able to load from save file
            throw new DukeSaveDataException("Unable to load from save file - " + e.toString());
        } catch (IOException e) {
            throw new DukeSaveDataException("Unable to load from save file - " + e.toString());
        }

    }

    public void save(TaskManager taskManager) throws DukeSaveDataException{

        // Initialise save data storage variable
        // Currently implemented as a String[] for use with taskManager.forEach()
        final String[] saveData = {""};

        // Convert Tasks in taskManager to save data format
        taskManager.forEach(task -> saveData[0] += this.toSaveFormat(task.convertToHashMap()));

        // Attempts to write to save file
        try {
            BufferedWriter bw = Files.newBufferedWriter(this.saveFilePath);
            bw.write(saveData[0]);
            bw.close();

        } catch (IOException e) {
            // Throw DukeSaveDataException if unable to save
            throw new DukeSaveDataException("Unable to save to file at <" + this.saveFilePath + ">");
        }
    }

    public String keyValueToString(String key, String value) {
        return "{\"" + key + "\":\"" + value + "\"}";
    }

    public String toSaveFormat(HashMap<String, String> objectData) {

        // Initialise variables
        HashMap<String, String> copyData = new HashMap<>(objectData);
        ArrayList<String> saveStrings = new ArrayList<>();

        // Add type data to save string as first variable
        saveStrings.add(this.keyValueToString("type", copyData.get("type")));

        // Remove "type" and iterate through the other key,value pairs in input HashMap data
        copyData.remove("type");
        copyData.forEach((key, value) -> saveStrings.add(this.keyValueToString(key, value)));

        // Convert ArrayList to String and output
        return saveStrings.toString() + "\n";
    }

    public HashMap<String, String> loadMapFromSave(String objectData) {

        // Convert save data to a list of parameters
        String copyData = objectData.substring(2, objectData.length()-2);
        String[] paramsList = copyData.split("}, \\{");

        // Convert list of parameters to HashMap form
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

        // Determine Task type from params
        String type = params.get("type");
        String name = params.get("name");
        boolean isDone = params.get("done").equals("true");

        // Create new Task based on type
        if (type.equals("duke.task.Task")) {
            return new Task(name, isDone);

        } else if (type.equals("duke.task.ToDo")) {
            return new ToDo(name, isDone);

        } else if (type.equals("duke.task.Deadline")) {
            return new Deadline(name, params.get("deadline"), isDone);

        } else if (type.equals("duke.task.Event")) {
            return new Event(name, params.get("when"), isDone);

        } else {
            //If unable to determine type, throw DukeSaveDataException
            throw new DukeSaveDataException("Save Data Error: " + params.toString());
        }
    }

    public Task loadTaskFromSave(String objectData) throws DukeSaveDataException{
        return this.loadTaskFromMap(this.loadMapFromSave(objectData));
    }

}
