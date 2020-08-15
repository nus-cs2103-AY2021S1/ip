import main.java.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<Task> taskList;

    public Duke() {
        taskList = new LinkedList<>();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.hello();
        Scanner sc = new Scanner(System.in);
        String[] next = sc.nextLine().split(" ",2);
        while (!next[0].equals("bye")) {
            if (next[0].equals("list")){
                duke.printList();
            } else if (next[0].equals("done")) {
                Integer toBeChange = Integer.valueOf(next[1]);
                Task cur = duke.taskList.get(toBeChange-1);
                cur.changeIsDone();
                System.out.println("This task has been mark as done.");
                System.out.println(cur);
                System.out.println();
            } else if (next[0].equals("todo")) {
                Task temp = new Task(next[1]);
                duke.addTask(temp);
            } else if (next[0].equals("deadline")) {
                String[] str = next[1].split("/by", 2);
                Task temp = new Deadline(str[0], str[1]);
                duke.addTask(temp);
            } else if (next[0].equals("event")) {
                String[] strEvent = next[1].split("/at",2);
                Task temp = new Event(strEvent[0], strEvent[1]);
                duke.addTask(temp);
            } else {
                System.out.println("Command not known");
            }
            next = sc.nextLine().split(" ",2);
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



    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("********************************************");
        System.out.println("Added new task " + task);
        System.out.println("********************************************");
        System.out.println();
    }

    private void printList() {
        for(int i = 0; i < this.taskList.size(); i++) {
            System.out.println( (i+1) + ". " + this.taskList.get(i));
        }
        System.out.println();
    }

}
