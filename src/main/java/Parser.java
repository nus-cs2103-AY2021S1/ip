import java.util.Scanner;

/**
 * deals with making sense of the user command
 */

public class Parser {
    private Ui ui;
    private TaskList tasks;

    Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public String readIn() {
        String input;
        Scanner scan = new Scanner(System.in);

        input = scan.nextLine();

        return input;
    }

    public void responder() {
        String input = this.readIn();
        String firstWord = input.split(" ")[0];

        while (true) {
            StringBuilder sb = new StringBuilder();
            sb.append("--------------------------------------------------------------\n");

            try {
                if (firstWord.equals("bye")) {
                    sb.append(this.ui.exit());
                    sb.append("\n--------------------------------------------------------------");
                    System.out.println(sb.toString());
                    break;
                } else if (firstWord.equals("list")) {
                    sb.append(this.tasks.returnList());
                } else if (firstWord.equals("done")) {
                    int i = Integer.valueOf(input.substring(5));
                    sb.append(this.tasks.done(i));
                } else if (firstWord.equals("delete")) {
                    int i = Integer.valueOf(input.substring(7));
                    sb.append(this.tasks.delete(i));
                } else if (firstWord.equals("todo")) {
                    Task task = new ToDo(input);
                    sb.append(this.tasks.add(task));
                } else if (firstWord.equals("deadline")) {
                    Task task = new Deadline(input);
                    sb.append(this.tasks.add(task));
                } else if (firstWord.equals("event")) {
                    Task task = new Event(input);
                    sb.append(this.tasks.add(task));
                } else if (firstWord.equals("find")) {
                    sb.append(this.tasks.find(input.substring(5)));
                }
                else {
                    throw new DukeException("oops! im sorry, but i do not know what that means :-(");
                }
            } catch (EmptyDescriptionException e) {
                sb.append(e.getMessage());
            } catch (DukeException e) {
                sb.append(e.getMessage());
            }
            sb.append("\n--------------------------------------------------------------");
            System.out.println(sb.toString());
            input = this.readIn();
            firstWord = input.split(" ")[0];
        }
    }
}
