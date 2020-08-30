package duke.ui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Gui implements Ui {

    private VBox dialogContainer;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image bot = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));

    public Gui(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    public void printInput(String input) {
        Label userText = new Label(input);
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userText, new ImageView(user))
        );
    }

    @Override
    public void print(String content) {
        Label response = new Label(content);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(response, new ImageView(bot))
        );
    }

    @Override
    public void greet() {
        String welcomeMessage = "Konnichiwa!\n"
                + "What can I do for you?\n";;
        Label greeting = new Label(welcomeMessage);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greeting, new ImageView(bot)));
    }

    @Override
    public void exit() {
        String exitMessage = "Ja ne!\n";
        Label goodbye = new Label(exitMessage);
        dialogContainer.getChildren().add(goodbye);
    }

    @Override
    public void showLoadingError() {

    }

}
