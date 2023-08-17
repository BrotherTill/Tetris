package main.java.tjirm.Tetris.Screens;
public class Screens {

    private static ScreenState Screen = ScreenState.TitleScreen;

    public static Screen titleScreen;
    public static Screen mainMenu;
    public static Screen levels;
    public static Screen options;
    public static Screen credits;
    public static Screen tryAgain;

    public static Screen game;

    public static void init() {
        titleScreen = new TitleScreen();
        mainMenu = new MainMenu();
        levels = new LevelSelect();
        options = new Options();
        credits = new Credits();
        tryAgain = new TryAgain();
        game = new Game();
    }

    public static Screen getCurrent() {
        Screen out = null;
        switch(Screen) {
            case TitleScreen -> out = titleScreen;
            case Menu -> out = mainMenu;
            case Game -> out = game;
            case LevelSelect -> out = levels;
            case Options -> out = options;
            case Credits -> out = credits;
            case TryAgain -> out = tryAgain;
        }
        return out;
    }

    public static void setScreen(ScreenState screen) {
        Screen = screen;
    }

    public static ScreenState getScreen() {
        return Screen;
    }

    public enum ScreenState {
        TitleScreen,
        Menu,
        LevelSelect,
        Options,
        Credits,
        Game,
        TryAgain
    }

}
