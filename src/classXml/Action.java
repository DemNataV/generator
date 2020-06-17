package classXml;

import java.util.ArrayList;

public class Action {
    String text;

    int bValue;
    double estimation;

    ArrayList<Parameter> parameters;
    ArrayList<Step> steps;
    ArrayList<InitialState> initialStates;
    ArrayList<Variation> variations;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getbValue() {
        return bValue;
    }

    public void setbValue(int bValue) {
        this.bValue = bValue;
    }

    public double getEstimation() {
        return estimation;
    }

    public void setEstimation(double estimation) {
        this.estimation = estimation;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public ArrayList<InitialState> getInitialStates() {
        return initialStates;
    }

    public void setInitialStates(ArrayList<InitialState> initialStates) {
        this.initialStates = initialStates;
    }

    public ArrayList<Variation> getVariations() {
        return variations;
    }

    public void setVariations(ArrayList<Variation> variations) {
        this.variations = variations;
    }

    @Override
    public String toString() {
        return "Action{" +
                "text='" + text + '\'' +
                ", bValue=" + bValue +
                ", estimation=" + estimation +
                ", parameters=" + parameters +
                ", steps=" + steps +
                ", initialStates=" + initialStates +
                ", variations=" + variations +
                '}';
    }
}
