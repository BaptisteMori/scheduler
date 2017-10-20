
package edt_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.GregorianCalendar;
import java.util.Map;

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
      ch += L.get(i).getAction() + " à " + edt.get(L.get(i)).get(GregorianCalendar.HOUR_OF_DAY) + " h\n";
    }
    return ch;
  }
}
