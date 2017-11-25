package edt_java;

import java.util.Map;
import java.util.HashMap;
import scheduleio.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ScheduleReader {

	public ScheduleReader() {
	}
	
	public Map<String, Activity> readActivities(String filename) throws IOException {
		BufferedReader fileReader = new BufferedReader(new FileReader(filename));
		IdStringStringReader activityReader = new IdStringStringReader(fileReader, "=", "_lasting_");
		Map<String, Activity> activityMap = new HashMap<>();
		for (Map.Entry<String, OrderedPair<String, String>> strings: activityReader.readAll().entrySet()) {
			String id = strings.getKey();
			OrderedPair<String, String> activityStrings = strings.getValue();
			String description = activityStrings.getFirst();
			int duration = Integer.parseInt(activityStrings.getSecond());
			activityMap.put(id,new Activity(description,duration));
		}
		return activityMap;
	}

	public ArrayList<PrecedenceConstraint> buildPrecedenceCollection (Map<String,Activity> activities, String fileConstraintsName) throws IOException {
		BufferedReader fileReader = new BufferedReader(new FileReader(filename));
		IdStringStringReader activityReader = new IdStringStringReader(fileReader, activities, "_before_"); //TODO
		for (Map.Entry<String,Activity> elt : activities.entrySet()) {

    }
	}
}
