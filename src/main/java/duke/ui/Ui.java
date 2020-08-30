package duke.ui;

/**
 * Class to handle and abstract Ui (currently stdio) operations.
 */
public interface Ui {
    boolean isActive();
    void start();
    void close();
    String nextLine();
    void systemMessage(String input);

}
