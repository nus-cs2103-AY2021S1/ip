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

}
