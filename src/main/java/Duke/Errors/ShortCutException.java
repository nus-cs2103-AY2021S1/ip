package Duke.Errors;

public class ShortCutException extends DukeException {
    private boolean isDescriptionAbsent;
    private boolean isShortCutAbsent;
    private boolean isShortCutAlreadyPresent;
    private String shortCut;

    public ShortCutException(boolean isDescriptionAbsent, boolean isShortCutAbsent, boolean isShortCutAlreadyPresent, String shortCut){
        this.isDescriptionAbsent = isDescriptionAbsent;
        this.isShortCutAbsent = isShortCutAbsent;
        this.isShortCutAlreadyPresent = isShortCutAlreadyPresent;
        this.shortCut = shortCut;
    }

    public String toString(){
        if(isDescriptionAbsent){
            return descriptionAbsent();
        }else if(isShortCutAbsent){
            return shortCutAbsent();
        }else if(isShortCutAlreadyPresent){
            return null;
        }else {
            return "default";
        }
    }

    public String descriptionAbsent() {
        return "  The description of short cannot be empty";
    }

    public String shortCutAbsent(){
        return "short cut is not provided";
    }


}
