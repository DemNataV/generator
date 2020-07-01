package ClassXml;

import java.util.ArrayList;

public class Variation {
    String text;

    String type;
    String done;
    String abbreviation;
    int BValue;

    ArrayList<Step> steps;
    ArrayList<FinalState> finalStates;
    ArrayList<InitialState> initialStates;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public ArrayList<FinalState> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(ArrayList<FinalState> finalStates) {
        this.finalStates = finalStates;
    }

    public ArrayList<InitialState> getInitialStates() {
        return initialStates;
    }

    public void setInitialStates(ArrayList<InitialState> initialStates) {
        this.initialStates = initialStates;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public int getBValue() {
        return BValue;
    }

    public void setBValue(int BValue) {
        this.BValue = BValue;
    }

    @Override
    public String toString() {
        return "Variation{" +
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", done='" + done + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", BValue=" + BValue +
                ", steps=" + steps +
                ", finalStates=" + finalStates +
                ", initialStates=" + initialStates +
                '}';
    }
}
