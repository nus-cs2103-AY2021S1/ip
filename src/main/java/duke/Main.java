package duke;

/**
 * Starts up Duke.
 */
public class Main {
    private static final String SAVE_FILE_PATH = "duke_save_data.txt";

    public static void main(String[] args) {
        Duke duke = new Duke(SAVE_FILE_PATH);
        duke.run();
    }
}
