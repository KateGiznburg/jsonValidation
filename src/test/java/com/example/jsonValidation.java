package com.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class jsonValidation {

    private static Iterable<JSONObject> eventsCollection;

    @Parameterized.Parameter(0)
    public JSONObject parameterEvent;

    @Parameterized.Parameter(1)
    public long parameterDefaultProvider;

    @Parameterized.Parameter(2)
    public long parameterDefaultProviderId;

    @Parameterized.Parameter(3)
    public String parameterTeam1Name;

    @Parameterized.Parameter(4)
    public String parameterTeam2Name;

    @Parameterized.Parameters(name = "{1}-{2} Team1: {3} | Team2: {4}")
    public static Iterable<Object[]> data() throws IOException, ParseException {
        if (eventsCollection == null) {
            eventsCollection = jsonController.extractJSON();
        }

        List<Object[]> result = new ArrayList<Object[]>();

        Iterator<JSONObject> iteratorEventsCollection = eventsCollection.iterator();
        while (iteratorEventsCollection.hasNext()) {
            JSONObject itemEvent = iteratorEventsCollection.next();

            //Creating arrays that consist of parameters we require (P, ID for default provider in IDs section (d), Nm for teams (T1, T2) )
            JSONArray jsonIDs = (JSONArray) itemEvent.get("IDs");
            JSONArray jsonT1 = (JSONArray) itemEvent.get("T1");
            JSONArray jsonT2 = (JSONArray) itemEvent.get("T2");

            Iterator<JSONObject> iteratorJsonIDs = jsonIDs.iterator();
            Iterator<JSONObject> iteratorJsonT1 = jsonT1.iterator();
            Iterator<JSONObject> iteratorJsonT2 = jsonT2.iterator();

            Long defaultProvider = null;
            Long defaultProviderId = null;
            String team1Name = "";
            String team2Name = "";

            //Extracting P and ID values for default provider (d)
            while (iteratorJsonIDs.hasNext()) {
                JSONObject itemID = iteratorJsonIDs.next();
                if (itemID.get("d") != null) {
                    defaultProvider = (Long) itemID.get("P");
                    defaultProviderId = (Long) itemID.get("ID");
                }
            }

            //NullPointerException handling
            if (defaultProvider == null) {
                defaultProvider = Long.valueOf(0);
            }

            if (defaultProviderId == null) {
                defaultProviderId = Long.valueOf(0);
            }

            //Extracting Nm value for Team 1 (T1)
            while (iteratorJsonT1.hasNext()) {
                JSONObject itemT1 = iteratorJsonT1.next();
                team1Name = (String) itemT1.get("Nm");
            }

            //Extracting Nm value for Team 2 (T2)
            while (iteratorJsonT2.hasNext()) {
                JSONObject itemT2 = iteratorJsonT2.next();
                team2Name = (String) itemT2.get("Nm");
            }


                result.add(new Object[]{itemEvent, defaultProvider, defaultProviderId, team1Name, team2Name});
            }

            return result;
        }

    @Test
    public void defaultProviderValidation() {

        //Verifying if there is a default provider 'd' for each event
        JSONArray jsonIDs = (JSONArray) parameterEvent.get("IDs");

        Iterator<JSONObject> iteratorJsonIDs = jsonIDs.iterator();

        Boolean hasDefaultProvider = false;

        while (iteratorJsonIDs.hasNext()) {
            JSONObject itemIDs = iteratorJsonIDs.next();

            if (itemIDs.get("d")!=null) {
                hasDefaultProvider = true;
            }
        }

        if (hasDefaultProvider) {
            //Output of values 'P' and id 'ID' in the following format 'P-ID'
            System.out.println(parameterDefaultProvider + "-" + parameterDefaultProviderId);

            //Output of the name of the teams 'Nm' in the format 'Team1: {1st team} | Team2: {2nd team}'
            System.out.println("Team1: {" + parameterTeam1Name + "} | Team2: {" + parameterTeam2Name + "}");
        }
        assertEquals(true, hasDefaultProvider);
    }

    @Test
    public void parametersTrTrhValidation() {

        //The status of match parameter 'Epr' validation
        Long statusOfMatch = (Long) parameterEvent.get("Epr");

        //NullPointerException handling
        assertNotEquals(null,statusOfMatch);

        if (statusOfMatch==0) {

            //Checking if parameters 'Tr1', 'Tr2', 'Trh1', 'Trh2' are present if 'Epr = 0'
            String parameterTr1 = (String) parameterEvent.get("Tr1");
            String parameterTr2 = (String) parameterEvent.get("Tr2");
            String parameterTrh1 = (String) parameterEvent.get("Trh1");
            String parameterTrh2 = (String) parameterEvent.get("Trh2");

            assertEquals(null, parameterTr1);
            assertEquals(null, parameterTr2);
            assertEquals(null, parameterTrh1);
            assertEquals(null, parameterTrh2);
        }
    }

    @Test
    public void statusOfMatchValidation() {

        //Validation if the status of match 'Epr' within the range [0,1,2,3,4,5,6,7]
        Long statusOfMatch = (Long) parameterEvent.get("Epr");

        //NullPointerException handling
        assertNotEquals(null,statusOfMatch);

        List<Integer> expectedRange = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
        Boolean isInArray = expectedRange.contains(statusOfMatch.intValue());

        assertEquals(true,isInArray);

    }

}

