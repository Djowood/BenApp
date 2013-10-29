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
    String fileName = "sourceCode.txt";                                         //Pour pouvoir ouvrir le fichier
    int compteur;                                                         //Pour pouvoir compter le nombre de récurence d'un mot dans ce fichier
    ArrayList<Integer> compteurindice = new ArrayList<Integer>();         //Pour pouvoir enregistrer a qu'elle position ce trouve les mots trouver
    char [] mot;                                                         //C'est le mot qu'on va rechercher
    
    File fe;                                                            //C'est le fichier que l'on va ouvrir
    
    FindInsideFile() throws IOException{                                  //Le constructeur 
        ouvertur();                                         //Appel de la méthode ouverture
        getMot();                                           //Appel de la méthode pour avoir le mot rechercher
        
        String reader;
        BufferedReader fent = new BufferedReader(new FileReader(fe));
        String enr;
        
        while ((enr=fent.readLine())!=null){
            int i=0;
            int j=i;
            int compt=0;                                                        //compte le nombre d'indice
            
            while(i<enr.length()-1){                                            //on parcours tous le fichier
                
                if (enr.charAt(i)==mot[j]){
                    while(enr.charAt(i)==mot[j] && j != mot.length-1){           //on parcour un mot en comparant toutes les lettes avec notre mot
                        i++; 
                        j++;
                    }

                    if(j == mot.length-1){                        //On regarde si la taille de j est égale a la taille du mot rechercher
                        compteurindice.add(i-j);          //On met dans le tableau où on a trouver le mot
                        compt++;
                    }
                    j=0;
                }
                i++;
            }
            
        }
        
        System.out.println("J'ai trouver " + (compteurindice.size()) + " mots");
        
    }
        
    
    private void ouvertur(){                             //Méthode ouverture du fichier et vérification si il existe ou non                      
        Scanner x = new Scanner(System.in);
        do{
            /*System.out.println("Rentrer le nom de votre fichier");
            nomfichier = x.nextLine();*/
            fe = new File(fileName) ;   
          }while(!fe.exists());
    }
    
    public void getMot(){                               //Méthode pour obtenir le mot rechercher par l'utilisateur
        Scanner x = new Scanner(System.in);
        String temp;
        int i;                
        
        System.out.println("Quel mot voulez vous rechercher ?");
        temp=x.nextLine();
        mot = new char[temp.length()];
        
        for (i=0;i<temp.length();i++){                  //Boucle pour mettre le mot dans la variable
            mot[i]=temp.charAt(i);
        }
        
    }
}