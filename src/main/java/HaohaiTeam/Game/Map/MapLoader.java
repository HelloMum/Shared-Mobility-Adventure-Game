package HaohaiTeam.Game.Map;


import HaohaiTeam.Game.Element.Transport.OnRoute.*;
import HaohaiTeam.Game.GUI.GameWindow;
import HaohaiTeam.Game.Element.*;
import HaohaiTeam.Game.Element.Transport.*;
import HaohaiTeam.Game.Element.Transport.OnRoute.*;

import java.util.Set;

//import HaohaiTeam.Game.Logic.ElementBehavior;

public class MapLoader {

    GameWindow gameWindow;
    // ElementBehavior elementBehavior;
    public MapLoader(GameWindow gameWindow) { //ElementBehavior elementBehavior not used
        this.gameWindow = gameWindow;
        // this.elementBehavior = elementBehavior;
    }
    public static String[] level_1 = {
            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
            "WrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrlrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrW",
            "WrL                                                                        rW",
            "Wr        rrrrrrrrr                         rrrrrrrrr                      rW",
            "Wr        r  2 W Cr     C    L     B        r    W Cr     C    L     B     rW",
            "Wr        r  P W  c WWWWWW  rrrrrrr         c       c WWWWWW  rrrrrrr      rW",
            "Wr        r    W  r WGGGGW  r     r         t    W  r WGGGGW  r     r      rW",
            "Wr        r    W  r WGGGGW  rrrlrrr         r    W  r WGGGG   rrrlrrr      rW",
            "Wr        r    W  r WWWWWW                  r    W  r WWWWWW               rW",
            "Wr        r G  W  r      C     C            r G  W  r      C     C         rW",
            "Wr        r    W  rE  F   C     rrrrrrrrrrrrr    W  r   F   C     rrrr     rW",
            "Wr        rC   W  rrrrrrrrrrrrrrrT               W  rrrrrrrrrrrrrrrT r     rW",
            "Wr        c    W             C                   W             C     r     rW",
            "Wr        r    W    WWW    C                     W    WWW    C       r     lW",
            "Wr        b    W    W   W                        W    W   W          r     rW",
            "Wr        t  C W    WG  W                      C W    WG  W          b     rW",
            "Wl        r         W   W     C   U    a     a        W   W     C   Ur     rW",
            "Wr        rrrrbrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrbrrrrrrrrrrrrrrrrrrrrr     rW",
            "Wr                                                                         rW",
            "Wr                                                                         rW",
            "Wr                                                                         rW",
            "Wr                                                                         rW",
            "Wr                                                                         rW",
            "Wr                                                                         rW",
            "Wr                                                                         rW",
            "WrL                                                                        rW",
            "WrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrlrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrW",
            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"
    };
    // This should be implemented in an external file but for now it suits the purpose of testing

    public void loadMap(String[] mapData) {
        System.out.println("No Saved Games, starting first level!");
        for (int y = 0; y < mapData.length; y++) {
            String line = mapData[y];
            for (int x = 0; x < line.length(); x++) {
                char tile = line.charAt(x);
                int posX = x * GameWindow.CELL_SIZE;// expand fake map 'level_1' use cell size = 30px
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
                    case '2':
                        placeReport("2", posX, posY);
                        Player player2 = new Player2(posX, posY);
                        gameWindow.addElement(player2);
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
                    case 'r':
                        placeReport("Road", posX, posY);
                        Road road = new Road(posX, posY);
                        gameWindow.addElement(road);
                        break;
                    case 'c':
                        placeReport("Crosswalk", posX, posY);
                        Crosswalk crosswalk = new Crosswalk(posX, posY);
                        gameWindow.addElement(crosswalk);
                        break;
                    case 'E':
                        placeReport("CameraEntity", posX, posY);
                        CameraEntity cameraEntity = new CameraEntity(posX, posY);
                        gameWindow.addElement(cameraEntity);
                        break;
                    case 'b':
                        placeReport("BusStation", posX, posY);
                        BusStation busStation = new BusStation(posX, posY);
                        gameWindow.addElement(busStation);
                        break;
                    case 't':
                        placeReport("TaxiStation", posX, posY);
                        TaxiStation taxiStation = new TaxiStation(posX, posY);
                        gameWindow.addElement(taxiStation);
                        break;
                    case 'l':
                        placeReport("LuasStation", posX, posY);
                        LuasStation luasStation = new LuasStation(posX, posY);
                        gameWindow.addElement(luasStation);
                        break;
                    case 'a':
                        placeReport("Car", posX, posY);
                        Car car = new Car(posX, posY);
                        gameWindow.addElement(car);
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
