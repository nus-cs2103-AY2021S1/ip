import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Repl {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        FileLoader.readSavedFile(manager);
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            try {
                if (next.equals("list")) {
                    manager.printList();
                    continue;
                }
                String[] actionExtracted = Evaluator.extractAction(next);
                String status = actionExtracted[0];
                String body = actionExtracted[1];
                switch (status) {
                case "done":
                    manager.markTaskAsDone(Integer.parseInt(body));
                    break;
                case "todo":
                    manager.addTask(body, status);
                    break;
                case "delete":
                    manager.deleteTask(Integer.parseInt(body));
                    break;
                default:
                    String[] timeExtracted = Evaluator.extractTime(body);
                    String content = timeExtracted[0];
                    String time = timeExtracted[1];
                    manager.addTask(content, status, time);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(Messenger.INDEX_FORMAT_ERROR);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(Messenger.COMMAND_UNRECOGNIZABLE_ERROR);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                next = sc.nextLine();
            }
        }
        FileLoader.saveDataToFile(manager);
    }
}
