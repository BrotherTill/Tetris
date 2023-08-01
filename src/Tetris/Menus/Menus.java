package Tetris.Menus;

import Tetris.Rendering.Render;

public class Menus {

    public static Menu titleScreen = new TitleScreen();
    public static Menu mainMenu = new MainMenu();
    public static Menu levels = new LevelSelect();
    public static Menu credits = new Credits();
    public static Menu tryAgain = new TryAgain();

    public static Menu getCurrent() {
        Menu out = null;
        switch(Render.Screen) {
            case TitleScreen -> out = titleScreen;
            case Menu -> out = mainMenu;
            case LevelSelect -> out = levels;
            case Credits -> out = credits;
            case TryAgain -> out = tryAgain;
        }
        return out;
    }

}
