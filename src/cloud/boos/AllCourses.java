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
public class AllCourses {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            MoodleCallRestWebService.init(Config.serviceUrl, Config.token);
            MoodleCallRestWebService.setLegacy(true);
            MoodleCourse[] allCourses = MoodleRestCourse.getAllCourses();
            for (int i = 0; i < allCourses.length; i++, System.out.println("************************************")) {
                printCourseDetails(allCourses[i]);
            }
        } catch (MoodleRestException ex) {
            Logger.getLogger(AllCourses.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AllCourses.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void printCourseDetails(MoodleCourse moodleCourse) {
        System.out.println("id                    =" + moodleCourse.getId());
        System.out.println("shortname             =" + moodleCourse.getShortname());
        System.out.println("category              =" + moodleCourse.getCategoryId());
        System.out.println("categorysortorder     =" + moodleCourse.getCategorySortOrder());
        System.out.println("fullname              =" + moodleCourse.getFullname());
        System.out.println("idnumber              =" + moodleCourse.getIdNumber());
        System.out.println("summary               =" + moodleCourse.getSummary());
        System.out.println("summaryformat         =" + moodleCourse.getSummaryFormat());
        System.out.println("format                =" + moodleCourse.getFormat());
        System.out.println("showgrades            =" + moodleCourse.getShowGrades());
        System.out.println("newsitems             =" + moodleCourse.getNewsItems());
        System.out.println("startdate             =" + moodleCourse.getStartDate());
        System.out.println("numsections           =" + moodleCourse.getNumSections());
        System.out.println("maxbytes              =" + moodleCourse.getMaxBytes());
        System.out.println("showreports           =" + moodleCourse.getShowReports());
        System.out.println("visible               =" + moodleCourse.getVisible());
        System.out.println("hiddensections        =" + moodleCourse.getHiddenSections());
        System.out.println("groupmode             =" + moodleCourse.getGroupMode());
        System.out.println("groupmodeforce        =" + moodleCourse.getGroupModeForce());
        System.out.println("defaultgroupingid     =" + moodleCourse.getDefaultGroupingId());
        System.out.println("timecreated           =" + moodleCourse.getTimeCreated());
        System.out.println("timemodified          =" + moodleCourse.getTimeModified());
        System.out.println("enablecompletion      =" + moodleCourse.getEnableCompletion());
        System.out.println("completionstartonenrol=" + moodleCourse.getCompletionStartOnEnrol());
        System.out.println("completionnotify      =" + moodleCourse.getCompletionNotify());
        System.out.println("lang                  =" + moodleCourse.getLang());
        System.out.println("forcetheme            =" + moodleCourse.getForceTheme());
    }
}
