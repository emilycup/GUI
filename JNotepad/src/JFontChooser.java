import javax.swing.*;
import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class JFontChooser extends JPanel
{
    DefaultListModel<String> fontListModel = new DefaultListModel<String>();
    DefaultListModel<String> fontStyleListModel = new DefaultListModel<String>();
    DefaultListModel<Integer> textSizeListModel = new DefaultListModel<Integer>();
    
    JList fontList;
    JList fontStyleList;
    JList fontSizeList;
    JScrollPane fontListScroll;
    JScrollPane fontStyleListScroll;
    JScrollPane fontSizeListScroll;
    
    
    // data for fonts, font styles, and sizes
    String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); //generates font list
    String[] fontStyles = {"Regular","Italic","Bold","Bold Italic"};
    int[] textSizes = new int[30];
    
    
    String fontName;
    int fontStyle;
    int fontSize;
    
   Object[] options = {"Ok","Cancel"};
   
   public JFontChooser()
   {
        this.setSize(400,300);
        this.setLayout(new FlowLayout());

        // filling font, style, and size models
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); //generates font list
        for (String font : fonts) {
            fontListModel.addElement(font);
        }
        
        for (String style: fontStyles) {
            fontStyleListModel.addElement(style);
        }
        
        int val = 5;
        for (int i = 0; i < textSizes.length; i++) {
            textSizes[i] = val++;
            textSizeListModel.addElement(textSizes[i]);
        }
        
        // fills Jlist with content from the DefaultListModel
        fontList = new JList(fontListModel);
        fontStyleList = new JList(fontStyleListModel);
        fontSizeList = new JList(textSizeListModel);
        
        // adding scroll panes
        fontListScroll = new JScrollPane(fontList);
        fontStyleListScroll = new JScrollPane(fontStyleList);
        fontSizeListScroll = new JScrollPane(fontSizeList);
        
        fontList.setSelectedIndex(0);
        fontStyleList.setSelectedIndex(0);
        fontSizeList.setSelectedIndex(0);

        // labels for the scroll panes
        JLabel leftLabel = new JLabel("Font:");
        leftLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel middleLabel = new JLabel("Font Style:");
        middleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel rightLabel = new JLabel("Size");
        rightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // left panel
        JPanel leftPanel = new JPanel();
        leftPanel.add(leftLabel);
        leftPanel.add(fontListScroll);
        BoxLayout boxLayoutLeft = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
        leftPanel.setLayout(boxLayoutLeft);
        
        // middle panel
        JPanel middlePanel = new JPanel();
        middlePanel.add(middleLabel);
        middlePanel.add(fontStyleListScroll);
        BoxLayout boxLayoutMiddle = new BoxLayout(middlePanel, BoxLayout.Y_AXIS);
        middlePanel.setLayout(boxLayoutMiddle);
        
        // right panel
        JPanel rightPanel = new JPanel();
        rightPanel.add(rightLabel);
        rightPanel.add(fontSizeListScroll);
        BoxLayout boxLayoutRight = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
        rightPanel.setLayout(boxLayoutRight);       
        
        fontList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged (ListSelectionEvent le) {
                int idx = fontList.getSelectedIndex();
                
                if (idx != 1)
                {
                    fontName = fonts[idx];
//                    text.setFont(new Font(fontName,fontStyle,fontSize));
                }
            }
         });
        
        fontStyleList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged (ListSelectionEvent le) {
                int idx = fontStyleList.getSelectedIndex();
                
                if (idx != 1)
                {
                    switch(fontStyles[idx]){
                    case "Regular" : fontStyle = Font.PLAIN;
                       break;
                    case "Italic" : fontStyle = Font.ITALIC;
                       break;
                    case "Bold" : fontStyle = Font.BOLD;
                       break;
                    case "Bold Italic" : fontStyle = (Font.BOLD + Font.ITALIC);
                       break;
                    }                    
                }
            }
         });
        
        fontSizeList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged (ListSelectionEvent le) {
                int idx = fontSizeList.getSelectedIndex();
                
                if (idx != 1)
                {
                    fontSize = textSizes[idx];
                }
            }
         });
        
        // adding everything to the frame
        this.add(leftPanel);
        this.add(middlePanel);
        this.add(rightPanel);
        this.setVisible(true);
   }
   
    public void setFontName(String name)
   {
      fontName = name;
      fontList.setSelectedValue(fontName, true);
   }
   
   public void setFontStyle(Integer style)
   {
      fontStyle = style;
      switch(style)
      {
      case Font.PLAIN : fontStyleList.setSelectedIndex(0);
         break;
      case Font.BOLD : fontStyleList.setSelectedIndex(1);
         break;
      case Font.ITALIC : fontStyleList.setSelectedIndex(2);
         break;
      default : fontStyleList.setSelectedIndex(3);
      }
//      text.setFont(new Font(fontName,fontStyle,fontSize));
   }
   
   public void setTextSize(Integer size)
   {
       System.out.println("size:" + size);
      fontSize = size;
      System.out.println(fontSize);
      fontSizeList.setSelectedIndex(size - 5);
   }
   
   public String getSelectedFontName()
   {
      return fontName;
   }
   
   public int getSelectedFontStyle()
   {
      return fontStyle;
   }
   
   public int getSelectedFontSize()
   {
      return fontSize;
   }


   public boolean showDialog(JFrame frame)
   {
      int result = JOptionPane.showOptionDialog(frame,this,"Font"
            ,JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
      if (result == 0)
         return true;
      else
         return false;
   }
}