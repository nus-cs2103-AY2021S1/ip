import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    /*public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }*/

    public static void main(String[] args) {
        ArrayList<String> arrList= new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        String endDuke = "bye";
        String greeting = "____________________________________________________________" +
                "\n" +
                "Hello! I'm Duke \nWhat can I do for you? \n" +
                "____________________________________________________________";

        System.out.println(greeting);
        String userinput = scanner.nextLine();
        while(!userinput.equals("bye")) {
            if(userinput.equals("list")){
                String arrString = "";

                for(int i = 0 ; i < arrList.size(); i++){
                    arrString += (i+1) +". "+ arrList.get(i) +"\n";
                }
                String reply = "____________________________________________________________\n"  + arrString + "\n"
                        +"____________________________________________________________";
                System.out.println(reply);
                userinput=scanner.nextLine();
            }else {
                arrList.add(userinput);

                String reply = "____________________________________________________________\n" + "added: " + userinput + "\n"
                        + "____________________________________________________________";
                System.out.println(reply);
                userinput = scanner.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
