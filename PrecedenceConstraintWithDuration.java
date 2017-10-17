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

    int hour = this.first.getDuree()/60;
    int minutes = this.first.getDuree() % 60;
    int duree_intervale = this.duree_max - this.duree_min;

    date1 = new GregorianCalendar(date1.get(GregorianCalendar.YEAR), date1.get(GregorianCalendar.MONTH), date1.get(GregorianCalendar.DAY_OF_MONTH), (date1.get(GregorianCalendar.HOUR_OF_DAY))%24 + hour, date1.get(GregorianCalendar.MINUTE) + minutes);

    if (date1.before(date2) && this.first.getDuree() <= duree_intervale){
      return true;
    } else {
      return false;
    }
  }
}
