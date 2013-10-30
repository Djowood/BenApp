/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package benapp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Djowood
 */
public class FindInsideFile{
    String fileName = "sourceCode.txt";                                         //The file name which gonna containe the source code
 
    File fe;                                                                    //To open the file = the name file above
    
    ArrayList<String> hotelName = new ArrayList<String>();                      //For the hotel names
    ArrayList<String> hotelPrice = new ArrayList<String>();                     //For the prices
    
    int roomType;                                                               //=1 for a on people room, =2 for a two peoples room etc. 

    FindInsideFile() throws IOException{                                   
        openFiles();                                                            //Open the file                                                          
        searchHotelProperty();                                                  //research hotel name and and price of their room a room type given
    }
    
    public void searchHotelProperty() throws IOException{                       //return name and price of an hotel 
        StringBuffer name = new StringBuffer();                                 //To catch hotel name
                                                            
        BufferedReader fent = new BufferedReader(new FileReader(fe));           //to read inside the file
        String enr;                                                             // ""
        
        char [] varHotelName;            //I'm gonna put "b_name" inside word, in the aim to research name hotel name and price inside the source code
        String temp="\"b_name\" : \"";                         
        varHotelName = new char[temp.length()];
        for (int i=0;i<temp.length();i++){
            varHotelName[i]=temp.charAt(i);
        }
        int i=0;    //gonna be usefull to read character of the files (with the bufferReader)
        int j=i;    //gonna be usefull to read varHotelName
        
        while ((enr=fent.readLine())!=null){        //while we are a the end of the file
            i=0;
            j=i; 

            while(i<enr.length()-1){               //We gonna read one record                                 
                
                if(enr.charAt(i)==varHotelName[j]){         //if the letter that we read is equals to the first letter of varHotelName (")
                    while(enr.charAt(i)==varHotelName[j] && j != varHotelName.length-1){         //while it is equals, we keep going  
                        i++; 
                        j++;
                    }
                    
                    if(j == varHotelName.length-1){                        //if the size of j = size of varHotelName, then we have the word, so we can treat it
                        i++;
                        j=0;
                        while(enr.charAt(i)!='\"'){                 //we put the hotel name inside an ArrayList (hotelName).
                            name.insert(j++, enr.charAt(i++));
                        }
                        hotelName.add(name.toString());
                        name.delete(0, name.length());
                        //==============================================================================
                        if(getRoomPrice(fent, enr));               //then we call the method in the aim to get price of this hotel. 
                    }
                    j=0;
                }
                i++;
            }
        }
    }
    
    private boolean getRoomPrice(BufferedReader fent, String enr) throws IOException{       //get price of room for an hotel 
        roomType=2;         //the roomType. 
        
        String temp;        //to put string inside variable (see juste below)
        StringBuffer price = new StringBuffer();
        
        char [] varRoomPrice;           
        char [] hotelPush;
        char [] end;
        
        temp="room_price\" : \"&#x20AC;&nbsp;";                                               //put "room_price\" : \"&#x20AC;&nbsp;" inside varRoomPrice, in the aim to reach prize of an hotel room
        varRoomPrice = new char[temp.length()];
        for (int i=0;i<temp.length();i++){
            varRoomPrice[i]=temp.charAt(i);
        }
        
        temp="hotels.push";                                               //put "hotels.push" inside hotelPush, in the aim to see when go to the next hotel
        hotelPush = new char[temp.length()];
        for (int i=0;i<temp.length();i++){
            hotelPush[i]=temp.charAt(i);
        }
        
        temp="booking.map";                                               //put "booking.map" inside end, in the aim to see when we are at the end of the hotel list
        end = new char[temp.length()];
        for (int i=0;i<temp.length();i++){
            end[i]=temp.charAt(i);
        }
        
        while ((enr=fent.readLine())!=null){        //while we are a the end of the file
            int i=0;
            int j=i;

            while(i<enr.length()-1){                //We gonna read one record 
                
                if(enr.charAt(i)==varRoomPrice[j]){                 //if the letter that we read is equals to the first letter of varRoomPrice (r)
                    while(enr.charAt(i)==varRoomPrice[j] && j != varRoomPrice.length-1){           //like above, we see if we find the good things
                        i++; 
                        j++;
                    }
                    if(j == varRoomPrice.length-1 && getRoomPers(fent, enr)==roomType){            //And if we do
                        
                        j=0;
                        
                        while(enr.charAt(i)!='\"'){
                            price.insert(j++, enr.charAt(i++));
                        }
                        hotelPrice.add(price.toString());                       // we put it in an arraylist hotelPrice
                        price.delete(0, price.length());
                        
                    }
                }
                j=0;
                if (enr.charAt(i)==hotelPush[j]){                                   //if we go to the next hotel
                    while(enr.charAt(i)==hotelPush[j] && j != hotelPush.length-1){           
                        i++; 
                        j++;
                    }
                    if(j == hotelPush.length-1){
                        price.insert(0, 'x');                                       //we put an x inside the list hotelPrice, in the aim to see it when we want to display prices
                        hotelPrice.add(price.toString());
                        return true;                                    //exist the prog
                    }
                }
                j=0;
                if (enr.charAt(i)==end[j]){                                         //if we are a the end of the list
                    while(enr.charAt(i)==end[j] && j != end.length-1){           
                        i++; 
                        j++;
                    }
                    if(j == end.length-1){
                        return true;                                //exist the prog
                    }
                }
               i++; 
            }
        }
        return false;                               //just in case, but we shouldn't return this
        
    }
    private int getRoomPers(BufferedReader fent, String enr) throws IOException{            //To see if the price that we have found match with our room type. We return an Int, and test it inside getRoomPrice
        String temp;
        char [] varRoomPersons; 
        temp="room_persons\" : \"";                                               
        varRoomPersons = new char[temp.length()];
        for (int i=0;i<temp.length();i++){
            varRoomPersons[i]=temp.charAt(i);
        }
        
        while ((enr=fent.readLine())!=null){
            int i=0;
            int j=i;

            while(i<enr.length()-1){
                while(enr.charAt(i)==varRoomPersons[j] && j != varRoomPersons.length-1){           
                        i++; 
                        j++;
                    }
                    if(j == varRoomPersons.length-1){
                        return Integer.parseInt(String.valueOf(enr.charAt(i+1)));
                    }
                i++;
            }
        }
        return 0;
    }
    
    private void openFiles(){                             //Test if the file exist and open it if it does                      
        Scanner x = new Scanner(System.in);
        do{
            /*System.out.println("Rentrer le nom de votre fichier");
            nomfichier = x.nextLine();*/
            fe = new File(fileName) ;   
          }while(!fe.exists());
    }
    
    @Override
    public String toString(){
        String str;
        str = "I've found " + hotelName.size() + " hotel\nWhich are : ";
        int h=0;
        String y=hotelPrice.get(h);
        for (int z=0;z<hotelName.size();z++){
            str +=hotelName.get(z);
            str +="\tPrix : ";
            y=hotelPrice.get(h);
            while(!y.equals("x") && h<hotelPrice.size()-1){
                str +=hotelPrice.get(h);
                y=hotelPrice.get(++h);
            }
            str+="\n";
            if(h<hotelPrice.size()-1){
                h++;
            }
        }
        return str;
    }
    
    
                            // ========= Useless for now
    
    
    /*public void getWord(){                               //Search a word ask by th user, and return how many time it have been found. 
        Scanner x = new Scanner(System.in);
        String temp;
        int i;                
        
        System.out.println("Which word do you want to search ?");
        temp=x.nextLine();
        
        while("".equals(temp) || "\n".equals(temp) || "\r".equals(temp) || " ".equals(temp) || "\t".equals(temp)){
            System.out.println("Your word is empty, try again please");
            temp=x.nextLine();
        }
        
        
        word = new char[temp.length()];
        
        for (i=0;i<temp.length();i++){                  //Boucle pour mettre le word dans la variable
            word[i]=temp.charAt(i);
        }
        
    }*/
}