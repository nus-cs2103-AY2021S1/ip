import java.util.ArrayList;
import java.util.HashMap;

public class SaveManager {


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
