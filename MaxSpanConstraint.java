package edt_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.GregorianCalendar;
import java.util.Map;

public class MaxSpanConstraint implements Constraint {

  private ArrayList<Activity> list_act;
  private int minutes;

  public MaxSpanConstraint(ArrayList<Activity> list_act, int minutes){
    this.list_act = list_act;
    this.minutes = minutes;
  }

  public boolean isSatisfied(Schedule edt) {
    int dure = 0;

    Activity first_act = this.list_act.get(0);
    Activity last_act = this.list_act.get(0);

    for (Activity act : this.list_act) {
      if (edt.get(act).compareTo(edt.get(act)) < 0) {
        first_act = act;
      }
      /*
      if (act.values()[0].compareTo(last_act.values()[0]) > 0) {
        last_act = act;
      }*/
    }

    if (dure >= this.minutes) {
      return false;
    } else {
      return true;
    }
  }
}
