import java.util.Scanner;
import java.util.Random;

public class Duke {
    int introVersion;

    public Duke(int num) {
        this.introVersion = num;
    }

    public void introDuke() {
        switch(this.introVersion) {
            case 0 :
                System.out.println("I'm Klaun ( ͡ᵔ ͜ʖ ͡ᵔ ) How are you doing today ?\n");
                System.out.println("Is there anything I can help you with ?\n");
                System.out.println("<----------------------------------------------->\n");
                break;
            case 1 :
                System.out.println("I'm Klaun (◕‿◕ ✿) Hope you are feeling great today <3\n");
                System.out.println("Is there anything you need ?\n");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                break;
            case 2 :
                System.out.println("I'm Klaun (☞ﾟヮﾟ)☞ ☜(ﾟヮﾟ☜) I hope you are having a wonderful day today :)\n");
                System.out.println("What can I do to make it better?\n");
                System.out.println("><><><><<><><><><><><><><><><><><><><><><><><><><><><><\n");
                break;
        }

    }

    public void echoCommand(String command) {
       if (this.introVersion == 0) {
           System.out.println("<----------------------------------------------->\n");
           System.out.print(command + "\n");
           System.out.println("<----------------------------------------------->\n");
       } else if (this.introVersion == 1) {
           System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
           System.out.println(command + "\n");
           System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
       } else {
           System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
           System.out.println(command + "\n");
           System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
       }
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

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
        Duke bot = new Duke(num);

        // introduce duke
        bot.introDuke();

        // get input
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            bot.echoCommand(command);
            command = sc.nextLine();
        }

        bot.echoCommand(command);
    }
}
