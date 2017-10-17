package edt_java;

import java.util.GregorianCalendar;

class PrecedenceConstraintWithDuration extends PrecedenceConstraint {

  private int duree_min;
  private int duree_max;


  public PrecedenceConstraintWithDuration(Activity first, Activity second, int duree_min, int duree_max){
    super(first,second);
    this.duree_min = duree_min;
    this.duree_max = duree_max;
  }

  @Override
  public boolean isSatisfied(GregorianCalendar date1, GregorianCalendar date2){

    GregorianCalendar date_min = new GregorianCalendar(date2.get(GregorianCalendar.YEAR), date2.get(GregorianCalendar.MONTH), date2.get(GregorianCalendar.DAY_OF_MONTH), date2.get(GregorianCalendar.HOUR_OF_DAY), date2.get(GregorianCalendar.MINUTE) - this.duree_min);
    GregorianCalendar date_max = new GregorianCalendar(date2.get(GregorianCalendar.YEAR), date2.get(GregorianCalendar.MONTH), date2.get(GregorianCalendar.DAY_OF_MONTH), date2.get(GregorianCalendar.HOUR_OF_DAY), date2.get(GregorianCalendar.MINUTE) - this.duree_max);

    date1 = new GregorianCalendar(date1.get(GregorianCalendar.YEAR), date1.get(GregorianCalendar.MONTH), date1.get(GregorianCalendar.DAY_OF_MONTH), date1.get(GregorianCalendar.HOUR_OF_DAY), date1.get(GregorianCalendar.MINUTE) + this.first.getDuree());

    if (date1.compareTo(date2)<0 && date1.compareTo(date_max)>=0 && date1.compareTo(date_min)<=0) {
      return true;
    } else {
      return false;
    }
  }
}
