package edt_java;

/**
	* Interface représentant la capacité d'une contrainte à être respectée.
	* @see Schedule
*/
public interface Constraint {

/**
	* Teste si une contrainte entre plusieurs activités (2 ou plus) d'un emploi du temps est satisfaite.
	* @param edt
	* Emploi du temps à tester.
	* @return Le résultat du test : <i>true</i> si l'emploi du temps respecte la contrainte entre les différentes activités,
	* <i>false</i> dans le cas contraire.
*/
  public abstract boolean isSatisfied(Schedule edt);

}
