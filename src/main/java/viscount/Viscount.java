package viscount;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import viscount.command.Command;
import viscount.exception.ViscountException;
import viscount.exception.ViscountIOException;
import viscount.gui.DialogBox;

/**
 * Represents Viscount, a chatbot that helps the user keep track of tasks.
 */
public class Viscount extends Application {
    private static final String DATA_DIRECTORY_PATH = System.getProperty("user.dir") + "/data/";
    
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/userIcon.png"));
    private final Image viscountImage = new Image(this.getClass().getResourceAsStream("/images/viscountIcon.png"));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean shouldRun;
    
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    
    public Viscount() {
        
    }
    
    public Viscount(String filePathString) {
        this.storage = new Storage(filePathString);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.loadFromDisk());
            this.shouldRun = true;
        } catch (ViscountIOException e) {
            ui.showError(e.getMessage());
            this.shouldRun = false;
        }
    }

    /**
     * Runs Viscount.
     */
    private void run() {
        ui.showWelcome();
        
        boolean isExit = false;
        while (!isExit) {
            try {
                String rawInput = ui.readInput();
                Command command = Parser.parse(rawInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (ViscountException e) {
                ui.showError(e.getMessage());
            }
        }
        
        exit();
    }

    /**
     * Closes Viscount.
     */
    private void exit() {
        ui.showExit();
    }

    /**
     * Starts Viscount.
     *
     * @param args Standard arguments
     */
    public static void main(String[] args) {
        Viscount viscount = new Viscount(DATA_DIRECTORY_PATH);
        if (viscount.shouldRun) {
            viscount.run();
        } else {
            viscount.exit();
        }
    }

    /**
     * Starts Viscount.
     *
     * @param stage Stage to show.
     */
    @Override
    public void start(Stage stage) {
        AnchorPane mainLayout = setupGui(stage);
        configureGui(stage, mainLayout);
        setEventListeners();
    }
    
    private AnchorPane setupGui(Stage stage) {
        // Setting up the GUI elements
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        
        return mainLayout;
    }
    
    private void configureGui(Stage stage, AnchorPane mainLayout) {
        // Configuring the GUI elements
        stage.setTitle("Viscount");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }
    
    private void setEventListeners() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
    }
    
    private Label getDialogLabel(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        
        return label;
    }
    
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label viscountText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getViscountDialog(viscountText, new ImageView(viscountImage))
        );
        userInput.clear();
    }
    
    private String getResponse(String input) {
        return "Viscount heard: " + input;
    }
}
