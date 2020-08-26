import java.io.IOException;
import java.nio.file.Paths;

public class Duke {
    public static void main(String[] args) throws IOException {
        new InputHandler(Paths.get("data", "duke.txt")).run();
    }
}
