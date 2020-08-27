import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;

public class Storage {
    private Path storagePath;
    
    Storage(Path storagePath) {
        this.storagePath = storagePath;
    }
    
    private void deleteStorage() {
        try {
            Files.deleteIfExists(storagePath);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    private void createStorage() {
        try {
            if (!Files.exists(storagePath)) {
                Files.createFile(storagePath);
            }
        } catch (IOException | SecurityException e) {
            System.out.println(e);
        }
    }
    
    private void writeData(TaskList tasks) {
        try {
            String[] data = new String[tasks.size()];
            for (int i = 1; i <= tasks.size(); i++) {
                data[i - 1] = tasks.get(i).toDataString();
            }
            if (Files.exists(storagePath)) {
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter(storagePath.toString(), true));
                for (int i = 0; i < data.length; i++) {
                    writer.append(data[i]);
                    writer.newLine();
                }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public TaskList readData() {
        TaskList tasks = new TaskList();
        try {
            if (Files.exists(storagePath)) {
                BufferedReader reader = new BufferedReader(new FileReader(storagePath.toString()));
                String line = reader.readLine();
                while (line != null) {
                    String[] taskData = line.split(" // ");
                    if (taskData[0].equals("T")) {
                        if (taskData.length == 3) {
                            boolean done = Integer.parseInt(taskData[1]) == 1;
                            tasks.add(new ToDo(taskData[2], done));
                        }
                    } else if (taskData[0].equals("E")) {
                        if (taskData.length == 5) {
                            boolean done = Integer.parseInt(taskData[1]) == 1;
                            tasks.add(Event.of(taskData[2], taskData[3], taskData[4], done));
                        }
                    } else if (taskData[0].equals("D")) {
                        if (taskData.length == 5) {
                            boolean done = Integer.parseInt(taskData[1]) == 1;
                            tasks.add(Deadline.of(taskData[2], taskData[3], taskData[4], done));
                        }
                    }
                    line = reader.readLine();
                }
                reader.close();
                return tasks;
            }
        } catch (UnsupportedOperationException | IOException | SecurityException e) {
            System.out.println(e);
            System.exit(1);
        }
        return tasks;
    }
    
    public static Storage setup(Path storagePath) {
        Storage storage = new Storage(storagePath);
        storage.createStorage();
        return storage;
    }
    
    public void update(TaskList tasks) {
        deleteStorage();
        createStorage();
        writeData(tasks);
    }
}
