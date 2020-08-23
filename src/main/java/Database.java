import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Database {
    private String directory;
    private File db;

    public static Database dbInstance() {
        return new Database().init();
    }

    private Database init() {
        String projectRoot = new File(System.getProperty("user.dir"))
                .getParentFile()
                .getPath();
        this.directory = String.format("%s/data/db.txt", projectRoot);
        try {
            this.db = new File(this.directory);
            this.db.getParentFile().mkdirs();
            this.db.createNewFile();
            System.out.print(this.db);
        } catch (IOException e) {
            System.out.println("Error creating file, file might already exist.");
        }
        return this;
    }

    public void updateDatabase(ArrayList<Task> taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        taskList.forEach(task -> {
            stringBuilder.append(task.formatTaskForDatabase());
        });

        try {
            FileWriter fileWriter = new FileWriter(this.directory);
            fileWriter.write(stringBuilder.toString());
            System.out.println(this.directory);
            System.out.println(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error while updating database file");
        }
    }

}
