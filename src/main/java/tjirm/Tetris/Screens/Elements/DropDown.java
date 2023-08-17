package main.java.tjirm.Tetris.Screens.Elements;

import main.java.tjirm.Tetris.Rendering.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DropDown<Value> extends Element {

    String selected;
    private final int fontSize;

    private final Map<String, Option<Value>> options = new TreeMap<>();
    private final Map<String, Boolean> inBounds = new HashMap<>();
    private int closeMark = 3;
    boolean active = false;

    public DropDown(String name, int fontSize, int x, int y) {
        this.name = name;
        this.fontSize = fontSize;
        this.y = y;
        this.x = x;
    }

    public DropDown<Value> addOption(String name, Value value) {
        options.put(name, new Option<>(name, fontSize, options.size(), value, this));
        inBounds.put(name, false);
        if(width < options.get(name).getWidth())
            width = options.get(name).getWidth();
        height += options.get(name).getHeight();
        selected = name;
        return this;
    }

    public DropDown<Value> setSelected(String select) {
        select = select.toLowerCase();
        assert options.containsKey(select) : "The option given does not exist in the DropDown";
        options.get(this.selected).selected = false;
        this.selected = select;
        options.get(this.selected).selected = true;
        setPos();
        return this;
    }

    public Value select(String select) {
        setSelected(select);
        return options.get(selected).getValue();
    }

    private void close() {
        active = false;
    }

    protected void open() {
        active = true;
    }

    private void setPos() {
        int id = options.get(selected).Id;
        int height = options.get(selected).getHeight();
        for (Map.Entry<String, Option<Value>> entry : options.entrySet()) {
            Option<Value> option = entry.getValue();
            option.setY(y - ((id - option.Id) * height));
        }
        setBounds();
    }

    private void setBounds() {
        int id = options.get(selected).Id;
        for (Map.Entry<String, Option<Value>> entry : options.entrySet()) {
            Option<Value> option = entry.getValue();
            int[] bounds = new int[4];
            bounds[0] = x - option.getWidth() / 2 - Text.fontHeight / 3;
            bounds[1] = y - (option.getHeight() * (id - option.Id)) - option.getHeight();
            bounds[2] = x + option.getWidth() / 2 + Text.fontHeight / 3;
            bounds[3] = y - (option.getHeight() * (id - option.Id));

            option.setBounds(bounds);
        }
    }

    protected void leftBounds(String name) {
        inBounds.put(name, false);
    }
    protected void enteredBounds(String name) {
        inBounds.put(name, true);
        if(closeMark != 0) {
            if(closeMark == 3)
                open();
            closeMark = 0;
        }
    }

    public boolean isActive() {return true;}
    @Override
    public boolean inBoundingBox(int x, int y) {
        if(!active)
            return false;
        for(Map.Entry<String, Boolean> entry : inBounds.entrySet()) {
            if(entry.getValue())
                return false;
        }
        if(closeMark == 3)
            close();
        if(closeMark < 3)
            closeMark++;
        return false;
    }

    public Map<String, Option<Value>> getOptions() {
        return options;
    }
    public int getOptionsSize() {
        return options.size();
    }

    public int getX() {
        return 0;
    }
    public int getMidX() {
        return 0;
    }
    public int getY() {
        return 0;
    }
}