package HaohaiTeam.Game.Map;


import HaohaiTeam.Game.Element.Transport.OnRoute.*;
import HaohaiTeam.Game.GUI.GameWindow;
import HaohaiTeam.Game.Element.*;
import HaohaiTeam.Game.Element.Transport.*;
import java.util.Random;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

//import HaohaiTeam.Game.Logic.ElementBehavior;

public class MapLoader {

    GameWindow gameWindow;
    private int currentLevel = 1; // Track the current level

    // ElementBehavior elementBehavior;
    public MapLoader(GameWindow gameWindow) { //ElementBehavior elementBehavior not used
        this.gameWindow = gameWindow;
        // this.elementBehavior = elementBehavior;
    }

    public void loadNextLevel() {
        String levelFile = String.format("src/main/resources/MapElement/level_%d.json", currentLevel);
        loadMapFromJson(levelFile);
        currentLevel++;  // Prepare for the next level
    }


    private void loadMapFromJson(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject json = new JSONObject(content);
            JSONArray mapLines = json.getJSONArray("map");
            loadMap(mapLines.toList().toArray(new String[0]));
        } catch (IOException e) {
            System.err.println("Error loading level file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadMap(String[] mapData) {
        gameWindow.clearElements();  // Clear existing elements from the game window
        ArrayList<Object> validGemCoordinates = new ArrayList<>();
        System.out.println("No Saved Games, starting first level!");

        for (int y = 0; y < mapData.length; y++) {
            String line = mapData[y];
            for (int x = 0; x < line.length(); x++) {
                char tile = line.charAt(x);
                int posX = x * GameWindow.CELL_SIZE;
                int posY = y * GameWindow.CELL_SIZE;
                processTile(tile, posX, posY, validGemCoordinates);
            }
        }
        placeRandomGems(validGemCoordinates);
    }

    private void processTile(char tile, int posX, int posY, ArrayList<Object> validGemCoordinates) {
        switch (tile) {
            case 'W': gameWindow.addElement(new Wall(posX, posY)); break;
            case 'G': gameWindow.addElement(new Gem(posX, posY)); break;
            case 'C': gameWindow.addElement(new Coin(posX, posY)); break;
            case 'P': gameWindow.addElement(new Player(posX, posY)); break;
            case '2': gameWindow.addElement(new Player2(posX, posY)); break;
            case 'B': gameWindow.addElement(new Bike(posX, posY)); break;
            case 'U': gameWindow.addElement(new Bus(posX, posY)); break;
            case 'T': gameWindow.addElement(new Taxi(posX, posY)); break;
            case 'L': gameWindow.addElement(new Luas(posX, posY)); break;
            case 'r': gameWindow.addElement(new Road(posX, posY)); break;
            case 'c': gameWindow.addElement(new Crosswalk(posX, posY)); break;
            case 'E': gameWindow.addElement(new CameraEntity(posX, posY)); break;
            case 'b': gameWindow.addElement(new BusStation(posX, posY)); break;
            case 't': gameWindow.addElement(new TaxiStation(posX, posY)); break;
            case 'l': gameWindow.addElement(new LuasStation(posX, posY)); break;
            case 'a': gameWindow.addElement(new Car(posX, posY)); break;
            case ' ': validGemCoordinates.add(posX); validGemCoordinates.add(posY); break;
            case 'x':
                        placeReport("FakeBus", posX, posY);
                        FakeBus fakeBus = new FakeBus(posX, posY);
                        gameWindow.addElement(fakeBus);
                        break;
                    case 'y':
                        placeReport("FakeLuas", posX, posY);
                        FakeLuas fakeLuas = new FakeLuas(posX, posY);
                        gameWindow.addElement(fakeLuas);
                        break;
                    case 'z':
                        placeReport("FakeTaxi", posX, posY);
                        FakeTaxi fakeTaxi = new FakeTaxi(posX, posY);
                        gameWindow.addElement(fakeTaxi);
                        break;
            // Additional cases can be added here
        }
    }

    private void placeRandomGems(ArrayList<Object> validGemCoordinates) {
        Random random = new Random();
        int maxRandomGems = 10;
        for (int i = 0; i < maxRandomGems; i++) {
            int randomIndex = random.nextInt(validGemCoordinates.size() / 2) * 2;
            int gemPosX = (int) validGemCoordinates.get(randomIndex);
            int gemPosY = (int) validGemCoordinates.get(randomIndex + 1);
            gameWindow.addElement(new Gem(gemPosX, gemPosY));
            validGemCoordinates.remove(randomIndex); // Remove X
            validGemCoordinates.remove(randomIndex); // Remove Y
        }
    }

    // This should be implemented in an external file but for now it suits the purpose of testing

//    public void loadMap(String[] mapData) {
//        ArrayList<Object> validGemCoordinates = new ArrayList<>();
//        System.out.println("No Saved Games, starting first level!");
//        for (int y = 0; y < mapData.length; y++) {
//            String line = mapData[y];
//            for (int x = 0; x < line.length(); x++) {
//                char tile = line.charAt(x);
//                int posX = x * GameWindow.CELL_SIZE;// expand fake map 'level_1' use cell size = 30px
//                int posY = y * GameWindow.CELL_SIZE;
//
//                switch (tile) {
//                    case 'W':
//                        placeReport("Wall", posX, posY);
//                        Wall wall = new Wall(posX, posY);
//                        gameWindow.addElement(wall);
//                        break;
//                    case 'G':
//                        placeReport("Gem", posX, posY);
//                        Gem gem = new Gem(posX, posY);
//                        gameWindow.addElement(gem);
//                        break;
//                    case 'C':
//                        placeReport("Coin", posX, posY);
//                        Coin coin = new Coin(posX, posY);
//                        gameWindow.addElement(coin);
//                        break;
//                    case 'P':
//                        placeReport("Player", posX, posY);
//                        Player player = new Player(posX, posY);
//                        gameWindow.addElement(player);
//                        break;
//                    case '2':
//                        placeReport("2", posX, posY);
//                        Player player2 = new Player2(posX, posY);
//                        gameWindow.addElement(player2);
//                        break;
//                    case 'B':
//                        placeReport("Bike", posX, posY);
//                        Bike bicycle = new Bike(posX, posY);
//                        gameWindow.addElement(bicycle);
//                        break;
//                    case 'U':
//                        placeReport("Bus", posX, posY);
//                        Bus bus = new Bus(posX, posY);
//                        gameWindow.addElement(bus);
//                        break;
//                    case 'T':
//                        placeReport("Taxi", posX, posY);
//                        Taxi taxi = new Taxi(posX, posY);
//                        gameWindow.addElement(taxi);
//                        break;
//                    case 'L':
//                        placeReport("Luas", posX, posY);
//                        Luas luas = new Luas(posX, posY);
//                        gameWindow.addElement(luas);
//                        break;
//                    case 'r':
//                        placeReport("Road", posX, posY);
//                        Road road = new Road(posX, posY);
//                        gameWindow.addElement(road);
//                        break;
//                    case 'c':
//                        placeReport("Crosswalk", posX, posY);
//                        Crosswalk crosswalk = new Crosswalk(posX, posY);
//                        gameWindow.addElement(crosswalk);
//                        break;
//                    case 'E':
//                        placeReport("CameraEntity", posX, posY);
//                        CameraEntity cameraEntity = new CameraEntity(posX, posY);
//                        gameWindow.addElement(cameraEntity);
//                        break;
//                    case 'b':
//                        placeReport("BusStation", posX, posY);
//                        BusStation busStation = new BusStation(posX, posY);
//                        gameWindow.addElement(busStation);
//                        break;
//                    case 't':
//                        placeReport("TaxiStation", posX, posY);
//                        TaxiStation taxiStation = new TaxiStation(posX, posY);
//                        gameWindow.addElement(taxiStation);
//                        break;
//                    case 'l':
//                        placeReport("LuasStation", posX, posY);
//                        LuasStation luasStation = new LuasStation(posX, posY);
//                        gameWindow.addElement(luasStation);
//                        break;
//                    case 'a':
//                        placeReport("Car", posX, posY);
//                        Car car = new Car(posX, posY);
//                        gameWindow.addElement(car);
//                        break;
//                    case ' ':
//                        validGemCoordinates.add(posX);
//                        validGemCoordinates.add(posY);
//                        break;
//                    case 'x':
//                        placeReport("FakeBus", posX, posY);
//                        FakeBus fakeBus = new FakeBus(posX, posY);
//                        gameWindow.addElement(fakeBus);
//                        break;
//                    case 'y':
//                        placeReport("FakeLuas", posX, posY);
//                        FakeLuas fakeLuas = new FakeLuas(posX, posY);
//                        gameWindow.addElement(fakeLuas);
//                        break;
//                    case 'z':
//                        placeReport("FakeTaxi", posX, posY);
//                        FakeTaxi fakeTaxi = new FakeTaxi(posX, posY);
//                        gameWindow.addElement(fakeTaxi);
//                        break;
//                }
//
//            }
//        }
//        int maxRandomGems = 10;
//        // This loop goes through the list of cells that are empty and places gems until it reaches the maximum
//        Random random = new Random();
//        for (int i = 0; i < maxRandomGems; i++) {
//            int randomIndex = random.nextInt(validGemCoordinates.size() / 2);
//            int gemPosX = (int)validGemCoordinates.get(randomIndex * 2);
//            int gemPosY = (int)validGemCoordinates.get(randomIndex * 2 + 1);
//
//            placeReport("Random Gem", gemPosX, gemPosY);
//            Gem gem = new Gem(gemPosX, gemPosY);
//            gameWindow.addElement(gem);
//
//            validGemCoordinates.remove(randomIndex * 2 + 1);
//            validGemCoordinates.remove(randomIndex * 2);
//        }
//    }
    private void placeReport(String placeName, int posX , int posY) {
        // This reports the map import is working correctly
        System.out.println( placeName );
        System.out.println("has been placed in the position X: " + posX + ", Y: " + posY );


    }
    public void loadStartingScreen() {
        loadNextLevel();

    }
}
