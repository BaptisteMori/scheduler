
package edt_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.GregorianCalendar;

public class Schedule {

  private HashMap<Activity, GregorianCalendar> edt;

  public Schedule(HashMap<Activity, GregorianCalendar> edt){
    this.edt = edt;
  }

  public boolean satisfies(ArrayList<PrecedenceConstraint> contraintes){
    for(int i = 0; i < contraintes.size(); i++){
      if (!contraintes.get(i).isSatisfied(edt.get(contraintes.get(i).first),edt.get(contraintes.get(i).second))) {
        return false;
      }
    }
    return true;
  }
}
