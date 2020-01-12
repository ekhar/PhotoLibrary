import java.util.ArrayList;
import java.util.HashSet;

public abstract class PhotographContainer {

	protected String name;
	protected ArrayList<Photograph> photos;

	// Constructor
	public PhotographContainer(String name1) {
		this.name = name1;
		this.photos = new ArrayList<Photograph>();
	}

	// Accessor
	public String getName() {
		return name;
	}

	// Accessor
	public ArrayList<Photograph> getPhotos() {
		return photos;
	}

	// Mutator
	public void setName(String newname) {
		this.name = newname;
	}

	// Adds photo to list and returns true. If photo is improperly formatted it
	// returns false.
	public boolean addPhoto(Photograph p) {
		if (p != null) {
			return photos.add(p);
		} else {
			return false;
		}
	}

	// Checks to see if a photo exists in photos list
	public boolean hasPhoto(Photograph p) {
		for (Photograph v : photos) {
			if (p.equals(v)) {
				return true;
			}
		}
		return false;
	}

	// removes a photo from photos list and returns true. If photo is not in list,
	// returns false.
	public boolean removePhoto(Photograph p) {
		if (photos.remove(p)) {
			photos.remove(p);
			return true;
		} else {
			return false;
		}
	}

	// returns number of elements in photos list
	public int numPhotographs() {
		return this.photos.size();
	}

	// returns photos of specific rating or higher
	public ArrayList<Photograph> getPhotos(int rating) {
		ArrayList<Photograph> temp = new ArrayList<Photograph>();
		for (Photograph v : photos) {
			if (v.getRating() >= rating) {
				temp.add(v);
			}
		}
		return temp;
	}

	public ArrayList<Photograph> getPhotosInYear(int year) {
		if (year < 0) {
			return null;
		} else {
			ArrayList<Photograph> temp = new ArrayList<Photograph>();
			for (Photograph v : photos) {
				if (year(v) == year) {
					temp.add(v);
				}
			}
			return temp;
		}
	}

	// returns photos of a specific month and year
	public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
		ArrayList<Photograph> temp = new ArrayList<Photograph>();
		if (month <= 0 || month >= 12) {
			return temp;
		}
		for (Photograph v : photos) {
			if (year(v) == year && (month(v) == month)) {
				temp.add(v);
			}
		}
		return temp;
	}

	// returns photos between two dates
	public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate) {
		ArrayList<Photograph> temp = new ArrayList<Photograph>();
		if ((datecheck(beginDate) && datecheck(endDate))) {
			int beginYear = Integer.parseInt(beginDate.substring(0, 4));
			int beginMonth = Integer.parseInt(beginDate.substring(5, 7));
			int beginDay = Integer.parseInt(beginDate.substring(8));

			int endYear = Integer.parseInt(endDate.substring(0, 4));
			int endMonth = Integer.parseInt(endDate.substring(5, 7));
			int endDay = Integer.parseInt(endDate.substring(8));

			boolean beforeisbefore; // checks if the begin date is actually before the end date chronologically

			if (beginYear > endYear) // if the beginning year is bigger than the ending year, this will be false
				beforeisbefore = false;
			else if (beginYear < endYear)
				beforeisbefore = true;
			else {// if begyear = endyear enter more nested conditionals
				if (beginMonth > endMonth)
					beforeisbefore = false;
				else if (beginMonth < endMonth)
					beforeisbefore = true;
				else {// if both month and year are equal
					if (beginDay > endDay)
						beforeisbefore = false;
					else
						beforeisbefore = true;
				}
			}

			// if before date is actually before the end date
			if (beforeisbefore) {
				for (Photograph v : photos) {

					// if beginning year is less than end year
					if (year(v) > beginYear && year(v) < endYear) {
						temp.add(v);
					}

					// if years are the same and the date is between the months
					else if ((year(v) == beginYear || year(v) == endYear) && month(v) > beginMonth
							&& month(v) < endMonth) {
						temp.add(v);
					}

					else if ((year(v) == beginYear && year(v) < endYear) && month(v) > beginMonth) {
						temp.add(v);
					} else if ((year(v) == endYear && year(v) > beginYear) && month(v) > endMonth) {
						temp.add(v);
					}

					// years are same, months are same, and the day is inclusively between the range
					// of days
					else if (((year(v) == beginYear || year(v) == endYear)
							&& (month(v) == beginMonth || month(v) == endMonth)) && day(v) >= beginDay
							&& day(v) <= endDay) {
						temp.add(v);
					}
				}
			}
			return temp;
		}
		return null;
	}

	// helper method to find the day of a photo
	private int day(Photograph v) {
		return Integer.parseInt(v.getDateTaken().substring(8));
	}

	// helper method to find the month of a photo
	private int month(Photograph v) {
		return Integer.parseInt(v.getDateTaken().substring(5, 7));
	}

	// helper method to find the year of a photo
	private int year(Photograph v) {
		return Integer.parseInt(v.getDateTaken().substring(0, 4));
	}

	// helper method to check if date is properly formatted
	private boolean datecheck(String date) {
		boolean checkFormat = false;

		if (date.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
			if (date.charAt(4) == '-' && date.charAt(7) == '-' && date.length() == "1901-01-01".length()
					&& Integer.parseInt(date.substring(5, 7)) <= 12 && Integer.parseInt(date.substring(8, 10)) <= 31) {
				checkFormat = true;
			}
		}
		return checkFormat;
	}

	// Overrides
	@Override
	public boolean equals(Object o) {
		if (o instanceof PhotographContainer) {
			PhotographContainer obj = (PhotographContainer) o;
			return (obj.getName().equals(name));
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Photograph Container Name: " + name + " Photo Names: " + photos.toString();
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
