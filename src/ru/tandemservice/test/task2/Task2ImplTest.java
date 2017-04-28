package ru.tandemservice.test.task2;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert.*;
import org.junit.Before;
import junit.framework.Assert;

public class Task2ImplTest
{
	private List<IElement> test_elems;
	
	@Before
    public void setUp()
	{
		final ElementExampleImpl.Context context = new ElementExampleImpl.Context();
		test_elems = new ArrayList<>();
		Collections.addAll(test_elems, new ElementExampleImpl(context, 0),
									   new ElementExampleImpl(context, 1),
									   new ElementExampleImpl(context, 2),
									   new ElementExampleImpl(context, 3));		
    }
	
    @org.junit.Test
    public void testAssignNumbers_1() throws Exception
    {
    	final ElementExampleImpl.Context context = new ElementExampleImpl.Context();
    	List<IElement> elems = new ArrayList<>();
        Collections.addAll(elems, new ElementExampleImpl(context, 1),
        						  new ElementExampleImpl(context, 3),
                				  new ElementExampleImpl(context, -1),                				  
                				  new ElementExampleImpl(context, 0));
        
        printData(elems, "Origin test 1");        
        Task2Impl.INSTANCE.assignNumbers(elems);        
        printData(elems, "Processed test 1");
        
        for (int k = 0; k < elems.size(); k++)
        	assertEquals(elems.get(k).getNumber(), test_elems.get(k).getNumber());
    }

    @org.junit.Test
    public void testAssignNumbers_2() throws Exception
    {
    	final ElementExampleImpl.Context context = new ElementExampleImpl.Context();
    	List<IElement> elems = new ArrayList<>();
        Collections.addAll(elems, new ElementExampleImpl(context, 0),
                				  new ElementExampleImpl(context, 1),
                				  new ElementExampleImpl(context, 2),
                				  new ElementExampleImpl(context, 3));
        
        printData(elems, "Origin test 2");
        Task2Impl.INSTANCE.assignNumbers(elems);        
        printData(elems, "Processed test 2");
        
        for (int k = 0; k < elems.size(); k++)
        	assertEquals(elems.get(k).getNumber(), test_elems.get(k).getNumber());
    }

    @org.junit.Test
    public void testAssignNumbers_3() throws Exception
    {
    	final ElementExampleImpl.Context context = new ElementExampleImpl.Context();
    	List<IElement> elems = new ArrayList<>();
        Collections.addAll(elems, new ElementExampleImpl(context, 0),
                				  new ElementExampleImpl(context, 1),
                				  new ElementExampleImpl(context, 3),
                				  new ElementExampleImpl(context, 2));
        
        printData(elems, "Origin test 3");
        Task2Impl.INSTANCE.assignNumbers(elems);        
        printData(elems, "Processed test 3");
        
        for (int k = 0; k < elems.size(); k++)
        	assertEquals(elems.get(k).getNumber(), test_elems.get(k).getNumber());
    }

	private void printData(List<IElement> elems, String sLabel)
	{
		System.out.println("---------------> " + sLabel + " <---------------");
		for (int k = 0; k < elems.size(); k++)
        	System.out.println(elems.get(k).getNumber());
	}
}
