package ru.tandemservice.test.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.Painter;

/**
 * <h1>Задание №1</h1>
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными средствами java.</p>
 */
public class Task1Impl implements IStringRowsListSorter {

    // ваша реализация должна работать, как singleton. даже при использовании из нескольких потоков.
    public static final IStringRowsListSorter INSTANCE = new Task1Impl();

    @Override
    public void sort(final List<String[]> rows, final int columnIndex)
    {    	
        // Проверка граничных значений номера столбца для сортировки 
    	try
    	{
			chkBounds(rows, columnIndex);
		}
    	catch (Exception e)
    	{
			e.printStackTrace();
			return;
		}
    	
    	// Выбираем данные колоноки columnIndex для сортировки,
    	// с сохранением № позиции в оригинальном массиве new Pair<rowNum, ColumnValue>
    	ArrayList<Pair<Integer, String>> lstSorting = new ArrayList<Pair<Integer, String>>();
    	for (int k = 0; k < rows.size(); k++)
    		lstSorting.add(new Pair<Integer, String>(k, rows.get(k)[columnIndex]));
    	
    	// Сортировка значений
    	Collections.sort(lstSorting, new Comparator<Pair<Integer, String>>()
    	{
			@Override
			public int compare(Pair<Integer, String> o1, Pair<Integer, String> o2)
			{
				// Проверка строки на null или пусто			
				if (o1.getRight() == null)
					return -1;
				if (o2.getRight() == null)
					return 1;
				if (o1.getRight() == "")
					return -1;
				if (o2.getRight() == "")
					return 1;
				
				IWordSplitter wordSplitter = new CharDigitWordSplitter();
				
				// Получение массивов токенов				
				String[] sToken_1 = wordSplitter.split(o1.getRight());				
				String[] sToken_2 = wordSplitter.split(o2.getRight());
				
				// Определение min/max размеров массивов токенов
				int iMinTokSize = Math.min(sToken_1.length, sToken_2.length);
				int iMaxTokSize = Math.max(sToken_1.length, sToken_2.length);
				
				for (int k = 0; k < iMinTokSize; k++)
				{					
					// Сравнение токенов-чисел
					if (wordSplitter.isNumber(sToken_1[k]) & wordSplitter.isNumber(sToken_2[k]))
					{						
						int iIntRes = new Integer(sToken_1[k]).compareTo(new Integer(sToken_2[k]));
						if (iIntRes == 0)
							continue;
						else
							return iIntRes;
					}									
					
					// Сравнение токенов-строк
					int iStrRes = sToken_1[k].compareTo(sToken_2[k]);
					if (iStrRes == 0)
						continue;
					else
						return iStrRes;					
				}
				
				// Обошли все токены, различий нет. Сравниваем остаток токенов.
				if (iMinTokSize == iMaxTokSize)
					return 1; // Сортировка должна быть устойчива к исходной сортировке списка
				
				// Более длинный массив идёт следующим в порядке сортировки
				if (sToken_1.length == iMinTokSize)
					return 1;
				else
					return -1;								
			}			
		});
    	
    	// Заполняем массив записями в отсортированном порядке
    	Object[] arrSortedRows = new Object[lstSorting.size()];
    	for (int k = 0; k < lstSorting.size(); k++)
    		arrSortedRows[k] = rows.get(lstSorting.get(k).getLeft());
    	
    	// Создаём список List<String[]> sorted_rows
    	List<String[]> lstSortedRows = new ArrayList<String[]>();
    	for (int i = 0; i < arrSortedRows.length; i++)
    		lstSortedRows.add((String[])arrSortedRows[i]);
    	
    	Printing.printListOfStringsArrs(lstSortedRows, "Sorted values:");
    }
    
    /** Пробежать по всему списку и выяснить размер массивов. Если
	    columnIndex >= размера массива, то ругнуться на выход за пределы
	    доступных границ */ 
    private void chkBounds(final List<String[]> rows, final int columnIndex) throws Exception
    {
		if (columnIndex < 0)
    		throw new Exception("Номер столбца для сортировки должен быть > 0!");
		
		for (int i = 0; i < rows.size(); i++)
    		if (columnIndex >= rows.get(i).length)
    			throw new Exception("Номер столбца для сортировки выходит за допустимые границы!");
	}
}

// Получение массивов токенов Ток_1 и Ток_2				

// Цикл по МинТок
	// Если (Ток_1[i] = null)
    	// return 1
	// Если (Ток_2[i] = null)
		// return -1;
	// Если (Ток_1[i] = "")
		// return 1
	// Если (Ток_2[i] = "")
		// return -1;
	
	// Сравнение токенов-чисел
	// Если (Ток_1[i].число и Ток_2[i].число)
		// if (Ток_1[i].число > Ток_2[i].число)
			// return 1;
		// Если (Ток_1[i].число < Ток_2[i].число)
			// return -1;
		// Иначе
			// continue
	
	// Сравнение токенов-строк
	// рез = Ток_1[i].compare(Ток_2[i])
	// Если (рез = 0)
		// continue;
	// Иначе
		// return рез;
	
// Обошли все токены, различий нет
// Если (МинТок = МаксТок)
	// return 1; // Сортировка должна быть устойчива к исходной сортировке списка
// Если (Ток_1.size = МинТок)
	// return 1;
// Если (Ток_1.size = МинТок)
	// return -1;

