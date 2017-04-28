package ru.tandemservice.test.task1;

import java.util.ArrayList;
import java.util.List;

public class TaskImp1Test
{
	public static void main(String[] args)
	{
		List<String[]> lst = new ArrayList<String[]>();
		lst.add(new String[]{ "324311", "fd3", "ds12"});
		lst.add(new String[]{ "3cg325dx", "js55w", "346792"});
		lst.add(new String[]{ "3cg325dx", "js55w", "3467924eqf"});
		lst.add(new String[]{ "324311", "fd3", ""});
		lst.add(new String[]{ "fdd24325dz", "14ssv3w", "32792"});
		lst.add(new String[]{ "324325df", "fdss55w", "ds492"});
		lst.add(new String[]{ "324325df", "fdss55w", null});
		Printing.printListOfStringsArrs(lst, "Origin values:");
		
		Task1Impl tsk = new Task1Impl();
		tsk.sort(lst, 2);
	}
}
