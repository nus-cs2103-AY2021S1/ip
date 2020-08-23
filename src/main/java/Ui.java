import java.util.Scanner;

public class Ui {

    private Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);
    }

    // format horizontal line to create divisions
    public String div() {
        return "\n______________________________"
            + "________________________________\n\n";
    }

    public String greet() {
        return "                      █████████\n"
            +  "  ███████          ███        ███\n"
            +  "  █      █       ███             ███\n"
            +  "   █      █    ██                   ██\n"
            +  "    █     █   ██     ██      ██     ███          Hey! I'm Jimmy,\n"
            +  "     █   █   █      ████    ████      ██     your personal assistant!\n"
            +  "   █████████████                      ██\n"
            +  "   █            █         █           ██   What can I do for you today?\n"
            +  " ██             █   ██          ██    ██\n"
            +  "██   ███████████     ██        ██     ██\n"
            +  "█               █      ████████       ██\n"
            +  "██              █                    ██\n"
            +  " █   ███████████                   ██\n"
            +  " ██          ████                 █\n"
            +  "  ████████████   █████████████████";
    }

    public String process() {
        return this.sc.nextLine();
    }

    // printing messages for user to view
    public void speak(String input) {
        System.out.println(this.div() +
            "\t" + input + this.div());
    }
}