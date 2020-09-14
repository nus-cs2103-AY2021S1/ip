// CommandInputView.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.gui.components;

import ikura.gui.GuiFrontend;

import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.scene.layout.Priority;
import javafx.scene.control.TextField;

/**
 * A view that has a button and a textfield, that is used for entering commands.
 */
public class CommandInputView extends HBox {

    private final GuiFrontend frontend;
    private final TextField field;

    /**
     * Constructs a new CommandInputView, linked to the given frontend
     *
     * @param frontend the frontend to use
     */
    public CommandInputView(GuiFrontend frontend) {

        this.frontend = frontend;
        this.field = new TextField();
        this.field.setPromptText("enter command here");
        this.field.setFont(Font.font(14));

        var enterButton = new Button("send");
        enterButton.setFont(Font.font(14));

        enterButton.setOnAction(event -> {
            this.runCommand();
        });

        this.field.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.runCommand();
            }
        });

        HBox.setHgrow(this.field, Priority.ALWAYS);
        this.getChildren().addAll(enterButton, this.field);

        // focus the text field.
        Platform.runLater(() -> {
            this.field.requestFocus();
        });
    }

    private void runCommand() {

        var cmd = this.field.getText();

        if (!cmd.isEmpty()) {
            this.field.setText("");

            if (!this.frontend.processCommand(cmd)) {
                Platform.exit();
            }
        }
    }

}
