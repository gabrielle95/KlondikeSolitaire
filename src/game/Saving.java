package game;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * Class taking care of saving and loading the game.
 */
public class Saving {

    /**
     * @param file file to be loaded
     * @return loaded data
     */
	public static Data loadGame(File file){

        try{
            FileInputStream fi = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream oi = new ObjectInputStream(fi);

            Object data = oi.readObject();
            if( !(data instanceof Data)){
            	oi.close();
                fi.close();
                return null;
            }

            oi.close();
            fi.close();
            return (Data) data;
        } catch (FileNotFoundException e) {
            return null;
        } catch (EOFException e) {

        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
        return null;
    }
	/**
     * @param data to be saved
     * @param file save file to be created
     */
   public static void saveGame(Data data, File file) {
        try{
            FileOutputStream f = new FileOutputStream(file.getAbsolutePath());
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(data);
            o.close();
            f.close();
        } catch (IOException ex) {
            //chyba
        }
    }
}
