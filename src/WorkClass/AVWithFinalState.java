package WorkClass;

import ClassXml.FinalState;

import java.util.ArrayList;

public class AVWithFinalState {
    ActionWithVariation actionWithVariation;
    FinalState finalState;

    public AVWithFinalState(ActionWithVariation actionWithVariation, FinalState finalState) {
        this.actionWithVariation = actionWithVariation;
        this.finalState = finalState;
    }

    public ActionWithVariation getActionWithVariation() {
        return actionWithVariation;
    }

    public void setActionWithVariation(ActionWithVariation actionWithVariation) {
        this.actionWithVariation = actionWithVariation;
    }

    public FinalState getFinalState() {
        return finalState;
    }

    public void setFinalState(FinalState finalState) {
        this.finalState = finalState;
    }

    public ArrayList<AVWithFinalState> creator(ArrayList<ActionWithVariation> actionWithVariations){
        ArrayList<AVWithFinalState> avWithFinalStates = new ArrayList<>();
        for (int i = 0; i < actionWithVariations.size(); i++) {
            for (int j = 0; j < actionWithVariations.get(i).getVariation().getFinalStates().size(); j++) {

                avWithFinalStates.add(new AVWithFinalState(actionWithVariations.get(i),
                        actionWithVariations.get(i).getVariation().getFinalStates().get(j)));
            }
        }
        return avWithFinalStates;
    }

    @Override
    public String toString() {
        return "AVWithFinalState{" +
                "actionWithVariation=" + actionWithVariation +
                ", finalState=" + finalState +
                '}';
    }
}
