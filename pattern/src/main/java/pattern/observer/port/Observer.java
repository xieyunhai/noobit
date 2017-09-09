package pattern.observer.port;

/**
 * 观察者对象接口
 *
 * @author noobit
 * @date 2017/07/13 22:41 - 星期四
 */
public interface Observer {

    /**
     * 更新观察者对象的数据
     * @param temperature 温度
     * @param humidity 湿度
     * @param pressure 压力
     */
    void update(float temperature, float humidity, float pressure);
}
