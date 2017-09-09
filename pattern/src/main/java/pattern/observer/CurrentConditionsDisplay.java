package pattern.observer;

import pattern.observer.port.DisplayElement;
import pattern.observer.port.Observer;
import pattern.observer.port.Subject;

/**
 * @author noobit
 * @date 2017/07/13 22:54 - 星期四
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.println("CurrentConditionsDisplay{" + "temperature=" + temperature + ", humidity=" + humidity + '}');
    }

}
