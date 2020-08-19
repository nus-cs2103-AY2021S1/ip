import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> myList = new ArrayList<>();

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(
                "_______________________________"+
                        "Hello! I'm Duke\n"+
                        "What can I do for you?\n"+
                        "_______________________________"
        );
        String input = scan.nextLine();
        while(!input.equals("bye")) {
            if(input.equals("list")){
                for(int i = 0 ; i <myList.size();i++) {
                    System.out.println((i + 1) +". "+myList.get(i));
                }
            }else {
                System.out.println(
                        "_______________________________\n" +
                                "added: " + input + "\n" +
                                "_______________________________");
                myList.add(input);
            }
            input = scan.nextLine();

        }
        System.out.println("_______________________________\n"+
                "Bye. Hope to see you again soon!");

    }
}
