package pixelcraft.project;
//Subject interface for model
public interface Subject {
    void addObserver(Observer observer);
    void notifyObservers();
}
