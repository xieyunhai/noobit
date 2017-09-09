package pattern.observer;

import pattern.observer.port.DisplayElement;
import pattern.observer.port.Observer;
import pattern.observer.port.Subject;

/**
 * @author noobit
 * @date 2017/07/13 23:42 - 星期四
 */
public class StatisticsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float pressure;
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.println(String.format("StatisticsDisplay{temperature=%s, pressure=%s}", temperature, pressure));
    }
}
