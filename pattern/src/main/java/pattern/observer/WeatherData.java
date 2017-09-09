package pattern.observer;

import pattern.observer.port.Observer;
import pattern.observer.port.Subject;

import java.util.ArrayList;

/**
 * @author noobit
 * @date 2017/07/13 22:42 - 星期四
 */
public class WeatherData implements Subject {
    private ArrayList<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(temperature, humidity, pressure));
    }

    private void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }


    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
