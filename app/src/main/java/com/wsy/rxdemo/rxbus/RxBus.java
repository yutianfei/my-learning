package com.wsy.rxdemo.rxbus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Description 用RxJava实现事件总线(Event Bus)
 * 2016/5/27.
 */
public class RxBus {

    private static volatile RxBus defaultInstance;

    /**
     * 创建一个可同时充当Observer和Observable的Subject
     * Subject是非线程安全的，为避免该问题，将Subject转换为SerializedSubject
     * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
     */
    private final Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

    /**
     * 获取RxBus单例
     */
    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    /**
     * 是否被订阅
     */
    public boolean hasObservers() {
        return bus.hasObservers();
    }

    /**
     * 在需要发送事件的地方，将事件post至Subject，
     * 此时Subject作为Observer接收到事件（onNext），
     * 然后发射给所有订阅该Subject的订阅者
     */
    public void post(Object o) {
        bus.onNext(o);
    }

    /**
     * 在需要接收事件的地方，
     * 订阅该Subject（此时Subject是作为Observable），
     * 在这之后，一旦Subject接收到事件，立即发射给该订阅者
     * <p>
     * 返回通用型的Observable
     */
    public Observable<Object> toObserverable() {
        return bus;
    }

    /**
     * 在需要接收事件的地方，
     * 订阅该Subject（此时Subject是作为Observable），
     * 在这之后，一旦Subject接收到事件，立即发射给该订阅者
     * <p>
     * 根据传递的 eventType 类型返回特定类型(eventType)的Observable
     */
    public <T> Observable<T> toObserverable(Class<T> eventType) {
        // ofType操作符只发射指定类型的数据
        return bus.ofType(eventType);
    }

}
