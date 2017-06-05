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
public class MoodleCreateUserExample {
    /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    try {
      MoodleCallRestWebService.init(Config.serviceUrl, Config.token);
      // The minimum required parameters to create a user. Modify the object "user" to add extra details.
      MoodleUser user=new MoodleUser("jaimito.al", "passwordA1!", "Jaimito", "Alimaña", "fred.alim@bloggs.com");
      MoodleUser createdUser = MoodleRestUser.createUser(user);
      printCreatedUserDetails(createdUser);
    } catch (MoodleRestUserException ex) {
      Logger.getLogger(MoodleCreateUserExample.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MoodleRestException ex) {
      Logger.getLogger(MoodleCreateUserExample.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }

  private static void printCreatedUserDetails(MoodleUser createdUser) {
    System.out.println("id        ="+createdUser.getId());
    System.out.println("username  ="+createdUser.getUsername());
  }
}
