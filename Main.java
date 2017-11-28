
package edt_java;

import scheduleio.*;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;

class Main {

  public static void main (String[] args) throws IOException {

    System.out.println("______________________________\n");

    if (!(args.length==0)) {
      ScheduleReader read = new ScheduleReader();
      Map<String,Activity> activities = read.readActivities(args[0]);

      if (args[1].compareTo("edt_java/before.txt")==0) {
        ArrayList<PrecedenceConstraint> listeContraints = read.buildPrecedenceCollection(activities,args[1]);
        ArrayList<Activity> listeActivities = read.getActivities(activities);
        Schedule edt = new Schedule ();
        edt.computeSchedule(listeActivities,listeContraints);
        System.out.println(edt.toString());

      } else {
        System.out.println("Erreur de fichier");
      }
    } else {
      System.out.println("Aucuns arguments n'a été donnés");
    }

    /*
    // MeetConstraint
    ScheduleReader read = new ScheduleReader();
    Map<String,Activity> activities = read.readActivities(args[0]);
    ArrayList<MeetConstraint> listeMeets = read.buildMeetingCollection(activities,args[1]);
    for (MeetConstraint meets : listeMeets) {
      System.out.println("act1 : " + meets.first.getAction() + " , act2 : " + meets.second.getAction());
    }
    */

    /*
    //MaxSpanConstraint
    ScheduleReader read = new ScheduleReader();
    Map<String,Activity> activities = read.readActivities(args[0]);
    ArrayList<MaxSpanConstraint> listeMaxSpan = read.buildMaxSpanCollection(activities,args[1]);
    for (MaxSpanConstraint maxSpan : listeMaxSpan) {
      int somme = 0;
      System.out.println("Un élement de MaxSpanConstraint :\n");

      for (Activity act : maxSpan.getListeActivities()) {
        if (act!=null) {
          somme += act.getDuree();
          System.out.println("Acitvité : " + act.getAction() + " , temps : " + act.getDuree());
        }
      }

      System.out.println("\nTemps total des activités : " + somme);
      System.out.println("Temps total maximum : " + maxSpan.getTimeTotal());
      System.out.println("Contrainte satisfaite : " + (somme <= maxSpan.getTimeTotal()) + "\n" + "\n" + "--------" + "\n");
    }
    */

    System.out.println("______________________________\n");
  }
}
