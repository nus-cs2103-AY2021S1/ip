package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import command.CommandHandler;
import tasks.Task;

public class Storage {
    /**
     * Saves the task list to a file.
     * @throws IOException if there was an error writing to the file
     */
    public static void save() throws IOException {
        final File saveFile = new File("./data/saved.txt");
        saveFile.getParentFile().mkdirs();
        saveFile.createNewFile();
        final FileWriter fw = new FileWriter(saveFile);
        fw.write(IntStream.rangeClosed(1, TaskList.size())
                .boxed()
                .map(i -> {
                    Task t = TaskList.get(i - 1);
                    return t.getParentCommand()
                            + (t.getDoneStatus() ? "\ndone " + i : "");
                })
                .collect(Collectors.joining("\n")));
        fw.close();
    }

    /**
     * Loads the task list from the default file location {@code ./data/saved.txt}.
     * @throws IOException if there was an error reading from the file
     */
    public static void load() throws IOException {
        load("./data/saved.txt");
    }

    /**
     * Loads the task list from a file.
     * @param fileName the file name of the file to load
     * @throws IOException if there was an error reading from the file
     */
    public static void load(String fileName) throws IOException {
        final File saveFile = new File(fileName);
        if (saveFile.exists()) {
            Files.lines(saveFile.toPath())
                    .forEach(new CommandHandler());
        }
    }

}
