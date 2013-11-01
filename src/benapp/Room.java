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
    ArrayList<Double> hotelPrice = new ArrayList<>();       //an ArrayList which gonna contain different prices
    int roomType;                                           // the room type
    
    public Room(){                                          //Constr
    }
    
    public void setName(String name){                       //Give a name to the hotel
        this.strName = name;
    }
        
    public void addPrice(double price){                     //Add a price inside the list, if it is not already inside.
        if ( !hotelPrice.contains(price) ){
            hotelPrice.add(price); 
        }
    }
    
    public void setRoomType(int type){                                          // Set a room type
        try{
        Scanner dataIn = new Scanner(System.in);
        while (type<=1 || type>=4) {
            System.out.println("You didn't give an alowed type.\n Please give a number between 1 and 4");
            type = Integer.valueOf(dataIn.nextLine());
            
        }
        this.roomType=type;
        }
        catch(NumberFormatException e){
            System.out.println("Please give a number");
            setRoomType(type);
        }
    }
    @Override
    public String toString(){
        String str;
        str ="\t\tHôtel\t\t\n"
                + "The hotel name is : " + strName
                + "\nFor a room of type : ";
        switch(roomType){
            case 1 : str +="simple"; break;
            case 2 : str +="double"; break;
            case 3 : str +="triple"; break;
            case 4 : str +="???"; break;
        }
        str +="\nPrices are :\n";
        for (int i=0; i<hotelPrice.size()-1;i++){
            str+=hotelPrice.get(i) +"\n";
        }
        str+="===========\n";
        return str;
    }
    
    public int compareTo(Room object){
        if(this.hotelPrice.get(0)==object.hotelPrice.get(0)){
            return 0;
        }
        else if(this.hotelPrice.get(0)<object.hotelPrice.get(0)){
            return -1;
        }
        return 1;
    }
}
