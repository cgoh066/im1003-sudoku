package sudoku;
import java.util.*;
import java.lang.*;
 
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.NameList;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;

public class WelcomePage extends JFrame {
    
    private JPanel welcomePanel;
    private JLabel welcomeLabel;
    private static JTextField playerNameField;
    private JButton playButton;
    private JButton scoreboardButton;
    public static ArrayList<String>nameList = new ArrayList<String>();
    public static ArrayList<Integer>pointList = new ArrayList<Integer>();
    public static ArrayList<Integer>timeList = new ArrayList<Integer>();
    public static HashMap<String, Integer> scores = new HashMap<String, Integer>();
    private JPanel difficultyPanel;
    private JRadioButton beginnerRadioButton;
    private JRadioButton intermediateRadioButton;
    private JRadioButton expertRadioButton;
    private ButtonGroup btnGroup;
    private Image img;

    private final Color BG_COLOR= new Color(253, 253, 240);
    private final Color darkerColor = new Color(204, 0, 140);
    

    public WelcomePage() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        welcomePanel = new JPanel();
        welcomePanel.setBackground(BG_COLOR);
        welcomePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        welcomePanel.setLayout(new GridBagLayout());
        
        welcomeLabel = new JLabel("Welcome to Sudoku! Please enter your name:");
        welcomeLabel.setForeground(darkerColor);
        welcomeLabel.setPreferredSize(new Dimension(400, 30));
        playerNameField = new JTextField();
        playButton = new JButton("Start Game");
        playButton.setBorder(null);
        playButton.setOpaque(true);
        playButton.setBackground(darkerColor);
        playButton.setForeground(BG_COLOR);
        playButton.setPreferredSize(new Dimension(200,30));
        scoreboardButton = new JButton("Leaderboard");
        scoreboardButton.setPreferredSize(new Dimension(200,30));
        scoreboardButton.setBorder(null);
        scoreboardButton.setOpaque(true);
        scoreboardButton.setBackground(darkerColor);
        scoreboardButton.setForeground(BG_COLOR);

        displayRadioButton();
        populateScoreboard();
        scoreboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message="Leaderboard is not implemented yet.";
                if(nameList.size()!=0){
                    message = "Name\t\tScore\n";
                    // message = "Name\t\tScore\t\tTime\n";
                    // for (int i = 0; i < nameList.size(); i++) {
                    //     message += nameList.get(i) + "\t\t" + pointList.get(i) + "\t\t" + timeList.get(i) +"s\n";
                    // }   
                    // scores.forEach(
                    //     (key,value) -> message += key + value
                    // );

                    // Hashmap

                    message += loadMessage();
                }
                JOptionPane.showMessageDialog(null, message, "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1.0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
        
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.0;
        c.insets = new Insets(10,0,0,0);
        welcomePanel.add(welcomeLabel, c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.0;
        welcomePanel.add(playerNameField, c);
        
        c.gridx = 0;
        c.gridy = 4;
        c.weighty = 0.0;
        welcomePanel.add(playButton, c);
        
        c.gridx = 0;
        c.gridy = 5;
        c.weighty = 0.0;
        welcomePanel.add(scoreboardButton, c);
        
        add(welcomePanel);
        setVisible(true);
        pack();

        setLocationRelativeTo(null);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameField.getText();
                SudokuMain sudoku = new SudokuMain();
                WelcomePage.this.dispose(); // Close the welcome page
            }
        });
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static String loadMessage(){
        String message = "";
        Map<String, Integer> hm1 = sortByValue(scores);
        for (Map.Entry<String, Integer> en : hm1.entrySet()) {
            System.out.println(en.getKey() +
                          ", " + en.getValue());
            message += en.getKey() + "\t\t" + en.getValue() +"s\n";
        }
        return message;
    }

    private void populateScoreboard() {
        nameList.add("Clara");
        pointList.add(700);
        timeList.add(20);
        nameList.add("Calista");
        pointList.add(600);
        timeList.add(30);
        nameList.add("Rei");
        pointList.add(400);
        timeList.add(40);
        nameList.add("Kairi");
        pointList.add(300);
        timeList.add(50);

        scores.put("Clara", 700);
        scores.put("Calista", 600);
        scores.put("Rei", 400);
        scores.put("Kairi", 300);

        // scores.put("Jason", Jason);
        // scores.put("Jonathan", Jonathan);
        // scores.put("Stephanie", Stephanie);
        // scores.put("Rachel", Rachel);

        Map<String, Integer> hm1 = sortByValue(scores);
 
        // print the sorted hashmap
        // for (Map.Entry<String, Integer> en : hm1.entrySet()) {
        //     System.out.println("Key = " + en.getKey() +
        //                   ", Value = " + en.getValue());
        // }

        for (Map.Entry<String, Integer> en : hm1.entrySet()) {
            System.out.println(en.getKey() +
                          ", " + en.getValue());
        }

    }

    private void displayRadioButton() {
        btnGroup = new ButtonGroup();
        beginnerRadioButton = new JRadioButton("Beginner");
        beginnerRadioButton.setForeground(darkerColor);
        intermediateRadioButton = new JRadioButton("Intermediate");
        intermediateRadioButton.setForeground(darkerColor);
        expertRadioButton = new JRadioButton("Expert");
        expertRadioButton.setForeground(darkerColor);
        btnGroup.add(beginnerRadioButton);
        btnGroup.add(intermediateRadioButton);
        btnGroup.add(expertRadioButton);

        
        
       

        difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new FlowLayout());
        difficultyPanel.add(beginnerRadioButton);
        difficultyPanel.add(intermediateRadioButton);
        difficultyPanel.add(expertRadioButton);
        
        beginnerRadioButton.setSelected(true);
        difficultyPanel.setBackground(BG_COLOR);
    }

    public static void addToScoreboard(int points, int time){
        nameList.add(playerNameField.getText());
        pointList.add(points);
        timeList.add(time);

        scores.put(playerNameField.getText(), points);

    }
    
    public static void main(String[] args) {
        WelcomePage welcomePage = new WelcomePage();
    }
    

    
}