package tr.qa;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * To build this project, install the Maven M2E plugin for eclipse and import 
 * this project as an existing Maven project.
 *
 * @author Jon Austen May 2013
 *
 */

public class Main {

//    static String buildNumberFileName = "buildVerBamboo.txt";
    static String buildNumber = "1";
    static String jenkinsHostPort = "c962stnapp.intqa.thomsonreuters.com:8080";
    //    static String targetHostPort = "http://c962stnapp.intqa.thomsonreuters.com:6500";
//    static String testHostPort = "http://c962stnapp.intqa.thomsonreuters.com:6500";
    static URL url,rep_URL;
    static int failures = 0;
    static int unstable = 0;
    static int isRunning = 0;
    static int successful = 0;
    static String suiteTitle = "Jenkins Test Suite Report for UI Automation QA jobs";
    static StringBuilder emailContent = new StringBuilder();
    static StringBuilder errorFooter = new StringBuilder();
//    static String releaseNumber = "DETC-R120";

    public static void main(String[] args) throws Exception {
//        Arrays.stream(args).forEach(System.out::println);
        String environment = args[0];
        System.out.println("environment: "+environment);
        //buildNumber = getLatestBuildNumberFromBamboo(releaseNumber);
        buildNumber = getLatestBuildNumber();
        //getTarget();
        buildEmailContent(environment);
//        sendEmail();
    }

//    public static void getTarget() {
//        if (System.getProperty("HOSTPORT") == null) {
//            System.out.println("ERROR reading Maven HOSTPORT arg.  Need to pass HOSTPORT arg property to Maven job.");
//        } else {
//            System.out.println("Maven property HOSTPORT=" + System.getProperty("HOSTPORT"));
////            targetHostPort = System.getProperty("HOSTPORT");
//        }
//        System.out.println("Build number is: " + buildNumber);
//
//    }

    private static void sendEmail() {
        // Recipient's email ID needs to be mentioned.
        String toPrudvi = "prudvinathreddy.mulinti@thomsonreuters.com";
//        String tonaveen = "naveen.pothireddy@thomsonreuters.com";

        // Sender's email ID needs to be mentioned
        String from = "qa-noreply@thomsonreuters.com";

        // Assuming you are sending email from localhost
        String host = "relay.int.westgroup.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth:", "true");
        properties.setProperty("mai.smtp.starttls.enable", "false");
        properties.setProperty("mail.smtp.port", "25");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toPrudvi));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toroop));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(tonaveen));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(tosangeeta));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(tosandy));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(tosrinath));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(tomatt));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toqa));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toqa2));
            // Set Subject: header field
            message.setSubject(suiteTitle);

            // Now set the actual message
            //message.setText( emailContent );
            Multipart multipart = new MimeMultipart("alternative");

            MimeBodyPart htmlPart = new MimeBodyPart();
            // String htmlContent = "<html><h1>" + suiteTitle + "</h1><p>" + emailContent.toString() + "</p></html>";
            String htmlContent = "<html><p>" + emailContent.toString() + "</p></html>";
            htmlPart.setContent(htmlContent, "text/html");

            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            errorFooter.append("Email Error:\n" + mex.getMessage() + "<br/><br/>");
        }

    }

    //needs an annotated suppress warning here but I don't know which one it needs
    private static String getLatestBuildNumberFromBamboo(String release) {
        // more info:  http://dom4j.sourceforge.net/dom4j-1.6.1/apidocs/org/dom4j/Attribute.html
        String returnVal = "0";
        try {
            url = new URL("http://bamboo.corp.ositax.com:8085/rest/api/latest/result/" + release + "/latest");
            System.out.println("getLatestBuildNumberFromBamboo with against url: " + url.toString());
            Document dom = new SAXReader().read(url);
            Element el = dom.getRootElement();
            returnVal = el.attribute("number").getValue();
            System.out.println("Latest version found:" + returnVal);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (DocumentException e2) {
            e2.printStackTrace();
        }
        return returnVal;
    }

    //needs an annotated suppress warning here but I don't know which one it needs
    private static String getLatestBuildNumberFromTarget(String release) {
        // more info:  http://dom4j.sourceforge.net/dom4j-1.6.1/apidocs/org/dom4j/Attribute.html
        String returnVal2 = "0";
        try {
            url = new URL("http://pdxsasqa149.corp.ositax.com:6500/sabrix/versioninfo");
            System.out.println("getLatestBuildNumberFromTarget with against url: " + url.toString());
            Document dom = new SAXReader().read(url);
            Element el = dom.getRootElement();
            returnVal2 = el.attribute("<RUNNING_VERSION>").getValue();
            System.out.println("Deployed version: " + returnVal2);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (DocumentException e2) {
            e2.printStackTrace();
        }
        return returnVal2;
    }


    //gets build number from target system
//    //needs an annotated suppress warning here for the XML request but I don't know which one it needs
//    private static void buildEmailContent_old() {
//        emailContent.append("Release number: " + releaseNumber + "<br/>");
//        emailContent.append("Build number: " + getLatestBuildNumberFromBamboo(releaseNumber) + "<br/>");
////        emailContent.append("Test target: http://" + testHostPort + "<br/>");
//        emailContent.append("Aggregate report: http://http://c962stnapp.intqa.thomsonreuters.com:8080 <br/>--------------------------<br/>");
//
//        myloop:
//        for (DefinedTests dt : DefinedTests.values()) {
//            System.out.print("Status \"" + dt.toString() + "\": ");
//            emailContent.append("Status \"" + dt.toString() + "\": ");
//            try {
//                url = new URL("http://" + jenkinsHostPort + "/job/" + dt.toString() + "/lastBuild/api/xml");
//                System.out.println("buildNumberFileHandleing status against url: " + url.toString());
//            } catch (MalformedURLException e1) {
//                e1.printStackTrace();
//            }
//
//            try {
//                Document dom = new SAXReader().read(url);
//                @SuppressWarnings("unchecked")
//                List<Element> les = dom.getRootElement().elements(); //TODO deprecated way of doing this?
//                for (Element el : les) {
//
//                    // if job is currently running print status to report
//                    if (el.getName() == "building") {
//                        if (el.getText() == "true") {
//                            System.out.println("Found a running job.");
//                            isRunning += 1;
//                            emailContent.append("<b>RUNNING</b>");
//                            break myloop; //break out of for loop
//                        }
//                    }
//
//                    // get result status from XML
//                    //System.out.println( "Test name: " + el.getName() );
//                    if (el.getName() == "result") {
//                        System.out.println(el.getText());
//                        if (el.getText().contains("FAILURE")) {
//                            failures += 1;
//                            emailContent.append("<b><font color='red'>" + el.getText() + "</font></b>");
//                        } else if (el.getText().contains("UNSTABLE")) {
//                            unstable += 1;
//                            emailContent.append("<b><font color='SteelBlue'>" + el.getText() + "</font></b>");
//                        } else if (el.getText().contains("SUCCESS")) {
//                            emailContent.append("<b><font color='green'>" + el.getText() + "</font></b>");
//                        }
//                    }
//
//                }
//
//            } catch (DocumentException e) {
//                System.out.println("No status. There was an error reading status.");
//                emailContent.append("<font color='red'>No status. There was an error reading status.</font>");
//                errorFooter.append("Test Error:\n" + e.getMessage() + "<br/><br/>");
//            }
//
//            emailContent.append("<br/>");
//
//        }
//
//        emailContent.append("<br/><br/>SUMMARY:<br/>");
//        emailContent.append("<h3>Failures: " + failures + "</h3>");
//        emailContent.append("<h3>Running Jobs: " + isRunning + "</h3>");
//        emailContent.append("<h3>Unstable jobs: " + unstable + "</h3>");
//        emailContent.append("<br/><br/>ERRORS:<br/><br>");
//        emailContent.append("<i>" + errorFooter + "</i><br/><br/>");
//        emailContent.append("-----------------------------------<br/><i>NOTE: To change the look of this report, edit the Main.java in this test report project.  To<br/>change the list of Jenkins jobs handled by this report, edit the DefinedTests.java .</i>");
//    }

    /*
    Added by Lakshmi
     */
    public static String detailedReport(DefinedTests dtObj) {
        String report;
        String DetRep = "";
        try {
            rep_URL = new URL("http://" + jenkinsHostPort + "/job/" + dtObj.toString() + "/lastBuild/api/xml");
        } catch (MalformedURLException e1) {
            System.out.println(e1.getMessage());
        }
        try {
            Document dom_report = new SAXReader().read(rep_URL);
            List<Element> les_rep = dom_report.getRootElement().elements();
            for (Element ls_rp : les_rep) {
                //System.out.println(ls_rp.getName());
                if (ls_rp.getName().contains("suite")) {
                    report = getTestcases(ls_rp, dtObj);
                    DetRep = report + DetRep;
                    // DetRep[1]= report[1]+DetRep[1];
                }
            }
        } catch (DocumentException e) {
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
        return DetRep;

    }

    public static String getTestcases(Element sEl, DefinedTests dtObj) {
        String err = "";
        // char[] err_char = new char[300];

        List<Element> tEl = sEl.elements("case");
        String[] tc_err = new String[2];
        String FinalRep = "";
        tc_err[0] = "";
        tc_err[1] = "";
        report :for (Element tc : tEl) {
            err = "\n";
            if (tc.element("status").getText().contains("FAILED") || tc.element("status").getText().contains("REGRESSION") || (!tc.element("status").getText().contains("PASSED"))) {
                try{
                    err = tc.element("errorStackTrace").getText();
                }catch (Exception E1){continue report;}
                if (err.length() > 400) {
                    err = err.substring(0, 399);
                    err = err + "<br><b> Error stack trace is too huge to print . Please look in to the console for more details</b></br>";
                }

                tc_err[0] = tc_err[0] + tc.element("name").getText();
                tc_err[1] = tc_err[1] + err;
                FinalRep = FinalRep + tc_err[0] + '\n' + tc_err[1];

            }

        }
        return FinalRep;
    }

    private static void buildEmailContent(String environment) {
        int counter = 1;
        emailContent.append("Hi Team, " + "<br/>" + "</br>");
        emailContent.append("Please find below the summary & detailed UI Automation Jobs execution report." + "<br/>" + "</br>");
        emailContent.append("<b>Build Number:  </b>" + getLatestBuildNumber() + "<br/>");
//        emailContent.append("<b>Test System: </b> http://" + testHostPort + "<br/>");
        emailContent.append("<br/>");
        emailContent.append("<b>Overall Summary: </b>" + "</br>");
        overallSummary();
        emailContent.append("<br/>");

//        if (failures > 15) {
//            emailContent.append("<i>Info : Most of the jobs failed due to common issues. Please look in to the console</b>" + "</i></br></br>");
////            emailContent.append("<b>Individual Job Report:</b>" + "</br>");
////            oldreportFormat();
//        }
        emailContent.append("<b>Individual Job Report:</b>" + "</br>");
        emailContent.append("<style> table ,th, td {border: 1px solid black;border-collapse: collapse;} th, td {padding: 15px;} th{background-color: #ff9900;}</style>");
        emailContent.append("<table style='width:auto' ><tr><th>S.No</th><th >RestJob Name</th><th >Status</th></tr>");

        if(environment == "qa"){
            myloop:
            for (DefinedTests dt : DefinedTests.values()) {
                try {
                    url = new URL("http://" + jenkinsHostPort + "/job/" + dt.toString() + "/lastBuild/api/xml");
                    // System.out.println("buildNumberFileHandling status against url: " + url.toString() );
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }

                try {
                    Document dom = new SAXReader().read(url);
                    @SuppressWarnings("unchecked")
                    List<Element> les = dom.getRootElement().elements(); //TODO deprecated way of doing this?
                    for (Element el : les) {


                        if (el.getName() == "building") {
                            if (el.getText() == "true") {
                                System.out.println("Found a running job.");
                                // isRunning += 1;
                                emailContent.append("<b>RUNNING</b>");
                                break myloop; //break out of for loop
                            }
                        }


                        if (el.getName() == "result") {
                            // System.out.println( el.getText() );
                            if (el.getText().contains("FAILURE")) {
                                failures += 1;

//                            TODO: Need to add detailed report.
//                            String TnEF = detailedReport(dt);
//                            TnEF = TnEF.replace("</body>","body");
//                            TnEF = TnEF.replace("</hr>","hr");
                                emailContent.append("<tr><td>" + counter + "</td><td><b>" + dt.toString() + "</b> </td><td><b><font color='red'>" + el.getText() + "</font></b></td></tr>");
                                //detailedReport(dt);
                            } else if (el.getText().contains("UNSTABLE")) {
                                unstable += 1;
//                            emailContent.append( "<b><font color='SteelBlue'>" + el.getText() + "</font></b>" );
//                            String TnEU = detailedReport(dt);
//                            TnEU = TnEU.replace("</body>","body");
//                            TnEU = TnEU.replace("</hr>","hr");
                                emailContent.append("<tr><td>" + counter + "</td><td><b>" + dt.toString() + "</b></td><td><b><font color='SteelBlue'>" + el.getText() + "</font></b></td></tr>");

                            } else if (el.getText().contains("SUCCESS")) {
                                System.out.print("result:"+ el.getText());
                                //emailContent.append( "<b><font color='green'>" + el.getText() + "</font></b>" );
                                emailContent.append("<tr><td>" + counter + "</td><td><b>" + dt.toString() + "</b></td><td><b><font color='green'>" + el.getText() + "</font></b></td></tr>");
                            }
                        }

                    }

                } catch (DocumentException e) {
                    //System.out.println( "No status. There was an error reading status." );
                    emailContent.append("<tr><td>" + counter + "</td><td><b>" + dt.toString() + "</b></td><td><b><font color='red'>No status. There was an error reading status.</font></b></td></tr>");
                    errorFooter.append("Test Error:\n" + e.getMessage() + "<br/><br/>");
                }

                emailContent.append("<br/>");
                counter++;

            }
        }else if(environment == "sat"){
            myloop:
            for (SatTests dt : SatTests.values()) {
                try {
                    url = new URL("http://" + jenkinsHostPort + "/job/" + dt.toString() + "/lastBuild/api/xml");
                    // System.out.println("buildNumberFileHandling status against url: " + url.toString() );
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }

                try {
                    Document dom = new SAXReader().read(url);
                    @SuppressWarnings("unchecked")
                    List<Element> les = dom.getRootElement().elements(); //TODO deprecated way of doing this?
                    for (Element el : les) {


                        if (el.getName() == "building") {
                            if (el.getText() == "true") {
                                System.out.println("Found a running job.");
                                // isRunning += 1;
                                emailContent.append("<b>RUNNING</b>");
                                break myloop; //break out of for loop
                            }
                        }


                        if (el.getName() == "result") {
                            // System.out.println( el.getText() );
                            if (el.getText().contains("FAILURE")) {
                                failures += 1;

//                            TODO: Need to add detailed report.
//                            String TnEF = detailedReport(dt);
//                            TnEF = TnEF.replace("</body>","body");
//                            TnEF = TnEF.replace("</hr>","hr");
                                emailContent.append("<tr><td>" + counter + "</td><td><b>" + dt.toString() + "</b> </td><td><b><font color='red'>" + el.getText() + "</font></b></td></tr>");
                                //detailedReport(dt);
                            } else if (el.getText().contains("UNSTABLE")) {
                                unstable += 1;
//                            emailContent.append( "<b><font color='SteelBlue'>" + el.getText() + "</font></b>" );
//                            String TnEU = detailedReport(dt);
//                            TnEU = TnEU.replace("</body>","body");
//                            TnEU = TnEU.replace("</hr>","hr");
                                emailContent.append("<tr><td>" + counter + "</td><td><b>" + dt.toString() + "</b></td><td><b><font color='SteelBlue'>" + el.getText() + "</font></b></td></tr>");

                            } else if (el.getText().contains("SUCCESS")) {
                                System.out.print("result:"+ el.getText());
                                //emailContent.append( "<b><font color='green'>" + el.getText() + "</font></b>" );
                                emailContent.append("<tr><td>" + counter + "</td><td><b>" + dt.toString() + "</b></td><td><b><font color='green'>" + el.getText() + "</font></b></td></tr>");
                            }
                        }

                    }

                } catch (DocumentException e) {
                    //System.out.println( "No status. There was an error reading status." );
                    emailContent.append("<tr><td>" + counter + "</td><td><b>" + dt.toString() + "</b></td><td><b><font color='red'>No status. There was an error reading status.</font></b></td></tr>");
                    errorFooter.append("Test Error:\n" + e.getMessage() + "<br/><br/>");
                }

                emailContent.append("<br/>");
                counter++;

            }
        }
        emailContent.append("</table>");

        /*emailContent.append( "<br/><br/>SUMMARY:<br/>" );
        emailContent.append( "<h3>Failures: " + failures + "</h3>" );
        emailContent.append( "<h3>Running Jobs: " + isRunning + "</h3>" );
        emailContent.append( "<h3>Unstable jobs: " + unstable + "</h3>" );*/
        emailContent.append("<br/><br/>ERRORS(if any):<br/><br>");
        emailContent.append("<i>" + errorFooter + "</i><br/><br/>");
        emailContent.append("-----------------------------------<br/><i>NOTE: To change the look of this report, edit the Main.java in this test report project.  To<br/>change the list of Jenkins jobs handled by this report, edit the DefinedTests.java .</i>");

    }

    public static String getLatestBuildNumber() {
        String buildNo = null;
        try {
            url = new URL("http://" + jenkinsHostPort + "/job/QA_BuildFlow/lastBuild/api/xml");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Document dom = new SAXReader().read(url);
            buildNo = dom.getRootElement().element("number").getText();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return buildNo;

    }

    public static void overallSummary() {
        myloop:
        for (DefinedTests dt : DefinedTests.values()) {
            try {
                url = new URL("http://" + jenkinsHostPort + "/job/" + dt.toString() + "/lastBuild/api/xml");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Document dom = null;
            try {
                dom = new SAXReader().read(url);
                @SuppressWarnings("unchecked")
                List<Element> les = dom.getRootElement().elements();
                for (Element el : les) {
                    if (el.getName() == "building") {
                        if (el.getText() == "true") {
                            System.out.println("Found a running job.");
                            isRunning += 1;
                            //emailContent.append( "<b>RUNNING</b>" );
                            break myloop; //break out of for loop
                        }
                    }
                    if (el.getName() == "result") {
                        if (el.getText().contains("FAILURE")) {
                            failures += 1;
                        } else if (el.getText().contains("UNSTABLE")) {
                            unstable += 1;
                        } else if (el.getText().contains("SUCCESS")) {
                            successful += 1;
                        }
                    }
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        emailContent.append("Success job(s)    : " + successful + "</br>");
        emailContent.append("Failed Job(s)       :" + failures + "</br>");
        emailContent.append("Running Job(s)   :" + isRunning + "</br>");
        emailContent.append("Unstable job(s)  : " + unstable + "</br>");
    }

//    public static void oldreportFormat() {
//        oldreport:
//        for (DefinedTests dt : DefinedTests.values()) {
//            //System.out.print( "Status \"" + dt.toString() + "\": " );
//            emailContent.append("Status \"" + dt.toString() + "\": ");
//            try {
//                url = new URL("http://" + jenkinsHostPort + "/job/" + dt.toString() + "/lastBuild/api/xml");
//
//            } catch (MalformedURLException e1) {
//                e1.printStackTrace();
//            }
//
//            try {
//                Document dom = new SAXReader().read(url);
//                @SuppressWarnings("unchecked")
//                List<Element> les = dom.getRootElement().elements(); //TODO deprecated way of doing this?
//                for (Element el : les) {
//
//                    // if job is currently running print status to report
//                    if (el.getName() == "building") {
//                        if (el.getText() == "true") {
//
//
//                            emailContent.append("<b>RUNNING</b>");
//                            break oldreport; //break out of for loop
//                        }
//                    }
//
//                    // get result status from XML
//                    //System.out.println( "Test name: " + el.getName() );
//                    if (el.getName() == "result") {
//
//                        if (el.getText().contains("FAILURE")) {
//
//                            emailContent.append("<b><font color='red'>" + el.getText() + "</font></b>");
//                            detailedReport(dt);
//                        } else if (el.getText().contains("UNSTABLE")) {
//
//                            emailContent.append("<b><font color='SteelBlue'>" + el.getText() + "</font></b>");
//                            detailedReport(dt);
//                        } else if (el.getText().contains("SUCCESS")) {
//                            emailContent.append("<b><font color='green'>" + el.getText() + "</font></b>");
//                        }
//                    }
//
//                }
//
//            } catch (DocumentException e) {
//
//                emailContent.append("<font color='red'>No status. There was an error reading status.</font>");
//                errorFooter.append("Test Error:\n" + e.getMessage() + "<br/><br/>");
//            }
//
//            emailContent.append("<br/>");
//
//        }
//
//
//    }
}