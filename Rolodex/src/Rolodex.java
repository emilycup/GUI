// Name:        Le, Emily
// Project:     2
// Due:         10/30/15
// Course:      CS-245-01-f15
//
// Description:
//      Implement a Rolodex class to display a contact file using tabs. The contact information is stored in the file name
//      contacts.txt
//
//      Each line consists of the following items:
//          last, first: email: photo.jpg
//

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Rolodex {
    private String[] name = new String[8];
    private String[] email = new String[8];
    private String[] photo = new String[8];
    JFrame frame = new JFrame("Rolodex");
    JTabbedPane tabbedPane;


    /**
     * constructor
     */
    public Rolodex() {
        tabbedPane = new JTabbedPane();
        frame.setSize(530, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // set application icon
        URL icon = null;
        try {
            icon = new URL("http://www.cpp.edu/~tvnguyen7/courses/cs245f15/projs/Rolodex-res/Rolodex.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(icon));

        // read .txt file to grab contact data
        readFile("src/Rolodex-res/contacts.txt");

        // creates and adds the menu to the frame
        JMenuBar menuBar = createMenu();

        // create tabbed pane
        tabbedPane = createContactTab(tabbedPane);
        JTabbedPane[] peopleTabs = createContactfromText(tabbedPane);

        // make frame visible
        frame.setJMenuBar(menuBar);
        frame.add(tabbedPane);
        for (JTabbedPane person : peopleTabs) {
            frame.add(person);
        }
        frame.setVisible(true);
    }


    /**
     * This creates the menu bar that appears at the top of the application.
     * The menubar will have the following menus: File, Tab, Layout
     *
     * @return
     */
    public JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();

        // File
        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        menuBar.add(file);

        // File -> Open
        JMenuItem open = new JMenuItem("Open");
        open.setEnabled(false);
        file.add(open);
        file.addSeparator();

        // File -> Exit
        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic('E');
        exit.addActionListener(e -> System.exit(0));
        file.add(exit);

        // Tabs
        JMenu tabs = new JMenu("Tabs");
        tabs.setMnemonic('T');
        menuBar.add(tabs);

        // Tabs -> Placement
        JMenu placement = new JMenu("Placement");
        placement.setMnemonic('P');

        // Tabs -> Placement -> Top
        JMenuItem top = new JMenuItem("Top");
        top.setMnemonic('T');
        placement.add(top);
        top.addActionListener(ae -> tabbedPane.setTabPlacement(JTabbedPane.TOP));

        // Tabs -> Placement -> right
        JMenuItem right = new JMenuItem("Right");
        right.setMnemonic('R');
        placement.add(right);
        right.addActionListener(ae -> tabbedPane.setTabPlacement(JTabbedPane.RIGHT));

        // Tabs -> Placement -> Bottom
        JMenuItem bottom = new JMenuItem("Bottom");
        bottom.setMnemonic('B');
        placement.add(bottom);
        bottom.addActionListener(ae -> tabbedPane.setTabPlacement(JTabbedPane.BOTTOM));

        // Tabs -> Placement -> Left
        JMenuItem left = new JMenuItem("Left");
        left.setMnemonic('L');
        placement.add(left);
        left.addActionListener(ae -> tabbedPane.setTabPlacement(JTabbedPane.LEFT));

        // Layout
        JMenu layout = new JMenu("Layout");
        layout.setMnemonic('L');

        // Layout -> Scroll
        JMenuItem scroll = new JMenuItem("Scroll");
        scroll.setMnemonic('S');
        layout.add(scroll);
        scroll.addActionListener(ae -> tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT));

        // Layout -> Wrap
        JMenuItem wrap = new JMenuItem("Wrap");
        wrap.setMnemonic('W');
        layout.add(wrap);
        wrap.addActionListener(ae -> tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT));

        // Defaults
        JMenuItem defaults = new JMenuItem("Defaults", 'D');
        defaults.setMnemonic('D');
        defaults.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        defaults.addActionListener(ae -> {
            tabbedPane.setTabPlacement(JTabbedPane.TOP);
            tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        });

        // completed defaults tab
        tabs.add(placement);
        tabs.add(layout);
        tabs.addSeparator();
        tabs.add(defaults);

        // Help
        JMenu help = new JMenu("Help");
        help.setMnemonic('H');
        menuBar.add(help);

        // Help -> About
        JMenuItem about = new JMenuItem("About");
        about.setMnemonic('A');
        help.add(about);

        about.addActionListener(ae -> {
            JOptionPane.showMessageDialog(null, "<html><i>App</i>&emsp;<b>Rolodex version 0.1</b></html>\n<html><i>Icon" +
                    "&emsp;</i><b>Copyright (c) 2015</b> <i>E.Le</i></html>", "", JOptionPane.PLAIN_MESSAGE);
        });

        return menuBar;
    }

    /**
     * Reads in a file to grab contact information
     *
     * @param fileName
     */
    public void readFile(String fileName) {
        String line = null;
        int fillIndex = 0;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] contactData = line.split(":");

                int ndx = 0;
                while (ndx < contactData.length) {
                    name[fillIndex] = contactData[ndx++];
                    email[fillIndex] = contactData[ndx++];
                    photo[fillIndex] = contactData[ndx++];
                }

                fillIndex++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method will create a contact tab with my personal information
     *
     * @return tabbedPane (my information stored in the tabbedPane)
     */
    public JTabbedPane createContactTab(JTabbedPane tabbedPane) {
        JPanel myContact = new JPanel(new GridLayout());
        JPanel verticalAlign = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));

        // add image info
        ImageIcon icon = new ImageIcon("src/Rolodex-res/nopic.jpg");
        myContact.add(new JLabel(icon));

        // add name info
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField("Emily Le", 15);
        verticalAlign.add(nameLabel);
        verticalAlign.add(nameField);

        // add email info
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField("eemly.le@gmail.com", 15);
        verticalAlign.add(emailLabel);
        verticalAlign.add(emailField);

        myContact.add(verticalAlign);

        tabbedPane.addTab("Le, Emily", myContact);

        return tabbedPane;
    }

    /**
     * This tab will create a series of tabs based on the content of the context.txt file
     *
     * @return tabbedPane2 (an array of tabbed panes that need to be added to the frame)
     */
    public JTabbedPane[] createContactfromText(JTabbedPane tabbedPane) {
        JTabbedPane[] tabbedPanelArray = new JTabbedPane[8];
        for (int i = 0; i < name.length; i++) {
            JPanel contactPanel = new JPanel(new GridLayout());
            JPanel verticalAlign = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));

            // adds image to jpanel
            ImageIcon icon = new ImageIcon("src/Rolodex-res/" + photo[i]);
            if (icon.getIconHeight() == -1) {
                icon = new ImageIcon("src/Rolodex-res/nopic.jpg");
            }
            contactPanel.add(new JLabel(icon));

            // adds label
            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField(name[i], 15);
            verticalAlign.add(nameLabel);
            verticalAlign.add(nameField);

            // adds email
            JLabel emailLabel = new JLabel("Email:");
            JTextField emailField = new JTextField(email[i], 15);
            verticalAlign.add(emailLabel);
            verticalAlign.add(emailField);

            contactPanel.add(verticalAlign);

            // creates other tabs for the tabbedPane
            tabbedPane.addTab(name[i], contactPanel);
            tabbedPanelArray[i] = tabbedPane;
        }
        return tabbedPanelArray;
    }

    /**
     * This is the main class that will run the program
     *
     * @param args
     */
    public static void main(String[] args) {

        // will allow this application's look and feel to be consistent across all platforms (I am using OSX)
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
s
        SwingUtilities.invokeLater(() ->
                new Rolodex());
    }
}
