import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws DukeException, IOException {

        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        Storage storage = new Storage(taskList,parser);

        Scanner Sc = new Scanner(System.in);
        String line = "____________________________\n"
                     +"____________________________\n";

        String logo = "****** ****** ****** ******\n"
                     +"   *   *      *      *\n"
                     +"   *   ****** ****** ******\n"
                     +"*  *   *      *      *\n"
                     +"***    ****** *      *\n";

        System.out.println("My name is\n" + logo);
        System.out.println("What do you want?");
        System.out.println(line);

        while(taskList.isUpdating) {
            String[] inputs = Sc.nextLine().trim().split(" ", 2);
            parser.ParseCommand(inputs);
            System.out.println(line);
        }

        storage.saveFile();
    }



}