package edt_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.GregorianCalendar;
import java.util.Map;

public class MaxSpanConstraint implements Constraint {

  private ArrayList<Activity> list_act;

  public MaxSpanConstraint(ArrayList<Activity> list_act){
    this.list_act = list_act;
  }

  public boolean timeSatisfied(Schedule edt, ArrayList<BinaryConstraint> list_contraintes, int minutes){
    Schedule edt_temp = new Schedule ();
    edt_temp.computeSchedule(this.list_act,list_contraintes);
    this.list_act = edt_temp.getSortedActivities();
    Activity first_act = this.list_act.get(0);
    Activity last_act = this.list_act.get(this.list_act.size() - 1);
    HashMap<Activity, GregorianCalendar> edt_temp_hashmap = edt_temp.getEdt();
    return edt_temp_hashmap.get(Activity last_act);
  }
}
