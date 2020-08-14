import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<String> toDoList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcome = "Hello! I'm Duke!\n" +
                         "What can I do for you?";
        System.out.println(logo + "\n" + welcome);
        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();
        while (!word.equals("bye")) {
            if (word.equals("list")) {
                for (String str : toDoList) {
                    System.out.println(str);
                }
            } else {
                System.out.println("added: " + word);
                storeItems(word);
            }
            word = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void storeItems(String item){
        int count = toDoList.size() + 1;
        if (count > 100) {
            System.out.println("You have 100 tasks yet to be cleared!");
        } else {
            String todo = count + ". " + item;
            toDoList.add(todo);
        }
    }
}
