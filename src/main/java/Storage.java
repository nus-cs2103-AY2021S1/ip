import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Storage {
    
    private final Path filePath;
    
    private Storage(Path filePath) {
        this.filePath = filePath;
    }
    
    public static Storage loadStorage(Path filePath) throws IOException {
        // create data directory if it does not exist
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        // create file if it does not exist
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        
        return new Storage(filePath);
    }
    
    public void writeLineToStorage(String line) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath.toString(), true);
        fileWriter.write(line + System.lineSeparator());
        fileWriter.close();
    }
    
    public void deleteLineFromStorage(String lineToDelete) throws IOException {
        FileReader reader = new FileReader(filePath.toString());
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder fileData = new StringBuilder();

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (!line.equals(lineToDelete)) {
                fileData.append(line);
                fileData.append(System.lineSeparator());
            }
        }

        FileWriter fileWriter = new FileWriter(filePath.toString(), false);
        fileWriter.write(fileData.toString());
        fileWriter.close();
    }

    public void editLineInStorage(String oldLine, String newLine) throws IOException {

        FileReader reader = new FileReader(filePath.toString());
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder oldFileData = new StringBuilder();
        
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            oldFileData.append(line);
            oldFileData.append(System.lineSeparator());
        }
        
        oldLine = Pattern.quote(oldLine);
        String newData = oldFileData.toString().replaceFirst(oldLine, newLine);
        
        FileWriter fileWriter = new FileWriter(filePath.toString(), false);
        fileWriter.write(newData);
        fileWriter.close();
    }
}
