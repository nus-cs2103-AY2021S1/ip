import java.util.Scanner;

public class Duke {
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }

    public static void main(String[] args) {

        String gap = "        ";

        String init = "        ____________________________________________________________\n" +
                "        Hello! I'm Duke\n" +
                "        What can I do for you?\n" +
                "        ____________________________________________________________\n";

        System.out.println(init);

        Scanner sc = new Scanner(System.in);
        StringBuilder sb;
        String temp;
        while(!(temp = sc.nextLine()).equals("bye")) {
            sb = new StringBuilder();
            sb.append(gap);
            sb.append("____________________________________________________________\n");
            sb.append(gap);
            sb.append(temp);
            sb.append("\n");
            sb.append(gap);
            sb.append("____________________________________________________________\n");
            System.out.println(sb.toString());
        }


        String exit = "        ____________________________________________________________\n" +
                "        Bye. Hope to see you again soon!\n" +
                "        ____________________________________________________________\n";

        System.out.println(exit);
    }
}