package org.example.event_informers.observer;

public interface Observer<T> {
    public abstract void update(T event);
}
