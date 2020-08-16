import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputs = new Scanner(System.in);
        boolean run = true;

        System.out.println("Hello~, I'm Duke!");

        while (run == true) {
            String next = inputs.next();

            if (next.equals("bye")) {
                System.out.println("Goodbye~");
                run = false;
            } else {
                System.out.println(next);
            }
        }
    }

    
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
}
