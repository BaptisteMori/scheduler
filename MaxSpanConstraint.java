package edt_java;


public class MaxSpanConstraint implements Constraint {

  private ArrayList<Activity> list_act;

  public MaxSpanConstraint(ArrayList<Activity> list_act){
    this.list_act = list_act;
  }

  public boolean timeSatisfied(Schedule edt, ArrayList<BinaryConstraint> list_contraintes, int minutes){
    Schedule edt_temp = new Schedule ();
    edt_temp.computeSchedule(this.list_act,list_contraintes);
    this.list_act = edt_temp.getSortedActivities();
    first_act = this.list_act.get(0);
    last_act = this.list_act.get(this.list_act.size() - 1);
    edt = edt.getEdt();
    return edt.
  }
}
