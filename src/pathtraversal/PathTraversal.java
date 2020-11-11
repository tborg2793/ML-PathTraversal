/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author tb
 */
public class Coursework2 {

   
    List<DataSet> dataTrain = new ArrayList<>();
    List<DataSetTest> dataTest = new ArrayList<>();
    List<DataSetAvg> dataTrainAvg = new ArrayList<>();
    Map<Integer,Integer> HashMode = new HashMap<>();
    List<Integer> Mode = new ArrayList<>();
    List<List> avgDataTrain0 = new ArrayList<>();
    List<List> avgDataTrain1 = new ArrayList<>();
    List<List> avgDataTrain2 = new ArrayList<>();
    List<List> avgDataTrain3 = new ArrayList<>();
    List<List> avgDataTrain4 = new ArrayList<>();
    List<List> avgDataTrain5 = new ArrayList<>();
    List<List> avgDataTrain6 = new ArrayList<>();
    List<List> avgDataTrain7 = new ArrayList<>();
    List<List> avgDataTrain8 = new ArrayList<>();
    List<List> avgDataTrain9 = new ArrayList<>();
    Scanner sc = null;
    
    

    List<Integer> kValues = new ArrayList<>();
    int counterTrain;
    int counterTest;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        Menu menu = new Menu();
    }
    
    
    
//----------------------------------------------------------- Load Training Data
public  void loadTrainData() throws FileNotFoundException
    {
    
        System.out.println("1. optdigits.tra");
        System.out.println("2. optdigits.tes");
        System.out.print("Enter which file should be loaded as training data here - ");
        Scanner scanner = new Scanner(System.in);
        
        int choice = scanner.nextInt();
        
        if(choice==1)
        {
        File file = new File("optdigits.tra"); // Training Data
        sc = new Scanner(file);
        }
        
        if(choice==2)
        {
        File file = new File("optdigits.tes.txt");   // Test Data
        sc = new Scanner(file);
        }

     
        System.out.println("Loaded Training data");
    
        String line; 
        String[] lineVector=null;
    
        while (sc.hasNextLine()) 
        {
            
          line = sc.nextLine();
          lineVector = line.split(","); // split elements by comma
          
          List<Integer> data = new ArrayList<>();
          
          for(int n = 0; n < lineVector.length; n++) 
          {
            int value = Integer.parseInt(lineVector[n]);
            
            data.add(value); // add each value to data
          }
          
          
        int label = data.get(data.size()-1); // get last element and save as label
        data.remove(data.size()-1);  // remove last element
       
        
        DataSet ds = new DataSet(label,data);
        dataTrain.add(ds); // add as dataset and add to dataTrain list of DataSets
        
        }
        
    }

//---------------------------------------------------------Display Training Data    
public  void displayData()
 {
        System.out.println("Displaying Data");

        for (DataSet ds : dataTrain)
        {
            System.out.println("Index Training Data - "+dataTrain.indexOf(ds));
            System.out.println("Label is - "+ds.getLabel());
            System.out.println("Data is - "+ds.getData());
            System.out.println("Distance is - "+ds.getDistance());
            System.out.println();
        }
}
    
//--------------------------------------------------------------- Load Test Data    
public  void loadTestData() throws FileNotFoundException
    {
        
        System.out.println("1. optdigits.tes");
        System.out.println("2. optdigits.tra");
        System.out.print("Enter which file should be loaded as Test data here - ");
        Scanner scanner = new Scanner(System.in);
        
        int choice = scanner.nextInt();
        
        if(choice==1)
        {
            File file = new File("optdigits.tes.txt"); // Test Data
            sc = new Scanner(file);
        }
        if(choice==2)
        {
            File file = new File("optdigits.tra"); // Training Data
            sc = new Scanner(file);
        }
        
       
        System.out.println("Loaded Test data");
    
        String line; 
        String[] lineVector=null;
    
        while (sc.hasNextLine()) 
        {
            
          line = sc.nextLine();
          lineVector = line.split(","); // split elements by comma
          
          List<Integer> testData = new ArrayList<Integer>();
          
          for(int n = 0; n < lineVector.length; n++) 
          {
            int value = Integer.parseInt(lineVector[n]);
            testData.add(value); // add each value to testData
          }
          
          
        int label = testData.get(testData.size()-1); // get last element and save as label
        testData.remove(testData.size()-1); // remove last element
        
      
        DataSetTest ds = new DataSetTest(label,testData);
        dataTest.add(ds); // add as dataset Test and add to dataTest list of DataSets Tests
        
        }
       
        
    }
    
//----------------------------------------------Run Nearest Neighbor Recursively    
public  void nNAlgorithmRec()
    {
        counterTest = 0;
        
        for(DataSetTest dsTest:dataTest) // loop throughout the Testing Data Sets
        {
            dataTest.get(counterTest).getData(); // Get the data for the test of counter counterTest
            
            counterTrain = 0;
            
            
            for(DataSet dsTrain:dataTrain) // loop throughout all Training Data Set
            {
                
                int element = 0;
                int tempResult = 0;
                //System.out.println("Checking Train - "+counterTrain);
                double distanceSum = InnerCalculation(element,tempResult); // Call Recursive function to make up the Euclidean distance
                double distance = Math.sqrt(distanceSum); // Square root to give Euclidean distance
                
                dsTrain.setDistance(distance); // Save the distance
                
                
                counterTrain++; 
            }
            
            Collections.sort(dataTrain, new DataSet.OrderByDistance()); // Sort by distances
            
          
            System.out.println();
            System.out.println();
            System.out.println("Checking Test - "+dataTest.indexOf(dsTest));
            System.out.println("Training Distance Min - "+dataTrain.get(0).getDistance());
            System.out.println("Training Label - "+dataTrain.get(0).getLabel());
            System.out.println("Testing Label - "+dataTest.get(counterTest).getLabel());
            
            
            
            if(dataTrain.get(0).getLabel()==dataTest.get(counterTest).getLabel()) // Check if training label of smallest is equal to the label of the currently tested data set.
            {
            System.out.println("Correct");
            dataTest.get(counterTest).setCorrect(true); // set correct
            }
            else
            {
               System.out.println("Incorrect"); 
               dataTest.get(counterTest).setCorrect(false); // set incorrect
            }
            
            System.out.println();
            System.out.println();
            
           counterTest++;
            

        }
        
        int testSize = dataTest.size();
        
        System.out.println();
        
        int correct = 0;
        
        for(DataSetTest dST:dataTest) // get all correct datasets
        {
            if(dST.getCorrect()==true)
            {
                correct++;
            }
        }
        System.out.println("Correct Tests - "+correct);
        System.out.println("Total Tests "+ testSize);
        double accuracy = ((double)correct/(double)testSize)*100; // get accuracy of correct dataset
        
        System.out.println("Accuracy of Nearest neighbor - "+accuracy);
        System.out.println();
        System.out.println();
        Results(); // get individual label accuracy

}
    

//--------------------------------------------Run K Nearest Neighbor Recursively
    
    
public void kNNAlgorithmRec(int kValue)
{
        
        counterTest = 0;
        
        for(DataSetTest dsTest:dataTest)
        {
            int occurrencesOf0 = 0;    // variables to contain the occurrences of labels in the mode functionality of the kNN
            int occurrencesOf1 = 0;
            int occurrencesOf2 = 0;
            int occurrencesOf3 = 0;
            int occurrencesOf4 = 0;
            int occurrencesOf5 = 0;
            int occurrencesOf6 = 0;
            int occurrencesOf7 = 0;
            int occurrencesOf8 = 0;
            int occurrencesOf9 = 0;
                                    
            dataTest.get(counterTest).getData();
            
          
            counterTrain = 0;
            
            
            for(DataSet dsTrain:dataTrain)
            {
                
                int element = 0;
                int tempResult = 0;
                
                int distanceInt = InnerCalculation(element,tempResult);
                double distance = Math.sqrt(distanceInt);
                
                dsTrain.setDistance(distance);
                
                
                
                
                
               
                counterTrain++;
            }
            
            Collections.sort(dataTrain, new DataSet.OrderByDistance()); // Sort by euclidean distance
            
            System.out.println("Checking Test - "+dataTest.indexOf(dsTest));
            
  
                for(int i=0;i<kValue;i++) // loop for kValue number and add the next smallest euclidean distance to a list kValues
                {
                    System.out.println("Label - "+dataTrain.get(i).getLabel());
                    System.out.println("Distance - "+dataTrain.get(i).getDistance());
                    System.out.println("");
                    System.out.println("");
                    kValues.add(dataTrain.get(i).getLabel());
                }
                
                System.out.println(kValues);
                System.out.println("");
                System.out.println("");
                

                
                HashMode.put(0, Collections.frequency(kValues, 0)); // get all similar values found in kValues list and place them in HashMap
                HashMode.put(1, Collections.frequency(kValues, 1));
                HashMode.put(2, Collections.frequency(kValues, 2));
                HashMode.put(3, Collections.frequency(kValues, 3));
                HashMode.put(4, Collections.frequency(kValues, 4));
                HashMode.put(5, Collections.frequency(kValues, 5));
                HashMode.put(6, Collections.frequency(kValues, 6));
                HashMode.put(7, Collections.frequency(kValues, 7));
                HashMode.put(8, Collections.frequency(kValues, 8));
                HashMode.put(9, Collections.frequency(kValues, 9));
                
                 int maxValueInMap=(Collections.max(HashMode.values()));  // This will return max value in the Hashmap
                    for (Map.Entry<Integer, Integer> entry : HashMode.entrySet()) {  // Itrate through hashmap
                        if (entry.getValue()==maxValueInMap) {
                            //System.out.println(entry.getKey());     // Print the key with max value
                        Mode.add(entry.getKey());
                        }
                    }  
                    
                int mode=0;    
                System.out.println("All Modes - "+Mode); 
                
                if(Mode.size()>1) // if there are multiple modes
                {
                    for(int i=0;i<Mode.size();i++)
                    {
                        if(kValues.get(0)==Mode.get(i)) // check if least euclidean distance model is present as one of the modes
                        {
                            mode = Mode.get(i); // if yes, set mode
                            System.out.println("Final Mode is - "+mode);
                        }
                    }
                }
                else
                {
                    mode = Mode.get(0);// else set mode as mode of first position
                    System.out.println("Final Mode is - "+mode);
                }
                    
                    
                System.out.println("Mode is - "+mode);
                
                
                
                if(mode==dataTest.get(counterTest).getLabel()) 
                    {
                    System.out.println("Correct");
                    dataTest.get(counterTest).setCorrect(true);
                    }
                else if(mode!=dataTest.get(counterTest).getLabel())
                    {
                       System.out.println("Incorrect"); 
                       dataTest.get(counterTest).setCorrect(false);
                       //System.out.println(dataTest.get(counterTest).getCorrect());
                    }

                    System.out.println();
                    System.out.println();
                
                kValues.clear();
                Mode.clear();
                HashMode.clear();
                        
               counterTest++;         
                        
             }
        
        
        int testSize = dataTest.size();
        
        System.out.println();
        
        int correct = 0;
        
        for(DataSetTest dST:dataTest)
        {
            if(dST.getCorrect()==true)
            {
                correct++;
            }
        }
        System.out.println("Correct Tests - "+correct);
        System.out.println("Total Tests "+ testSize);
        double accuracy = ((double)correct/(double)testSize)*100;
        
        System.out.println("Accuracy of K-Nearest neighbor when K="+kValue+" - "+accuracy);
        System.out.println();
        System.out.println();            
        Results();

        
        
              
    }
    
    
    




    
// ------------------------------------------------ Simplified K Means Algorithm    
    
public void simplifiedKMeans()
{
            int count0 = 0; // get count of every labels to use for performing averages
            int count1 = 0;
            int count2 = 0;
            int count3 = 0;
            int count4 = 0;
            int count5 = 0;
            int count6 = 0;
            int count7 = 0;
            int count8 = 0;
            int count9 = 0;
            
            for(DataSet dsTrain:dataTrain) // get all training data sets and place them in seperate lists by label
            {
               if(dsTrain.getLabel()==0) 
               {
               count0++;
               avgDataTrain0.add(dsTrain.getData());
               }
               if(dsTrain.getLabel()==1)
               {
               count1++;
               avgDataTrain1.add(dsTrain.getData());
               }
               
               if(dsTrain.getLabel()==2)
               {
               count2++;
               avgDataTrain2.add(dsTrain.getData());
               }
               
               if(dsTrain.getLabel()==3)
               {
               count3++;
               avgDataTrain3.add(dsTrain.getData());
               }
               
               if(dsTrain.getLabel()==4)
               {
               count4++;
               avgDataTrain4.add(dsTrain.getData());
               }
               
               if(dsTrain.getLabel()==5)
               {
               count5++;
               avgDataTrain5.add(dsTrain.getData());
               }
               
               if(dsTrain.getLabel()==6)
               {
               count6++;
               avgDataTrain6.add(dsTrain.getData());
               }
               
               if(dsTrain.getLabel()==7)
               {
               count7++;
               avgDataTrain7.add(dsTrain.getData());
               }
               
               if(dsTrain.getLabel()==8)
               {
               count8++;
               avgDataTrain8.add(dsTrain.getData());
               }
               
               if(dsTrain.getLabel()==9)
               {
               count9++;
               avgDataTrain9.add(dsTrain.getData());
               }
           }
            
            
            HashMap<Integer,List> hash = new HashMap<Integer,List>(); // place lists in hasp maps of type integer and list
            hash.put(0, avgDataTrain0);
            hash.put(1, avgDataTrain1);
            hash.put(2, avgDataTrain2);
            hash.put(3, avgDataTrain3);
            hash.put(4, avgDataTrain4);
            hash.put(5, avgDataTrain5);
            hash.put(6, avgDataTrain6);
            hash.put(7, avgDataTrain7);
            hash.put(8, avgDataTrain8);
            hash.put(9, avgDataTrain9);
            
            
            
            for(int a=0; a<hash.size(); a++) // work out the average of all lists
            {
            List<List>dataTemp = new ArrayList<List>();
            
                for(int b = 0; b<hash.get(a).size();b++)
                {
                    dataTemp.add((List)hash.get(a).get(b));
                }
            
            List <Double> avgData = new ArrayList<Double>();
                
                for(int x = 0;x<64;x++)
                {
                    double average = 0;
                    int sum = 0;
                    
                    for(int y=0;y<dataTemp.size();y++)
                    {
                        sum = sum + (Integer)dataTemp.get(y).get(x);
                    }
                    average = (double)sum/dataTemp.size();
                    avgData.add(average);
                }
               DataSetAvg dSA = new DataSetAvg(a,avgData);
               dataTrainAvg.add(dSA);
               dataTemp.clear();
               
            
            }
            
            //int x = 0;
            for(DataSetAvg dSA: dataTrainAvg)
            {
                System.out.println(dSA.getLabel()+" - "+dSA.getData());
                //System.out.println(x+" - "+dSA.getLabel());
                
            }
           
            

           workerKMeans(); // call the worker algorithm. 
           Results(); 
               
        }




public void workerKMeans() 
{
    
        counterTest = 0;
        
        for(DataSetTest dsTest:dataTest)
        {
            dataTest.get(counterTest).getData();
            
            // Get the element in dataTest row 1 ..... x
            //System.out.println(dataTest.get(counter).getData().get(3));
            counterTrain = 0;
            
            
            for(DataSetAvg dsTrainAvg:dataTrainAvg)
            {
                
                int element = 0;
                double tempResult = 0;
                //System.out.println("Checking Train - "+counterTrain);
                double distanceSum = KMeansInnerCalculation(element,tempResult);
                double distance = Math.sqrt(distanceSum);
                
                dsTrainAvg.setDistance(distance);
                
                
                //unsortMap.put(distance, dataTrain.indexOf(dsTrainAvg));
                
                
                System.out.println("Distance for "+dataTrainAvg.get(counterTrain).getLabel()+" - "+distance);
                counterTrain++;
                
                
            
                
            }
            
            Collections.sort(dataTrainAvg, new DataSetAvg.OrderByDistance());
            
            for(DataSetAvg dSA: dataTrainAvg)
            {
                System.out.println("Label - "+dSA.getLabel());
            }
            
            
            System.out.println("After Sorting Min is - "+dataTrainAvg.get(0).getDistance());
            System.out.println("After Sorting Min is - "+dataTrainAvg.get(0).getLabel());
            
            System.out.println();
            System.out.println();
            System.out.println("Checking Test - "+dataTest.indexOf(dsTest));
            System.out.println("Training Distance Min - "+dataTrainAvg.get(0).getDistance());
            System.out.println("Training Label - "+dataTrainAvg.get(0).getLabel());
            System.out.println("Testing Label - "+dataTest.get(counterTest).getLabel());
            
            
            
            if(dataTrainAvg.get(0).getLabel()==dataTest.get(counterTest).getLabel())
            {
            System.out.println("Correct");
            dataTest.get(counterTest).setCorrect(true);
            }
            else
            {
               System.out.println("Incorrect"); 
               dataTest.get(counterTest).setCorrect(false);
            }
            
            System.out.println();
            System.out.println();
            
            


            counterTest++;
            

        }
        
        int testSize = dataTest.size();
        
        System.out.println();
        
        int correct = 0;
        
        for(DataSetTest dST:dataTest)
        {
            if(dST.getCorrect()==true)
            {
                correct++;
            }
        }
        System.out.println("Correct Tests - "+correct);
        System.out.println("Total Tests "+ testSize);
        double accuracy = ((double)correct/(double)testSize)*100;
        
        System.out.println("Accuracy of Simplified K Means - "+accuracy);
        System.out.println();
        System.out.println();
   

}




    
// ----------------------------------------------- Inner Calculation Recursively    
public int InnerCalculation(int x, int tempResult) // works out the euclidean distance with a recursive function
{
    if (x < dataTest.get(counterTest).getData().size()-1) // for x when less than the size of the dataset
    {
        tempResult += Math.pow((dataTrain.get(counterTrain).getData().get(x)-dataTest.get(counterTest).getData().get(x)),2); // work out (a1+b1)^2
        return InnerCalculation(x+1, tempResult); // call the same function with new element and tempResult
    }

    return tempResult; // When x is the same as the size, exit function with tempResult
}






// ----------------------------------------------- Inner Calculation Recursively K Means    
public double KMeansInnerCalculation(int x, double tempResult)
        {
            if (x < dataTest.get(counterTest).getData().size()-1)
            {
                tempResult += Math.pow((dataTrainAvg.get(counterTrain).getData().get(x)-dataTest.get(counterTest).getData().get(x)),2);
                return KMeansInnerCalculation(x+1, tempResult);
            }

            return tempResult;
        }



public void Results() // get results of labels individually
{
        int count0 = 0;
        int correct0 = 0;
        double accuracy0 = 0;
        int count1 = 0;
        int correct1 = 0;
        double accuracy1 = 0;
        int count2 = 0;
        int correct2 = 0;
        double accuracy2 = 0;
        int count3 = 0;
        int correct3 = 0;
        double accuracy3 = 0;
        int count4 = 0;
        int correct4 = 0;
        double accuracy4 = 0;
        int count5 = 0;
        int correct5 = 0;
        double accuracy5 = 0;
        int count6 = 0;
        int correct6 = 0;
        double accuracy6 = 0;
        int count7 = 0;
        int correct7 = 0;
        double accuracy7 = 0;
        int count8 = 0;
        int correct8 = 0;
        double accuracy8 = 0;
        int count9 = 0;
        int correct9 = 0;
        double accuracy9 = 0;
        
        
        
        for(DataSetTest dSt:dataTest)
        {
            
            if(dSt.getLabel()==0)
            {
                count0++;
                if((dSt.getCorrect()==true)&&(dSt.getLabel()==0))
                {
                    correct0++;
                }
            }
            
            if(dSt.getLabel()==1)
            {
                count1++;
                if(dSt.getCorrect()==true)
                {
                    correct1++;
                }
            }
            
            if(dSt.getLabel()==2)
            {
                count2++;
                if(dSt.getCorrect()==true)
                {
                    correct2++;
                }
            }
            
            if(dSt.getLabel()==3)
            {
                count3++;
                if(dSt.getCorrect()==true)
                {
                    correct3++;
                }
            }
            
            if(dSt.getLabel()==4)
            {
                count4++;
                if(dSt.getCorrect()==true)
                {
                    correct4++;
                }
            }
            
            if(dSt.getLabel()==5)
            {
                count5++;
                if(dSt.getCorrect()==true)
                {
                    correct5++;
                }
            }
            
            if(dSt.getLabel()==6)
            {
                count6++;
                if(dSt.getCorrect()==true)
                {
                    correct6++;
                }
            }
            
            if(dSt.getLabel()==7)
            {
                count7++;
                if(dSt.getCorrect()==true)
                {
                    correct7++;
                }
            }
            
            if(dSt.getLabel()==8)
            {
                count8++;
                if(dSt.getCorrect()==true)
                {
                    correct8++;
                }
            }
            
            if(dSt.getLabel()==9)
            {
                count9++;
                if(dSt.getCorrect()==true)
                {
                    correct9++;
                }
            }
            
            
        }
        
        accuracy0 = ((double)correct0/(double)count0)*100;
        accuracy1 = ((double)correct1/(double)count1)*100;
        accuracy2 = ((double)correct2/(double)count2)*100;
        accuracy3 = ((double)correct3/(double)count3)*100;
        accuracy4 = ((double)correct4/(double)count4)*100;
        accuracy5 = ((double)correct5/(double)count5)*100;
        accuracy6 = ((double)correct6/(double)count6)*100;
        accuracy7 = ((double)correct7/(double)count7)*100;
        accuracy8 = ((double)correct8/(double)count8)*100;
        accuracy9 = ((double)correct9/(double)count9)*100;
        
        System.out.println("Accuracy for Label 0 - "+accuracy0);
        System.out.println("Accuracy for Label 1 - "+accuracy1);
        System.out.println("Accuracy for Label 2 - "+accuracy2);
        System.out.println("Accuracy for Label 3 - "+accuracy3);
        System.out.println("Accuracy for Label 4 - "+accuracy4);
        System.out.println("Accuracy for Label 5 - "+accuracy5);
        System.out.println("Accuracy for Label 6 - "+accuracy6);
        System.out.println("Accuracy for Label 7 - "+accuracy7);
        System.out.println("Accuracy for Label 8 - "+accuracy8);
        System.out.println("Accuracy for Label 9 - "+accuracy9);
        System.out.println();
        System.out.println();
}















}


