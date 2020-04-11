package stepsDefinition;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static common.finals.testPathToJsonFile;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class jsonValidationFeature {

    private static Iterable<JSONObject> result;

    @Parameterized.Parameter
    public JSONObject parameterEvent;
    
    @Parameterized.Parameters
    public static Iterable<JSONObject> data() throws IOException, ParseException {
        if (result == null) {
            result = extractJSON();
        }
        return result;
    }

    public static Iterable<JSONObject> extractJSON() throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader(testPathToJsonFile));

        // JSON object. Key value pairs are unordered
        JSONObject jsonObject = (JSONObject) obj;

        // Converting JSON Object to JSON array
        JSONArray jsonStages = (JSONArray) jsonObject.get("Stages");

        List<JSONObject> result = new ArrayList<JSONObject>();

        // An iterator over a collection that creates a ArrayList of events
        Iterator<JSONObject> iteratorJsonStages = jsonStages.iterator();
        while (iteratorJsonStages.hasNext()) {
            JSONObject item = iteratorJsonStages.next();
            JSONArray events = (JSONArray) item.get("Events");
            if (events != null) {
                Iterator<JSONObject> iteratorJsonEvents = events.iterator();
                while (iteratorJsonEvents.hasNext()) {
                    JSONObject event = iteratorJsonEvents.next();
                    result.add(event);
                }
            }
        }

        return result;
    }

    @Test
    public void defaultProviderValidation() {

        //Verifying if there is a default provider 'd' for each event
        JSONArray jsonIDs = (JSONArray) parameterEvent.get("IDs");
        JSONArray jsonT1 = (JSONArray) parameterEvent.get("T1");
        JSONArray jsonT2 = (JSONArray) parameterEvent.get("T2");

        Iterator<JSONObject> iteratorJsonIDs = jsonIDs.iterator();
        Iterator<JSONObject> iteratorJsonT1 = jsonT1.iterator();
        Iterator<JSONObject> iteratorJsonT2 = jsonT2.iterator();

        Boolean hasDefaultProvider = false;
        String jsonTeam1 = null;
        String jsonTeam2 = null;

        while (iteratorJsonIDs.hasNext()) {
            JSONObject itemIDs = iteratorJsonIDs.next();

            if (itemIDs.get("d")!=null) {
                hasDefaultProvider = true;

                //Passing to output a default provider value 'P' and id 'ID' in the following format 'P-ID'
                System.out.println(itemIDs.get("P")+"-"+itemIDs.get("ID"));
            }
        }

        assertTrue (hasDefaultProvider);

        while (iteratorJsonT1.hasNext()) {

            JSONObject itemT1 = iteratorJsonT1.next();
            jsonTeam1 = (String) itemT1.get("Nm");

        }

        while (iteratorJsonT2.hasNext()) {

            JSONObject itemT2 = iteratorJsonT2.next();
            jsonTeam2 = (String) itemT2.get("Nm");

        }

        //Printing the name of the teams 'Nm' in the format 'Team1: {1st team} | Team2: {2nd team}'
        System.out.println("Team1: {" + jsonTeam1 + "} | Team2: {" +jsonTeam2 + "}");

        }
}
