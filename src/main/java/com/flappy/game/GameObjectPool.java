package com.flappy.game;
import java.util.ArrayList;
import java.util.List;

public class GameObjectPool<T extends GameObject> {
    private List<T> objects;
    private Class<T> objectType;
    
    public GameObjectPool(int initialSize, Class<T> objectType) {
        objects = new ArrayList<>();
        this.objectType = objectType;
        
        for (int i = 0; i < initialSize; i++) {
            T object = createObject();
            objects.add(object);
        }
    }
    
    public T getObject() {
        for (T object : objects) {
            if (!object.isActive()) {
                object.setActive(true);
                return object;
            }
        }
        
        T object = createObject();
        object.setActive(true);
        objects.add(object);
        return object;
    }
    
    private T createObject() {
        try {
            return objectType.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}