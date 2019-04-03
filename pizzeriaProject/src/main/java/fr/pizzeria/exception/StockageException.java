/**
 * 
 */
package fr.pizzeria.exception;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class StockageException extends Exception
{
	private static final long serialVersionUID = -1030966979133115038L;

	public StockageException(String msg) 
	{
		super(msg);
	}
	
	public StockageException(Exception e) 
	{
		super(e);
	}

}