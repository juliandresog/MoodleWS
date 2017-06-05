/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloud.boos;

import cloud.boos.constants.Config;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.beaconhillcott.moodlerest.*;
/**
 *
 * @author josorio
 */
public class CourseContent {
    private static long courseId;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            MoodleCallRestWebService.init(Config.serviceUrl, Config.token);
            courseId=5;
            MoodleCourseContent[] courseContent = MoodleRestCourse.getCourseContent(courseId, null);
            for (int i=0; i<courseContent.length; i++, System.out.println("*****************************"))
                printCourseContent(courseContent[i]);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CourseContent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MoodleRestCourseException ex) {
            Logger.getLogger(CourseContent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MoodleRestException ex) {
            Logger.getLogger(CourseContent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void printCourseContent(MoodleCourseContent moodleCourseContent) {
        System.out.println("id      ="+moodleCourseContent.getId());
        System.out.println("name    ="+moodleCourseContent.getName());
        System.out.println("visible ="+moodleCourseContent.getVisible());
        System.out.println("summary ="+moodleCourseContent.getSummary());
        if (moodleCourseContent.getMoodleModules()!=null)
            for (int i=0; i<moodleCourseContent.getMoodleModules().length; i++)
                printCourseModules(moodleCourseContent.getMoodleModules()[i]);
    }

    private static void printCourseModules(MoodleModule moodleModule) {
        System.out.println("Module: id             ="+moodleModule.getId());
        System.out.println("Module: url            ="+moodleModule.getUrl());
        System.out.println("Module: name           ="+moodleModule.getName());
        System.out.println("Module: description    ="+moodleModule.getDescription());
        System.out.println("Module: visible        ="+moodleModule.getVisible());
        System.out.println("Module: modicon        ="+moodleModule.getModIcon());
        System.out.println("Module: modname        ="+moodleModule.getModName());
        System.out.println("Module: modplural      ="+moodleModule.getModPlural());
        System.out.println("Module: availablefrom  ="+moodleModule.getAvailableFrom());
        System.out.println("Module: availableuntil ="+moodleModule.getAvailableUntil());
        System.out.println("Module: indent         ="+moodleModule.getIndent());
        MoodleModuleContent[] content = moodleModule.getContent();
        if (content!=null)
            for (int i=0; i<content.length; i++)
                printModuleContent(content[i]);
    }

    private static void printModuleContent(MoodleModuleContent moodleModuleContent) {
        System.out.println("Content: author       ="+moodleModuleContent.getAuthor());
        System.out.println("Content: content      ="+moodleModuleContent.getContent());
        System.out.println("Content: filepath     ="+moodleModuleContent.getFilePath());
        System.out.println("Content: fileurl      ="+moodleModuleContent.getFileURL());
        System.out.println("Content: filename     ="+moodleModuleContent.getFilename());
        System.out.println("Content: license      ="+moodleModuleContent.getLicense());
        System.out.println("Content: type         ="+moodleModuleContent.getType());
        System.out.println("Content: filesize     ="+moodleModuleContent.getFileSize());
        System.out.println("Content: sortorder    ="+moodleModuleContent.getSortOrder());
        System.out.println("Content: timecreated  ="+moodleModuleContent.getTimeCreated());
        System.out.println("Content: timemodified ="+moodleModuleContent.getTimeModified());
        System.out.println("Content: userid       ="+moodleModuleContent.getUserId());
    }
}
