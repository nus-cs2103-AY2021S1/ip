import src.main.java.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class Duke {
    int introVersion;
    int index;
    List<Task> ls;

    public Duke(int num) {
        this.introVersion = num;
        this.index = 0;
        this.ls = new ArrayList<Task>();
    }

    // prints introduction of klaun
    public void introDuke() {
        switch(this.introVersion) {
            case 0 :
                System.out.println("I'm Klaun (=^.^=) How are you doing today ?\n");
                System.out.println("Is there anything I can help you with ?\n");
                System.out.println("<----------------------------------------------->\n");
                break;
            case 1 :
                System.out.println("I'm Klaun (>_<) Hope you are feeling great today <3\n");
                System.out.println("Is there anything you need ?\n");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                break;
            case 2 :
                System.out.println("I'm Klaun (*_*) I hope you are having a wonderful day today :)\n");
                System.out.println("What can I do to make it better?\n");
                System.out.println("><><><><<><><><><><><><><><><><><><><><><><><><><><><><\n");
                break;
        }

    }

    public int getIndex() {
        return this.index;
    }

    // prints output based on command
    public void echoCommand(String command) {
       if (this.introVersion == 0) {
           System.out.println("<----------------------------------------------->\n");
           if (command.equals("bye")) {
               System.out.println("oh man ... bye ~~ o.o \n");
           } else {
               System.out.print("added: " + command + "\n");
           }
           System.out.println("<----------------------------------------------->\n");
       } else if (this.introVersion == 1) {
           System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
           if (command.equals("bye")) {
               System.out.println("oh man ... bye ~~ o.o \n");
           } else {
               System.out.print("added: " + command + "\n");
           }
           System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
       } else {
           System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
           if (command.equals("bye")) {
               System.out.println("oh man ... bye ~~ o.o \n");
           } else {
               System.out.print("added: " + command + "\n");
           }
           System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
       }
    }

    // prints list of tasks
    public void getList() {
        System.out.println("#################################################\n");
        System.out.println("Here's your amazing task list :");
        for (int i = 0; i < this.index; i++) {
            String item = this.ls.get(i).getItem();
            String status = this.ls.get(i).getStatus();
            if (i + 1 != this.index) { // if last item in ls
                System.out.println(i + ". " + status + " " + item);
            } else {
                System.out.println(i + ". " + status + " " + item + "\n");
            }
        }
        System.out.println("#################################################\n");
    }

    // adds task to list
    public void addToList(Task item) {
        this.ls.add(item);
        this.index++; // increment index
    }

    // mark task as done
    public void markDone(int index) {
        this.ls.get(index).markAsDone();

        System.out.println("=================================================\n");
        System.out.println("Yayyyy !! Letsgedditt");
        System.out.println(index + ". " + this.ls.get(index).getStatus() + " " + this.ls.get(index).getItem() + "\n");
        System.out.println("=================================================\n");
    }

    public static void main(String[] args) {

        String logo = "⊂_ヽ\n" +
                "　 ＼＼ ＿\n" +
                "　　 ＼(　•_•) F\n" +
                "　　　 <　⌒ヽ A\n" +
                "　　　/ 　 へ＼ B\n" +
                "　　 /　　/　＼＼ U\n" +
                "　　 ﾚ　ノ　　 ヽ_つ L\n" +
                "　　/　/ O\n" +
                "　 /　/| U\n" +
                "　(　(ヽ S\n" +
                "　|　|、＼\n" +
                "　| 丿 ＼ ⌒)\n" +
                "　| |　　) /\n" +
                "`ノ )　 Lﾉ";
        System.out.println("HIIIIII\n" + logo + "\n");

        Scanner sc = new Scanner(System.in);

        // generate random number for duke's introduction version
        Random rand = new Random();
        int num = rand.nextInt(3);

        // instantiate duke object
        Duke klaun = new Duke(num);

        // introduce duke
        klaun.introDuke();

        // get input
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) { // if user calls for list
                klaun.getList();
            } else if (command.contains(" ") && command.split(" ")[0].equals("done") && parseInt(command.split(" ")[1]) < klaun.getIndex()) { // if its "done x"
                klaun.markDone(parseInt(command.split(" ")[1]));
            } else {
                // add item to list
                klaun.addToList(new Task(command));

                // print item
                klaun.echoCommand(command);
            }

            command = sc.nextLine();
        }

        klaun.echoCommand(command);
    }
}
