package pattern.observer;

import org.junit.Test;

/**
 * @author noobit
 * @date 2017/07/13 23:34 - 星期四
 */
public class CurrentConditionsDisplayTest {


    @Test
    public void update() throws Exception {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay conditionsDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);

        conditionsDisplay.display();

        weatherData.setMeasurements(82, 70, 29.2f);
    }

}