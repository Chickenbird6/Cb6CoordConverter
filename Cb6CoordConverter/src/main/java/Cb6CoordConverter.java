import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 * Helps convert minecraft coordinates
 */
public class Cb6CoordConverter
{

    private static boolean isOw = true;
    private static int[] owCoords = new int[2];
    private static int[] nCoords = new int[2];
    private static int[] cleanCoords = new int[2];
    
    private static final String PLACEHOLDER = "Waiting for coords...";
    private static final String baritonePrefix = ".b ";
    
    // Make Buttons ########################################################
    private static JButton calcButton = Cb6JFrameUtil.makeJButton("Calculate", 0, 0, 0, 0);
    private static JButton dimToggle = Cb6JFrameUtil.makeJButton("Overworld", 0, 0, 0, 0);
        
    // Declare TextFields ##########################################
    // Make the text box prompt for the input
    private static final JTextField inputMsg = Cb6JFrameUtil.makeJTextField(
            "Enter coords to convert:", 0, 0, false);
    // make the text box labeling overworld coords
    private static final JTextField owcMsg = Cb6JFrameUtil.makeJTextField(
            "Overworld Coords:", 0, 0, false);
    // make the text box that labels the nether coords
    private static final JTextField ncMsg = Cb6JFrameUtil.makeJTextField(
            "Nether Coords:", 0, 0, false);
    // overworld goto message
    private static final JTextField owGotoMsg = Cb6JFrameUtil.makeJTextField(
            "Overworld Baritone Command: ", 0, 0, false);
    // nether goto message
    private static final JTextField nGotoMsg = Cb6JFrameUtil.makeJTextField(
            "Nether Baritone Command: ", 0, 0, false);
    // overworld tp message
    private static final JTextField owTpMsg = Cb6JFrameUtil.makeJTextField(
            "Overworld /tp Command: ", 0, 0, false);
    // nether tp message
    private static final JTextField nTpMsg = Cb6JFrameUtil.makeJTextField(
            "Nether /tp Command: ", 0, 0, false);
    
    // make the output text boxes
    // make the text box that displays the overworld coords
    // make the text box that accepts input
    private static JTextField inputBox = Cb6JFrameUtil.makeJTextField(
            "", 0, 0, true);
    private static JTextField owc = Cb6JFrameUtil.makeJTextField(
            PLACEHOLDER, 0, 0, false);
    // make the text box that displays the nether coords
    private static JTextField nc = Cb6JFrameUtil.makeJTextField(
            PLACEHOLDER, 0, 0, false);
    // make a text box that displays the baritone goto command for the overworld coords
    private static JTextField owGoto = Cb6JFrameUtil.makeJTextField(
            PLACEHOLDER, 0, 0, false);
    // make a text box that displays the baritone /tp command for the overworld coords
    private static JTextField owTp = Cb6JFrameUtil.makeJTextField(
            PLACEHOLDER, 0, 0, false);
    // make a text box that displays the baritone goto command for the nether coords
    private static JTextField nGoto = Cb6JFrameUtil.makeJTextField(
            PLACEHOLDER, 0, 0, false);
    // make a text box that displays the baritone /tp command for the nether coords
    private static JTextField nTp = Cb6JFrameUtil.makeJTextField(
            PLACEHOLDER, 0, 0, false);
    
    public static void main(String[] args)
    {
        final int defaultFrameWidth = 400;
        
        // Make the JFrame #####################################################
        // make the JFrame object
        JFrame f = Cb6JFrameUtil.makeJFrame("Cb6 Coord Converter", defaultFrameWidth, 225, 1, 1, null);
        
        // set the Icon
        ImageIcon ii = new ImageIcon("icon.png");
        f.setIconImage(ii.getImage());
        
        // set Resizable
        f.setResizable(false);
        
        // Set Component sizes ################################################
        // Set first row
        inputMsg.setSize(inputMsg.getPreferredSize());
        f.add(calcButton);
        calcButton.setSize((int) calcButton.getPreferredSize().getWidth(), (int) inputBox.getPreferredSize().getHeight());
        
        // Set second row
        owcMsg.setSize(owcMsg.getPreferredSize());
        inputBox.setSize(50, (int) inputBox.getPreferredSize().getHeight());
        
        // Set third row
        ncMsg.setSize(ncMsg.getPreferredSize());
        nc.setSize(nc.getPreferredSize());
        
        // set fourth row
        owGotoMsg.setSize(owGotoMsg.getPreferredSize());
        owGoto.setSize(owGoto.getPreferredSize());
        
        // set eighth row
        f.add(dimToggle);
        dimToggle.setSize((int) dimToggle.getPreferredSize().getWidth(), calcButton.getHeight());
        
        // Set Component Locations ############################################
        // Set first row
        Cb6JFrameUtil.snapRight(inputBox, inputMsg);
        Cb6JFrameUtil.snapRight(calcButton, inputBox);
        
        // Set second row
        Cb6JFrameUtil.snapDown(owcMsg, inputMsg);
        Cb6JFrameUtil.snapRight(owc, owcMsg);
        Cb6JFrameUtil.snapRight(owGoto, owc);
        
        // Set third row
        Cb6JFrameUtil.snapDown(ncMsg, owcMsg);
        Cb6JFrameUtil.snapRight(nc, ncMsg);
        Cb6JFrameUtil.snapRight(nGoto, nc);
        
        // set fourth row
        Cb6JFrameUtil.snapDown(owGotoMsg, ncMsg);
        Cb6JFrameUtil.snapRight(owGoto, owGotoMsg);
        
        // set fifth row
        Cb6JFrameUtil.snapDown(nGotoMsg, owGotoMsg);
        Cb6JFrameUtil.snapRight(nGoto, nGotoMsg);
        
        // set sixth row
        Cb6JFrameUtil.snapDown(owTpMsg, nGotoMsg);
        Cb6JFrameUtil.snapRight(owTp, owTpMsg);
        
        // set seventh row
        Cb6JFrameUtil.snapDown(nTpMsg, owTpMsg);
        Cb6JFrameUtil.snapRight(nTp, nTpMsg);
        
        // set eighth row
        Cb6JFrameUtil.snapDown(dimToggle, nTpMsg);
        
        
        // Add Listeners #######################################################
        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshDisplay();
            }
        });
        
        dimToggle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Cb6CoordConverter.isOw) {
                Cb6CoordConverter.isOw = false;
                dimToggle.setText("Nether");
                dimToggle.setSize((int) dimToggle.getPreferredSize().getWidth(), calcButton.getHeight());
            }
            else {
                Cb6CoordConverter.isOw = true;
                dimToggle.setText("Overworld");
                dimToggle.setSize((int) dimToggle.getPreferredSize().getWidth(), calcButton.getHeight());
            }
            refreshDisplay();
            }
        });
        
        inputBox.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateSize();
            }
            
            public void insertUpdate(DocumentEvent e) {
                updateSize();
            }

            public void removeUpdate(DocumentEvent e) {
                updateSize();
            }
            
            public void updateSize() {
                // width of the combined
                double rowLength = inputMsg.getPreferredSize().getWidth() + inputBox.getPreferredSize().getWidth() + calcButton.getWidth();
                if (inputBox.getPreferredSize().getWidth() > 50) {
                    // resize the inputBox to the size of the entered text
                    inputBox.setSize(inputBox.getPreferredSize());
                    calcButton.setLocation(inputBox.getX() + inputBox.getWidth(), inputBox.getY());
                }
                else if (inputBox.getPreferredSize().getWidth() < 50) {
                    inputBox.setSize(50, inputBox.getHeight());
                    calcButton.setLocation(inputBox.getX() + inputBox.getWidth(), inputBox.getY());
                }
                if (rowLength > defaultFrameWidth) {
                    f.setSize((int) rowLength, f.getHeight());
                }
                else {
                    f.setSize(defaultFrameWidth, f.getHeight());
                }
            }
        });
        
        
        // add frame elements
        f.add(inputMsg);
        f.add(inputBox);
        f.add(calcButton);
        f.add(owcMsg);
        f.add(owc);
        f.add(nc);
        f.add(ncMsg);
        f.add(owGoto);
        f.add(owTp);
        f.add(nGoto);
        f.add(nTp);
        f.add(owGotoMsg);
        f.add(nGotoMsg);
        f.add(nTpMsg);
        f.add(owTpMsg);
        f.add(dimToggle);
        
        f.setVisible(true);
    }
    
    public static void refreshDisplay() {
            // deep copy overworldCoords array
                Cb6CoordConverter.cleanCoords[0] = Cb6MCCoordsUtil.cleanCoords(inputBox.getText())[0];
                Cb6CoordConverter.cleanCoords[1] = Cb6MCCoordsUtil.cleanCoords(inputBox.getText())[1];
                
                if (Cb6CoordConverter.isOw) {
                    
                    owCoords[0] = Cb6MCCoordsUtil.cleanCoords(inputBox.getText())[0];
                    owCoords[1] = Cb6MCCoordsUtil.cleanCoords(inputBox.getText())[1];
                    
                    // deep copy nether coords array
                    nCoords[0] = Cb6MCCoordsUtil.getNetherCoords(owCoords)[0];
                    Cb6CoordConverter.nCoords[1] = Cb6MCCoordsUtil.getNetherCoords(owCoords)[1];
                }
                else {
                    nCoords[0] = cleanCoords[0];
                    nCoords[1] = cleanCoords[1];
                    
                    owCoords[0] = Cb6MCCoordsUtil.getOverworldCoords(Cb6CoordConverter.nCoords)[0];
                    owCoords[1] = Cb6MCCoordsUtil.getOverworldCoords(Cb6CoordConverter.nCoords)[1];
                }
                
                // deep copy nether coords array
                nCoords[0] = Cb6MCCoordsUtil.getNetherCoords(Cb6CoordConverter.owCoords)[0];
                nCoords[1] = Cb6MCCoordsUtil.getNetherCoords(owCoords)[1];
                
                // set the coords
                owc.setText(owCoords[0] + " " + owCoords[1]);
                nc.setText(nCoords[0] + " " + nCoords[1]);
                
                owGoto.setText(baritonePrefix + "goto " + owCoords[0] + " " + owCoords[1]);
                owTp.setText("/tp @p " + owCoords[0] + " ~ " + owCoords[1]);
                
                nGoto.setText(baritonePrefix + "goto " + nCoords[0] + " " + nCoords[1]);
                nTp.setText("/tp @p " + nCoords[0] + " ~ " + nCoords[1]);
                
                // resize the coord boxes
                owc.setSize(owc.getPreferredSize());
                nc.setSize(nc.getPreferredSize());
                owGoto.setSize(owGoto.getPreferredSize());
                nGoto.setSize(nGoto.getPreferredSize());
                owTp.setSize(owTp.getPreferredSize());
                nTp.setSize(nTp.getPreferredSize());
                
                Cb6JFrameUtil.snapRight(owGoto, owGotoMsg);
                Cb6JFrameUtil.snapRight(nGoto, nGotoMsg);
                Cb6JFrameUtil.snapRight(owTp, owTpMsg);
                Cb6JFrameUtil.snapRight(nTp, nTpMsg);
    }
}
