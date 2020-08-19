import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        run();
    }

    public static void greet(){
        String logo = "____________________________________________________________\n"
                /*+ " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"*/
                + " Hello I'm Duke \n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(logo);
    }

    public static void run(){
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        String input;
        String line = "____________________________________________________________\n";
        ArrayList<String> list = new ArrayList<>();
        while(isRunning){
            input = sc.nextLine();
            if(input.equals("list")){
                System.out.println(line);
                for(int i = 1; i <= list.size(); i++){
                    System.out.println(i + ". " + list.get(i-1));
                }
                System.out.println(line);
            }
            else if(input.equals("bye")){
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                isRunning = false;
            }
            else{
                list.add(input);
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }
    }
}
