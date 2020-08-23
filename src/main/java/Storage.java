import java.util.List;

import com.google.gson.*;

public class Storage {
    private String filePath;
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
        TaskList result = new TaskList();
        List<Task> resultInternalList = result.getItemsList();
        JsonArray taskElems = json.getAsJsonArray();
        for (JsonElement elem : taskElems) {
            JsonObject elemObject = elem.getAsJsonObject();
            Task task;
            String description = elemObject.get("description").getAsString();
            String taskType = elemObject.get("taskType").getAsString();
            String time = elemObject.get("time") == null ? null : elemObject.get("time").getAsString();
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
            if (elemObject.get("isDone").getAsBoolean()) {
                task.markAsDone();
            }
            resultInternalList.add(task);
        }
        return result;
    };

    static {
        gsonBuilder.registerTypeAdapter(TaskList.class, taskListSerializer);
        gsonBuilder.registerTypeAdapter(TaskList.class, taskListDeserializer);
    }

    public Storage(String filePath) {
        this.filePath = filePath;
        this.gsonObject = gsonBuilder.create();
    }

    /*
    public void save(TaskList taskList) {
        String json = this.gsonObject.toJson(taskList, TaskList.class);
        // TODO save json to file
    }

    public TaskList load() throws DukeException {
        // TODO load file here
        String input = "";
        return this.gsonObject.fromJson("", TaskList.class);
    }
     */

}
