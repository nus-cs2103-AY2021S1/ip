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
        ArrayList<task> list = new ArrayList<>();
        while(!input.equals("bye")){
            System.out.println("_____________________________");
            if (input.equals("list")){
                print(list);
                System.out.println("_____________________________");
                input = sc.nextLine();
                continue;
            }
            String[] request = input.split(" ");
            if (request[0].equals("done")){
                int index = Integer.parseInt(request[1]);
                list.get(index - 1).updateStatus();
                System.out.println("Nice! I've marked this task as done: \n [✓] " + list.get(index - 1));
                System.out.println("_____________________________");
                input = sc.nextLine();
                continue;
            }
            list.add(new task(input));
            System.out.println("added: " + input);
            System.out.println("_____________________________");
            input = sc.nextLine();
//            System.out.println("_____________________________");
        }
        System.out.println("_____________________________");
        System.out.println("Bye. Hope to see you again soon!\n_____________________________");
    }

    public static void print(ArrayList<task> list){
//        System.out.println("_____________________________");
        for (int i = 0; i < list.size(); i++){
            if (!list.get(i).isDone()) {
                System.out.println("" + (i + 1) + ".[✗]" + list.get(i));
            } else {
                System.out.println("" + (i + 1) + ".[✓]" + list.get(i));
            }
        }
//        System.out.println("_____________________________");
    }
}
