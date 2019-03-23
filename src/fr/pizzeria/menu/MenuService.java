package fr.pizzeria.menu;

import java.util.Scanner;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaFileDao;
import fr.pizzeria.exception.*;

/**
 * @author BIRABEN-BIANCHI Hugo
 */
public abstract class MenuService  
{
	protected static IPizzaDao gestionnairePizza = new PizzaFileDao ();
		
	public abstract void executeUC (Scanner scanner) throws StockageException;
}