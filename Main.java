
package edt_java;

import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Main {

  public static void main (String[] args) {

    /*
    Activity act1 = new Activity ("Manger des Chocapic", 70);
    Activity act2 = new Activity ("Faire une game de LOL", 45);

    PrecedenceConstraint contrainte = new PrecedenceConstraint (act1, act2);

    GregorianCalendar date1 = new GregorianCalendar(2008,0,1,23,0);
    GregorianCalendar date2 = new GregorianCalendar(2008,0,2,0,0);
    GregorianCalendar date3 = new GregorianCalendar(2017,6,24,15,0);

    // Satisfaite
    if (contrainte.isSatisfied(date1, date3) ) {
        System.out.println("Satisfaite");
    } else {
        System.out.println("Non Satisfaite");
    }

    // Non Satisfaite
    if (contrainte.isSatisfied(date1, date2) ) {
      System.out.println("Satisfaite");
    } else {
      System.out.println("Non Satisfaite");
    }

    // Non Satisfaite
    if ( contrainte.isSatisfied(date1, date1) ) {
      System.out.println("Satisfaite");
    } else {
      System.out.println("Non Satisfaite");
    }
    */

    Activity act1 = new Activity ("Manger des Chocapic", 70);
    Activity act2 = new Activity ("Faire une game de LOL", 45);
    Activity act3 = new Activity ("Regarder Koh-Lanta", 120);
    Activity act4 = new Activity ("Réviser", 5);

    PrecedenceConstraint contrainte1 = new PrecedenceConstraint (act1, act2);
    PrecedenceConstraint contrainte2 = new PrecedenceConstraint (act2, act3);
    PrecedenceConstraint contrainte3 = new PrecedenceConstraint (act3, act4);

    ArrayList<PrecedenceConstraint> list_contraintes = new ArrayList<> ();
    list_contraintes.add(contrainte1);
    list_contraintes.add(contrainte2);
    list_contraintes.add(contrainte3);

    GregorianCalendar date1 = new GregorianCalendar(2008,0,1,23,0);
    GregorianCalendar date2 = new GregorianCalendar(2008,0,2,0,10);
    GregorianCalendar date3 = new GregorianCalendar(2010,6,24,15,0);
    GregorianCalendar date4 = new GregorianCalendar(2017,4,16,8,0);

    //PrecedenceConstraintWithDuration contrainte1 = new PrecedenceConstraintWithDuration(act1,act2,40,50);

    Schedule edt = new Schedule();
    /*
    edt.schedule(act1,date1);
    edt.schedule(act2,date2);
    edt.schedule(act3,date3);
    edt.schedule(act4,date4);
    */

    if (edt.satisfies(list_contraintes)) {
      System.out.println("Test ok");
    } else {
      System.out.println("Test pas ok du tout");
    }

    System.out.println(edt.toString());

  }
}
