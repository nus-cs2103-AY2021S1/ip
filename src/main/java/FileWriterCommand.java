import java.util.HashMap;
import java.util.Map;

public enum FileWriterCommand {
    APPEND("append"),
    DELETE("delete"),
    EDIT("edit");

    private final String command;
    private static Map<String, FileWriterCommand> map;

    private FileWriterCommand(String command) {
        this.command = command;
    }

    static {
        map = new HashMap<>();
        for (FileWriterCommand c : FileWriterCommand.values()) {
            map.put(c.command, c);
        }
    }

    public static FileWriterCommand getValue(String s) {
        return FileWriterCommand.map.get(s);
    }
}
