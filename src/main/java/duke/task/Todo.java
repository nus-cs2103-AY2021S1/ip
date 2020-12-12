package duke.task;

import java.util.ArrayList;

public class Todo extends Task {

    /**
     * Gets the description of the Todo
     * @param input the todo input by user
     * @return the description of the Todo
     */
    public static String getDescription(String input) {
        int len = input.length(), end = 0;
        while (end < len && input.charAt(end) != '#') {
            end++;
        }
        return input.substring(0, end);
    }

    /**
     * Create a Todo object
     * @param input input by user
     * @return the Todo object
     */
    public static ArrayList<Todo> of(String input) {

        assert !input.equals("") : "input string passed into of method of Todo class is empty";

        String trimmed = trim(input, TaskType.TODO);
        if (trimmed.equals("")) {
            return null;
        }
        String[] descriptions = trimmed.split(",");
        ArrayList<Todo> res = new ArrayList<>();
        for (String des : descriptions) {
            String description = getDescription(des);
            String[] tags = getTags(des);
            res.add(new Todo(description, tags));
        }
        return res;
    }

    /**
     * Construct a Todo object
     * @param description the description of the Todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Construct a Todo object
     * @param description the description of the Todo
     * @param isDone whether the todo is done
     */
    public Todo(String description, boolean isDone){
        super(description, isDone);
        this.tagList = new TagList();
    }

    /**
     * Constructs a Todo object
     * @param description the description of the Todo
     * @param tags list of tags of the Todo
     */
    public Todo(String description, String[] tags) {
        super(description);
        this.tagList = TagList.of(tags);
    }

    /**
     * Construct a Todo object
     * @param description the description of the Todo
     * @param isDone whether the todo is done
     * @param tags the tags of the todo
     */
    public Todo(String description, boolean isDone, String tags) {
        super(description, isDone);
        this.tagList = TagList.of(tags);
    }


    /**
     * Overrides the toString method
     * @return the String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}