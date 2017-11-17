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

    Activity first_act = this.list_act.get(0);
    Activity last_act = this.list_act.get(0);

    for (Activity act : this.list_act) {

      if (edt.getEdt().get(act).compareTo(edt.getEdt().get(first_act)) < 0) {
        first_act = act;
      }

      GregorianCalendar fin_last_act = new GregorianCalendar(edt.getEdt().get(last_act).get(GregorianCalendar.YEAR),edt.getEdt().get(last_act).get(GregorianCalendar.MONTH),edt.getEdt().get(last_act).get(GregorianCalendar.DAY_OF_MONTH),edt.getEdt().get(last_act).get(GregorianCalendar.HOUR_OF_DAY),edt.getEdt().get(last_act).get(GregorianCalendar.MINUTE) + last_act.getDuree());

      GregorianCalendar fin_act = new GregorianCalendar(edt.getEdt().get(act).get(GregorianCalendar.YEAR),edt.getEdt().get(act).get(GregorianCalendar.MONTH),edt.getEdt().get(act).get(GregorianCalendar.DAY_OF_MONTH),edt.getEdt().get(act).get(GregorianCalendar.HOUR_OF_DAY),edt.getEdt().get(act).get(GregorianCalendar.MINUTE) + act.getDuree());

      if (fin_act.compareTo(fin_last_act) > 0) {
        last_act = act;
      }
    }

    GregorianCalendar time1 = edt.getEdt().get(first_act);
    GregorianCalendar time2 = edt.getEdt().get(last_act);
    time2 = new GregorianCalendar(time2.get(GregorianCalendar.YEAR),time2.get(GregorianCalendar.MONTH),time2.get(GregorianCalendar.DAY_OF_MONTH),time2.get(GregorianCalendar.HOUR_OF_DAY),time2.get(GregorianCalendar.MINUTE) + last_act.getDuree());

    double duree = time2.getTimeInMillis() - time1.getTimeInMillis();
    duree = duree/60000;
    System.out.println(duree);

    if (duree > this.minutes) {
      return false;
    } else {
      return true;
    }
  }
}
