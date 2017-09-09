package pattern.observer.port;

/**
 * 主题接口
 *
 * @author noobit
 * @date 2017/07/13 22:43 - 星期四
 */
public interface Subject {

    /**
     * 注册观察者对象
     * @param observer 实现 Observer 接口的观察者对象
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者对象
     * @param observer 实现 Observer 接口的观察者对象
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有的观察者对象
     */
    void notifyObservers();
}
