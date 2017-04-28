package ru.tandemservice.test.task1;

import java.util.List;

public class Printing
{
	public static void printListOfStringsArrs(List<String[]> lst, String label)
	{
		System.out.println(label);
    	for (String[] resSort : lst)
		{
			for (String msg : resSort)
				System.out.print(msg + " ");
			System.out.println("");
		}
    	System.out.println("============================");
	}
}
