package edt_java;

class Activity {
	private String action;
	private int duree; //en minutes

	public Activity(String action, int duree) {
		this.action=action;
		this.duree=duree;
	}

	public int getDuree(){
		return this.duree;
	}
}
