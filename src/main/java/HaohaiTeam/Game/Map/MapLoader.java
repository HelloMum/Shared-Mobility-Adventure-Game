package HaohaiTeam.Game.Map;

import HaohaiTeam.Game.Element.Coin;
import HaohaiTeam.Game.Element.Gem;
import HaohaiTeam.Game.Element.Player;
import HaohaiTeam.Game.Element.Wall;
import HaohaiTeam.Game.GUI.GameWindow;
import HaohaiTeam.Game.Element.Transport.*;
import HaohaiTeam.Game.Logic.ElementBehavior;

public class MapLoader {

    GameWindow gameWindow;
    ElementBehavior elementBehavior;
    public MapLoader(GameWindow gameWindow, ElementBehavior elementBehavior) {
        this.gameWindow = gameWindow;
        this.elementBehavior = elementBehavior;
    }
    public static String[] level_1 = {
            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
            "W                     G        W",
            "W    P  W C      C  L          GW",
            "W       W    WWWWWW            W",
            "W       W    WGGGGW            W",
            "W       W    WGGGG             W",
            "W       W    WWWWWW            W",
            "W    G  W         C     C      W",
            "W       W   C      C           W",
            "W   C   W   B    G             W",
            "W       W             C        W",
            "W       W    WWW    C          W",
            "W       W    W   W       T     W",
            "W     C W    WG  W    U        W",
            "W            W   W     C       W",
            "W            W   W             W",
            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"
    };
    // This should be implemented in an external file but for now it suits the purpose of testing

    public void loadMap(String[] mapData) {
        System.out.println("No Saved Games, starting first level!");
        for (int y = 0; y < mapData.length; y++) {
            String line = mapData[y];
            for (int x = 0; x < line.length(); x++) {
                char tile = line.charAt(x);
                int posX = x * GameWindow.CELL_SIZE;
                int posY = y * GameWindow.CELL_SIZE;

                switch (tile) {
                    case 'W':
                        placeReport("Wall", posX, posY);
                        Wall wall = new Wall(posX, posY);
                        gameWindow.addElement(wall);
                        break;
                    case 'G':
                        placeReport("Gem", posX, posY);
                        Gem gem = new Gem(posX, posY);
                        gameWindow.addElement(gem);
                        break;
                    case 'C':
                        placeReport("Coin", posX, posY);
                        Coin coin = new Coin(posX, posY);
                        gameWindow.addElement(coin);
                        break;
                    case 'P':
                        placeReport("Player", posX, posY);
                        Player player = new Player(posX, posY);
                        gameWindow.addElement(player);
                        break;
                    case 'B':
                        placeReport("Bike", posX, posY);
                        Bike bicycle = new Bike(posX, posY);
                        gameWindow.addElement(bicycle);
                        break;
                    case 'U':
                        placeReport("Bus", posX, posY);
                        Bus bus = new Bus(posX, posY);
                        gameWindow.addElement(bus);
                        break;
                    case 'T':
                        placeReport("Taxi", posX, posY);
                        Taxi taxi = new Taxi(posX, posY);
                        gameWindow.addElement(taxi);
                        break;
                    case 'L':
                        placeReport("Luas", posX, posY);
                        Luas luas = new Luas(posX, posY);
                        gameWindow.addElement(luas);
                        break;
                }
            }
        }
    }
    private void placeReport(String placeName, int posX , int posY) {
        // This reports the map import is working correctly
        System.out.println( placeName );
        System.out.println("has been placed in the position X: " + posX + ", Y: " + posY );


    }
    public void loadStartingScreen() {
        // Implementation for loading just an animation, then load level 1
        loadMap(level_1);

    }
}
