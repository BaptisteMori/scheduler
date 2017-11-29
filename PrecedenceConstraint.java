package edt_java;

import java.util.GregorianCalendar;

/**
	* Hérite de la classe abstraite BinaryConstraint.
	* Représente une contrainte de précédence entre deux créneaux horaires de la classe <b>Activity</b>.
	* @see Activity
*/
public class PrecedenceConstraint extends BinaryConstraint {

/**
	* Constructeur de la classe.
	* @param first
	* Activité devant être planifiée en premier (variable héritée).
	* @param second
	* Activité devant être planifiée en second (variable héritée).
*/
  public PrecedenceConstraint(Activity first, Activity second){
    super(first,second);
  }

/**
	* Retourne l'activité à planifier en premier.
	* @return L'activité à planifier en premier.
*/
  @Override
  public Activity getFirst() {
    return this.first;
  }

/**
	* Retourne l'activité à planifier en second.
	* @return L'activité à planifier en second.
*/
  @Override
  public Activity getSecond() {
    return this.second;
  }

/**
	* Teste si la contrainte peut être respectée, c'est-à-dire si les activités se chevauchent.
	* @param date1
	* Date de début de l'activité <b>first</b>.
	* @param date2
	* Date de début de l'activité <b>second</b>.
	* @return Le résultat du test : <i>true</i> si les deux activités ne se chevauchent pas,
	* <i>false</i> dans le cas contraire.
*/
  @Override
  public boolean isSatisfied(GregorianCalendar date1, GregorianCalendar date2){

    date1 = new GregorianCalendar(date1.get(GregorianCalendar.YEAR),date1.get(GregorianCalendar.MONTH),date1.get(GregorianCalendar.DAY_OF_MONTH),date1.get(GregorianCalendar.HOUR_OF_DAY), date1.get(GregorianCalendar.MINUTE) + this.first.getDuree());

    if (date1.compareTo(date2)<0){
      return true;
    } else {
      return false;
    }
  }
}
