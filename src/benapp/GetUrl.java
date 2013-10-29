/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package benapp;

import java.io.FileOutputStream;
import java.io.IOException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import java.io.*;
/**
 *
 * @author Djowood
 */
public class GetUrl {
    WebClient webClient;                                                        //It's like a browser
    String link = "http://www.booking.com/searchresults.html?src=index&nflt=&ss_raw=&error_url=http%3A%2F%2Fwww.booking.com%2Findex.fr.html%3Fsid%3D0e7e41c6ad79e6cd6e61df44964475c8%3Bdcid%3D1%3B&dcid=1&lang=fr&sid=0e7e41c6ad79e6cd6e61df44964475c8&si=ai%2Cco%2Cci%2Cre%2Cdi&ss=Dublin&checkin_monthday=11&checkin_year_month=2014-1&checkout_monthday=12&checkout_year_month=2014-1&interval_of_time=any&flex_checkin_year_month=any&org_nr_rooms=1&org_nr_adults=2&org_nr_children=0&group_adults=2&group_children=0&search_button_clicked=1&dest_type=city&dest_id=-1502554";
        //our page link
    String fileName = "sourceCode.txt";                                               //The name of the files, where we put the source code of the page
    String sourceCodeStr;                           //this stirng gonna get the source 
    public GetUrl() throws IOException{
        
        buildWebClient();                                                       //call the constrWebClient method
        HtmlPage page = webClient.getPage(link);                                //establish a connection and get the page
        sourceCodeStr = page.getWebResponse().getContentAsString();      //put the source code in a string
        writeFile();
    }
    
    private void writeFile(){                                                   //put the source code inside a file
        try{
            File fe = new File(fileName); 
        
            if ( !fe.exists() ){
                throw new Exception("The file : " + fileName +" doesn't exist");
            } 

            FileOutputStream fsor = new FileOutputStream (fileName);

            for (int i =0;i<sourceCodeStr.length();i++){
                fsor.write(sourceCodeStr.charAt(i));
            }
            fsor.close();
            System.out.println("Copy finished");
         }
        
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    private void buildWebClient(){                                              //set setting of the webClient
        
        webClient = new WebClient(BrowserVersion.CHROME_16);
        webClient.setThrowExceptionOnFailingStatusCode(false);
        webClient.setThrowExceptionOnScriptError(false);
        webClient.setPrintContentOnFailingStatusCode(false);
        webClient.setRedirectEnabled(true);
        webClient.setAppletEnabled(false);
        webClient.setJavaScriptEnabled(false);
   }
}
