/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework2;

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author tb
 */
class DataSet implements Comparable<DataSet> {
    
    private List<Integer> data;
    private int label;
    private double distance;
    
public DataSet(int label,List<Integer>data)
    {
        this.label = label;
        this.data = data;
    }
    
    public void setLabel(int label)
    {
        this.label=label;
    } 
    
    public int getLabel()
    {
        return label;
    }

    public void setData(List<Integer>data)
    {
        this.data = data;
    }
    
    public List<Integer> getData()
    {
        return data;
    }
    
     public void setDistance(double distance)
    {
        this.distance=distance;
    } 
    
    public double getDistance()
    {
        return distance;
    }   

    @Override
    public int compareTo(DataSet o)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    public static class OrderByDistance implements Comparator<DataSet> 
    {
        @Override
        public int compare(DataSet o1, DataSet o2) 
        {
            return o1.getDistance() > o2.getDistance() ? 1 : (o1.getDistance() < o2.getDistance() ? -1 : 0);
        }
    }
    
    

    
 
   
    
}
