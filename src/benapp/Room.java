/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package benapp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Djowood
 */
public class Room {                                         //Represent an hotel, with a room type and different prices. 
    String strName;                                         //the hotel name
    ArrayList<Double> roomPrice = new ArrayList<>();       //an ArrayList which gonna contain different prices
    int roomType;                                           // the room type
    int stars;
    public Room(){                                          //Constr
    }
    
    public void setName(String name){                       //Give a name to the hotel
        this.strName = name;
    }
        
    public void addPrice(double price){                     //Add a price inside the list, if it is not already inside.
        if ( !roomPrice.contains(price) ){
            roomPrice.add(price); 
        }
    }
    
    public void setRoomType(int type){                                          // Set a room type
        try{
            if (type<=1 || type>=4) {
                System.out.println("This room type isn't alowed.\n What you have give : " + type);
            }
            this.roomType=type;
        }
        catch(NumberFormatException e){
            System.out.println("This room type isn't alowed\n.What you have give : " + String.valueOf(type));
        }
    }
    
    public void setStars(int stars){
       try{
        if (stars<0 || stars>5){
            System.out.println("This hotel rang isn't alowed.\n What you have give : " + stars);
        }
        this.stars=stars;
       }
       catch(Exception e){
           System.out.println("This hotel rang isn't alowed.\n What you have give : " + String.valueOf(stars));
       }
    }
    
    @Override
    public String toString(){
        String str;
        str ="\t\tHôtel\t\t\n"
                + "The hotel name is : " + strName
                + "\nIs ranked with " + stars +" stars"
                + "\nFor a room of type : ";
        switch(roomType){
            case 1 : str +="simple"; break;
            case 2 : str +="double"; break;
            case 3 : str +="triple"; break;
            case 4 : str +="???"; break;
        }
        str +="\nPrices are :\n";
        for (int i=0; i<roomPrice.size()-1;i++){
            str+=roomPrice.get(i) +"\n";
        }
        str+="===========\n";
        return str;
    }
    
    public int compareTo(Room object){
        if(this.roomPrice.get(0)==object.roomPrice.get(0)){
            return 0;
        }
        else if(this.roomPrice.get(0)<object.roomPrice.get(0)){
            return -1;
        }
        return 1;
    }
}
