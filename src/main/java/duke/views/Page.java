package duke.views;

import javafx.stage.Stage;

public interface Page {
    int DEFAULT_PAGE_WIDTH = 600;
    int DEFAULT_PAGE_HEIGHT = 400;
    void setScene(Stage window);
}
