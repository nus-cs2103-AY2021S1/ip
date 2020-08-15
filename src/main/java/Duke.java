import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("_____________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("_____________________________");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<String> list = new ArrayList<>();
        while(!input.equals("bye")){
//            if (input.equals("list")){
//                print(list);
//                input = sc.nextLine();
//                continue;
//            }
            System.out.println("_____________________________");
            System.out.println(input);
//            list.add(input);
//            System.out.println("added: " + input);
            System.out.println("_____________________________");
            input = sc.nextLine();
        }
        System.out.println("_____________________________");
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void print(ArrayList<String> list){
        System.out.println("_____________________________");
        for (int i = 0; i < list.size(); i++){
            System.out.println("" + (i + 1) + ". " + list.get(i));
        }
        System.out.println("_____________________________");
    }
}
