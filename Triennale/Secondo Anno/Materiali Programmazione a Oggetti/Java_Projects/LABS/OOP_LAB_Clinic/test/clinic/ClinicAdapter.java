package clinic;

import java.util.Collection;

/**
 * This class is used just to open up the visibility of three methods that
 * were mistakenly made too narrow and are not accessible by tests
 * 
 * @author mtk
 *
 */
public class ClinicAdapter extends Clinic {

	@Override
	public Collection<Integer> idleDoctors(){
		return super.idleDoctors();
	}
	
	@Override
	public Collection<Integer> busyDoctors(){
		return super.busyDoctors();
	}

	@Override
	public Collection<String> doctorsByNumPatients(){
		return super.doctorsByNumPatients();
	}
}
