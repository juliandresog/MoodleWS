/*
 * This file is NOT a part of Moodle - http://moodle.org/
 *
 * This client for Moodle 2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package restjsonmoodleclient;

import cloud.boos.constants.Config;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.beaconhillcott.moodlerest.MoodleCallRestWebService;
import net.beaconhillcott.moodlerest.MoodleCourse;
import net.beaconhillcott.moodlerest.MoodleCourseContent;
import net.beaconhillcott.moodlerest.MoodleModule;
import net.beaconhillcott.moodlerest.MoodleModuleContent;
import net.beaconhillcott.moodlerest.MoodleRestCourse;
import net.beaconhillcott.moodlerest.MoodleRestCourseException;
import net.beaconhillcott.moodlerest.MoodleRestException;

/**
 * REST MOODLE Client It's very basic. You'll have to write the JavaObject2POST
 * code.
 *
 * https://moodle.org/mod/forum/discuss.php?d=169650
 * https://docs.moodle.org/22/en/Web_services_FAQ#What_is_the_.27Access_control_exception.27_error
 * https://www.moodletips.com/web-service-access-control-exception/
 *
 * @author Jerome Mouneyrac jerome@moodle.com
 */
public class RestJsonMoodleClient {

    /**
     * Do a REST call to Moodle. Result are displayed in the console log.
     * https://sourceforge.net/projects/moodlerestjava/files/V0.1.5/
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ProtocolException, IOException {

        try {
            /// NEED TO BE CHANGED
//            String token = "dfb6582a32040bc0c3caec7295a93e7f";
//            String domainName = "http://local.boos.com.co/moodle2.5";

            /// REST RETURNED VALUES FORMAT
            String restformat = "json"; //Also possible in Moodle 2.2 and later: 'json'
            //Setting it to 'json' will fail all calls on earlier Moodle version
            if (restformat.equals("json")) {
                restformat = "&moodlewsrestformat=" + restformat;
            } else {
                restformat = "";
            }
//            
//            //JSONObject resultado = new JSONObject();
            System.out.println("CURSOS: "+getCursos(Config.domainName, Config.token, restformat)); 
//            System.out.println("*****************************************");
//            System.out.println("CONTENIDO CURSO: "+getContenidoCursoCursos(2, Config.domainName, Config.token, restformat)); 
            System.out.println("INFOSERV: "+getInfoServicio(Config.domainName, Config.token, restformat)); 
            
//            MoodleCallRestWebService.init(domainName+"/webservice/rest/server.php", token);
//            MoodleCallRestWebService.setDebug(false);
//            
//            MoodleRestCourse restCourse = new MoodleRestCourse();
//            MoodleCourse[] courses = restCourse.getAllCourses();
//            for (MoodleCourse course : courses) {
//                System.out.println("************************************");
//                //System.out.println("Course "+course.getId()+":"+course.getFullname());
//                printCourseDetails(course);
//            }
//            System.out.println("************************************");
//            System.out.println("************************************");
//            System.out.println("************************************");
//            
//            long courseId=2;
//            MoodleCourseContent[] courseContent = MoodleRestCourse.getCourseContent(courseId, null);
//            for (int i=0; i<courseContent.length; i++, System.out.println("*****************************"))
//                printCourseContent(courseContent[i]);

//        } catch (MoodleRestCourseException ex) {
//            Logger.getLogger(RestJsonMoodleClient.class.getName()).log(Level.SEVERE, null, ex);    
//        } catch (MoodleRestException ex) {
//            Logger.getLogger(RestJsonMoodleClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RestJsonMoodleClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(RestJsonMoodleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //------------//------------//------------//------------//------------//------------//------------//------------
    
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
    
    //------------//------------//------------//------------//------------//------------//------------//------------
    
    /**
     * 
     * @param moodleCourse 
     */
    private static void printCourseDetails(MoodleCourse moodleCourse) {
        System.out.println("id                    ="+moodleCourse.getId());
        System.out.println("shortname             ="+moodleCourse.getShortname());
        System.out.println("category              ="+moodleCourse.getCategoryId());
        System.out.println("categorysortorder     ="+moodleCourse.getCategorySortOrder());
        System.out.println("fullname              ="+moodleCourse.getFullname());
        System.out.println("idnumber              ="+moodleCourse.getIdNumber());
        System.out.println("summary               ="+moodleCourse.getSummary());
        System.out.println("summaryformat         ="+moodleCourse.getSummaryFormat());
        System.out.println("format                ="+moodleCourse.getFormat());
        System.out.println("showgrades            ="+moodleCourse.getShowGrades());
        System.out.println("newsitems             ="+moodleCourse.getNewsItems());
        System.out.println("startdate             ="+moodleCourse.getStartDate());
        System.out.println("numsections           ="+moodleCourse.getNumSections());
        System.out.println("maxbytes              ="+moodleCourse.getMaxBytes());
        System.out.println("showreports           ="+moodleCourse.getShowReports());
        System.out.println("visible               ="+moodleCourse.getVisible());
        System.out.println("hiddensections        ="+moodleCourse.getHiddenSections());
        System.out.println("groupmode             ="+moodleCourse.getGroupMode());
        System.out.println("groupmodeforce        ="+moodleCourse.getGroupModeForce());
        System.out.println("defaultgroupingid     ="+moodleCourse.getDefaultGroupingId());
        System.out.println("timecreated           ="+moodleCourse.getTimeCreated());
        System.out.println("timemodified          ="+moodleCourse.getTimeModified());
        System.out.println("enablecompletion      ="+moodleCourse.getEnableCompletion());
        System.out.println("completionstartonenrol="+moodleCourse.getCompletionStartOnEnrol());
        System.out.println("completionnotify      ="+moodleCourse.getCompletionNotify());
        System.out.println("lang                  ="+moodleCourse.getLang());
        System.out.println("forcetheme            ="+moodleCourse.getForceTheme());
    }
    
    /**
     * 
     * @param domainName
     * @param token
     * @param restformat
     * @return
     * @throws UnsupportedEncodingException
     * @throws ProtocolException
     * @throws IOException 
     */
    private static String getContenidoCursoCursos(Integer id, String domainName, String token, String restformat) throws UnsupportedEncodingException, ProtocolException, IOException {
        /// PARAMETERS - NEED TO BE CHANGED IF YOU CALL A DIFFERENT FUNCTION
        String functionName = "core_course_get_contents";
        String urlParameters = "courseid=" + id;//URLEncoder.encode("1", "UTF-8");
        
        return peticionHttp(domainName, token, restformat, functionName, urlParameters);
    }
    
    /**
     * 
     * @param domainName
     * @param token
     * @param restformat
     * @return
     * @throws UnsupportedEncodingException
     * @throws ProtocolException
     * @throws IOException 
     */
    private static String getCursos(String domainName, String token, String restformat) throws UnsupportedEncodingException, ProtocolException, IOException {
        /// PARAMETERS - NEED TO BE CHANGED IF YOU CALL A DIFFERENT FUNCTION
        String functionName = "moodle_course_get_courses";
        String urlParameters = "";//options[ids][0]=" + URLEncoder.encode("1", "UTF-8");
        
        return peticionHttp(domainName, token, restformat, functionName, urlParameters);
    }

    /**
     * Peticion para saber info en general del servicio
     * @param domainName
     * @param token
     * @param restformat
     * @return
     * @throws UnsupportedEncodingException
     * @throws ProtocolException
     * @throws IOException 
     */
    private static String getInfoServicio(String domainName, String token, String restformat) throws UnsupportedEncodingException, ProtocolException, IOException {
        /// PARAMETERS - NEED TO BE CHANGED IF YOU CALL A DIFFERENT FUNCTION
        String functionName = "moodle_webservice_get_siteinfo";
        String urlParameters = "serviceshortnames[0]=" + URLEncoder.encode("Talento", "UTF-8");

//        String functionName = "moodle_webservice_get_siteinfo";
//        String urlParameters =
//        "users[0][username]=" + URLEncoder.encode("testusername1", "UTF-8") +
//        "&users[0][password]=" + URLEncoder.encode("testpassword1", "UTF-8") +
//        "&users[0][firstname]=" + URLEncoder.encode("testfirstname1", "UTF-8") +
//        "&users[0][lastname]=" + URLEncoder.encode("testlastname1", "UTF-8") +
//        "&users[0][email]=" + URLEncoder.encode("testemail1@moodle.com", "UTF-8") +
//        "&users[0][auth]=" + URLEncoder.encode("manual", "UTF-8") +
//        "&users[0][idnumber]=" + URLEncoder.encode("testidnumber1", "UTF-8") +
//        "&users[0][lang]=" + URLEncoder.encode("en", "UTF-8") +
//        "&users[0][theme]=" + URLEncoder.encode("standard", "UTF-8") +
//        "&users[0][timezone]=" + URLEncoder.encode("-12.5", "UTF-8") +
//        "&users[0][mailformat]=" + URLEncoder.encode("0", "UTF-8") +
//        "&users[0][description]=" + URLEncoder.encode("Hello World!", "UTF-8") +
//        "&users[0][city]=" + URLEncoder.encode("testcity1", "UTF-8") +
//        "&users[0][country]=" + URLEncoder.encode("au", "UTF-8") +
//        "&users[0][preferences][0][type]=" + URLEncoder.encode("preference1", "UTF-8") +
//        "&users[0][preferences][0][value]=" + URLEncoder.encode("preferencevalue1", "UTF-8") +
//        "&users[0][preferences][1][type]=" + URLEncoder.encode("preference2", "UTF-8") +
//        "&users[0][preferences][1][value]=" + URLEncoder.encode("preferencevalue2", "UTF-8") +
//        "&users[1][username]=" + URLEncoder.encode("testusername2", "UTF-8") +
//        "&users[1][password]=" + URLEncoder.encode("testpassword2", "UTF-8") +
//        "&users[1][firstname]=" + URLEncoder.encode("testfirstname2", "UTF-8") +
//        "&users[1][lastname]=" + URLEncoder.encode("testlastname2", "UTF-8") +
//        "&users[1][email]=" + URLEncoder.encode("testemail2@moodle.com", "UTF-8") +
//        "&users[1][timezone]=" + URLEncoder.encode("Pacific/Port_Moresby", "UTF-8");
        
        return peticionHttp(domainName, token, restformat, functionName, urlParameters);
    }

    /**
     * Ejecuta peticion HTTP
     * @param domainName
     * @param token
     * @param restformat
     * @param functionName
     * @param urlParameters
     * @return
     * @throws MalformedURLException
     * @throws ProtocolException
     * @throws IOException 
     */
    private static String peticionHttp(String domainName, String token, String restformat, String functionName, String urlParameters) throws MalformedURLException, ProtocolException, IOException {
        /// REST CALL
        // Send request
        String serverurl = domainName + "/webservice/rest/server.php" + "?wstoken=" + token + "&wsfunction=" + functionName + restformat;
        HttpURLConnection con = (HttpURLConnection) new URL(serverurl).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        con.setRequestProperty("Content-Language", "en-US");
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setDoInput(true);
        DataOutputStream wr = new DataOutputStream(
                con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        //Get Response
        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append("\r\n");
        }
        rd.close();
        //System.out.println(response.toString());
        return response.toString();
    }
}
