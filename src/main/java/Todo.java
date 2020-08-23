public class Todo extends task{
    private String type = "[T]";
    private String date;

    public Todo(String name){
        super(name);
    }

    public void addDate(String date){
        this.date = date;
    }

    public String read(){
        String done = "";
        if(super.completed){
            done = "[✓]";
        }
        else{
            done = "[X]";
        }
        return this.type + done + " " + this.name;
    }

    public String read2(){
        String done = "";
        if(this.completed){
            done = "[✓]";
        }
        else{
            done = "[X]";
        }
        return this.type + done + " " + this.name;
    }

    public void print(){
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + this.type + "[X]" + " " + this.name);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

}
