package ru.tandemservice.test.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.text.html.parser.Entity;

/**
 * <h1>Задание №2</h1>
 * Реализуйте интерфейс {@link IElementNumberAssigner}.
 *
 * <p>Помимо качества кода, мы будем обращать внимание на оптимальность предложенного алгоритма по времени работы
 * с учетом скорости выполнения операции присвоения номера:
 * большим плюсом (хотя это и не обязательно) будет оценка числа операций, доказательство оптимальности
 * или указание области, в которой алгоритм будет оптимальным.</p>
 */
public class Task2Impl implements IElementNumberAssigner {

    // ваша реализация должна работать, как singleton. даже при использовании из нескольких потоков.
    public static final IElementNumberAssigner INSTANCE = new Task2Impl();

    @Override
    public void assignNumbers(final List<IElement> elements)
    {
    	// Проверка на предмет того, что коллекция неизменяема
    	if (Collections.unmodifiableCollection(elements).getClass().isInstance(elements))
    	{
    		System.err.println("It`s unmodifiable list!");
    		return;
    	}
    	    	
    	// Быстрая проверка порядка элементов
    	if (!isValidElementsOrder(elements))
    	{
    		int iMaxElemNum = getMaxElementsNum(elements);
    		
    		// Выбираем список элементов (row_num) и значений присвоенных им номеров (elem_num) в hmBrokenElements,
    		// где row_num!= elem_num, также сохраняем elem_num в sBrokenNums
    		HashMap<Integer, Integer> hmBrokenElements = new HashMap<Integer, Integer>();    		
    		Set<Integer> sBrokenNums = new HashSet<Integer>();    		
    		for (int k = 0; k < elements.size(); k++)
    		{
    			if (k != elements.get(k).getNumber())
    			{
    				hmBrokenElements.put(k, elements.get(k).getNumber());
    				sBrokenNums.add(elements.get(k).getNumber());
    			}
    		}
    		
    		// "Простой" отсев элементов
    		fixElementsNums(elements, hmBrokenElements, sBrokenNums);
    		
    		// Остались элементы с номерами, занятыми другими элементами.
    		for (Map.Entry<Integer, Integer> entry: hmBrokenElements.entrySet())
    		{
    			// Определяем позицию элемента занявшего нужный номер
    			int iPos = getPositionByNumber(elements, entry.getKey());
    			
    			// Если позиция свободна, то просто присваиваем номер элемента
    			if (iPos == -1)
    				elements.get(entry.getKey()).setupNumber(entry.getKey());
    			else // Если позиция занята, то перестыковываем занятый номер и присваиваем номер
    			{
    				elements.get(iPos).setupNumber(++iMaxElemNum);
    				elements.get(entry.getKey()).setupNumber(entry.getKey());
    			}    			
    		}
       	}
    }

    private int getPositionByNumber(final List<IElement> elements, int iElementNum)
    {
    	for (int k = 0; k < elements.size(); k++)
    	{
    		if (elements.get(k).getNumber() == iElementNum)
    			return k;
    	}
    	
    	return -1;
    }
    
	/** Максимальный номер элемента в коллекции */
    private int getMaxElementsNum(final List<IElement> elements)
	{
		int iMaxNum = -1;
		
		for (int k = 0; k < elements.size(); k++)
		{
			if (elements.get(k).getNumber() > iMaxNum)
				iMaxNum = elements.get(k).getNumber();
		}
		
		return iMaxNum;
	}
    
    /**
     * Отсев элементов путём фиксации порядковых номеров и в номера элементов, для простых,
     * не пересекающихся с другими элементами, случаев.
     * elements - коллекция элементов, hmBrokenElements - "элементы" с ошибками,
     * sBrokenNums - номера элементов с ошибками.
     * */
	private void fixElementsNums(final List<IElement> elements,
								 HashMap<Integer, Integer> hmBrokenElements,
								 Set<Integer> sBrokenNums)
	{
		ArrayList<Integer> alExclude = new ArrayList<Integer>();
		
		for (Map.Entry<Integer, Integer> entry: hmBrokenElements.entrySet())
		{
			// Если номера элемента нет в списке "неверных" номеров, то помещаем его
			// в очередь на присваивание номера
			if (!sBrokenNums.contains(entry.getKey()))
				alExclude.add(entry.getKey());
		}
		    		
		// Присваиваем номера элемента и убираем из коллекции "неправильных"
		for (int k = 0; k < alExclude.size(); k++)
		{
			elements.get(alExclude.get(k)).setupNumber(alExclude.get(k));
			hmBrokenElements.remove(alExclude.get(k));
			sBrokenNums.remove(alExclude.get(k));
		}
	}

    /** Проверка нарушения порядка следования номеров элементов в коллекции */
	private boolean isValidElementsOrder(final List<IElement> elements)
	{
		for (int k = 0; k < elements.size(); k++)
    	{
    		if (elements.get(k).getNumber() != k)
    			return false;
    	}
		
		return true;
	}

}
