package edt_java;

import java.util.HashMap;
import java.util.GregorianCalendar;
import java.util.Map;

/**
	* Classe abstraite représentant une contrainte de temps quelconque entre deux activités.
	* @see Activity
	* @see Schedule
*/
public abstract class BinaryConstraint implements Constraint {

  protected Activity first;
  protected Activity second;

/**
	* Constructeur de la classe.
	* @param first
	* Activité devant être planifiée en premier.
	* @param second
	* Activité devant être planifiée en second.
*/
	public BinaryConstraint(Activity first, Activity second) {
    this.first = first;
    this.second = second;
	}

/**
	* Retourne l'activité à planifier en premier.
	* @return L'activité à planifier en premier.
*/
  public abstract Activity getFirst();

/**
	* Retourne l'activité à planifier en second.
	* @return L'activité à planifier en second.
*/
  public abstract Activity getSecond();

/**
	* Teste si la contrainte peut être respectée, c'est-à-dire si les activités se chevauchent.
	* @param date1
	* Date de début de l'activité <b>first</b>.
	* @param date2
	* Date de début de l'activité <b>second</b>.
	* @return Le résultat du test : <i>true</i> si les deux activités ne se chevauchent pas,
	* <i>false</i> dans le cas contraire.
*/
  public abstract boolean isSatisfied(GregorianCalendar date1,GregorianCalendar date2);

/**
	* Teste si un emploi du temps respecte la contrainte binaire entre deux activités qui le composent.
	* @param edt
	* Emploi du temps à tester.
	* @return Le résultat du test : <i>true</i> si l'emploi du temps respecte la contrainte entre les deux activités de
	* l'instance de BinaryConstraint, <i>false</i> dans le cas contraire.
*/
  @Override
  public boolean isSatisfied(Schedule edt){
    HashMap<Activity, GregorianCalendar> emploiDT = edt.getEdt();
    GregorianCalendar date1 = emploiDT.get(this.first);
    GregorianCalendar date2 = emploiDT.get(this.second);
    return isSatisfied(date1,date2);
  }

}
