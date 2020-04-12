package com.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static common.finals.defaultPathToJsonFile;

public class jsonController {

    public static Iterable<JSONObject> extractJSON() throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader(defaultPathToJsonFile));

        // JSON object. Key value pairs are unordered
        JSONObject jsonObject = (JSONObject) obj;

        // Converting JSON Object to JSON array
        JSONArray jsonStages = (JSONArray) jsonObject.get("Stages");

        List<JSONObject> result = new ArrayList<JSONObject>();

        // An iterator over a collection that creates a List of events
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

}
