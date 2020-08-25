public class UserInterface {
    private String input;
    public static boolean exit = false;

    public UserInterface(){
        System.out.println("Welcome to MattBot v1.0!" + System.lineSeparator() +
                        "How may I assist you today?");
    }
    public void input(String input){
        this.input = input;
    }
    private void action(){
        try {
            InputManager.parse(this.input);
        } catch(ErrorExceptions e){
            System.out.println(e);
        }
    }
    private void failed(){
        System.out.println("No commands entered, please enter a command!");
    }
    public void parse(){
        if(this.input.equals("")){
            failed();
        } else{
            action();
        }
    }
    public static void stop(){
        exit = true;
        System.out.println("Awww, leaving so soon? Hope to see you again!");
    }
    public static void done(){
        System.out.println("Beep Boop Beep .....");
    }
    public boolean getStop(){
        return exit;
    }
    public static void wrongCommand(){
        System.out.println("Errroorrrr! Invalid command entered! Cannot compute!");
    }
    public static void addedTask(task t){
        System.out.println("Task has been successfully added!");
        System.out.println("    " + TaskManager.read(t));
        System.out.println("MattBot is tracking " + TaskManager.storeIndex() + " number of task!");
    }
}
