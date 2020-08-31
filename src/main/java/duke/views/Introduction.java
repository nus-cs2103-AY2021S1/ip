package duke.views;

import duke.utils.Storage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Encapsulates an Introduction class that renders the introduction page
 * inside the GUI.
 */
public class Introduction implements Page {
    private static final int ANIMATION_DURATION = 30;
    private static final double THRESHOLD_OPACITY = 0.01;
    private Storage storage;

    /**
     * Constructs a Introduction instance that displays the introduction view.
     *
     * @param storage a storage object used to read and store data.
     */
    public Introduction(Storage storage) {
        this.storage = storage;
    }

    /**
     * Set the scene of an introduction page in the current window.
     *
     * @param window the current window displayed in the GUI.
     */
    @Override
    public void setScene(Stage window) {

        // set up layout
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);

        // set up title
        Text sceneTitle = new Text(Messenger.INTRO_MESSAGE);
        sceneTitle.setFont(Page.DEFAULT_TITLE_FONT);
        root.add(sceneTitle, 3, 2);

        // set up window
        window.setTitle("Menu");
        Scene scene = new Scene(root, Page.DEFAULT_PAGE_WIDTH, Page.DEFAULT_PAGE_HEIGHT);
        window.setScene(scene);
        window.show();

        // define animation
        Timeline animationTimeLine = new Timeline();
        animationTimeLine.setCycleCount(Timeline.INDEFINITE);
        animationTimeLine.getKeyFrames().add(
            new KeyFrame(new Duration(ANIMATION_DURATION), t -> {
                root.setOpacity(root.getOpacity() - THRESHOLD_OPACITY);
                if (root.getOpacity() < THRESHOLD_OPACITY) {

                    // execute next view if triggered
                    new Menu(storage).setScene(window);

                    // end animation
                    animationTimeLine.stop();
                }
            })
        );
        animationTimeLine.play();
    }
}
