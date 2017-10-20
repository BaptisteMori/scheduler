
package edt_java;

import java.util.GregorianCalendar;

public class PrecedenceConstraint {

  Activity first;
  Activity second;

  public PrecedenceConstraint(Activity first, Activity second){
    this.first = first;
    this.second = second;
  }

  public boolean isSatisfied(GregorianCalendar date1, GregorianCalendar date2){

    date1 = new GregorianCalendar(date1.get(GregorianCalendar.YEAR),date1.get(GregorianCalendar.MONTH),date1.get(GregorianCalendar.DAY_OF_MONTH),date1.get(GregorianCalendar.HOUR_OF_DAY), date1.get(GregorianCalendar.MINUTE) + this.first.getDuree());

    if (date1.compareTo(date2)<0){
      return true;
    } else {
      return false;
    }
  }
}