import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<String> taskList;

    public Duke() {
        taskList = new LinkedList<>();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.hello();
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        while(!next.equals("bye")){
            if(next.equals("list")){
                duke.printList();
            } else {
                duke.Echo(next);
            }
            next = sc.nextLine();
        }
        duke.goodbyeMessage();
    }

    public void goodbyeMessage() {
        System.out.println("********************************************");
        System.out.println("GoodBye, Hope to see you back soon.");
        System.out.println("********************************************");
    }

    public void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }
    public void Echo(String input) {
        System.out.println("********************************************");
        this.addTask(input);
        System.out.println("added new Task: "+ input);
        System.out.println("********************************************");
        System.out.println();
    }

    public void addTask(String task) {
        this.taskList.add(task);
    }

    private void printList() {
        for(int i = 0; i < this.taskList.size(); i++) {
            System.out.println( (i+1) + ". " + this.taskList.get(i));
        }
    }
}
