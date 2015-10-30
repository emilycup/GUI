//
// Name:        Le, Emily
// Project:     2
// Due:         10/30/15
// Course:      CS-245-01-f15
//
// Description:
//      This is a font selector that allows user to select a font from the right list. When a font is chosen, the
//      font is deleted from the first list and added to the second.
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FontSelect {

    public FontSelect() {
        JList fontList;
        JList secondList;
        JButton addButton;
        JButton printButton;
        JScrollPane leftScroll; //left scrollpane
        JScrollPane rightScroll; //right scrollpane
        JFrame frame;
        JPanel buttonPanel;
        DefaultListModel<String> fontListModel = new DefaultListModel<String>();
        DefaultListModel<String> secondListModel = new DefaultListModel<String>();

        // frame
        frame = new JFrame("Font Select");
        frame.setSize(600, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // adding lists (font family list)
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); //generates font list
        for (String font : fonts) {
            fontListModel.addElement(font);
        }

        // fills Jlist with content from the DefaultListModel
        fontList = new JList<String>(fontListModel);
        //fontList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        secondList = new JList<String>(secondListModel);

        // adding list to scrollPanel
        leftScroll = new JScrollPane(fontList);
        rightScroll = new JScrollPane(secondList);

        // adding titles of scroll panes
        JLabel leftLabel = new JLabel("System Fonts");
        leftLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel rightLabel = new JLabel("Selected");
        rightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button Panel
        addButton = new JButton("Add >>");
        addButton.setEnabled(false);
        addButton.setMnemonic('A');
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        printButton = new JButton("Print");
        printButton.setEnabled(true);
        printButton.setMnemonic('P');
        printButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(printButton);
        BoxLayout boxLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(boxLayout);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.add(leftLabel);
        leftPanel.add(leftScroll);
        BoxLayout boxLayoutLeft = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
        leftPanel.setLayout(boxLayoutLeft);

        // Right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.add(rightLabel);
        rightPanel.add(rightScroll);
        BoxLayout boxLayoutRight = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
        rightPanel.setLayout(boxLayoutRight);

        // font list selection listener
        fontList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent le) {
                // if item is selected, enable it
                if (fontList.getSelectedValuesList().size() > 0)
                    addButton.setEnabled(true);
                else
                    addButton.setEnabled(false);
            }
        });

        // addButton Listener
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int index = fontList.getSelectedIndex();
                secondListModel.addElement(fontListModel.getElementAt(index));
                fontListModel.removeElement(fontListModel.getElementAt(index));
            }
        });

        // printButton Listener
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                for (int i = 0; i < secondListModel.size(); i++) {
                    System.out.println(secondListModel.getElementAt(i));
                }
            }
        });

        // adding everything to the frame
        frame.add(leftPanel);
        frame.add(buttonPanel);
        frame.add(rightPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        // Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FontSelect();
            }
        });
    }
}
