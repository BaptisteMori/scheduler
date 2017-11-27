package edt_java;

/**
* Représente un créneau horaire dans un emploi du temps.
*/
public class Activity {

	private String action;
	private int duree;

/**
	* Constructeur de la classe.
	* @param action
	* L'intitulé de l'activité prenant place dans le créneau horaire.
	* @param duree
	* La longueur du créneau horaire, exprimée en minutes.
*/
	public Activity(String action, int duree) {
		this.action=action;
		this.duree=duree;
	}

/**
	* Retourne l'intitulé de l'activité.
	* @return L'intitulé de l'activité.
*/
	public String getAction() {
		return this.action;
	}

/**
	* Retourne la durée de l'activité.
	* @return La durée de l'activité.
*/
	public int getDuree(){
		return this.duree;
	}
}
