import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import task.*;

import java.io.IOException;

public class Gui extends Application {
    private Duke duke = new Duke("duke.txt");

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getUserDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }



    private static final String FILEPATH = "duke.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            String[] command = Parser.parseCommand(input);

            if(command[0].contentEquals("bye")){
                return "Bye. Hope to see you again soon!";
            }
            else if(command[0].contentEquals("list")){
                StringBuilder reply = new StringBuilder();

                for(int i = 0; i < taskList.size(); i++){
                    reply.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
                }
                return reply.toString();
            }
            else if(command[0].contentEquals("remove")){
                String indexStr = input.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(indexStr) - 1;
                Task t = taskList.remove(index);
                String reply ="Noted. I've removed this task:\n"
                        +"\t" + t + "\n"
                        + "\t" + "Now you have " + taskList.size() + " tasks in the list.";
                return reply;
            }
            else if(command[0].contentEquals("done")){
                String indexStr = input.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(indexStr) - 1;
                taskList.get(index).setDone();
                String reply = "Nice! I've marked this task as done:\n"
                        + taskList.get(index);
                return reply;
            }
            else if(command[0].contentEquals("find") ){
                TaskList foundList = taskList.find(command[1]);
                StringBuilder reply = new StringBuilder();

                for(int i = 0; i < foundList.size(); i++){
                    reply.append(i + 1).append(". ").append(foundList.get(i)).append("\n");
                }
                return reply.toString();
            }
            else if(command[0].contentEquals("todo") ){
                try {
                    Task newTask = new Todo(command[1]);
                    taskList.add(newTask);
                    String reply = "Got it. I've added this task:\n"
                            + "\t" + newTask;
                    return reply;
                }
                catch(EmptyStringException e){
                    return "Todo cannot be empty.";
                }
            }
            else if(command[0].contentEquals("deadline")){
                try {
                    Task newTask = new Deadline(command[1]);
                    taskList.add(newTask);
                    String reply = "Got it. I've added this task:\n"
                            + "\t" + newTask;
                    return reply;
                }
                catch(EmptyStringException e){
                    return "Deadline cannot be empty.";
                }
            }
            else if(command[0].startsWith("event")){
                try {
                    Task newTask = new Event(command[1]);
                    taskList.add(newTask);
                    String reply = "Got it. I've added this task:\n"
                            + "\t" + newTask;
                    return reply;
                }
                catch(EmptyStringException e){
                    return "Event cannot be empty.";
                }
            }
            else{
                return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }

            //TODO SAVE THE TASKS!
//            try {
//                Storage.saveTasks(FILEPATH, taskList);
//            }
//            catch (IOException e){
//                Ui.printException("Unable to save to file.");
//            }
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    public void startDuke() {
        taskList = Storage.loadTasks(FILEPATH);
    }
    @Override
    public void start(Stage stage) {

        taskList = Storage.loadTasks(FILEPATH);
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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


        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }


    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}