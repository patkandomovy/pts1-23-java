package sk.uniba.fmph.dcs;

interface ObserverInterface {
    void registerObserver(ObserverInterface observer);
    void cancelObserver(ObserverInterface observer);
    void notify(String newState);
}
