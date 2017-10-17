
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

    int hour = this.first.getDuree()/60;
    int minutes = this.first.getDuree() % 60;

    date1 = new GregorianCalendar(date1.get(GregorianCalendar.YEAR),date1.get(GregorianCalendar.MONTH),date1.get(GregorianCalendar.DAY_OF_MONTH),(date1.get(GregorianCalendar.HOUR_OF_DAY))%24 + hour, date1.get(GregorianCalendar.MINUTE) + minutes);

    if (date1.before(date2)){
      return true;
    } else {
      return false;
    }
  }
}
