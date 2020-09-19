package schools;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
/**
 * Represents the schools.
 * Schools are aggregations of branches, 
 * each school has one or more {@link Branch branches} (at least one)
 *
 */
public class School {

	private String name;
	private String code;
	private int grade;
	private String description;
	private Map<Integer,Branch> branches;
	
	public School(String name,String code,int grade,String description) {
		this.name=name;
		this.code=code;
		this.grade=grade;
		this.description=description;
		this.branches=new HashMap<>();
	}
	/**
	 * Getter method for the school name
	 * @return name of the school
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter method for the school code
	 * @return code of the school
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Getter method for the grade
	 * @return grade
	 */
	public int getGrade() {
		return this.grade;
	}

	/**
	 * Getter method for the description
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Retrieves the branches of the school.
	 * 
	 * @return collection of the branches
	 */
	public Collection<Branch> getBranches() {
		return this.branches.values();
	}
	
	public void addBranch(Branch bra) {
		branches.put(bra.getCode(),bra);
	}

}
