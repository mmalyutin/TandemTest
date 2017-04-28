package ru.tandemservice.test.task1;

import java.util.ArrayList;

/**
 * Разбивка переданной строки на массив токенов чисел и строк, в порядке следования в строке.
 * Пробелы считаются частью строкового токена.
 * */
public class CharDigitWordSplitter implements IWordSplitter
{
	public String[] split(String sPhrase)
	{
		ArrayList<String> lstSplited = new ArrayList<String>();

		boolean bPin = true;
		String sToken = "";

		for (char chr : sPhrase.toCharArray())
		{
			if (isNumeric(chr))
			{
				if (bPin)
					sToken += chr;
				else
				{
					if (sToken != "")
						lstSplited.add(sToken);
					
					lstSplited.add(sToken);
					bPin = true;
					sToken = "";
					sToken += chr;
				}
			}
			else
			{
				if (!bPin)
					sToken += chr;
				else
				{
					if (sToken != "")
						lstSplited.add(sToken);
					
					bPin = false;        			
					sToken = "";
					sToken += chr;
				}        		
			}        	
		}

		lstSplited.add(sToken);
		return lstSplited.toArray(new String[lstSplited.size()]);
	}

	private static boolean isNumeric(char chr)
	{
		if (chr == '1')
			return true;
		if (chr == '2')
			return true;
		if (chr == '3')
			return true;
		if (chr == '4')
			return true;
		if (chr == '5')
			return true;
		if (chr == '6')
			return true;
		if (chr == '7')
			return true;
		if (chr == '8')
			return true;
		if (chr == '9')
			return true;
		if (chr == '0')
			return true;
		return false;
	}

	@Override
	public boolean isNumber(String sWord)
	{
		if (sWord.equals(""))
			return false;
		
		for (char chr : sWord.toCharArray())
			if (!isNumeric(chr))
				return false;
		
		return true;
	}
}
