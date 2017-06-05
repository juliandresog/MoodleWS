/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloud.boos;

import cloud.boos.constants.Config;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.beaconhillcott.moodlerest.*;
/**
 *
 * @author josorio
 */
public class SendMessage {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            MoodleCallRestWebService.init(Config.serviceUrl, Config.token);
            MoodleRestMessage.sendInstantMessage(new MoodleMessage(2l, "A message to the administrator!"));
        } catch (MoodleRestMessageException ex) {
            Logger.getLogger(SendMessage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MoodleRestException ex) {
            Logger.getLogger(SendMessage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
