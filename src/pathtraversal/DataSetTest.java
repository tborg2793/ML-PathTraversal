/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework2;

/**
 *
 * @author Thomas-Home
 */
import java.util.List;

/**
 *
 * @author tb
 */
public class DataSetTest {
    
    private List<Integer> data;
    private int label;
    private boolean correct;
    
    DataSetTest(int label,List<Integer>data)
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
    
    public boolean getCorrect()
    {
        return correct;
    }
        
    public void setCorrect (boolean correct)
    {
        this.correct = correct;
    }

    

    

    

    
    
   
}
