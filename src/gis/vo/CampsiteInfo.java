package gis.vo;

public class CampsiteInfo {
	
	public CampsiteInfo() {
		super();
	}
	
	public CampsiteInfo(String name, String region, String address, int phone,
			String price, int bed, int toilet, int wifi, int first, int second, int third) {
		super();
		this.name = name;
		this.region = region;
		this.address = address;
		this.phone = phone;
		this.price = price;
		this.bed = bed;
		this.toilet = toilet;
		this.wifi = wifi;
		this.first = first;
		this.second = second;
		this.third = third;
	}
	private String name;
	private String region;
	private String address;
	private int phone;
	private String price;
	private int bed;
	private int toilet;
	private int wifi;
	private int first;
	private int second;
	private int third;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getBed() {
		return bed;
	}

	public void setBed(int bed) {
		this.bed = bed;
	}

	public int getToilet() {
		return toilet;
	}

	public void setToilet(int toilet) {
		this.toilet = toilet;
	}

	public int getWifi() {
		return wifi;
	}

	public void setWifi(int wifi) {
		this.wifi = wifi;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getThird() {
		return third;
	}

	public void setThird(int third) {
		this.third = third;
	}
	
	

}
