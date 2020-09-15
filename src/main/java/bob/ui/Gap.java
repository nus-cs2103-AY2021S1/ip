package bob.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

public class Gap extends Region {
    private Gap() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Gap.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Region createGap() {
        return new Gap();
    }
}
