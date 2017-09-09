package pattern.observer;

import pattern.observer.port.DisplayElement;
import pattern.observer.port.Observer;
import pattern.observer.port.Subject;

/**
 * @author noobit
 * @date 2017/07/13 23:46 - 星期四
 */
public class ForecastDisplay implements Observer, DisplayElement {
    private float humidity;
    private float pressure;
    private Subject weatherData;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.println(String.format("ForecastDisplay{humidity=%s, pressure=%s}", humidity, pressure));
    }

}
