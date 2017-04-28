package ru.tandemservice.test.task1;

/**
 * Интерфейс разбивки строки на слова.
 */
public interface IWordSplitter
{
	/** Разбивка строки на слова */
	public String[] split(String sPhrase);
	
	/** Определине, является ли переданная строка числом */
	public boolean isNumber(String sWord);
}
