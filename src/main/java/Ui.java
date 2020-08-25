import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
    private int lengthOfLine = 55;
    private String line;
    private Scanner sc;

    public Ui() {
        this.line = getHorizontalLine();
        this.sc = new Scanner(System.in);
    }

    public String getHorizontalLine() {
        String line = "";
        for (int i = 0; i < lengthOfLine; i++) {
            line = line + "-";
        }
        return line;
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void closeScanner() {
        sc.close();
    }

    public void showLine() {
        System.out.println(line);
    }

    public String readCommand() throws DukeException {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            throw new DukeException("There is no next line");
        }
    }

    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showListMessage() {
        this.showLine();
    }

    public void showDoneMessage() {
        this.showLine();
    }

    public void showDeleteMessage() {
        this.showLine();
    }

    public void showDateMessage() {
        this.showLine();
    }

    public void showAddMessage() {
        this.showLine();
    }

    public void greet() {
        String logo =
                "                 .   ,\n" +
                        "               .';_.';\n" +
                        "                    _   \\\n" +
                        "             .     (.) (.)--._\n" +
                        "            .       \"   \"     `.\n" +
                        "           .                   :\n" +
                        "          .           `\"-.___.\"\n" +
                        "         .   .         `.\n" +
                        "         .    .  `.      .\n" +
                        ",,.      .      ` . `.    .\n" +
                        "\\W;      .         \"`     .\n" +
                        "   `--'    ,    __,..-   '  .\n" +
                        "          .   .'     `.   `' ;\n" +
                        "          `.   `,      `.  .'\n" +
                        "            \"._.'        `'";
        System.out.println("Hello from Moomin\n" + logo);
        System.out.println("I'm your personal assistant.");
    }
}
