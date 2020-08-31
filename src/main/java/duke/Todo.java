package duke;

public class Todo extends Task {

    /**
     * Gets the description of the Todo
     * @param s the todo input by user
     * @return the description of the Todo
     */
    public static String getDescription(String s){
        int start = 0;
        while(!s.substring(start, start + 4).equals("todo")){
            start++;
        }
        return s.substring(start + 5);
    }

    /**
     * Create a Todo object
     * @param input input by user
     * @return the Todo object
     */
    public static Todo of(String input){
        String description = getDescription(input);
        if(description.equals("")){
            return null;
        }
        return new Todo(description);
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