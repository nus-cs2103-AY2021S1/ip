package duke.ui;

/**
 * Class to define the UI Operations
 */
public interface UserInterface {
    public boolean isRunning();
    public void start(String username);
    public void close();
    public String nextLine();
    public void systemMessage(String message);
}
