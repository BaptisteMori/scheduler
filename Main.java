package edt_java;

import scheduleio.*;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;

/**
	* Classe exécutable du package edt_java.
	* @see Activity
	* @see Schedule
	* @see ScheduleReader
	* @see BinaryConstraint
	* @see MaxSpanConstraint
*/
public class Main {

/**
	* Lit des fichiers texte pour construire des listes d'activités et de constraintes, puis crée et affiche un emploi du
	* temps à partir de ces listes. Indique également si toutes les contraintes sont respectées.
	* Lors de l'appel, les arguments sont interprétés de la manière suivante :
	* 0 - fichier d'activités
	* 1 - fichier de contraintes de précédence (optionnel)
	* 2 et 3 - fichier de contraintes de précédence immédiate ou de durée maximale (optionnel)
*/
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
          Collection<BinaryConstraint> listeConstraints = read.buildPreceMeetCollection(activities,x,"_before_");
          edt.computeSchedule(listeActivities,listeConstraints);
          testBefore = true;
          break;
        }
      }
      if (!testBefore){
        ArrayList<BinaryConstraint> listeConstraints = new ArrayList<> ();
        edt.computeSchedule(listeActivities,listeConstraints);
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
    } else {
      System.out.println("Le nombre d'arguments donnés n'est pas correct, besoin de 2 arguments");
    }
    System.out.println("______________________________\n");
  	}
	}
}
