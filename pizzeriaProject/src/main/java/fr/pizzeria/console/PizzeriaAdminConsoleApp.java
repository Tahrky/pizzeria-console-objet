package fr.pizzeria.console;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.PersonnalSqlException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.menu.ListerCommandesAttenteService;
import fr.pizzeria.menu.MenuFactory;

/**
 * Cette classe permet de gérer le menu d'affichage de l'application pour un
 * administrateur.
 * 
 * @author BIRABEN-BIANCHI Hugo
 */
public class PizzeriaAdminConsoleApp implements IPizzeriaConsole
{
	protected static Logger logger = LoggerFactory.getLogger(PizzeriaAdminConsoleApp.class);
	static String choice = null;
	static MenuFactory menu = new MenuFactory();
	static boolean sortiBoucle = false;
	static boolean password = false;
	
	public void displayMenu ()
	{
		IPizzeriaConsole.clean();
		logger.info("***** Pizzeria Administration *****");
		logger.info("1.  Lister les pizzas ");
		logger.info("2.  Ajouter une nouvelle pizza");
		logger.info("3.  Mettre à jour une pizza");
		logger.info("4.  Supprimer une pizza");
		logger.info("5.  Lister toutes les commandes non traitées");
		logger.info("6.  Ajouter un livreur à une livraison");
		logger.info("7.  Expédier toutes les commandes");
		logger.info("99. Quitter l'application");
		logger.info("Veuillez saisir votre choix : ");
	}
	
	public static void enterPassword (Scanner questionUser)
	{
		IPizzeriaConsole.clean();
		logger.info("Veuillez saisir le mot de passe pour rentrer dans la partie administrateur !");
		logger.info("(le mot de passe est admin)");
		choice = questionUser.nextLine();
		
		if (choice.equals("admin"))
		{
			password = true;
		}
	}
	
	public static void manageMenu (Scanner questionUser) throws StockageException
	{
		if (Integer.parseInt(choice) == 1)
		{
			menu.create("list").executeUC(questionUser);
		}
		else if (Integer.parseInt(choice) == 2)
		{
			menu.create("ajout").executeUC(questionUser);
		}
		else if (Integer.parseInt(choice) == 3)
		{
			menu.create("modif").executeUC(questionUser);
		}
		else if (Integer.parseInt(choice) == 4)
		{
			menu.create("suppr").executeUC(questionUser);
		}
		else if (Integer.parseInt(choice) == 5)
		{
			ListerCommandesAttenteService l = (ListerCommandesAttenteService) menu
					.create("listerCommandesAttente");
			ListerCommandesAttenteService.addPrecision();
			l.executeUC(questionUser);
		}
		else if (Integer.parseInt(choice) == 6)
		{
			menu.create("ajouterLivreur").executeUC(questionUser);
		}
		else if (Integer.parseInt(choice) == 7)
		{
			menu.create("expedierCommande").executeUC(questionUser);
		}
		else if (Integer.parseInt(choice) == 99)
		{
			sortiBoucle = true;
		}
		else
		{
			logger.info("Choix invalide, veuillez recommencer !");
		}
	}
	
	public void displayException (String e)
	{
		logger.info(e);
	}
	
	@Override
	public void display(Scanner questionUser)
	{
		enterPassword (questionUser);
		
		while (password && !sortiBoucle)
		{
			displayMenu ();
			setChoice(questionUser.nextLine());

			try
			{
				manageMenu (questionUser);
			}
			catch (NumberFormatException e)
			{
				// Fait boucler le programme sans interaction de l'utilisateur
			}
			catch (SavePizzaException e)
			{
				displayException (e.getMessage());

				if (e.getMessage().equals("codeTropCourt"))
				{
					displayException ("Code trop court");
				}
			}
			catch (StockageException | PersonnalSqlException e)
			{
				displayException (e.getMessage());
			}
		}
	}

	public static String getChoice()
	{
		return choice;
	}

	public static void setChoice(String choice)
	{
		PizzeriaAdminConsoleApp.choice = choice;
	}
}
