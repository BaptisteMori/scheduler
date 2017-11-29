package edt_java;

import java.util.Map;
import java.util.HashMap;
import scheduleio.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
	* Classe permettant la création de listes d'activités et de contraintes pour construire un emploi du temps, via la lecture
	* de fichiers texte formatés.
	* @see Activity
	* @see PrecedenceConstraint
	* @see MeetConstraint
	* @see MaxSpanConstraint
*/
public class ScheduleReader {

/**
	* Constructeur de la classe.
*/
	public ScheduleReader() {
	}

/**
	* Lit un fichier texte et en extrait une Map d'activités avec un identifiant comme clé.
	* @param filename
	* Nom du fichier à lire.
	* @return Un dictionnaire de paires identifiant-activité.
*/
	public Map<String, Activity> readActivities(String filename) throws IOException {
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(filename));
			IdStringStringReader activityReader = new IdStringStringReader(fileReader, "=", "_lasting_");
			Map<String, Activity> activityMap = new HashMap<>();
			for (Map.Entry<String, OrderedPair<String, String>> strings: activityReader.readAll().entrySet()) {
				String id = strings.getKey();
				OrderedPair<String, String> activityStrings = strings.getValue();
				String description = activityStrings.getFirst();
				int duration = Integer.parseInt(activityStrings.getSecond());
				activityMap.put(id,new Activity(description,duration));
			}
			return activityMap;
		} catch (IOException e) {
			return new HashMap<> ();
		}
	}

/**
	* Transforme une Map d'activités en une liste.
	* @param map
	* Le dictionnaire à transformer.
	* @return Une liste des paires contenues dans le dictionnaire.
*/
	public ArrayList<Activity> getActivities(Map<String, Activity> map) {
		ArrayList<Activity> activities = new ArrayList<> ();
		for (Map.Entry<String, Activity> elt : map.entrySet()) {
			activities.add(elt.getValue());
		}
		return activities;
	}

/**
	* Lit un fichier texte et en extrait une liste de contraintes de précédence (éventuellement immédiate).
	* @param activities
	* Dictionnaire de paires identifiant-activité.
	* @param constraintsFilename
	* Nom du fichier à lire.
	* @param separator
	* Séparateur à utliser lors de la lecture du fichier.
	* @return Une collection de contraintes binaires de même type.
*/
	public Collection<BinaryConstraint> buildPreceMeetCollection (Map<String,Activity> activities, String  constraintsFilename, String separator) throws IOException {
		BufferedReader fileReader = new BufferedReader(new FileReader(constraintsFilename));
		IdStringReader constraintReader = new IdStringReader(fileReader, separator);
		Collection<BinaryConstraint> listePrecedence = new ArrayList<> ();
		OrderedPair<String,String> line = constraintReader.read();
		while (line != null) {
			Activity act1 = activities.get(line.getFirst());
			Activity act2 = activities.get(line.getSecond());
			if (separator=="_before_") {
				PrecedenceConstraint contrainte = new PrecedenceConstraint(act1,act2);
				listePrecedence.add(contrainte);
			}
			else if (separator=="_meets_") {
				MeetConstraint contrainte = new MeetConstraint(act1,act2);
				listePrecedence.add(contrainte);
			}
			line = constraintReader.read();
		}
		return listePrecedence;
	}

/**
	* Lit un fichier texte et en extrait une liste de contraintes de durée maximale.
	* @param activities
	* Dictionnaire de paires identifiant-activité.
	* @param constraintsFilename
	* Nom du fichier à lire.
	* @return Une liste de contraintes de durée maximale.
*/
	public ArrayList<MaxSpanConstraint> buildMaxSpanCollection (Map<String,Activity> activities, String constraintsFilename) throws IOException {
		BufferedReader fileReader = new BufferedReader(new FileReader(constraintsFilename));
		IdStringReader ConstraintReader = new IdStringReader(fileReader, "_within_");
		ArrayList<MaxSpanConstraint> listeMaxSpan = new ArrayList<> ();
		OrderedPair<String,String> line = ConstraintReader.read();
		while (line != null) {
			String chartmp=line.getFirst();
			ArrayList<String> listetmp = new ArrayList<String>(Arrays.asList(chartmp.split(", ")));
			ArrayList<Activity> list_act = new ArrayList<> ();
			for(String x : listetmp){
				list_act.add(activities.get(x));
			}
			int duree = Integer.parseInt(line.getSecond());
			MaxSpanConstraint contrainte = new MaxSpanConstraint(list_act,duree);
			listeMaxSpan.add(contrainte);
			line = ConstraintReader.read();
		}
		return listeMaxSpan;
	}
}
