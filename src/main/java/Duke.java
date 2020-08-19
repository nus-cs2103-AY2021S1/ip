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
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);
        String input;
        String line = "____________________________________________________________\n";
        while(isRunning){
            input = sc.nextLine();
            if(input.equals("bye")){
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                isRunning = false;
            }
            else {
                System.out.println(line);
                System.out.println(input);
                System.out.println(line);
            }
        }
    }
}
