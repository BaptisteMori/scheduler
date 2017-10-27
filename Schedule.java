
package edt_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.NoSuchElementException;

public class Schedule {

  private HashMap<Activity, GregorianCalendar> edt;

  public Schedule(){
    this.edt = new HashMap<> ();
  }

  public void schedule(Activity act, GregorianCalendar date){
    this.edt.put(act,date);
  }

  public boolean satisfies(ArrayList<PrecedenceConstraint> contraintes){
    for(int i = 0; i < contraintes.size(); i++){
      if (!contraintes.get(i).isSatisfied(edt.get(contraintes.get(i).first),edt.get(contraintes.get(i).second))) {
        return false;
      }
    }
    return true;
  }

  private ArrayList<Activity> getSortedActivities() {
    ArrayList<Activity> Ltemp = new ArrayList<> ();
    ArrayList<Activity> Lfinal = new ArrayList<> ();
    for (Map.Entry<Activity,GregorianCalendar> x : this.edt.entrySet()) {
      Ltemp.add(x.getKey());
    }
    int min = 0;
    while (Ltemp.size() != 0) {
      min = 0;
      for (int i = 0; i < Ltemp.size(); i++) {
        if (edt.get(Ltemp.get(i)).compareTo(edt.get(Ltemp.get(min))) < 0) {
          min = i;
        }
      }
      Lfinal.add(Ltemp.get(min));
      Ltemp.remove(min);
    }
    return Lfinal;
  }

  public String toString() {
    ArrayList<Activity> L = this.getSortedActivities();
    String ch = "";
    for(int i=0; i < L.size(); i++){
      ch += L.get(i).getAction() + " à " + edt.get(L.get(i)).get(GregorianCalendar.HOUR_OF_DAY) + " h " + edt.get(L.get(i)).get(GregorianCalendar.MINUTE) + "\n";
    }
    return ch;
  }

  private Activity next(ArrayList<Activity> l_act, ArrayList<PrecedenceConstraint> l_contr,ArrayList<Activity> l_planified) {
    for (Activity x : l_act) {
      if (! l_planified.contains(x)) {
        boolean test_act_est_secondaire = true;
        for (PrecedenceConstraint y : l_contr) {
          if (x == y.second && !l_planified.contains(y.first)) {
            test_act_est_secondaire = false;
            break;
          }
        }
        if (test_act_est_secondaire) {
          return x;
        }
      }
    }
    throw new NoSuchElementException("Pas de possibilité");
  }

  public void computeSchedule(ArrayList<Activity> l_act,ArrayList<PrecedenceConstraint> l_contr) throws NoSuchElementException {
    try {
      ArrayList<Activity> l_planified = new ArrayList<> ();
      GregorianCalendar date = new GregorianCalendar(2009,6,10,9,0);
      Activity act;
      while (! l_act.isEmpty()) {
        act = this.next(l_act,l_contr,l_planified);
        l_planified.add(act);
        l_act.remove(act);
      }
      for (Activity a : l_planified) {
        this.schedule(a,date);
        date=new GregorianCalendar(date.get(GregorianCalendar.YEAR),date.get(GregorianCalendar.MONTH),date.get(GregorianCalendar.DAY_OF_MONTH),date.get(GregorianCalendar.HOUR_OF_DAY),date.get(GregorianCalendar.MINUTE)+a.getDuree());
      }
    } catch (NoSuchElementException e) {
      System.out.println("Error");
    }
  }
}
