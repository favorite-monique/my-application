package gcu.mpd.bgsdatastarter.Ndubuisi_Monica_Onyinye_S2040060.model;

public class EarthQuake {
	public String title;
	public String link;
	public String pubDate;
	public double lat;
	public double _long;
	public double magnitude;
	public double depth;

	public void setTitle(String title) {
		this.title=title;
	}

	public void setOriginDateTime(String pubDate) {
		this.pubDate=pubDate;
	}

	public void setLatitude(double latitude) {
		this.lat=latitude;
	}

	public void setLongitude(double longitude) {
		this._long=longitude;
	}

	public void setLink(String link) {
		this.link=link;
	}

	public void setMagnitude(double magnitude) {
		this.magnitude=magnitude;
	}

	public void setDepth(int depth) {
		this.depth=depth;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getPubDate() {
		return pubDate;
	}

	public double getLat() {
		return lat;
	}

	public double get_long() {
		return _long;
	}

	public double getMagnitude() {
		return magnitude;
	}

	public double getDepth() {
		return depth;
	}

	@Override
	public String toString() {
		return "EarthQuake{" +
				"title='" + title + '\'' +
				", link='" + link + '\'' +
				", pubDate='" + pubDate + '\'' +
				", lat=" + lat +
				", _long=" + _long +
				", magnitude=" + magnitude +
				", depth=" + depth +
				'}';
	}
}