import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {
    
    private final String path;
    private boolean isToAppend = false;
    
    public WriteFile(String filePath) {
        path = filePath;
    }
    
    public WriteFile(String filePath, boolean isToAppendValue) {
        path = filePath;
        isToAppend = isToAppendValue;
    }
    
    public void writeToFile(String text) throws IOException {
        FileWriter write = new FileWriter(path, isToAppend);
        PrintWriter print = new PrintWriter(write);
        
        print.printf(text == "" ? "%s" : "%s" + "%n", text);
        print.close();
    }
}
