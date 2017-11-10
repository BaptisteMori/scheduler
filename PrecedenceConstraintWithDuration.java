package edt_java;

import java.util.GregorianCalendar;

/**
	* Fille de PrecedenceConstraint. Cette classe prend également en compte
	* un intervalle entre la fin de la première activité et le début de la seconde.
	* @see PrecedenceConstraint
*/
public class PrecedenceConstraintWithDuration extends PrecedenceConstraint {

  private int duree_min;
  private int duree_max;

/**
	* Constructeur de la classe.
	* @param first
	* Activité devant être planifiée en premier (variable héritée).
	* @param second
	* Activité devant être planifiée en second (variable héritée).
	* @param duree_min
	* Longueur minimale de l'intervalle, exprimée en minutes.
	* @param duree_max
	* Longueur maximale de l'intervalle, exprimée en minutes.
*/
  public PrecedenceConstraintWithDuration(Activity first, Activity second, int duree_min, int duree_max){
    super(first,second);
    this.duree_min = duree_min;
    this.duree_max = duree_max;
  }

/**
	* Surcharge de la méthode isSatisfied de la classe mère.
	* @param date1
	* Date de début de l'activité <b>first</b>.
	* @param date2
	* Date de début de l'activité <b>second</b>.
	* @return Le résultat du test : <i>true</i> si les deux activités ne se chevauchent pas,
	* <i>false</i> dans le cas contraire.
*/
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
