package edt_java;

import java.util.Map;
import java.util.HashMap;
import scheduleio.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ScheduleReader {

	public ScheduleReader() {
	}

	public Map<String, Activity> readActivities(String filename) throws IOException {
		try {
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
		} catch (IOException e) {
			return new HashMap<> ();
		}
	}


	public ArrayList<PrecedenceConstraint> buildPrecedenceCollection (Map<String,Activity> activities, String fileConstraintsName) throws IOException {
		BufferedReader fileReader = new BufferedReader(new FileReader(fileConstraintsName));
		IdStringReader ConstraintReader = new IdStringReader(fileReader, "_before_");
		ArrayList<PrecedenceConstraint> listePrecedence = new ArrayList<> ();
		for (Map.Entry<String,String> elt : ConstraintReader.readAll().entrySet()) {
			String id1 = elt.getKey();
			System.out.println(id1);
			String id2 = elt.getValue();
			PrecedenceConstraint contrainte = new PrecedenceConstraint(activities.get(id1),activities.get(id2));
			listePrecedence.add(contrainte);
    }
		return listePrecedence;
	}
}
