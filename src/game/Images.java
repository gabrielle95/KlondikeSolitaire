package game;

import gui.*;
import javax.swing.ImageIcon;

/**
 * Class assigning card images.
 */
public class Images {

	/**
	 * @param path of the resource
     * @return data from previous turn
     */
	public ImageIcon setImage(String path) {
        java.net.URL imgURL = Game.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Image not found: " + path);
            return null;
        }
	}

}