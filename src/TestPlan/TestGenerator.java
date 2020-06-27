package TestPlan;

import ClassXml.Action;
import ClassXml.InitialState;
import ClassXml.Variation;
import WorkClass.*;

import java.util.ArrayList;
import java.util.HashMap;

public class TestGenerator {
    ArrayList<Action> actions;

public TestPlan createTestPlanWithoutAssert(ArrayList<Action> actions){

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

        TestSuite testSuite = new TestSuite(scenarios, scenario.getInitialStates().size());
        while (testSuite.getN() > 0){
            for (int j = 0; j < scenarios.size(); j++) {
                for (int k = 0; k <scenarios.get(i).getInitialStates().size() ; k++) {
                    var avFound = avWithFinalState.found(avWithFS, scenarios.get(i).getInitialStates().get(k));
                    ArrayList<AVWithInitialStates> foundAvWithIS = avWithInitialStates.creator(avFound);
                    scenarios.get(i).getInitialStates().remove(scenarios.get(i).getInitialStates().get(k));
                    testSuite.setN(testSuite.getN()-1);

                    Scenario sc = scenarios.get(i);
                    testSuite.getScenarios().remove(scenarios.get(i));

                    for (int l = 0; l < foundAvWithIS.size(); l++) {

                        if (sc.foundCountAv(foundAvWithIS.get(l).getActionWithVariation()) < 3) { //проверка на зацикливание
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
        testPlan.getTestSuites().add(testSuite);

    }
    return testPlan;

}


}


