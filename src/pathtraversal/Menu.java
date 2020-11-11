/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework2;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas-Home
 */
public class Menu
{
    Scanner s = new Scanner(System.in);
    Coursework2 cw = new Coursework2();
    
        
    Menu(){
        
        String menu = "Please Choose A Menu Option";
        menu += "\n1.Load Training Data";
        menu += "\n2.Display Training Data";
        menu += "\n3.Load Test Data";
        menu += "\n4.Apply Nearest Neighbor Algorithm Recursively ";
        menu += "\n5.Apply K-Nearest Neighbor Algorithm Recursively";
        menu += "\n6.Apply Simplified K-Means";
        menu += "\n7.Exit";
        
        boolean continueRunning = true;

        do
        {
            System.out.println(menu);
            System.out.print("User Input - ");
            int menuOption = s.nextInt();

            switch (menuOption)
            {
                case 1:
                    {
                        try
                        {
                            cw.loadTrainData();
                        } catch (FileNotFoundException ex)
                        {
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case 2:
                    cw.displayData();
                    break;
                case 3:
                    {
                        try
                        {
                            cw.loadTestData();
                        } catch (FileNotFoundException ex)
                        {
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case 4:
                    cw.nNAlgorithmRec();
                    break;
                case 5:
                    System.out.print("Enter K-Value - ");
                    int kValue = s.nextInt();
                    cw.kNNAlgorithmRec(kValue);
                    break;
                case 6:
                    cw.simplifiedKMeans();
                    break; 
                case 7:
                    continueRunning = false;
                    break;
                default:
                    System.out.println("Invalid Option");
            }

        } while (continueRunning);
} 
}
