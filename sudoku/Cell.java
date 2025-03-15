package sudoku;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.*;
import javax.swing.BorderFactory;

/**
 * The Cell class model the cells of the Sudoku puzzle, by customizing (subclass)
 * the javax.swing.JTextField to include row/column, puzzle number and status.
 */
public class Cell extends JTextField {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   // Define named constants for JTextField's colors and fonts
   //  to be chosen based on CellStatus
   // Light mode colours
   public static final Color BG_GIVEN0 = new Color(255, 192, 203); // filled cells pastel pink
   public static final Color FG_GIVEN0 = new Color (32, 32, 32); // filled text black
   public static final Color FG_NOT_GIVEN0 = new Color(128, 128, 128); // entered text grey
   public static final Color BG_TO_GUESS0 = new Color(173, 216, 230); // empty cells pastel blue
   public static final Color BG_CORRECT_GUESS0 = new Color(0, 204, 102); // correct entry green
   public static final Color BG_WRONG_GUESS0 = new Color(255, 100, 100); // wrong entry pastel red
   	
   // Dark mode colours
   public static final Color BG_GIVEN1 = new Color(9, 9, 9); // filled cells black
   public static final Color FG_GIVEN1 = new Color(211, 211, 211); // filled text off-white
   public static final Color FG_NOT_GIVEN1 = new Color(169, 169, 169); // entered text 
   public static final Color BG_TO_GUESS1 = new Color(44, 58, 71); // empty cells dark blue-grey
   public static final Color BG_CORRECT_GUESS1 = new Color(23, 84, 67); // correct entry dark green
   public static final Color BG_WRONG_GUESS1 = new Color(153,0,0); // wrong entry dark red
   
   public static final Font FONT_NUMBERS = new Font("Times New Roman", Font.BOLD, 28);

   // Border and grid colours 
   Border rowupGrid = BorderFactory.createMatteBorder(0,0,5,0,new Color(112,128,144)); //slate grey
   Border rowdownGrid = BorderFactory.createMatteBorder(5,0,0,0, new Color(112,128,144));
   Border colleftGrid = BorderFactory.createMatteBorder(0,0,0,5, new Color(112,128,144));
   Border colrightGrid = BorderFactory.createMatteBorder(0,5,0,0, new Color(112,128,144));
   Border downright = BorderFactory.createMatteBorder(0,0,5,5, new Color(112,128,144)); 
   Border upright = BorderFactory.createMatteBorder(5,0,0,5, new Color(112,128,144));
   Border upleft = BorderFactory.createMatteBorder(5,5,0,0, new Color(112,128,144));
   Border downleft = BorderFactory.createMatteBorder(0,5,5,0, new Color(112,128,144));
   Border inner = BorderFactory.createMatteBorder(3,3,3,3, Color.WHITE);
   
   // Define properties (package-visible)
   /** The row and column number [0-8] of this cell */
   int row, col;
   /** The puzzle number [1-9] for this cell */
   int number;
   /** The status of this cell defined in enum CellStatus */
   CellStatus status;
   
   boolean color = false; // false = light mode, true = dark mode

   /** Constructor */
   public Cell(int row, int col) {
      super();   // JTextField
      this.row = row;
      this.col = col;
      // Inherited from JTextField: Beautify all the cells once for all
      super.setHorizontalAlignment(JTextField.CENTER);
      super.setFont(FONT_NUMBERS);
   }

   /** Reset this cell for a new game, given the puzzle number and isGiven */
   public void init(int number, boolean isGiven) {
      this.number = number;
      status = isGiven ? CellStatus.GIVEN : CellStatus.TO_GUESS;
      paint();    // paint itself
   }

   /** This Cell (JTextField) paints itself based on its status */
   public void paint() {
	   	super.setBorder(inner);
	   	/** Sets the colour for the outside borders and inner grid */
	   	if ((row == 2 && col == 2) || (row == 2 && col == 5) || (row == 5 && col == 2) || (row == 5 && col == 5) || (row == 8 && col == 2) || (row == 8 && col ==5) || (row == 8 && col == 8) || (row == 2 && col == 8) || (row == 5 && col == 8)) {
	   		super.setBorder(BorderFactory.createCompoundBorder(downright ,inner));
	   	}
	   	if((row == 0 && col == 2 || row == 0 && col == 5) || (row == 0 && col == 8)) { 
	   		super.setBorder(BorderFactory.createCompoundBorder(upright ,inner));
	   	}   	
	   	if(row == 0 && col == 0) {
	   		super.setBorder(BorderFactory.createCompoundBorder(upleft ,inner));
	   	}   	
	   	if((row == 8 && col == 0) || (row == 2 && col == 0) || (row == 5 && col == 0)) {
	   		super.setBorder(BorderFactory.createCompoundBorder(downleft ,inner));
	   	}
	   	if ((row == 8 && col == 1) || (row == 8 && col == 3)  || (row == 8 && col == 4) || (row == 8 && col == 6) || (row == 8 && col == 7) || (row == 2 && col == 1) || (row == 2 && col == 3)  || (row == 2 && col == 4) || (row == 2 && col == 6) || (row == 2 && col == 7) || (row == 5 && col == 1) || (row == 5 && col == 3) ||  (row == 5 && col == 4) || (row == 5 && col == 6) || (row == 5 && col == 7)) {
	   		super.setBorder(BorderFactory.createCompoundBorder(rowupGrid ,inner));
	   	}
	   	if ((row == 1 && col == 2) || (row == 3 && col == 2) || (row == 4 && col == 2) || (row == 6 && col == 2) || (row == 7 && col == 2) || (row == 1 && col == 5) || (row == 3 && col == 5) || (row == 4 && col == 5) || (row == 6 && col == 5) || (row == 7 && col == 5) || (row == 1 && col == 8) || (row == 3 && col == 8) || (row == 4 && col == 8) || (row == 6 && col == 8) || (row == 7 && col == 8)) {
	   		super.setBorder(BorderFactory.createCompoundBorder(colleftGrid ,inner));
	   	}
	   	if ((row == 0 && col == 1) || (row == 0 && col == 3)  || (row == 0 && col == 4) || (row == 0 && col == 6) || (row == 0 && col == 7)) {
	   		super.setBorder(BorderFactory.createCompoundBorder(rowdownGrid ,inner));
	   	}
	   	if ((row == 1 && col == 0) || (row == 3 && col == 0)  || (row == 4 && col == 0) || (row == 6 && col == 0) || (row == 7 && col == 0)) {
	   		super.setBorder(BorderFactory.createCompoundBorder(colrightGrid ,inner));
	   	} 
	  
      if (status == CellStatus.GIVEN) {
         // Inherited from JTextField: Set display properties
         if (color == true) {
        	 super.setText(number + "");
             super.setEditable(false);
             super.setBackground(BG_GIVEN1);
             super.setForeground(FG_GIVEN1);
         } else {
        	 super.setText(number + "");
             super.setEditable(false);
             super.setBackground(BG_GIVEN0);
             super.setForeground(FG_GIVEN0);
         }
      } else if (status == CellStatus.TO_GUESS) {
         // Inherited from JTextField: Set display properties
         if (color == true) {
        	 super.setText("");
        	 super.setEditable(true);
             super.setBackground(BG_TO_GUESS1);
             super.setForeground(FG_NOT_GIVEN1);
         } else {
        	 super.setText("");
        	 super.setEditable(true);
             super.setBackground(BG_TO_GUESS0);
             super.setForeground(FG_NOT_GIVEN0);
         }
      } else if (status == CellStatus.CORRECT_GUESS) {  // from TO_GUESS
    	  if (color == true) {
    		  super.setEditable(true);
    	      super.setBackground(BG_CORRECT_GUESS1);
    	  } else {
    		  super.setEditable(true);
    	      super.setBackground(BG_CORRECT_GUESS0);
    	  }
      } else if (status == CellStatus.WRONG_GUESS) {    // from TO_GUESS
    	  if (color == true) {
    	      super.setBackground(BG_WRONG_GUESS1);
    	  } else {
    	      super.setBackground(BG_WRONG_GUESS0);
    	  }
      }
   }
}