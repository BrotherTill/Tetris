package main.java.tjirm.Tetris.Screens;

import java.util.Objects;

public class elementKey {

    private final Object key;

    elementKey(int key) {
        this.key = key;
    }

    elementKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        elementKey that = (elementKey) o;

        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
