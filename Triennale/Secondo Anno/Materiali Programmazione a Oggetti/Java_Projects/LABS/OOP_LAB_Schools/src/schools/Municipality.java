package schools;

import java.util.Collection;
import java.util.Optional;

/**
 * Represents a municipality.
 * A municipality may belong to a {@link Community}
 *
 */

public class Municipality {

	private String name;
	private String province;
	private Community community;
	/*private Collection<Branch> sedi;? Al limite fare più strutture dati per gestire più facilmente le ricerche! es. lista di sedi in una comunity!*/
	
	public Municipality(String name,String province,Community community) {
		this.name=name;
		this.province=province;
		this.community=community;
	}
	/**
	 * Getter method for the municipality's name
	 * 
	 * @return name of the municipality
	 */
	public String getName() {
		return this.name;	
	}
	
	/**
	 * Getter method for province where the municipality lies
	 * 
	 * @return province of the municipality
	 */
	public String getProvince() {
		return this.province;
	}

	/**
	 * Retrieves the community the municipality belongs to as an {@link Optional}
	 * If the municipality the optional will be empty.
	 * 
	 * @return optional community
	 */
	public Optional<Community> getCommunity() {
		Optional<Community> OpCommunity=Optional.ofNullable(this.community);
		
		return OpCommunity;
	}	
	
	/**
	 * Retrieves all the school branches located in the municipality 
	 * 
	 * @return collection of branches
	 */
	public Collection<Branch> getBranches() {
		return null;
	}

}
