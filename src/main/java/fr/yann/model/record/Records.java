package fr.yann.model.record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Records {

	private Map<String, List<LigneRecord>> records = new HashMap<String, List<LigneRecord>>();

	public void add(LigneRecord lb) {
		
		List<LigneRecord> recordsCategorie = records.get(lb.getCategorie());
		if (recordsCategorie != null){
			recordsCategorie.add(lb);
		}
		else {
			List<LigneRecord> newRecordsCategorie = new ArrayList<LigneRecord>();
			newRecordsCategorie.add(lb);
			records.put(lb.getCategorie(), newRecordsCategorie);
		}
	}

	public List<LigneRecord> getRecords(String categorie) {
		return records.get(categorie);
	}

}
