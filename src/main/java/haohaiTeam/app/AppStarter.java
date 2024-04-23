package haohaiTeam.app;
import haohaiTeam.game.GameStarter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppStarter {

    public static void main(String[] args) {
        // Create an instance of the AppStarter class and run it
        System.out.println("App is starting...");
        startApp();
    }

    public static void startApp() {
        // Create a frame
        JFrame frame = new JFrame("App Starter");

        // Create buttons
        JButton runGameButton = new JButton("Run Game");
        JButton aboutButton = new JButton("About");
        JButton howToPlayButton = new JButton("How to Play");

        // Create a text area to display information
        JTextArea infoTextArea = new JTextArea();
        infoTextArea.setEditable(false); // Make it non-editable
        JScrollPane scrollPane = new JScrollPane(infoTextArea);

        // Load initial text from a text file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/haohaiTeam/app/initial_text.txt"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            infoTextArea.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            infoTextArea.setText("Failed to load initial text.");
        }
    
        // Add ActionListener to the "Run Game" button
        runGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Run the main method of the GameStarter class
                GameStarter.main(new String[]{});
            }
        });
    
        // Add ActionListener to the "About" button
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display "About" information
                infoTextArea.setText("This is the about page.");
            }
        });
    
        // Add ActionListener to the "How to Play" button
        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display "How to Play" information
                infoTextArea.setText("This is the how to play page.");
            }
        });
    
        // Create a panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        buttonPanel.add(runGameButton);
        buttonPanel.add(aboutButton);
        buttonPanel.add(howToPlayButton);
    
        frame.getContentPane().add(buttonPanel, BorderLayout.WEST);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
}
}
