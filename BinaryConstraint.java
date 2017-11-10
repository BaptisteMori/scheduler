package edt_java;

import java.util.HashMap;
import java.util.GregorianCalendar;
import java.util.Map;

public abstract class BinaryConstraint {

  protected Activity first;

  protected Activity second;

	public BinaryConstraint(Activity first, Activity second) {
    this.first = first;
    this.second = second;
	}

  public abstract boolean isSatisfied(GregorianCalendar date1,GregorianCalendar date2);

  public boolean isSatisfied(Schedule edt){
    HashMap<Activity, GregorianCalendar> emploiDT = edt.getEdt();
    GregorianCalendar date1 = emploiDT.get(this.first);
    GregorianCalendar date2 = emploiDT.get(this.second);
    return isSatisfied(date1,date2);
  }
}
