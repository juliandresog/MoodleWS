/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloud.boos;

import cloud.boos.constants.Config;
import java.io.UnsupportedEncodingException;
import java.util.logging.*;
import net.beaconhillcott.moodlerest.*;

/**
 *
 * @author josorio
 */
public class MoodleGetUsersByIdExample {
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    MoodleCallRestWebService.init(Config.serviceUrl, Config.token);
    try {
      Long[] users=new Long[1];
      users[0]=5l;
//      users[1]=2l;
//      users[2]=3l;
//      users[3]=4l;
//      users[4]=5l;
      MoodleUser[] user=MoodleRestUser.getUsersById(users);
      for (int i=0; i<users.length; i++, System.out.println("***************************************************"))
        printUser(user[i]);
    } catch (MoodleRestException ex) {
      Logger.getLogger(MoodleGetUsersByIdExample.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedEncodingException ex) {
      Logger.getLogger(MoodleGetUsersByIdExample.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public static void printUser(MoodleUser user) {
    System.out.println("User ID                = "+user.getId());
    System.out.println("Username               = "+user.getUsername());
    System.out.println("Firstname              = "+user.getFirstname());
    System.out.println("Lastname               = "+user.getLastname());
    System.out.println("Email                  = "+user.getEmail());
    System.out.println("Address                = "+user.getAddress());
    System.out.println("Phone 1                = "+user.getPhone1());
    System.out.println("Phone 2                = "+user.getPhone2());
    System.out.println("ICQ                    = "+user.getICQ());
    System.out.println("Skype                  = "+user.getSkype());
    System.out.println("Yahoo                  = "+user.getYahoo());
    System.out.println("MSN                    = "+user.getMSN());
    System.out.println("Department             = "+user.getDepartment());
    System.out.println("Institution            = "+user.getInstitution());
    System.out.println("Interests              = "+user.getInterests());
    System.out.println("First access           = "+user.getFirstAccess());
    System.out.println("Last access            = "+user.getLastAccess());
    System.out.println("Auth                   = "+user.getAuth());
    System.out.println("Confirmed              = "+user.getConfirmed());
    System.out.println("Id number              = "+user.getIdNumber());
    System.out.println("Lang                   = "+user.getLang());
    System.out.println("Theme                  = "+user.getTheme());
    System.out.println("Timezone               = "+user.getTimezone());
    System.out.println("Mail format            = "+user.getMailFormat());
    System.out.println("Description            = "+user.getDescription());
//    System.out.println("Description format     = "+user.getDescriptionFormat());
    System.out.println("City                   = "+user.getCity());
    System.out.println("URL                    = "+user.getURL());
    System.out.println("Country                = "+user.getCountry());
    System.out.println("Profile image URL small= "+user.getProfileImageURLSmall());
    System.out.println("Profile image URL      = "+user.getProfileImageURL());
    if (user.getCustomFields()!=null && !user.getCustomFields().isEmpty()) {
      for (int i=0; i<user.getCustomFields().size(); i++)
        System.out.println("Custom field: type="+user.getCustomFields().get(i).getType()+" value="+user.getCustomFields().get(i).getValue()+" name="+user.getCustomFields().get(i).getName()+" shortname="+user.getCustomFields().get(i).getShortname());
    }
    if (user.getPreference()!=null && !user.getPreference().isEmpty()) {
      for (int i=0; i<user.getPreference().size(); i++)
        System.out.println("Preference: "+user.getPreference().get(i).getType()+"="+user.getPreference().get(i).getValue());
    }
    if (user.getEnrolledCourses()!=null && !user.getEnrolledCourses().isEmpty()) {
      for (int i=0; i<user.getEnrolledCourses().size(); i++)
        System.out.println("Enrolled courses: id="+user.getEnrolledCourses().get(i).getId()+" fullname="+user.getEnrolledCourses().get(i).getFullName()+" shortname="+user.getEnrolledCourses().get(i).getShortName());
    }
  }
}
