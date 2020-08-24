import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ui {
    public  Ui(){
    }
    public void uiRun(TaskList taskList, Storage store) throws IOException {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        ArrayList<Task> todoList = taskList.todoList;
        System.out.println("What's new scooby doo?\n" + "How can I help you today?");
        while (sc.hasNext()) {
            parser.parse(sc, todoList, store);
        }
    }


}