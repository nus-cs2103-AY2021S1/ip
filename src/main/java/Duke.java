import java.util.Scanner;
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


public class Duke extends Application{

    private final Storage storage;
    private final TaskList inputTasks;

    /**
     * Duke constructor
     * @param filePath the file for storage of tasks
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        inputTasks = new TaskList(Storage.readFile(filePath));
    }

    /**
     * runs Duke
     */
    /*public void run(){
        Ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String ans = sc.nextLine();
        while(!ans.equals("bye")){
            try {
                Command c = Parser.parse(ans);
                c.execute(this.inputTasks, this.storage);
                ans = sc.nextLine();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        Ui.showGoodbye();

    }*/

    /**
     * Start of the program
     * @param args command-line arguments for execution
     */
    public static void main(String[] args){
        //new Duke("./data/duke.txt").run();
    }

    @Override
    public void start(Stage stage){
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

    }
}
