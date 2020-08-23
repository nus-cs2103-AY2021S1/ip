import Exceptions.*;
public class AddCommand extends Command {
    AddCommand(String string) {
        super(string);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        super.execute(tasks, ui, storage);
        if (string.length() >= 4 && string.substring(0, 4).equals("todo")) {
            //System.out.println("\n" + string + "\n  ____________________________________________________________");
            if (string.length() == 4 || string.length() == 5) {
                throw new TodoException();
            } else {
                todo t = new todo(string.substring(5));
                t.output(storage.filePath);
            }
        } else if (string.length() >= 5 && string.substring(0, 5).equals("event")) {
            //System.out.println("\n" + string + "\n  ____________________________________________________________");
            if (string.length() == 5 || string.length() == 6) {
                throw new EventException(true);
            }
            String s = "";
            int index = -1;
            int end = -1;
            boolean duration = false;
            boolean time = false;
            String start = "";
            for (int i = 5; i < string.length(); i++) {
                if (string.charAt(i) == '/') {
                    index = i;
                    time = true;
                    break;
                }
                s = s + string.charAt(i);
            }
            for (int i = index + 1; i < string.length(); i++) {
                if (string.charAt(i) == '-' && i != string.length() - 1) {
                    end = i;
                    duration = true;
                    break;
                }
                start = start + string.charAt(i);
            }
            if (!time) {
                throw new EventException(false);
            }
            if (!duration) {
            }
            event d = event.provide(s.substring(1, s.length() - 1), string.substring(index + 4, end), string.substring(end + 1));
            d.output(storage.filePath);
        } else if (string.length() >= 8 && string.substring(0, 8).equals("deadline")) {
            if (string.length() == 8 || string.length() == 9) {
                System.out.println(new DeadlineException(true).toString());
            }
            String s = "";
            int index = -1;
            boolean time = false;
            for (int i = 8; i < string.length(); i++) {
                if (string.charAt(i) == '/') {
                    index = i;
                    time = true;
                    break;
                }
                s = s + string.charAt(i);
            }
            if (!time) {
                throw new DeadlineException(false);
            }
            Deadline d = Deadline.provide(s.substring(1, s.length() - 1), string.substring(index + 4));
            d.output(storage.filePath);
        }
    }
}
