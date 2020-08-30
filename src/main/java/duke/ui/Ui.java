package duke.ui;

/**
 * Class to handle and abstract Ui (currently stdio) operations.
 */
public interface Ui {
    String nextLine();
    boolean isActive();
    void start();
    void close();
    void systemMessage(String input);

}
