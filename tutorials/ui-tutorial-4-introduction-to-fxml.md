# JavaFX Tutorial 4 – Introduction to FXML 
This tutorial assumes that you have attempted the exercises at the end of tutorial 3.
You will need to clone the code sample for this tutorial.

While we have produced a fully functional prototype, there are a few major problems with our application.

1. The process of visually enhancing the GUI is long and painful:
    > * Does the `TextField` need to be 330px or 325px wide? 
    > * How much padding is enough padding to look good?
    
    Every small change requires us to rebuild and run the application.  

1. Components are heavily dependent on each other:
    > Why does `Main` need to know that `DialogBox` needs a `Label`? 
    > What happens if we change the `Label` to a custom `ColoredLabel` in the future?  
    
    We need to minimize the amount of information each control needs to know about another.
    Otherwise, making changes in the future will break existing features.

1. The code is untidy and long:
    > Why is all the code in one place?
                                              
    The `Main` class attempts to do it all. 
    Code for visual tweaks, listeners and even utility methods are all in one file.
    This makes it difficult to find and make changes to existing code.

# The solution
FXML is a XML-based language that allows us to define our user interface.
Properties of JavaFX objects can be defined in the FXML file.
For example:  
```xml
 <TextField fx:id="userInput" layoutY="558.0" onAction="#handleUserInput" prefHeight="41.0" prefWidth="324.0" AnchorPane.bottomAnchor="1.0" />
```

The FXML snippet define a TextField similar to the one that we programmatically defined previous in Tutorial 2.
Notice how concise FXML is compared to the plain Java version.

Let's return to Duke and convert it to use FXML instead.

# Rebuilding our scenes
Scene Builder is a tool developed by Oracle and currently maintained by Gluon.
It is a What-You-See-Is-What-You-Get GUI creation tool. 
[Download](https://gluonhq.com/products/scene-builder/#download) the appropriate version for your OS and install it: 

Let’s explore the provided FXML files in Scene Builder.
Running the tool brings up the main screen.
Select `Open Project` > `src/main/resources/view/MainWindow.fxml`.
Inspect each control and its properties.

![SceneBuilder opening MainWindow.fxml](assets/SceneBuilder.png)

On the right accordion pane, you can modify the properties of the control that you have selected.
Try changing the various settings and see what they do!
 
On the left accordion, you can see that we have set the controller class to `MainWindow`. 
We will get to that later.
 
![Controller for MainWindow](assets/MainWindowController.png)

Let’s repeat the process for `DialogBox`.
The main difference here is that DialogBox checks `Use fx:root construct` and _does not define a controller class_. 


![Settings for DialogBox](assets/DialogBoxController.png)

# Structure of a Controller.
Let's take a closer look at the `MainWindow` class that we specified as the controller in the FXML file:

```java
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = DukeStub.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, duke)
        );
        userInput.clear();
    }

}
```

As the controller, `MainWindow` is solely responsible for the logic behind the application.

The `@FXML` annotation marks a `private` or `protected` member and makes it accessible to FXML despite its modifier.
Without the annotation, we will have to make everything `public` and expose our UI to unwanted changes.

The `FXMLLoader` will map the a control with a `fx:id` defined in FXML to a variable with the same name in its controller.
Notice how in `MainWindow`, we can invoke `TextField#clear()` on `userInput` and access its content just as we did in the previous example.
Similarly, methods like private methods like `handleUserInput` can be used in FXML when annotated by `@FXML`. 

# Using FXML in our application

We load a FXML file using a `FXMLLoader`.
Note how we use `getResource` instead of using `InputStreams` from `java.io` as our resources will no longer be accessible through file IO when we eventually package our application into an executable `jar`.

```java
@Override
public void start(Stage stage) {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = (AnchorPane) fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

Again, we can interact with the `AnchorPane` defined in the FXML as we would have if we created the `AnchorPane` ourselves.

For our custom `DialogBox`, we did not define a controller.
Looking at the code for the `DialogBox` class:

```java
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }
    // ... 
}
```

When we create a new instance of `DialogBox`, we set both the controller and root Node to `DialogBox`. 
From this point onwards we can interact with `DialogBox` as we have in the previous tutorials.

[todo]: # (Discussion on the fx:root pattern.)

# Exercise

1. Convert `MainWindow` to use the `fx:root` construct.
1. Extend `MainWindow` to have a `Stage` as a root Node.
1. Customize the appearance of the application further with CSS.  