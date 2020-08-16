import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String intro = "____________________________________________________________ \n" +
                        "Hello! I'm Duke \n" + "What can I do for you? \n" +
                        "____________________________________________________________";
        System.out.println(intro);
        Scanner input =  new Scanner(System.in);
        String underscore = "____________________________________________________________ \n";
        String line = "";
        while(!line.equals("bye")) {
            line = input.nextLine();
            if(line.equals("bye")) {
                System.out.println(underscore + " Bye. Hope to see you again soon!" + "\n" + underscore);
            } else {
                System.out.println(underscore + " " + line + "\n" + underscore);
            }
        }
        input.close();
    }
}
