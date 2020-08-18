import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today?");

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        List<Task> itemList = new ArrayList<>();

        while(true){
            String input = scanner.nextLine();
            if(input.contentEquals("bye")){
                System.out.println("\t" + "Bye. Hope to see you again soon!");
                break;
            }
            else if(input.contentEquals("list")){
                for(int i = 0; i < itemList.size(); i++){
                    System.out.println("\t" + (i + 1) + ". " + itemList.get(i));
                }
            }
            else{
                System.out.println("\t" + input);
                Task newTask = new Task(input);
                itemList.add(newTask);
            }
        }
    }
}
