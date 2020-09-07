import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents the storage of the client data. Every storage instance will have a file path. Contains methods to save or
 * load from disk.
 */
public class Storage {
    private static final String MESSAGE_INVALID = "I don't understand anything you just said";
    private static final Map<String,String> presetAliasMap= Map.of(
            "t","todo",
            "d","deadline",
            "e","event");
    private final Path taskFilePath;
    private final Path aliasMapFilePath;


    /**
     * Public Constructor.
     *
     * @param filepath Path of data storage file.
     * @param aliasMapFilePath Path of key mappings file.
     */
    public Storage(Path filepath, Path aliasMapFilePath) {
        this.taskFilePath = filepath;
        this.aliasMapFilePath = aliasMapFilePath;
    }

    /**
     * Saves the current list of tasks to the disk.
     *
     * @param tasks List of tasks.
     * @throws IOException If an error occurred when saving to disk.
     */
    void updateMemory(ArrayList<Task> tasks) throws IOException {
        StringBuilder taskListString = new StringBuilder();
        for (Task task : tasks) {
            for (String attribute : task.attributeList()) {
                assert !attribute.isBlank() : "Attribute cannot be empty";
                taskListString.append(attribute).append("\n");
            }
        }
        Files.writeString(taskFilePath, taskListString);
    }

    /**
     * Loads the tasks that was previously saved to disk. If the file or directory cannot be found, it will be created
     * and a new list is returned.
     *
     * @return List populated with previously saved tasks, otherwise returns a new list if an existing one cannot be
     * found.
     * @throws BlankTaskException If the task provided in the list has a blank name.
     * @throws IOException        If there was an error in saving to disk.
     */
    ArrayList<Task> loadTaskList() throws BlankTaskException, IOException, InvalidCommandException {
        if (Files.notExists(taskFilePath)) {
            if (Files.notExists(taskFilePath.getParent())) {
                Files.createDirectories(taskFilePath.getParent());
            }
            Files.createFile(taskFilePath);
            return new ArrayList<>();
        }

        Scanner data;
        data = new Scanner(taskFilePath);
        ArrayList<Task> taskList = new ArrayList<>();
        populateTaskList(data, taskList);
        return taskList;
    }

    private void populateTaskList(Scanner data, ArrayList<Task> taskList)
            throws BlankTaskException, InvalidCommandException {
        while (data.hasNextLine()) {
            Task curr;
            switch (data.nextLine()) {
            case "T":
                curr = new ToDo(data.nextLine());
                break;
            case "E":
                curr = new Event(data.nextLine(), LocalDate.parse(data.nextLine()),
                        LocalTime.parse(data.nextLine()));
                break;
            case "D":
                curr = new Deadline(data.nextLine(), LocalDate.parse(data.nextLine()),
                        LocalTime.parse(data.nextLine()));
                break;
            default:
                throw new InvalidCommandException(MESSAGE_INVALID);
            }

            assert data.hasNextBoolean() : "There should be a boolean for each task";
            if (data.nextBoolean()) {
                curr.markAsDone();
            }

            taskList.add(curr);
            data.nextLine();
        }
    }
    public HashMap<String,String> loadAliasMapping() throws IOException {
        HashMap<String,String>aliasMappings=new HashMap<>(presetAliasMap);
        if (Files.exists(aliasMapFilePath)){
            Scanner data;
            data = new Scanner(aliasMapFilePath);
            while (data.hasNext()){
                aliasMappings.put(data.next(),data.next());
            }
        }
        return aliasMappings;
    }

    public void saveMapping(HashMap<String, String> aliasMap) throws IOException {
        StringBuilder aliasMapString = new StringBuilder();
        for (String key : aliasMap.keySet()) {
            aliasMapString.append(key).append(" ").append(aliasMap.get(key)).append("\n");
        }
        Files.writeString(aliasMapFilePath, aliasMapString);
    }
}
