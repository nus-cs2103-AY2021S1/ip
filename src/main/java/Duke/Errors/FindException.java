package Duke.Errors;

public class FindException extends DukeException {
    private boolean description;
    private String string;
    public FindException(boolean description, String string){
        this.description = description;
        this.string = string;
    }
    public String toString(){
        if(description){
            return "there are no matches to your keyword: " + string;
        }
        return "description of find cannot be empty!";
    }
}
