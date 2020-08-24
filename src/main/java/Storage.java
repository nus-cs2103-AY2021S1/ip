import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

public class Storage {
    private final String filePath;
    private final Gson gsonObject;
    private static final GsonBuilder gsonBuilder = new GsonBuilder();
    private static final JsonSerializer<TaskList> taskListSerializer = (src, typeOfSrc, context) -> {
        JsonArray list = new JsonArray();
        for (Task task : src.getItemsList()) {
            JsonElement elem = context.serialize(task);
            elem.getAsJsonObject().addProperty("taskType", task.getTaskIdentifier());
            list.add(elem);
        }
        return list;
    };
    private static final JsonDeserializer<TaskList> taskListDeserializer = (json, typeOfT, context) -> {
        List<Task> tasks = new ArrayList<>();
        JsonArray taskElems = json.getAsJsonArray();
        for (JsonElement elem : taskElems) {
            JsonObject elemObject = elem.getAsJsonObject();
            Task task;
            String description = elemObject.get("description").getAsString();
            String taskType = elemObject.get("taskType").getAsString();
            String time = parseDate(elemObject.get("date"));
            try {
                switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "E":
                    task = new Event(description, time);
                    break;
                case "D":
                    task = new Deadline(description, time);
                    break;
                default:
                    throw new JsonParseException("Invalid task type.");
                }
            } catch (DukeException e) {
                throw new JsonParseException("Invalid date format.");
            }
            if (elemObject.get("isDone").getAsBoolean()) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        return new TaskList(tasks);
    };
    private static String parseDate(JsonElement elem) throws JsonParseException {
        if (elem == null) return null;
        JsonObject obj = elem.getAsJsonObject();
        try {
            int year = obj.get("year").getAsInt();
            int month = obj.get("month").getAsInt();
            int date = obj.get("day").getAsInt();
            return String.format("%04d-%02d-%02d", year, month, date);
        } catch (NullPointerException e) {
            throw new JsonParseException("Invalid date format.");
        }
    }

    static {
        gsonBuilder.registerTypeAdapter(TaskList.class, taskListSerializer);
        gsonBuilder.registerTypeAdapter(TaskList.class, taskListDeserializer);
    }

    public Storage(String filePath){
        this.filePath = filePath;
        this.gsonObject = gsonBuilder.create();
    }

    public void save(TaskList taskList) throws DukeException {
        try (FileWriter fw = new FileWriter(this.filePath)) {
            this.gsonObject.toJson(taskList, TaskList.class, fw);
        } catch (IOException e) {
            throw DukeException.Errors.FILE_WRITE_ERROR.create();
        }
    }

    public TaskList load() throws DukeException {
        Path path = Paths.get(filePath);
        if (!path.toFile().isFile()) return new TaskList();
        try (Reader reader = Files.newBufferedReader(path)) {
            return gsonObject.fromJson(reader, TaskList.class);
        } catch (IOException e) {
            throw DukeException.Errors.FILE_READ_ERROR.create();
        }
    }

}
