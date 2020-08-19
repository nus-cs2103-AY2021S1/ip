import java.util.Scanner;

public class Duke {

    static String line = "—————————————————————————————————————————————————————————————————";
    static Task[] list = new Task[100];
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

    public static void addTask(Task task) {
        list[index] = task;
        index++;
        System.out.println("\t" + line + "\n"
                + "\t" + "added: " + task + "\n"
                + "\t" + line);
    }

    public static void showList(){
        System.out.println("\t" + line + "\n\tHere are the tasks in your list:");
        for (int i = 0; i < index; i++){
            System.out.println("\t" + (i+1) + ". " + list[i]);
        }
        System.out.println("\t" + line);
    }

    public static String firstWord(String str) {
        return str.split(" ")[0]; // create array of words and return 0th word
    }

    public static int getTaskNum(String str) {
        return Integer.parseInt(str.split(" ")[1]);
    }

    public static void markDone(int num) {
        if (list[num-1] == null) {
            System.out.println("Error :("); // error catch
        } else {
            list[num - 1].markDone();
            System.out.println("\t" + line + "\n\tNice! I've marked this task as done:\n\t  "
                    + list[num - 1]
                    + "\n\t" + line);
        }
    }



    public static void main(String[] args) {
        greeting();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            // possible actions: type task, list, bye, done 2
            if (input.equals("bye")) {
                bye();
                sc.close();
                break;
            } else if (input.equals("list")) {
                showList();
            } else if (firstWord(input).equals("done")) {
                int taskNum = getTaskNum(input);
                markDone(taskNum);
            } else {
                Task newTask = new Task(input);
                addTask(newTask);
            }
        }
    }

}
