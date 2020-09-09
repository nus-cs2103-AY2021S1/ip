package duke.gui;

import duke.Utility;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * Chat display for DUke
 */
public class Display {

    private static final String WELCOME_MESSAGE = "Welcome to Duke!";

    private final Pane outputChatBox;
    private final Image chatbotAvatar;
    private final Image userAvatar;
    private final Image errorAvatar;

    /**
     * Initialize Duke chat display on the Pane
     * @param outputChatBox The display Pane where chatbubbles will be displayed
     */
    public Display(Pane outputChatBox) {
        assert outputChatBox != null;
        this.outputChatBox = outputChatBox;
        this.chatbotAvatar = new Image(Utility.getResource("chatbot.jpg"));
        this.userAvatar = new Image(Utility.getResource("user.jpg"));
        this.errorAvatar = new Image(Utility.getResource("error.jpg"));
    }

    /**
     * Output a new chatbubble to the display (user's chatbubble)
     * @param message The message to display
     */
    public void in(String message) {
        assert message != null;

        if (message.isBlank()) {
            return;
        }

        // Display chatbubble for user input
        outputChatBox.getChildren().add(ChatBubble.generate(
                userAvatar,
                message,
                ChatBubble.Align.RIGHT));
    }

    /**
     * Output a new chatbubble to the display (chatbot's chatbubble)
     * @param message The message to display
     */
    public void out(String message) {
        assert message != null;

        if (message.isBlank()) {
            return;
        }

        // Display chatbubble for program output
        outputChatBox.getChildren().add(ChatBubble.generate(
                chatbotAvatar,
                message,
                ChatBubble.Align.LEFT));
    }

    /**
     * Output a new chatbubble to the display (error message chatbubble)
     * @param message The message to display
     */
    public void err(String message) {
        assert message != null;

        if (message.isBlank()) {
            return;
        }

        // Display chatbubble for error output
        Pane pane = ChatBubble.generate(
                errorAvatar,
                message,
                ChatBubble.Align.LEFT);
        pane.setStyle("-fx-background-color: #ffcccc");
        outputChatBox.getChildren().add(pane);
    }

    /**
     * Clear the display of all chatbubbles
     */
    public void clear() {
        outputChatBox.getChildren().clear();
        this.out(WELCOME_MESSAGE);
    }
}
