package duke.gui;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * Chat display for DUke
 */
public class Display {

    private static final String WELCOME_MESSAGE = "Welcome to Duke!";

    private final Pane outputChatBox;
    private final Image dukeAvatar;
    private final Image userAvatar;
    private final Image errorAvatar;

    /**
     * Initialize Duke chat display on the Pane
     * @param outputChatBox The display Pane where chatbubbles will be displayed
     */
    public Display(Pane outputChatBox) {
        assert outputChatBox != null;
        this.outputChatBox = outputChatBox;
        this.dukeAvatar = new Image(Display.getResource("images/duke.jpg"));
        this.userAvatar = new Image(Display.getResource("images/user.jpg"));
        this.errorAvatar = new Image(Display.getResource("images/error.jpg"));
    }

    /**
     * Get application resource
     * @param path Path from resource folder
     * @return InputStream of the resource
     */
    private static InputStream getResource(String path) {
        assert path != null;
        // @@author akgrenSoar-reused
        // Source: https://stackoverflow.com/a/15749281/6943913
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(path);
    }

    /**
     * Output a new chatbubble to the display (user's chatbubble)
     * @param message The message to display
     */
    public void showInput(String message) {
        assert message != null;

        if (message.isBlank()) {
            return;
        }

        // Display chatbubble for user input
        Pane pane = ChatBubble.generate(userAvatar, message, ChatBubble.Align.RIGHT);
        outputChatBox.getChildren().add(pane);
    }

    /**
     * Output a new chatbubble to the display (chatbot's chatbubble)
     * @param message The message to display
     */
    public void showOutput(String message) {
        assert message != null;

        if (message.isBlank()) {
            return;
        }

        // Display chatbubble for program output
        Pane pane = ChatBubble.generate(dukeAvatar, message, ChatBubble.Align.LEFT);
        outputChatBox.getChildren().add(pane);
    }

    /**
     * Output a new chatbubble to the display (error message chatbubble)
     * @param message The message to display
     */
    public void showError(String message) {
        assert message != null;

        if (message.isBlank()) {
            return;
        }

        // Display chatbubble for error output
        Pane pane = ChatBubble.generate(errorAvatar, message, ChatBubble.Align.LEFT);
        pane.setStyle("-fx-background-color: #ffcccc");
        outputChatBox.getChildren().add(pane);
    }

    /**
     * Clear the display of all chatbubbles
     */
    public void clear() {
        outputChatBox.getChildren().clear();
        this.showOutput(WELCOME_MESSAGE);
    }
}
