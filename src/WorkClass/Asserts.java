package WorkClass;

import ClassXml.FinalState;

import java.util.ArrayList;

public class Asserts {
    ActionWithVariation actionWithVariation;
    ArrayList<FinalState> finalStates;

    public Asserts(ActionWithVariation actionWithVariation, ArrayList<FinalState> finalStates) {
        this.actionWithVariation = actionWithVariation;
        this.finalStates = finalStates;
    }

    public Asserts() {}

    public ActionWithVariation getActionWithVariation() {
        return actionWithVariation;
    }

    public void setActionWithVariation(ActionWithVariation actionWithVariation) {
        this.actionWithVariation = actionWithVariation;
    }

    public ArrayList<FinalState> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(ArrayList<FinalState> finalStates) {
        this.finalStates = finalStates;
    }


    @Override
    public String toString() {
        return "Asserts{" +
                "actionWithVariation=" + actionWithVariation +
                ", finalStates=" + finalStates +
                '}';
    }
}
