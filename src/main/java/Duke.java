import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String hello = "Hello! I'm Duke";
        String question = "What can I do for you?";
        System.out.println(hello + "\n" + question);
        String ans = sc.nextLine();
        ArrayList<Task> listOfStuff = new ArrayList<>();
        while (ans != null) {
            if (ans.equals("list")){
                for(int i = 0; i <listOfStuff.size(); i++){
                    Integer listNum = i + 1;
                    System.out.println(listNum.toString() + ".[" +
                            listOfStuff.get(i).getStatusIcon() + "] " + listOfStuff.get(i).description);
                }
            }else if (ans.substring(0, 4).equals("done")) {
                listOfStuff.get(Integer.parseInt(ans.substring(5)) - 1).markAsDone();
            }else{
                listOfStuff.add(new Task(ans));
                System.out.println("added: " + ans);
            }
            ans = sc.nextLine();
            if (ans.equals("bye")) {
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
