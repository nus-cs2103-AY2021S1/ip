package duke.Command;

import duke.*;

public class AddCommand extends Command {

    private final String desc;

    AddCommand(String description) {
        this.desc = description;
    }

    @Override
    public String execute() {

        String[] words = this.desc.split("\\s+");

        StringBuilder taskDescr = new StringBuilder();
        StringBuilder atOrBy = new StringBuilder();
        int index = 0;
        for (int i = 1; i < words.length; i ++) {
            if (words[i].equals("/by") || words[i].equals("/at")) {
                index = i;
                break;
            }
            taskDescr.append(words[i]).append(" ");
        }

        // at/by
        for (int i = index + 1; i < words.length; i ++) {
            if (i == words.length - 1) {
                atOrBy.append(words[i]);
            } else {
                atOrBy.append(words[i]).append(" ");
            }
        }

        if (desc.contains("todo")) {
            listArray.add(new ToDo(taskDescr.toString()));
        } else if (desc.contains("deadline")) {
            listArray.add(new Deadline(taskDescr.toString(), atOrBy.toString()));
        } else if (desc.contains("event")) {
            listArray.add(new Event(taskDescr.toString(), atOrBy.toString()));
        } else {
            listArray.add(new Task(desc));
        }

        // return the last added, which is the latest
        return Message.ADDED + listArray.get((listArray.size()) - 1) + "\n" + Message.INDENT
                + "Now you have " + listArray.size() +
                (listArray.size() == 1 ? " task " : " tasks ")
                + "in the list";
    }
}
