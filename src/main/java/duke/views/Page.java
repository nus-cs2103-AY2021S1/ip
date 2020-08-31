package duke.views;

import javafx.stage.Stage;

/**
 * Encapsulates an interface that represents a page in the GUI.
 */
public interface Page {
    /**
     * The default width of a window page.
     */
    int DEFAULT_PAGE_WIDTH = 600;

    /**
     * The default height of a window page.
     */
    int DEFAULT_PAGE_HEIGHT = 400;

    /**
     * Renders a scene on the window.
     *
     * @param window the current window displayed in the GUI.
     */
    void setScene(Stage window);
}
