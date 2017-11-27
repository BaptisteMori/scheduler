
package edt_java;

import scheduleio.*;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;

class Main {

  public static void main (String[] args) throws IOException {

    System.out.println("______________________________\n");

		ScheduleReader test = new ScheduleReader();
		Map<String,Activity> activities = test.readActivities("edt_java/activities.txt");
    ArrayList<PrecedenceConstraint> listePrecedence = test.buildPrecedenceCollection(activities,"edt_java/before.txt");
    ArrayList<MeetConstraint> listeMeeting = test.buildMeetingCollection(activities,"edt_java/meets.txt");
    ArrayList<MaxSpanConstraint> listeMaxSpan = test.buildMaxSpanCollection(activities,"edt_java/within.txt");
    System.out.println("______________________________\n");
  }
}
