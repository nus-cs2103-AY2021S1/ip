import java.util.Scanner;

public class Ui {
    public void run() {

        // initialize utilities
        Scanner sc = new Scanner(System.in);
        TaskList list = new TaskList();
        Storage storage = new Storage(list);
        Parser parser = new Parser();

        // start working
        storage.readSavedFile();
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            try {
                if (next.equals("list")) {
                    list.printList();
                    continue;
                }
                String[] actionExtracted = parser.extractAction(next);
                String status = actionExtracted[0];
                String body = actionExtracted[1];
                switch (status) {
                case "done":
                    list.markTaskAsDone(Integer.parseInt(body));
                    break;
                case "todo":
                    list.addTask(body, status);
                    break;
                case "delete":
                    list.deleteTask(Integer.parseInt(body));
                    break;
                default:
                    String[] timeExtracted = parser.extractTime(body);
                    String content = timeExtracted[0];
                    String time = timeExtracted[1];
                    list.addTask(content, status, time);
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
        storage.saveDataToFile();
    }
}
