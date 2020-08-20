import java.util.Scanner;

public class Duke {
    static Task[] list = new Task[100];
    static int index = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        bot();
    }

    public static void bot(){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                printlist();
            } else if (input.split(" ")[0].equals("done")) {
                int num = Integer.parseInt(input.split(" ")[1]);
                list[num-1] = list[num-1].completedTask();
            } else {
                add(input);
            }
        }
    }

    public static void add(String input){
        list[index] = new Task(input);
        index += 1;
        System.out.println("added: " + input);
    }

    public static void printlist(){
        int tempindex = 1;
        for (int k =0; list[k] != null; k++ ){
            System.out.println(tempindex + "." + list[k]);
            tempindex += 1;
        }
    }


}

