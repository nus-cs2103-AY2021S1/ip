package Duke.Errors;

import Duke.Helpers.ShortCuts;

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
            return shortCutAlreadyPresent();
        }else {
            return "default";
        }
    }

    private String descriptionAbsent() {
        return "  The description of short cannot be empty";
    }

    private String shortCutAbsent(){
        return "short cut is not provided";
    }
    private String shortCutAlreadyPresent(){
        assert ShortCuts.getShortCuts().containsKey(this.shortCut);
        return this.shortCut + " is already present as a short form for" + ShortCuts.getShortCuts().get(this.shortCut);
    }

}
