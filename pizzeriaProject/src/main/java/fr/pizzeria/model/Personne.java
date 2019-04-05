/**
 * 
 */
package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Personne
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column
	String nom;
	@Column
	String prenom;
	
	@Override
	public String toString ()
	{
		return (id + " " + nom + " " + prenom);
	}
	
	/**
	 * Getter
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}
	/**
	 * Setter
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	/**
	 * Getter
	 * @return the nom
	 */
	public String getNom()
	{
		return nom;
	}
	/**
	 * Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	/**
	 * Getter
	 * @return the prenom
	 */
	public String getPrenom()
	{
		return prenom;
	}
	/**
	 * Setter
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}
}
