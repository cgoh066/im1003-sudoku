package sudoku;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;

import javax.swing.*;

import java.util.TimerTask;


/**
 * The main Sudoku program
 */
public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L; // to prevent serial warning

    // private variables
    GameBoardPanel board = new GameBoardPanel();
    JMenuBar MenuBar = new JMenuBar(); // the menu-bar
    JMenu SubMenu;
    JMenuItem MenuItem;
    JPanel SubPanel = new JPanel();
    WelcomePage WelcomePage = new WelcomePage(); // welcome page

    // Constructor
    public SudokuMain() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setJMenuBar(MenuBar);

        cp.add(board, BorderLayout.CENTER);
        cp.add(SubPanel, BorderLayout.NORTH); // position of restart

        JButton btnRestart = new JButton("Restart");
        SubPanel.add(btnRestart);
        btnRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String filepath = "Button.wav";
                SoundEffects btnSoundEffects = new SoundEffects(); // need this line and line below to play sound
                btnSoundEffects.playSound(filepath);
            	ImageIcon warnIcon = new ImageIcon("warn1.gif");
                
                Object[] options = { "Yes", "No" };
                JLabel restart = new JLabel();
                restart.setText("Start a new game? \nAll the progress of current game will not be saved!");
                restart.setFont(new Font("Arial", Font.BOLD, 20));

                int n = JOptionPane.showOptionDialog(null, restart, "Warning!", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, warnIcon, options, options[0]); // "warning is title of dialog box
                
                              
                if (n == JOptionPane.YES_OPTION) {
                	btnSoundEffects.playSound(filepath); //so that button sound plays for yes
                    board.Reset();
                } else {
                	btnSoundEffects.playSound(filepath); // button sound for no
                    setDefaultCloseOperation(JOptionPane.CANCEL_OPTION);
                }
                
            }
        });

        // File Menu
        JMenu Menu = new JMenu("File"); // File option at the top
        MenuBar.add(Menu); // adds file to the menu bar
        MenuItem = new JMenuItem("New Game");
        Menu.add(MenuItem); // the menu adds this item
        MenuItem.addActionListener(new ActionListener() { // when new game is clicked, actionperformed method in action listener is executed
            @Override
            public void actionPerformed(ActionEvent e) {
            	String filepath = "Button.wav";
                SoundEffects btnSoundEffects = new SoundEffects(); // need this line and line below to play sound
                btnSoundEffects.playSound(filepath);
                Object[] levels = { "Easy", "Moderate", "Advanced", "Expert" };
                String difficulty = (String) JOptionPane.showInputDialog(null,
                        "Current game progress will be lost! \nSelect Game Mode:", "New Game", JOptionPane.PLAIN_MESSAGE,
                        null, levels, levels[0]);
                // If a string was returned, restart game to difficulty chosen.
                if ((difficulty != null) && (difficulty.length() > 0)) {
                    if (difficulty == levels[0]) {
                        board.init();
                    }
                    if (difficulty == levels[1]) {
                        board.Moderate();
                    }
                    if (difficulty == levels[2]) {
                        board.Advanced();
                    }
                    if (difficulty == levels[3]) {
                        board.Expert();
                    }
                    return;
                }
            }
        });

        Menu.addSeparator(); // adds a horizontal line btwn the options (IN FILE/NEWGAME ETC)

        MenuItem = new JMenuItem("Exit");
        Menu.add(MenuItem); // the menu adds this item
        MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filepath = "Button.wav";
                SoundEffects btnSoundEffects = new SoundEffects();
                btnSoundEffects.playSound(filepath);
                ImageIcon quesIcon = new ImageIcon("lose1.gif");
                Object[] options = { "See you soon", "Not yet" };
                int n = JOptionPane.showOptionDialog(null, "Exiting Game!\nBye!", "SEE YOU AGAIN!",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, quesIcon, options, options[0]);
                if (n == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                	btnSoundEffects.playSound(filepath);
                    setDefaultCloseOperation(JOptionPane.CANCEL_OPTION);
                }
            }
        });

        Menu = new JMenu("Theme");
        MenuBar.add(Menu);

        MenuItem = new JMenuItem("Light Mode");
        Menu.add(MenuItem);
        MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.lightcolor();
                String filepath = "Button.wav";
                SoundEffects btnSoundEffects = new SoundEffects();
                btnSoundEffects.playSound(filepath);
            }
        });

        MenuItem = new JMenuItem("Dark Mode");
        Menu.add(MenuItem);
        MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.darkcolor();
                String filepath = "Button.wav";
                SoundEffects btnSoundEffects = new SoundEffects();
                btnSoundEffects.playSound(filepath);
            }
        });

        Menu = new JMenu("Hints");
        MenuBar.add(Menu);

        MenuItem = new JMenuItem("Reveal All");
        Menu.add(MenuItem);
        MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.RevealAll();
                ImageIcon LoseIcon = new ImageIcon("lose2.gif");
                JOptionPane.showMessageDialog(null, "Try Again!", null, JOptionPane.INFORMATION_MESSAGE,
                        LoseIcon);
                String filepath = "wrong.wav";
                SoundEffects btnSoundEffects = new SoundEffects();
                btnSoundEffects.playSound(filepath);
            }
        });

        MenuItem = new JMenuItem("Reveal One");
        Menu.add(MenuItem);
        MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.RevealOne();
                String filepath = "Button.wav";
                SoundEffects btnSoundEffects = new SoundEffects();
                btnSoundEffects.playSound(filepath);
            }

        });

        // menu for Help
        Menu = new JMenu("Help");
        MenuBar.add(Menu);
        MenuItem = new JMenuItem("How to Play");
        Menu.add(MenuItem);
        MenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String filepath = "Button.wav";
                SoundEffects btnSoundEffects = new SoundEffects(); // need this line and line below to play sound
                btnSoundEffects.playSound(filepath);
                ImageIcon quesIcon = new ImageIcon("howtoplay1.jpg");
                JOptionPane.showMessageDialog(null, "A 9×9 square must be filled in with numbers from 1-9 with no repeated numbers in each line, horizontally or vertically. \nThe 3×3 squares marked out in the grid, and each of these squares can't have any repeat numbers either.",
                        null, JOptionPane.INFORMATION_MESSAGE, quesIcon);
            }
        });

        Menu.add(MenuItem);
        board.init(); // intialise the game board (or reset)

        pack(); // Pack the UI components, instead of using setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to handle window-closing
        setTitle("Sudoku");
        setVisible(true); // need this or the gameboard will not show

        // BGM
        String filepath = "BGM.wav";
        BGM soundtrack = new BGM();
        soundtrack.playmusic(filepath);
    }

    /** The entry main() entry method */
    public static void main(String[] args) {
        // [TODO 1] Check "Swing program template" on how to run
        // the constructor of "SudokuMain"
        // Run GUI codes in Event-Dispatching thread for thread-safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuMain(); // Let the constructor do the job
            }
        });
    }
}
