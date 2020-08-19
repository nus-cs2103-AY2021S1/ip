import java.util.Scanner;

public class Duke {

    static String line = "—————————————————————————————————————————————————————————————————";
    static String[] list = new String[100];
    static int index = 0;

    public static void greeting() {
        String str = ("\t" + line + "\n"
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + "\t" + line);
        System.out.println(str);
    }

    public static void bye(){
        System.out.println("\t" + line + "\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t" + line);
    }

    public static void repeat(String input){
        if (input.equals("bye")) {
            bye();
        } else {
            System.out.println( "\t" + line + "\n"
                    + "\t" + input + "\n"
                    + "\t" + line);
        }
    }

    public static void addTask(String task) {
        list[index] = task;
        index++;
        System.out.println("\t" + line + "\n"
                + "\t" + "added: " + task + "\n"
                + "\t" + line);
    }

    public static void showList(){
        System.out.println("\t" + line);
        for (int i = 0; i < index; i++){
            System.out.println("\t" + (i+1) + ". " + list[i]);
        }
        System.out.println("\t" + line);
    }



    public static void main(String[] args) {
        greeting();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                bye();
                sc.close();
                break;
            } else if (input.equals("list")){
                showList();
            } else {
                addTask(input);
            }
        }
    }

}
