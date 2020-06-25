package TestPlan;

import ClassXml.Action;
import ClassXml.InitialState;
import ClassXml.Variation;
import WorkClass.AVWithFinalState;
import WorkClass.AVWithInitialStates;
import WorkClass.ActionWithVariation;

import java.util.ArrayList;
import java.util.HashMap;

public class TestGenerator {
    ArrayList<Action> actions;

public TestPlan createTpBranch(ArrayList<Action> actions){

    TestPlan testPlan = new TestPlan();

    ActionWithVariation actionWithVariation = new ActionWithVariation();
    var av = actionWithVariation.creator(actions);

    AVWithInitialStates avWithInitialStates = new AVWithInitialStates();
    var avWithIS = avWithInitialStates.creator(av);

    AVWithFinalState avWithFinalState = new AVWithFinalState();
    var avWithFS = avWithFinalState.creator(av);

    for (int i = 0; i < avWithIS.size(); i++) {
        ArrayList<ActionWithVariation> actionWithVariations = new ArrayList<>();
        actionWithVariations.add(avWithIS.get(i).getActionWithVariation());
        Scenario scenario = new Scenario(actionWithVariations, avWithIS.get(i).getInitialStates());
        ArrayList<Scenario> scenarios = new ArrayList<Scenario>();
        scenarios. add(scenario);

        TestSuite testSuite = new TestSuite(scenarios, scenario.initialStates.size());
        while (testSuite.n > 0){
            for (int j = 0; j < scenarios.size(); j++) {
                for (int k = 0; k <scenarios.get(i).getInitialStates().size() ; k++) {
                    var avFound = avWithFinalState.found(avWithFS, scenarios.get(i).getInitialStates().get(k));
                    ArrayList<AVWithInitialStates> foundAvWithIS = avWithInitialStates.creator(avFound);
                    scenarios.get(i).getInitialStates().remove(scenarios.get(i).getInitialStates().get(k));
                    testSuite.setN(testSuite.getN()-1);

                    Scenario sc = scenarios.get(i);
                    testSuite.getScenarios().remove(scenarios.get(i));

                    for (int l = 0; l < foundAvWithIS.size(); l++) {

                        if (sc.foundCountAv(foundAvWithIS.get(l).getActionWithVariation()) < 3) {
                            Scenario scen = sc;
                            scen.getActionWithVariations().add(foundAvWithIS.get(l).getActionWithVariation());
                            scen.getInitialStates().addAll(foundAvWithIS.get(l).getInitialStates());

                            testSuite.getScenarios().add(scen);
                            testSuite.setN(testSuite.getN() + foundAvWithIS.get(l).getInitialStates().size());
                        }
                    }


                }
            }
        }
        testPlan.testSuites.add(testSuite);

    }
    return testPlan;

}
}

class Scenario{
    ArrayList<ActionWithVariation> actionWithVariations;
    ArrayList<InitialState> initialStates;

    public Scenario(ArrayList<ActionWithVariation> scenario, ArrayList<InitialState> initialStates) {
        this.actionWithVariations = scenario;
        this.initialStates = initialStates;
    }

    public Scenario() {}

    public ArrayList<ActionWithVariation> getActionWithVariations() {
        return actionWithVariations;
    }

    public void setActionWithVariations(ArrayList<ActionWithVariation> scenario) {
        this.actionWithVariations = scenario;
    }

    public ArrayList<InitialState> getInitialStates() {
        return initialStates;
    }

    public void setInitialStates(ArrayList<InitialState> initialStates) {
        this.initialStates = initialStates;
    }

    public int foundCountAv(ActionWithVariation actionWithVariation){
        int n = 0;
        for (int i = 0; i < actionWithVariations.size(); i++) {
            if (actionWithVariations.get(i).equals(actionWithVariation)){
                n++;
            }
        }
        return n;
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "actionWithVariations=" + actionWithVariations +
                ", initialStates=" + initialStates +
                '}';
    }
}

class TestSuite{
    ArrayList<Scenario> scenarios;
    int n;

    public TestSuite(ArrayList<Scenario> scenarios, int n) {
        this.scenarios = scenarios;
        this.n = n;
    }

    public TestSuite() {}

    public ArrayList<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(ArrayList<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "TestSuite{" +
                "scenarios=" + scenarios +
                '}';
    }
}

class TestPlan{
    ArrayList<TestSuite> testSuites;
}