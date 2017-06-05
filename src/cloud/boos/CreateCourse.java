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
import net.beaconhillcott.moodlerest.*;

/**
 *
 * @author josorio
 */
public class CreateCourse {
    private static MoodleCourse[] courses;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            MoodleCallRestWebService.init(Config.serviceUrl, Config.token);
            courses=new MoodleCourse[2];
            courses[0]=new MoodleCourse("TC1","Test Course 1",1);
            courses[1]=new MoodleCourse("TC2","Test Course 2",1);
            courses=MoodleRestCourse.createCourses(courses);
            System.out.println("Course: "+courses[0].getShortname()+" has been set an id of "+courses[0].getId());
            System.out.println("Course: "+courses[1].getShortname()+" has been set an id of "+courses[1].getId());
        } catch (MoodleRestException ex) {
            Logger.getLogger(CreateCourse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CreateCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
