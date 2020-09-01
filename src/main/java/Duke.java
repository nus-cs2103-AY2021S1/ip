import java.io.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        try {
            String home = System.getProperty("user.home");
            java.nio.file.Path path = java.nio.file.Paths.get(home, "Documents", "ipSave.txt");
            boolean directoryExists = java.nio.file.Files.exists(path);
            MainLogic logic;

            if (directoryExists) {
                Storage storage = SaveFileParser.readToStorage(path);
                logic = new MainLogic(storage);
            } else {
                logic = new MainLogic();
            }
            logic.main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
