package HaohaiTeam.Game;
import HaohaiTeam.Game.GUI.GameWindow;
import HaohaiTeam.Game.Map.MapLoader;
// import HaohaiTeam.Game.Logic.ElementBehavior;

public class GameStarter {

    public void startGame() { // 之前的startApp现在改名为startGame
        System.out.println("Welcome to Dublin!");
        GameWindow gameWindow = new GameWindow();
        // ElementBehavior elementBehavior = new ElementBehavior();
        MapLoader mapLoader = new MapLoader(gameWindow); //, elementBehavior disabled

        // 加载起始屏幕，其中包括将元素添加到ElementManager中
        mapLoader.loadMap(MapLoader.level_1);

        // 显示游戏窗口
        gameWindow.openWindow();

        // 游戏主循环在GameWindow中处理
    }

    public static void main(String[] args) {
        GameStarter gameStarter = new GameStarter();
        gameStarter.startGame();
    }

}