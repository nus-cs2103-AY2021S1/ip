package duke.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Helper class to generate a chat bubble
 */
public class ChatBubble {

    /**
     * Enumeration of possible alignments
     */
    public enum Align {
        LEFT, RIGHT
    }

    /**
     * Resize the image and wrap image into a node
     * @param image The image to resize and wrap
     * @return ImageView that displays the image
     */
    private static ImageView getImageView(Image image) {
        // @@author akgrenSoar-reused
        // Source: https://www.tutorialspoint.com/javafx/javafx_images.htm
        ImageView imageView = new ImageView(image);

        //setting the fit height and width of the image view
        imageView.setFitHeight(90);
        imageView.setFitWidth(90);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        // Source: https://stackoverflow.com/a/20490028/6943913
        Rectangle clip = new Rectangle(
                imageView.boundsInParentProperty().get().getWidth(),
                imageView.boundsInParentProperty().get().getHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);

        imageView.setClip(clip);

        return imageView;
    }

    /**
     * Generate a chat bubble
     * @param avatar Avatar for the chat bubble
     * @param message Message in the chat bubble
     * @param alignment Aligns the chatbubble left/right
     * @return A Pane containing the generated chatbubble
     */
    public static Pane generate(Image avatar, String message, ChatBubble.Align alignment) {
        assert avatar != null;
        assert message != null;
        assert alignment != null;

        HBox hBox = new HBox();
        hBox.setSpacing(15);

        ImageView avatarView = getImageView(avatar);

        if (alignment == Align.LEFT) {
            // Text prevents overrun, but does not fit to message size
            Text messageLabel = new Text();
            messageLabel.setText(message);
            messageLabel.setWrappingWidth(300);

            hBox.getChildren().addAll(avatarView, messageLabel);
            if (message.lines().count() == 1) {
                hBox.setAlignment(Pos.CENTER_LEFT);
            } else {
                hBox.setAlignment(Pos.TOP_LEFT);
            }

        } else if (alignment == Align.RIGHT) {
            // Label fits to message size, but does not prevent overrun
            Label messageLabel = new Label();
            messageLabel.setText(message);
            messageLabel.setWrapText(true);

            hBox.getChildren().addAll(messageLabel, avatarView);
            hBox.setAlignment(Pos.CENTER_RIGHT); // Align right

        } else {
            assert false : "Error: Unknown OutputMessage.Align property";
            // Critical failure. Cannot recover
            System.exit(-1);
            // Warning: System.out and System.err potentially loop back to this code
            //System.err.println("Error: Unknown OutputMessage.Align property");
        }

        return hBox;
    }

}
