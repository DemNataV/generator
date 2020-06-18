package Parser;

import classXml.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.ArrayList;
import java.util.function.Function;


public class Parser {
    public Parser() throws ParserConfigurationException {
    }

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // f.setValidating(false); // не делать проверку валидации
        DocumentBuilder db = dbf.newDocumentBuilder(); // создали конкретного строителя документа
        Document doc = db.parse(new File("sample.xml")); // стооитель построил документ

        visitDoc(doc, 0);

        System.out.println(actions);
    }
    static ArrayList<Action> actions = new ArrayList<>();

    public static void visitDoc(Node node, int level) {


        NodeList list = node.getFirstChild().getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {

            Node childNode = list.item(i); // текущий нод
           // childNode = childNode.getFirstChild();
            process(childNode, level + 1); // обработка
            visit(childNode, level + 1, actions); // рекурсия
        }
    }
    public static void visit(Node node, int level, ArrayList<Action> actions) {
       var act = getAttributeValue(node, "TEXT");

        switch (act){
            case "Б-С": {

                NodeList list = node.getChildNodes();
                for (int i = 0; i < list.getLength(); i++) {

                    Node childNode = list.item(i); // текущий нод
                    //process(childNode, level + 1); // обработка
                   Action action = visitAction(childNode); // рекурсия
                    if (action.getText() != null)
                    actions.add(action);
                }

            }
            break;
        }



    }

    public static Action visitAction(Node node){
        Action action = new Action();
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (!getAttributeValue(node, "TEXT").equals("") || getAttributeValue(node, "TEXT") != null) {

                Node childNode = list.item(i); // текущий нод
                action.setText(getAttributeValue(node, "TEXT"));
                switch (childNode.getNodeName()) {
                    case "attribute": {
                        //var attributes = childNode.getAttributes();
                        if (getAttributeValue(childNode, "NAME").equals("B Value")) {
                            if (!getAttributeValue(childNode, "VALUE").equals("")) {
                                action.setbValue(Integer.parseInt(getAttributeValue(childNode, "VALUE")));
                            }
                        }

                    }
                    if (getAttributeValue(childNode, "NAME").equals("Estimation")) {
                        if (!getAttributeValue(childNode, "VALUE").equals("")) {
                            action.setEstimation(Double.parseDouble(getAttributeValue(childNode, "VALUE")));
                        }
                    }

                    break;
                    case "node": {
                        switch (getAttributeValue(childNode, "TEXT")) {
                            case "parameters": {
                                var parameters = fillElements(node, Parser::visitParameter);
                                action.setParameters(parameters);
                            }
                            break;
                            case "initial states": {
                                var initialStates = fillElements(node, Parser::visitInitialState);
                                action.setInitialStates(initialStates);
                            }
                            break;
                            case "variations": {
                                var variations = fillElements(node, Parser::visitVariation);
                                action.setVariations(variations);

                            }
                            break;
                            case "Steps": {
                                var steps = fillElements(node, Parser::visitStep);
                                action.setSteps(steps);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            else break;
        }

       return action ;
    }

    public static Variation visitVariation(Node node){
        Variation variation = new Variation();

        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (!getAttributeValue(node, "TEXT").equals("") || getAttributeValue(node, "TEXT") != null) {

                Node childNode = list.item(i); // текущий нод
                variation.setText(getAttributeValue(node, "TEXT"));
                switch (childNode.getNodeName()) {
                    case "attribute": {
                        var attributes = childNode.getAttributes();
                        if (getAttributeValue(childNode, "NAME").equals("done")) {
                            if (!getAttributeValue(childNode, "VALUE").equals("")) {
                                variation.setDone(getAttributeValue(childNode, "VALUE"));
                            }
                        }

                        if (getAttributeValue(childNode, "NAME").equals("type")) {
                            if (!getAttributeValue(childNode, "VALUE").equals("")) {
                                variation.setType(getAttributeValue(childNode, "VALUE"));
                            }
                        }

                    }
                    break;
                    case "node": {
                        switch (getAttributeValue(childNode, "TEXT")) {
                            case "final states": {
                                var finalStates = fillElements(node, Parser::visitFinalState);
                                variation.setFinalStates(finalStates);
                            }
                            break;
                            case "initial states": {
                                var initialStates = fillElements(node, Parser::visitInitialState);
                                variation.setInitialStates(initialStates);
                            }
                            break;

                            case "Steps": {
                                var steps = fillElements(node, Parser::visitStep);
                                variation.setSteps(steps);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            else break;

        }
        return variation ;
    }

    public static Parameter visitParameter(Node node){
        Parameter parameter = new Parameter(getAttributeValue(node, "TEXT"));
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node childNode = list.item(i);
            var attributes = childNode.getAttributes();
            switch (getAttributeValue(childNode, "NAME")){
                case "diapason": {
                    //parameter.setDiapason(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        parameter.setDiapason(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;
                case "min": {
                    //parameter.setMin(Integer.parseInt(attributes.getNamedItem("VALUE").getNodeValue()));
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        parameter.setMin(Integer.parseInt(getAttributeValue(childNode, "VALUE")));
                    }
                }
                break;
                case "max": {
                    //parameter.setMax(Integer.parseInt(attributes.getNamedItem("VALUE").getNodeValue()));
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        parameter.setMax(Integer.parseInt(getAttributeValue(childNode, "VALUE")));
                    }
                }
                break;
                case "valid": {
                    //parameter.setValid(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        parameter.setValid(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;
                case "not valid": {
                    //parameter.setNotValid(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        parameter.setNotValid(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;
                case "required": {
                    //parameter.setRequired(Boolean.parseBoolean(attributes.getNamedItem("VALUE").getNodeValue()));
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        parameter.setRequired(Boolean.parseBoolean(getAttributeValue(childNode, "VALUE")));
                    }
                }
                break;
                case "available": {
                   // parameter.setAvailable(Boolean.parseBoolean(attributes.getNamedItem("VALUE").getNodeValue()));
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        parameter.setAvailable(Boolean.parseBoolean(getAttributeValue(childNode, "VALUE")));
                    }
                }
                break;
                case "type": {
                    //parameter.setType(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        parameter.setType(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;
            }

        }
        return parameter;
    }

    public static Step visitStep(Node node) {
        Step step = new Step(getAttributeValue(node, "TEXT"));
        return step;
    }

    public static InitialState visitInitialState(Node node){
        InitialState initialState = new InitialState();
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {

            Node childNode = list.item(i);
            var attributes = childNode.getAttributes();
            switch (getAttributeValue(childNode, "NAME")){
                case "priority": {
                    //initialState.setPriority(Integer.parseInt(attributes.getNamedItem("VALUE").getNodeValue()));
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        initialState.setPriority(Integer.parseInt(getAttributeValue(childNode, "VALUE")));
                    }
                }
                break;
                case "Epic": {
                   // initialState.setEpic(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        initialState.setEpic(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;
                case "Page": {
                   // initialState.setPage(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        initialState.setPage(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;
                case "Table": {
                    //initialState.setTable(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        initialState.setTable(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;
                case "Value": {
                    //initialState.setValue(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        initialState.setValue(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;
                case "object": {
                    //initialState.setObject(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        initialState.setObject(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;

            }

        }
        return initialState;
    }

    public static FinalState visitFinalState(Node node){
        FinalState finalState = new FinalState();
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {

            Node childNode = list.item(i);
            var attributes = childNode.getAttributes();
            switch (//attributes.getNamedItem("NAME").getNodeValue()
            getAttributeValue(childNode, "NAME")){
                case "priority": {
                    //finalState.setPriority(Integer.parseInt(attributes.getNamedItem("VALUE").getNodeValue()));
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        finalState.setPriority(Integer.parseInt(getAttributeValue(childNode, "VALUE")));
                    }
                }
                break;
                case "Epic": {
                    //finalState.setEpic(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        finalState.setEpic(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;
                case "Page": {
                    //finalState.setPage(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        finalState.setPage(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;
                case "Table": {
                   // finalState.setTable(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        finalState.setTable(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;
                case "Value": {
                   // finalState.setValue(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        finalState.setValue(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;
                case "object": {
                    //finalState.setObject(attributes.getNamedItem("VALUE").getNodeValue());
                    if (!getAttributeValue(childNode, "VALUE").equals("")) {
                        finalState.setObject(getAttributeValue(childNode, "VALUE"));
                    }
                }
                break;

            }

        }
        return finalState;
    }

    public static void process(Node node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print('\t');
        }
        System.out.print(node.getNodeName() + " : " + node.getAttributes());
        if (node instanceof Element) {
            Element e = (Element) node;
            // работаем как с элементом (у него есть атрибуты и схема)
        }
        System.out.println();
    }

    private static <TElement> ArrayList<TElement> fillElements (Node node, Function<Node, TElement> function){
        ArrayList<TElement> tElements = new ArrayList<>();
        NodeList list = node.getChildNodes();
        for (int j = 0; j < list.getLength(); j++) {

            Node childNode = list.item(j); // текущий нод
            TElement tElement = function.apply(childNode); // рекурсия
            if (tElement != null) {
                if(tElement.getClass() == Variation.class) {
                    if (((Variation) tElement).getText() != null)
                    tElements.add(tElement);
                }
            }
        }
        return tElements;
    }

    private static String getAttributeValue(Node node, String attributeName){
        var atrib = node.getAttributes();
        if (atrib == null || atrib.getLength() == 0) return"";
        var at = atrib.getNamedItem(attributeName);
        if (at == null) return"";
        return at.getNodeValue();
    }

    private static void setAttrib(Node childNode, String param, Function function){
   if (getAttributeValue(childNode, "NAME").equals(param))
        if (!getAttributeValue(childNode, "VALUE").equals("")) {
            function.apply(Integer.parseInt(getAttributeValue(childNode, "VALUE")));
        }
    }

}