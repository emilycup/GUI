//
// Name:        Le, Emily
// Homework:    3
// Due:         11/6/15
// Course:      CS-245-01-f15
//
// Description:
//      Creates a menu that mimics that of the Notepad Program
//

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class NotepadMenu {

    JLabel label;

    /**
     * constructor
     */
    public NotepadMenu() {
        JFrame frame = new JFrame("Untitled - Notepad");
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // label under the menu bar
        label = new JLabel("Select a menu");

        // make menu object
        JMenuBar menuBar = new JMenuBar();

        // File
        JMenu jmFile = new JMenu("File");
        jmFile.setMnemonic('F');
        JMenuItem fileItemNew = new JMenuItem("New", 'N');
        fileItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        jmFile.add(fileItemNew);

        // Edit
        JMenu jmEdit = new JMenu("Edit");
        jmEdit.setMnemonic('E');

        // Edit - > undo
        JMenuItem editItemUndo = new JMenuItem("Undo", 'Z');
        editItemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        editItemUndo.setMnemonic('U');

        // Edit -> cut
        JMenuItem editItemCut = new JMenuItem("Cut", 'X');
        editItemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        editItemCut.setMnemonic('t');

        // Edit -> clock
        JMenu jmClock = new JMenu("Clock");
        jmClock.setMnemonic('k');

        // Edit -> clock -> date
        JMenuItem clockItemDate = new JMenuItem("Date");
        clockItemDate.setMnemonic('D');

        // Edit -> clock -> time
        JMenuItem clockItemTime = new JMenuItem("Time", 'T');
        clockItemTime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        clockItemTime.setMnemonic('T');

        // add action listener to display current time
        clockItemTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // display date
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a zzz");
                label.setText("The time is " + dateFormat.format(date));
            }
        });

        // adding clock submenu items
        jmClock.add(clockItemDate);
        jmClock.add(clockItemTime);

        // adding edit submenu to edit
        jmEdit.add(editItemUndo);
        jmEdit.addSeparator();
        jmEdit.add(editItemCut);
        jmEdit.addSeparator();
        jmEdit.add(jmClock);

        // Format
        JMenu jmformat = new JMenu("Format");
        jmformat.setMnemonic('o');


        // View
        JMenu jmView = new JMenu("View");
        jmView.setMnemonic('V');


        // Help
        JMenu jmHelp = new JMenu("Help");
        jmHelp.setMnemonic('H');


        // add menus to main menu bar
        menuBar.add(jmFile);
        menuBar.add(jmEdit);
        menuBar.add(jmView);
        menuBar.add(jmHelp);

        // setting the menu bar to the frame
        frame.setJMenuBar(menuBar);

        frame.add(label);


        frame.setVisible(true);
    }

    /**
     * the main class that runs the program
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NotepadMenu();
            }
        });
    }
}
