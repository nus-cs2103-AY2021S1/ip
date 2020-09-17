package duke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner sc;
    //Object to store the list
    ArrayList<Task> itemList = new ArrayList<>();
    //create file
    String path = "out/todo.txt";


    public Ui() throws DukeException, IOException {
        this.sc = new Scanner(System.in);
    }


    public void takeUserInput(Storage storage) throws IOException, DukeException {
        while (sc.hasNextLine()) {
            String userMessage = sc.nextLine();
            //exit
            if (userMessage.equals("bye")) {
                System.out.println("Bye! Nice serving you. Hope to see you again soon! :D");
                break;
            }
            Parser.parseInput(userMessage, storage);
        }
    }

}
