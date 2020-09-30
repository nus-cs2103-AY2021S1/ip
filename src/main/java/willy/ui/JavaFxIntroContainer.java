package willy.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * Responsible for Willy Greetings Section
 */
public class JavaFxIntroContainer {

    private static String introGUI = " __       ___        __\n"
            + " \\  \\    /    \\     /  /\n"
            + "  \\  \\  /  /\\  \\  /  /\n"
            + "   \\  \\/  /  \\  \\/  /\n"
            + "    \\___/     \\__/ ILLY ~(^-^)~\n"
            + "    Your personal life secretary\n";

    /**
     * Creates an IntroContainer in the form of a VBox
     * @return
     */
    public static VBox createIntroContainer() {

        Label willy = new Label(introGUI);
        Greet startDuke = new Greet();
        Label botCommand = new Label(startDuke.toString());
        willy.setAlignment(Pos.CENTER);

        // Putting together Intro Components of the Bot which consist of the profile of the bot and it's greetings
        // Responsible for Willy image
        Image image = new Image("images/willy.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(60);
        StackPane imageContainer = new StackPane();
        imageContainer.getChildren().addAll(new Circle(48), imageView);

        // Combine all the components in introContainer
        HBox willyIntro = new HBox();
        willyIntro.setSpacing(10);
        willyIntro.setPadding(new Insets(20, 20, 0, 30));
        willyIntro.getChildren().addAll(imageContainer, willy);

        VBox introContainer = new VBox();
        introContainer.getChildren().addAll(willyIntro, botCommand);
        return introContainer;
    }

}
