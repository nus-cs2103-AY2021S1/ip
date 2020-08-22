import java.util.HashMap;

public class ToDo extends Task{

    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> dict = super.convertToHashMap();
        dict.put("type", "ToDo");
        return dict;
    }
}
