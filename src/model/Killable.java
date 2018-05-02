package model;

public interface Killable {
	void attachKillableObserver(KillableObserver Ko);
	void notifyKillableObserver(); 
}
