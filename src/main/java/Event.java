public class Event extends task{

    private String type = "[E]";
    private String date;

    public Event(String name){
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
        return this.type + done + " " + this.name + this.date;
    }

    public String read2(){
        String done = "";
        if(this.completed){
            done = "[✓]";
        }
        else{
            done = "[X]";
        }
        return this.type + done + " " + this.name + this.date;
    }

    public void print(){
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + this.type + "[X]" + " " + this.name + this.date);
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}
