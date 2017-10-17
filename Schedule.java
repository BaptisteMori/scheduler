
package edt_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.GregorianCalendar;

public class Schedule {

  private HashMap<Activity, GregorianCalendar> edt;

  public Schedule(HashMap<Activity, GregorianCalendar> edt){
    this.edt = edt;
  }
  /*
  public schedule(Activity activite, GregorianCalendar date){
    this.edt.put(activite,date);
  }
  */
}
