
package edt_java;

import scheduleio.*;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;

class Main {

  public static void main (String[] args) throws IOException {

    System.out.println("______________________________\n");

    if (args.length>1) {
      Schedule edt = new Schedule ();

      ScheduleReader read = new ScheduleReader();
      Map<String,Activity> activities = read.readActivities(args[0]);
      ArrayList<Activity> listeActivities = read.getActivities(activities);

      boolean testBefore = false;

      for (String x : args){
        if (x.compareTo("edt_java/before.txt")==0) {
          Collection<BinaryConstraint> listeContraints = read.buildPreceMeetCollection(activities,x,"_before_");
          edt.computeSchedule(listeActivities,listeContraints);
          testBefore = true;
          break;
        }
      }

      if (! testBefore){
        ArrayList<BinaryConstraint> listeContraints = new ArrayList<> ();
        edt.computeSchedule(listeActivities,listeContraints);
      }


      System.out.println(edt.toString());

      boolean testMeet = true;
      boolean testWithin = true;

      for (String y : args){
        if (y.compareTo("edt_java/meets.txt")==0){
          Collection<BinaryConstraint> listeMeetContraints = read.buildPreceMeetCollection(activities,y,"_meets_");
          for (BinaryConstraint conste : listeMeetContraints){
            if (! conste.isSatisfied(edt)){
              testMeet = false;
              break;
            }
          }
        }
        else if (y.compareTo("edt_java/within.txt")==0){
          Collection<MaxSpanConstraint> listeMaxSpan = read.buildMaxSpanCollection(activities,y);
          for (MaxSpanConstraint consta : listeMaxSpan){
            if (! consta.isSatisfied(edt)){
              testWithin = false;
              break;
            }
          }
        }

      }

      if (Arrays.asList(args).contains("edt_java/meets.txt")) {
        if (testMeet){
          System.out.println("toutes les contraintes Meets sont respectées");
        } else {
          System.out.println("certaines contraintes de type Meets ne sont pas respectées");
        }
      }

      if (Arrays.asList(args).contains("edt_java/within.txt")) {
        if (testWithin){
          System.out.println("toutes les contraintes MaxSpan sont respectées");
        } else {
          System.out.println("certaines contraintes de type MaxSpan ne sont pas respectées");
        }
      }


      /*

      if (args[1].compareTo("edt_java/before.txt")==0) {
        Collection<BinaryConstraint> listeContraints = read.buildPreceMeetCollection(activities,args[1],"_before_");
        ArrayList<Activity> listeActivities = read.getActivities(activities);
        Schedule edt = new Schedule ();
        edt.computeSchedule(listeActivities,listeContraints);
        System.out.println(edt.toString());

        if (args.length == 3) {
          if (args[2].compareTo("edt_java/meets.txt")==0) {
          Collection<BinaryConstraint> listeContraints = read.buildPreceMeetCollection(activities,args[1],"_meets_");
          ArrayList<Activity> listeActivities = read.getActivities(activities);
          }
          else if (args[1].compareTo("edt_java/within.txt")==0) {

          }
        }

      }
      else if (args[1].compareTo("edt_java/within.txt")==0) {
        ScheduleReader read = new ScheduleReader();
        Map<String,Activity> activities = read.readActivities(args[0]);
        ArrayList<MaxSpanConstraint> listeMaxSpan = read.buildMaxSpanCollection(activities,args[1]);
        Schedule edt = new Schedule ();
        boolean test = true;
        for (MaxSpanConstraint maxSpan : listeMaxSpan) {
          if (!maxSpan.isSatisfied()) {

          }
        }

      } else {
        System.out.println("Erreur de fichier");
      }*/
    } else {
      System.out.println("Le nombre d'arguments donnés n'est pas correct, besoin de 2 arguments");
    }
/*

    //MaxSpanConstraint
    ScheduleReader read = new ScheduleReader();
    Map<String,Activity> activities = read.readActivities(args[0]);
    ArrayList<MaxSpanConstraint> listeMaxSpan = read.buildMaxSpanCollection(activities,args[1]);
    for (MaxSpanConstraint maxSpan : listeMaxSpan) {
      int somme = 0;
      System.out.println("Un élement de MaxSpanConstraint :\n");

      for (Activity act : maxSpan.getListeActivities()) {
        somme += act.getDuree();
        System.out.println("Acitvité : " + act.getAction() + " , temps : " + act.getDuree());
      }

      System.out.println("\nTemps total des activités : " + somme);
      System.out.println("Temps total maximum : " + maxSpan.getTimeTotal());
      System.out.println("Contrainte satisfaite : " + (somme <= maxSpan.getTimeTotal()) + "\n" + "\n" + "--------" + "\n");
    }
    */

    System.out.println("______________________________\n");
  }
}
