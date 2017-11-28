
package edt_java;

import scheduleio.*;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;

class Main {

  public static void main (String[] args) throws IOException {

    System.out.println("______________________________\n");

		ScheduleReader read = new ScheduleReader();
		Map<String,Activity> activities = read.readActivities(args[0]);

    boolean erreur = false;


    if (args[1].compareTo("edt_java/before.txt")==0) {
      ArrayList<PrecedenceConstraint> listePrecedence = read.buildPrecedenceCollection(activities,args[1]);
      ArrayList<Activity> listeActivities = read.getActivities(activities);
      if () {
        Schedule edt = new Schedule ();
        edt.computeSchedule(listeActivities,listePrecedence);
        System.out.println(edt.toString());
      }
    }
    else if (args[1].compareTo("edt_java/meets.txt")==0) {
      ArrayList<MeetConstraint> listeMeeting = read.buildMeetingCollection(activities,args[1]);
    }
    else if (args[1].compareTo("edt_java/within.txt")==0) {
      ArrayList<MaxSpanConstraint> listeMaxSpan = read.buildMaxSpanCollection(activities,args[1]);
    } else {
      erreur = true;
      System.out.println("Erreur de fichier");
    }

    System.out.println("______________________________\n");
  }
}
