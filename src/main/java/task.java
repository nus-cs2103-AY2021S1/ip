public class task {
    protected static int count = 0;
    protected boolean completed;
    protected String name;
    protected int index;

    public task(String name){
        this.count++;
        this.index = this.count;
        this.name = name;
        this.completed = false;
    }

    public String taskName(){
        return this.name;
    }

    public int taskIndex(){
        return this.index;
    }

    public boolean taskCompleted(){
        return this.completed;
    }

    public void done(){
        this.completed = true;
    }

    public String read(){
        String done = "";
        if(this.completed){
            done = "[✓]";
        }
        else{
            done = "[X]";
        }
        return this.index + ". " + done + " " + this.name;
    }

    public String read2(){
        String done = "";
        if(this.completed){
            done = "[✓]";
        }
        else{
            done = "[X]";
        }
        return done + " " + this.name;
    }

    public void delete(){
        this.count = count - 1;
        String line1 = "Noted. I've removed this task: ";
        String line2 = "  " + this.read2();
        String line3 = "Now you have " + this.count + " tasks in the list.";
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
         
    }
}
