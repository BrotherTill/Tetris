package Tetris.Screens;

import Tetris.Rendering.Render;

public class Screens {

    public static Screen titleScreen;
    public static Screen mainMenu;
    public static Screen levels;
    public static Screen credits;
    public static Screen tryAgain;

    public static Screen game;

    public static void init() {
        titleScreen = new TitleScreen();
        mainMenu = new MainMenu();
        levels = new LevelSelect();
        credits = new Credits();
        tryAgain = new TryAgain();
        game = new Game();
    }

    public static Screen getCurrent() {
        Screen out = null;
        switch(Render.Screen) {
            case TitleScreen -> out = titleScreen;
            case Menu -> out = mainMenu;
            case Game -> out = game;
            case LevelSelect -> out = levels;
            case Credits -> out = credits;
            case TryAgain -> out = tryAgain;
        }
        return out;
    }

}
