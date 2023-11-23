package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private final List<Observer> observers = new ArrayList<>();
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    void cancelObserver(Observer observer) {
        observers.remove(observer);
    }
    void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
