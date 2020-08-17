import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\n"
                + " oooooooooooo                                                 oooo        \n"
                + "d'\"\"\"\"\"\"d888'                                                 `888        \n"
                + "      .888P    .ooooo.  oooo d8b  .ooooo.   .oooo.   oooo d8b  888  oooo  \n"
                + "     d888'    d88' `88b `888\"\"8P d88' `88b `P  )88b  `888\"\"8P  888 .8P'   \n"
                + "   .888P      888   888  888     888   888  .oP\"888   888      888888.    \n"
                + "  d888'    .P 888   888  888     888   888 d8(  888   888      888 `88b.  \n"
                + ".8888888888P  `Y8bod8P' d888b    `Y8bod8P' `Y888\"\"8o d888b    o888o o888o \n"
                + "                                                                          \n"
                + "                                                                          \n"
                + "                                                                          \n";
        String lowerLine = "(¯`·._.·(¯`·._.· Zoroark ·._.·´¯)·._.·´¯)\n";
        String upperLine = "    ♫♪.ılılıll|̲̅̅●̲̅̅|̲̅̅=̲̅̅|̲̅̅●̲̅̅|llılılı.♫♪\n";
        String goodbye = " __   __   __   __   __       ___ \n"
                + "/ _` /  \\ /  \\ |  \\ |__) \\ / |__  \n"
                + "\\__> \\__/ \\__/ |__/ |__)  |  |___ \n" + "                                  ";

        System.out.println("Hello I am\n" + logo + "What do you want to talk about?");
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        ArrayList<String> answers = new ArrayList<>();
        while (!answer.equals("bye")) {
            if (answer.equals("list")) {
                System.out.println(upperLine);
                for (int i = 0; i < answers.size(); i++) {
                    System.out.println((i + 1) + ". " + answers.get(i) + "\n");
                }
                System.out.println(lowerLine);
            } else {
                System.out.println(upperLine + "Added: " + answer + "\n" + lowerLine);
                answers.add(answer);
            }
            answer = in.nextLine();
        }

        System.out.println(upperLine + goodbye + "\n" + lowerLine);
    }
}
