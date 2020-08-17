import java.util.Scanner;
import java.util.Random;

public class Duke {
    int introVersion;
    int index;
    String[] ls;

    public Duke(int num) {
        this.introVersion = num;
        this.index = 0;
        this.ls = new String[100];
    }

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

    public void echoCommand(String command) {
       if (this.introVersion == 0) {
           System.out.println("<----------------------------------------------->\n");
           if (command.equals("bye")) {
               System.out.println("oh man ... bye ~~ o.o");
           } else {
               System.out.print("added: " + command + "\n");
           }
           System.out.println("<----------------------------------------------->\n");
       } else if (this.introVersion == 1) {
           System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
           if (command.equals("bye")) {
               System.out.println("oh man ... bye ~~ o.o");
           } else {
               System.out.print("added: " + command + "\n");
           }
           System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
       } else {
           System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
           if (command.equals("bye")) {
               System.out.println("oh man ... bye ~~ o.o");
           } else {
               System.out.print("added: " + command + "\n");
           }
           System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
       }
    }

    public void getList() {
        System.out.println("#################################################\n");
        for (int i = 0; i < this.index; i++) {
            if (i + 1 != this.index) {
                System.out.println(i + ". " + this.ls[i]);
            } else {
                System.out.println(i + ". " + this.ls[i]
                        + "\n");
            }
        }
        System.out.println("#################################################\n");
    }

    public void addToList(String item) {
        this.ls[this.index] = item;
        this.index++; // increment index
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
            } else {
                // add item to list
                klaun.addToList(command);

                // print item
                klaun.echoCommand(command);
            }

            command = sc.nextLine();
        }

        klaun.echoCommand(command);
    }
}
