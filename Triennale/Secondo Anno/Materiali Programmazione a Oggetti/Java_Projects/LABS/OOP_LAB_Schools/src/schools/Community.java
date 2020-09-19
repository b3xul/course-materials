package schools;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a community.
 * A community may consist of several {@link Municipality municipalities} belonging to the community.
 * There are two {@link Community.Type types} of community:
 * <ul>
 * <li>{@code Community.Type.MONTANA}: mountain community
 * <li>{@code Community.Type.COLLINARE}: hill community
 * </ul>
 */
public class Community {
	
	/**
	 * Enumeration of valid {@link Community} types.
	 *
	 */
	public enum Type {
		/**
		 * Mountain community
		 */
		MONTANA, 
		/**
		 * Hill community
		 */
		COLLINARE };

	private String name;
	private Type type;
	private Map<String,Municipality> municipalities=new HashMap<>();
	
	public Community(String name) {
		this.name=name;
	}
	
	public Community(String name, Type type) {
		this.name=name;
		this.type=type;
	}
	/**
	 * Getter method for the community name
	 * 
	 * @return name of the community
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Getter method for the community type
	 * 
	 * @return type of the community
	 */
	public Type getType(){
		return type;
	}

	/**
	 * Retrieves the municipalities the belong to this community
	 * 
	 * @return collection of municipalities
	 */
	/*N.B. Non restituire la lista!! Restituire una copia o una versione non modificabile!!*/
	
	public Collection<Municipality> getMunicipalities() {
		/*copia?*/
		return municipalities.values();
	}

	public void addMunicipality(Municipality town) {
		municipalities.put(town.getName(), town);
		return;
	}
}
