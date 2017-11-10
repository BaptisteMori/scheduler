
package edt_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.NoSuchElementException;

/**
	* Crée un emploi du temps à partir d'activités et de contraintes données.
	* @see Activity
	* @see PrecedenceConstraint
	* @see PrecedenceConstraintWithDuration
*/
public class Schedule {

  private HashMap<Activity, GregorianCalendar> edt;

/**
	* Constructeur de la classe.
	* Crée une HashMap destinée à être remplie de paires activité/date de début, formant ainsi un emploi du temps.
*/
  public Schedule(){
    this.edt = new HashMap<> ();
  }

  public HashMap<Activity, GregorianCalendar> getEdt(){
    return this.edt;
  }

/**
	* Insère une paire activité/date de début dans l'emploi du temps.
	* @param act
	* Activité à insérer dans l'emploi du temps.
	* @param date
	* Date à insérer dans l'emploi du temps.
*/
  public void schedule(Activity act, GregorianCalendar date){
    this.edt.put(act,date);
  }

/**
	* Vérifie si l'emploi du temps vérifie toutes les contraintes de l'ArrayList en argument.
	* @param contraintes
	* Liste de contraintes à satisfaire.
	* @return Le résultat du test : <i>true</i> si tout est satisfait,
	* <i>false</i> dans le cas contraire.
*/
  public boolean satisfies(ArrayList<PrecedenceConstraint> contraintes){
    for(int i = 0; i < contraintes.size(); i++){
      if (!contraintes.get(i).isSatisfied(edt.get(contraintes.get(i).first),edt.get(contraintes.get(i).second))) {
        return false;
      }
    }
    return true;
  }

/**
	* Trie les activités de l'emploi du temps par ordre chronologique.
	* @return La liste des activités triée par ordre chronologique.
*/
  private ArrayList<Activity> getSortedActivities() {
    ArrayList<Activity> Ltemp = new ArrayList<> ();
    ArrayList<Activity> Lfinal = new ArrayList<> ();
    for (Map.Entry<Activity,GregorianCalendar> x : this.edt.entrySet()) {
      Ltemp.add(x.getKey());
    }
    int min = 0;
    while (Ltemp.size() != 0) {
      min = 0;
      for (int i = 0; i < Ltemp.size(); i++) {
        if (edt.get(Ltemp.get(i)).compareTo(edt.get(Ltemp.get(min))) < 0) {
          min = i;
        }
      }
      Lfinal.add(Ltemp.get(min));
      Ltemp.remove(min);
    }
    return Lfinal;
  }

/**
	* Crée une version affichable de l'emploi du temps.
	* @return Une chaîne de caractère mise en forme permettant d'afficher en console l'emploi du temps.
*/
  public String toString() {
    ArrayList<Activity> L = this.getSortedActivities();
    String ch = "";
    for(int i=0; i < L.size(); i++){
      ch += L.get(i).getAction() + " à " + edt.get(L.get(i)).get(GregorianCalendar.HOUR_OF_DAY) + " h " + edt.get(L.get(i)).get(GregorianCalendar.MINUTE) + "\n";
    }
    return ch;
  }

/**
	* Identifie la prochaine activité à planifier lors de la création d'un emploi du temps.
	* @param l_act
	* Liste des activités à planifier.
	* @param l_contr
	* Liste des contraintes à prendre en compte.
	* @param l_planified
	* Liste des activités déjà planifiées.
	@return L'activité suivante dans l'ordre de planification.
	@throws NoSuchElementException Si il n'y a pas d'activité remplissant les contraintes à retourner.
*/
  private Activity next(ArrayList<Activity> l_act, ArrayList<BinaryConstraint> l_contr,ArrayList<Activity> l_planified) {
    for (Activity x : l_act) {
      if (! l_planified.contains(x)) {
        boolean test_act_est_secondaire = true;
        for (BinaryConstraint y : l_contr) {
          if (x == y.second && !l_planified.contains(y.first)) {
            test_act_est_secondaire = false;
            break;
          }
        }
        if (test_act_est_secondaire) {
          return x;
        }
      }
    }
    throw new NoSuchElementException("Pas de possibilité");
  }

/**
	* Remplit un emploi du temps avec des activités ordonnées chronologiquement.
	* @param l_act
	* Liste des activités à planifier.
	* @param l_contr
	* Liste des contraintes à prendre en compte.
*/
  public void computeSchedule(ArrayList<Activity> l_act,ArrayList<BinaryConstraint> l_contr) throws NoSuchElementException {
    try {
      ArrayList<Activity> l_planified = new ArrayList<> ();
      GregorianCalendar date = new GregorianCalendar(2009,6,10,9,0);
      Activity act;
      while (! l_act.isEmpty()) {
        act = this.next(l_act,l_contr,l_planified);
        l_planified.add(act);
        l_act.remove(act);
      }
      for (Activity a : l_planified) {
        this.schedule(a,date);
        date=new GregorianCalendar(date.get(GregorianCalendar.YEAR),date.get(GregorianCalendar.MONTH),date.get(GregorianCalendar.DAY_OF_MONTH),date.get(GregorianCalendar.HOUR_OF_DAY),date.get(GregorianCalendar.MINUTE)+a.getDuree());
      }
    } catch (NoSuchElementException e) {
      System.out.println("Error");
    }
  }
}
