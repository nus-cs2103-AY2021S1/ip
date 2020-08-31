package duke.views;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Encapsulates an interface that represents a page in the GUI.
 */
public interface Page {
    /**
     * The default width of a window page.
     */
    int DEFAULT_PAGE_WIDTH = 800;

    /**
     * The default height of a window page.
     */
    int DEFAULT_PAGE_HEIGHT = 600;

    /**
     * The default font for Titles.
     */
    Font DEFAULT_TITLE_FONT = Font.font("Tahoma", FontWeight.NORMAL, 40);

    /**
     * Renders a scene on the window.
     *
     * @param window the current window displayed in the GUI.
     */
    void setScene(Stage window);
}
