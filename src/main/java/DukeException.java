public class DukeException {

    public String message;
    public boolean isValid;

    public DukeException(String message){
        this.message = message;
        this.isValid = true;
    }

    public DukeException(){
        this.isValid = false;
    }

    public String toString(){
        if(this.isValid){
            return "☹ OOPS!!! The description of a " + this.message + " cannot be empty.";
        }else{
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}

