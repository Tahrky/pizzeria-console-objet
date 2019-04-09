package fr.pizzeria.dao.oldDao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Classe permettant la gestion des Pizza comme une véritable
 * BDD avec écriture dans un fichier pour la persistance des données
 * @author BIRABEN-BIANCHI Hugo
 */
public class PizzaFileDao implements IPizzaDao
{
	private List <Pizza> listPizzas;
	private GestionFichier gestionFichier;
	
	public PizzaFileDao ()
	{
		listPizzas = new ArrayList <Pizza> ();
		gestionFichier = new GestionFichier ("src/main/ressources/save.txt");
		
		if (gestionFichier.isEmpty())
		{
			initialisation ();
			ecriture ();
		}
		else
		{
			lecture ();
		}
	}
	
	public void initialisation ()
	{
		listPizzas.add(new Pizza ("PEP",  "Pépéroni", 	 12.50, CategoriePizza.INCONNU));
		listPizzas.add(new Pizza ("MAR",  "Margherita", 	 14.00, CategoriePizza.SANS_VIANDE));
		listPizzas.add(new Pizza ("REIN", "La Reine", 	 11.50, CategoriePizza.VIANDE));
		listPizzas.add(new Pizza ("FRO",  "La 4 Fromage",  12.00, CategoriePizza.SANS_VIANDE));
		listPizzas.add(new Pizza ("CAN",  "La cannibale",  12.50, CategoriePizza.VIANDE));
		listPizzas.add(new Pizza ("SAV",  "La savoyarde",  13.00, CategoriePizza.INCONNU));
		listPizzas.add(new Pizza ("ORI",  "L’orientale",   13.50, CategoriePizza.INCONNU));
		listPizzas.add(new Pizza ("IND",  "L’indienne", 	 14.00, CategoriePizza.INCONNU));
	}

	public List <Pizza> findAllPizzas()
	{
		return listPizzas;
	}

	public void saveNewPizza(Pizza pizza)
	{
		listPizzas.add(pizza);
		this.ecriture ();
	}
	
	public void updatePizza(String codePizza, Pizza pizza)
	{
		for (Pizza p : listPizzas)
		{
			if (p.getCode().equals(codePizza))
			{
				p.modifPizza (pizza);
			}
		}
		
		this.ecriture ();
	}

	public void deletePizza(String codePizza)
	{
		for (Pizza p : listPizzas)
		{
			if (p.getCode().equals(codePizza))
			{
				System.out.println(p.getId());
				listPizzas.remove (p);
				break;
			}
		}
		
		for (int i = 0; i < listPizzas.size (); i++)
		{
			if (listPizzas.get(i).getId() != i)
			{
				listPizzas.get(i).setId(i);
			}
		}
		
		this.ecriture ();
	}
	
	public Pizza findPizzaByCode(String codePizza)
	{
		for (Pizza pizza : listPizzas)
		{
			if (pizza.getCode().equals(codePizza.toUpperCase()))
			{
				return pizza;
			}
		}
		
		return null;
	}

	public boolean pizzaExists(String codePizza)
	{
		boolean exist = false;
		
		for (Pizza pizza : listPizzas)
		{
			if (pizza.getCode().equals(codePizza))
			{
				exist = true;
				break;
			}
		}
		
		return exist;
	}
	
	public void ecriture () 
	{
		List <String> listString = new ArrayList<String> ();
		
		for (Pizza pizza : listPizzas)
		{
				listString.add(pizza.toSave());
		}
		
		gestionFichier.ecriture(listString);
	}
	
	public void lecture ()
	{
		List <String> listString = gestionFichier.lecture();
			
		for (String s : listString)
		{
			String [] pizza = s.split(",");
			listPizzas.add(new Pizza (Integer.parseInt(pizza[0]), pizza[1], pizza[2], Double.parseDouble(pizza[3]), CategoriePizza.valueOf(pizza[4].toUpperCase())));
		}
	}
}
