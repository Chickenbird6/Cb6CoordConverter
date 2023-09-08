import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * A class used to make JFrame easier to use
 * @version 1.2
 * @since 4-3-23
 */

public class Cb6JFrameUtil
{
    /**
     * Makes creating a JFrame object more simple.
     * @param name The text to display at the top of the window.
     * @param width The width of the JFrame.
     * @param height The height of the JFrame.
     * @param xPos The x position of the JFrame.
     * @param yPos The y position of the JFrame.
     * @param layout The layout type to use.
     * @return The new window object.
     */
    public static JFrame makeJFrame(
        String name, int width, int height, int xPos, int yPos, LayoutManager layout) {
        // catch illegal arguments
        if (width < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter width");
        }
        if (height < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter length");
        }
        if (xPos < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter xPos");
        }
        if (yPos < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter yPos");
        }
       
        // make JFrame object
        JFrame frame = new JFrame(name);
            
        // set frame size
        frame.setSize(width, height);
            
        // set location
        frame.setLocation(xPos, yPos);
            
        // set layout
        frame.setLayout(layout);
            
        // make JFrame close correctly
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }
    
    /**
     * Makes creating a JButton object more simple.
     * @param buttonText The text to display on the button.
     * @param width The x size of the button.
     * @param height The y size of the button.
     * @param xPos The x position of the button on the frame.
     * @param yPos The y position of the button on the frame.
     * @return The new JButton object.
     */
    public static JButton makeJButton(
        String buttonText, int width, int height, int xPos, int yPos) {
        if (width < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter width");
        }
        if (height < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter height");
        }
        if (xPos < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter xPos");
        }
        if (yPos < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter yPos");
        }
        
        // Make the new JButton object
        JButton jb = new JButton(buttonText);
        
        // Set button position
        jb.setLocation(xPos, yPos);
            
        // Set button size
        jb.setSize(width, height);
        
        // return the finished JButton
        return jb;
    }
        
    /**
     * Makes creating a JTextField object more simple.
     * @param text The text to put in the box.
     * @param xPos The x position of the text box.
     * @param yPos The y position of the text box.
     * @param canEdit Whether the text box can be edited.
     * @return The new JTextField object.
     */
    public static JTextField makeJTextField(
        String text, int xPos, int yPos, boolean canEdit) {
        if (xPos < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter xPos");
        }
        if (yPos < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter yPos");
        }
        
        // make the JTextField object
        JTextField textField = new JTextField();
        
        // set the text in the box
        textField.setText(text);
        
        // set the size of the text field
        textField.setSize(textField.getPreferredSize());
        
        // set the location of the text field
        textField.setLocation(xPos, yPos);
        
        // set if the box can be edited
        textField.setEditable(canEdit);
        
        return textField;
    }
    
    /**
     * Makes creating a JLabel image object more simple.
     * @param filePath The filepath to the JLabel image to make.
     * @param width The width of the JLabel image.
     * @param height The height of the JLabel image.
     * @param xPos The x position of the JLabel image.
     * @param yPos The y position of the JLabel image
     * @return The new JLabel image.
     */
    public static JLabel makeJLabelImage(
        String filePath, int width, int height, int xPos, int yPos) {
        if (width < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter width");
        }
        if (height < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter height");
        }
        if (xPos < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter xPos");
        }
        if (yPos < 0) {
            throw new IllegalArgumentException("Invalid argument for parameter yPos");
        }

        try {
            // make an empty BufferedImage
            BufferedImage img = null;
            // set the BufferedImage to the input image
            img = ImageIO.read(new File(filePath));
            // scale the BufferedImage
            Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // make the BufferedImage an ImageIcon
            ImageIcon ii = new ImageIcon(dimg);

            // make the JLabel with the image
            JLabel jl = new JLabel(ii);

            // set the position and size of the JLabel
            jl.setBounds(xPos, yPos, width, height);

            return jl;
        }
        catch (IOException e) {
        }
        return null;
    }
    
    public static void snapRight(JComponent snapper, JComponent anchor) {
        snapper.setLocation(anchor.getX() + anchor.getWidth(), anchor.getY());
    }
    
    public static void snapDown(JComponent snapper, JComponent anchor) {
        snapper.setLocation(anchor.getX(), anchor.getY() + anchor.getHeight());
    }
}
