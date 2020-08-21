import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    
    private final Path filepath;
    
    private Storage(Path filePath) {
        this.filepath = filePath;
    }
    
    public static Storage loadStorage(Path filepath) throws IOException {
        // create data directory if it does not exist
        if (!Files.exists(filepath.getParent())) {
            Files.createDirectories(filepath.getParent());
        }

        // create file if it does not exist
        if (!Files.exists(filepath)) {
            Files.createFile(filepath);
        }
        
        return new Storage(filepath);
    }
    
    public void writeLineToStorage(String line) throws IOException {
        FileWriter fileWriter = new FileWriter(filepath.toString(), true);
        fileWriter.write(line + "\n");
        fileWriter.close();
    }
}
