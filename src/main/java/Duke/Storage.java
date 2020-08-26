package duke;

import java.io.*;
import java.util.Optional;
import java.util.stream.Stream;

public class Storage {

    File outputFile;
    File dir;

    public Storage(String filepath) {
        this.outputFile = new File(filepath);
        this.dir = this.outputFile.getParentFile();
    }

    public Optional<Stream<String>> load() throws FileNotFoundException {
        if (outputFile.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(outputFile));
            return Optional.of(reader.lines());
        }
        return Optional.empty();
    }
    public void save(TaskList taskList) throws IOException {

        if (!this.dir.exists()) {
            dir.mkdirs();
        }
        outputFile.createNewFile();

        FileWriter writer = new FileWriter(outputFile);
        writer.write(taskList.fileText());
        writer.close();
    }
}
