import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<String> todos = new ArrayList<>();
    private static void scan(){
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()) {
            do {
                todos.add(sc.nextLine());
            } while (sc.hasNextLine());
        }
    }
    public static void output(){
        for(String string : todos){
            System.out.println("\n" + string + "\n  ____________________________________________________________");
            if(string.equals("bye")){
                System.out.println("  Bye. Hope to see you again soon!\n" + "  ____________________________________________________________");
                break;
            }
            if(string.equals("list")){
                for(int i = 0; i < todos.size(); i++){
                    if(!todos.get(i).equals("list") && !todos.get(i).equals("bye")) {
                        System.out.println("  " + (i + 1) + ". " + todos.get(i));
                    }

                }
                System.out.println("\n  ____________________________________________________________");
            }else {
                System.out.println("  " + "added: " + string + "\n" +
                        "  ____________________________________________________________");
            }
        }

    }
    public static void main(String[] args) {
        scan();
        System.out.println("  ____________________________________________________________\n" + "  Hello! I'm Duke\n" + "  What can I do for you?\n"
                + "  ____________________________________________________________");
        output();
    }
}