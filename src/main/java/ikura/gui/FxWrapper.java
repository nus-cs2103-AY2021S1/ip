// FxWrapper.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.gui;

import java.util.stream.Stream;
import java.util.stream.Collectors;

import ikura.Frontend;
import ikura.task.Task;

import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.layout.*;
import javafx.scene.control.*;  // TODO: get rid of this wildcard
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.application.Application;


public class FxWrapper extends Application {

    // this is kind of hack; javafx insists on "taking over" main(), so we can't control
    // constructors or anything like that. in the run() function then we just set a
    // static field so that we can use it from the javafx start() method.
    private static Frontend frontend;

    public static void run(Frontend frontend) {
        FxWrapper.frontend = frontend;

        Application.launch(new String[] { });
    }


    // javafx stuff
    private Node todo(Region box, Task task) {
        return new TaskPane(box, task);
    }


    @Override
    public void start(Stage stage) {

        var main = new StackPane();
        StackPane.setAlignment(main, Pos.CENTER);

        main.setPrefWidth(1280);
        main.setPrefHeight(800);

        var splitPane = new SplitPane();
        main.getChildren().add(splitPane);
        StackPane.setMargin(splitPane, new Insets(10.0));

        var leftPane = new AnchorPane();

        var enterButton = new Button("owo");
        var commandField = new TextField();
        var inputArea = new HBox(enterButton, commandField);
        HBox.setHgrow(commandField, Priority.ALWAYS);

        var flow = new TextArea();
        flow.setEditable(false);
        flow.setText(Stream.iterate(0, i -> i + 1).map(i -> "ayaya " + i).limit(15)
            .reduce("", (a, b) -> a + "\n" + b));

        var leftBox = new VBox(flow, inputArea);
        leftBox.setFillWidth(true);
        leftPane.getChildren().add(leftBox);
        VBox.setVgrow(flow, Priority.ALWAYS);

        var rightBox = new ScrollPane();
        rightBox.setFitToWidth(true);
        rightBox.setPadding(new Insets(20));

        var items = new VBox(40.0);
        items.setFillWidth(true);

        rightBox.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        items.getChildren().addAll(frontend.getBot()
            .getTaskList()
            .stream()
            .map(x -> todo(items, x))
            .collect(Collectors.toList()));

        rightBox.setContent(items);



        AnchorPane.setTopAnchor(leftBox, 5.0);
        AnchorPane.setLeftAnchor(leftBox, 5.0);
        AnchorPane.setRightAnchor(leftBox, 5.0);
        AnchorPane.setBottomAnchor(leftBox, 5.0);

        splitPane.getItems().addAll(leftPane, rightBox);

        var scene = new Scene(main);

        stage.setScene(scene);
        stage.requestFocus();
        stage.show();
    }
}
