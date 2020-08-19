import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> myList = new ArrayList<>();

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(
                "_______________________________"+
                        "Hello! I'm Duke\n"+
                        "What can I do for you?\n"+
                        "_______________________________"
        );
        String input = scan.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < myList.size(); i++) {

                    Task currentTask = myList.get(i);

                    System.out.println((i + 1) + ". " + currentTask.toString());
                }
            }else if(input.startsWith("done")){
                int taskNum = Character.getNumericValue(input.charAt(5));
                Task currentTask = myList.get(taskNum-1);

                currentTask.finishTask();

                System.out.println(
                        "_______________________________\n" +
                                "Nice! I've marked this task as done:\n"+
                                currentTask.toString()+"\n"+
                        "_______________________________");


            }else {
                System.out.println(
                        "_______________________________\n" +
                                "added: " + input + "\n" +
                                "_______________________________");
                myList.add(new Task(input));

            }
            input = scan.nextLine();

        }
        System.out.println("_______________________________\n"+
                "Bye. Hope to see you again soon!");

    }
}
