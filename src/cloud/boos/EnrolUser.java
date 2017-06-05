/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloud.boos;

import cloud.boos.constants.Config;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.beaconhillcott.moodlerest.MoodleCallRestWebService;
import net.beaconhillcott.moodlerest.MoodleRestException;
import net.beaconhillcott.moodlerest.MoodleRestUserEnrolment;
import net.beaconhillcott.moodlerest.MoodleRestUserEnrolmentException;
import net.beaconhillcott.moodlerest.MoodleUserEnrolment;
import net.beaconhillcott.moodlerest.Role;

/**
 *
 * @author josorio
 */
public class EnrolUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MoodleCallRestWebService.init(Config.serviceUrl, Config.token);
        MoodleCallRestWebService.setDebug(false); 
        try {
            MoodleUserEnrolment[] users = new MoodleUserEnrolment[1];
            MoodleUserEnrolment student = new MoodleUserEnrolment();
            student.setCourseId(5l);
            student.setUserId(6l);
            student.setRoleId(Role.STUDENT.toLongValue().intValue());
            student.setTimeStart(null);
            student.setTimeStart(null);
            student.setSuspend(null); 
            users[0] = student;
            
            //Enrolo el usuario al curso
            MoodleRestUserEnrolment.enrolUsers(users);
        } catch (MoodleRestUserEnrolmentException ex){
            Logger.getLogger(MoodleGetUsersByIdExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MoodleRestException ex) {
            Logger.getLogger(MoodleGetUsersByIdExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
