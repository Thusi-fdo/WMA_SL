package Code;

public class Waste {
	
	private int wID;
	private String WasteTitle;
	private String Type;
	private String Description;
	private float EstimatedWeight;
	private String hazardousLevel;
	
	
	public Waste(int wID, String wasteTitle, String type, String description, float estimatedWeight,
			String hazardousLevel) {
		this.wID = wID;
		WasteTitle = wasteTitle;
		Type = type;
		Description = description;
		EstimatedWeight = estimatedWeight;
		this.hazardousLevel = hazardousLevel;
	}
	


	public int getwID() {
		return wID;
	}


	public void setwID(int wID) {
		this.wID = wID;
	}


	public String getWasteTitle() {
		return WasteTitle;
	}


	public void setWasteTitle(String wasteTitle) {
		WasteTitle = wasteTitle;
	}


	public String getType() {
		return Type;
	}


	public void setType(String type) {
		Type = type;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public String getHazardousLevel() {
		return hazardousLevel;
	}


	public void setHazardousLevel(String hazardousLevel) {
		this.hazardousLevel = hazardousLevel;
	}


	public float getEstimatedWeight() {
		return EstimatedWeight;
	}


	public void setEstimatedWeight(float estimatedWeight) {
		EstimatedWeight = estimatedWeight;
	}
	
	
	
}
