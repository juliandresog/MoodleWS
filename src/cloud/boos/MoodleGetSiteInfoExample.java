/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloud.boos;

import cloud.boos.constants.Config;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.beaconhillcott.moodlerest.*;
/**
 *
 * @author josorio
 */
public class MoodleGetSiteInfoExample {
    /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    try {
      MoodleCallRestWebService.init(Config.serviceUrl, Config.token);
      MoodleCallRestWebService.setLegacy(true);
      MoodleWebService siteInfo = MoodleRestWebService.getSiteInfo();
      printSiteInfo(siteInfo);
    } catch (MoodleRestWebServiceException ex) {
      Logger.getLogger(MoodleGetSiteInfoExample.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MoodleRestException ex) {
      Logger.getLogger(MoodleGetSiteInfoExample.class.getName()).log(Level.SEVERE, null, ex);
    }
      
  }

  private static void printSiteInfo(MoodleWebService siteInfo) {
    System.out.println("sitename      ="+siteInfo.getSiteName());
    System.out.println("username      ="+siteInfo.getUserName());
    System.out.println("firstname     ="+siteInfo.getFirstName());
    System.out.println("lastname      ="+siteInfo.getLastName());
    System.out.println("userid        ="+siteInfo.getUserId());
    System.out.println("siteurl       ="+siteInfo.getSiteURL());
    System.out.println("userpictureurl="+siteInfo.getUserPictureURL());
    System.out.println("downloadfiles ="+siteInfo.canDownloadFiles());
    ArrayList<Function> functions = siteInfo.getFunctions();
    for (int i=0; i<functions.size(); i++)
      System.out.println("Functions: "+functions.get(i).getName()+" Version: "+functions.get(i).getVersion());
    
  }
}
