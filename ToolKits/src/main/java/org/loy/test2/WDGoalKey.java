package org.loy.test2;

import java.io.Serializable;

public class WDGoalKey implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	
	private String goalID;
	
	private String planID;

	public WDGoalKey(String goalIDParam, String planIDParam) {
		goalID = goalIDParam;
		planID = planIDParam;
	}
	
	@Override
	public String toString() {
		return "WDGoalKey [goalID=" + goalID + ", planID=" + planID + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goalID == null) ? 0 : goalID.hashCode());
		result = prime * result + ((planID == null) ? 0 : planID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WDGoalKey other = (WDGoalKey) obj;
		if (goalID == null) {
			if (other.goalID != null)
				return false;
		} else if (!goalID.equals(other.goalID))
			return false;
		if (planID == null) {
			if (other.planID != null)
				return false;
		} else if (!planID.equals(other.planID))
			return false;
		return true;
	}

	public String getGoalID() {
		return goalID;
	}

	public void setGoalID(String goalID) {
		this.goalID = goalID;
	}

	public String getPlanID() {
		return planID;
	}

	public void setPlanID(String planID) {
		this.planID = planID;
	}

	public static void main(){
		WDGoalKey key1 = new WDGoalKey("660201", "888814");
		WDGoalKey key2 = new WDGoalKey("660201", "888814");
		
		System.out.println(key1.equals(key2));
		
	}
	
}
