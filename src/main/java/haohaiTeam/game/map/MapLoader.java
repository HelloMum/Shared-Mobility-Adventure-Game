package haohaiTeam.game.map;


import haohaiTeam.game.element.transport.onRoute.*;
import haohaiTeam.game.element.transport.onRoute.auto.Bus;
import haohaiTeam.game.element.transport.onRoute.auto.Luas;
import haohaiTeam.game.element.transport.onRoute.auto.Taxi;
import haohaiTeam.game.element.transport.onRoute.faketrans.FakeBus;
import haohaiTeam.game.element.transport.onRoute.faketrans.FakeLuas;
import haohaiTeam.game.element.transport.onRoute.faketrans.FakeTaxi;
import haohaiTeam.game.element.transport.onRoute.stationRoad.*;
import haohaiTeam.game.gui.GameWindow;
import haohaiTeam.game.element.*;
import haohaiTeam.game.element.transport.*;

import java.util.Collections;
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
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < validGemCoordinates.size() / 2; i++) {indices.add(i);}
        Collections.shuffle(indices);
        int maxRandomGems = 45;
        int minRandomGems = 15;
        int randomGemCount = random.nextInt(maxRandomGems - minRandomGems + 1) + minRandomGems;
        System.out.println("Random gems produced: " + randomGemCount);
        for (int i = 0; i < randomGemCount; i++) {
            int index = indices.get(i);
            int gemPosX = (int) validGemCoordinates.get(index * 2);
            int gemPosY = (int) validGemCoordinates.get(index * 2 + 1);
            GameWindow.addElement(new Gem(gemPosX, gemPosY));
        }

    }

    private void placeReport(String placeName, int posX , int posY) {
        // This reports the map import is working correctly
        System.out.println( placeName );
        System.out.println("has been placed in the position X: " + posX + ", Y: " + posY );


    }
    public void loadStartingScreen() {
        loadNextLevel();

    }
}
