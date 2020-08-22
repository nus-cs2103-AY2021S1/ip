import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Parser {
    private static final Ui ui = new Ui();

    public static String parseDateTime(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy h:mma");
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HHmm");

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (s.contains(" ")) {
                return formatter.format((parser.parse(s)));
            } else {
                return dateFormatter.format(dateParser.parse(s));
            }
        } catch (ParseException e) {
            return ui.getBorder() + "Please input the time and date in\n"
                            + dateParser.toPattern() + " or " + parser.toPattern() + "\n" + ui.getBorder();
        }
    }
    
    public static void parseInput(TaskList taskList, Storage storage) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String test = scan.next();
            if (ui.checkBye(test)) {
                ui.exitLine();
                break;
            } else {
                String next = scan.nextLine().replaceFirst(" ", "");
                if (taskList.checkList(test)) {
                    taskList.displayList();
                } else if (taskList.checkDone(test)) {
                    try {
                        taskList.doneTask(next);
                        storage.updateFile();
                    } catch (DukeException e) {
                        System.out.println(ui.getBorder() + e.getMessage() + "\n" + ui.getBorder());
                    }
                } else if (taskList.checkDel(test)) {
                    try {
                        taskList.delTask(next);
                        storage.updateFile();
                    } catch (DukeException e) {
                        System.out.println(ui.getBorder() + e.getMessage() + "\n" + ui.getBorder());
                    }
                } else {
                    try {
                        taskList.addTask(test, next);
                        storage.updateFile();
                    } catch (DukeException e) {
                        System.out.println(ui.getBorder() + e.getMessage() + "\n" + ui.getBorder());
                    }
                }
            }
        }
        scan.close();
    }
}
