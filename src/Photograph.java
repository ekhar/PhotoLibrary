import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//Create a class Photograph
public class Photograph implements Comparable<Photograph> {
	// Instance variables
	protected BufferedImage imageData = null;
	private String caption;
	private String filename;
	private String dateTaken;
	private int rating;

	// Constructor
	public Photograph(String caption, String filename) {
		this.caption = caption;
		this.filename = filename;
		this.dateTaken = "1901-01-01";
		this.rating = 0;
	}

	public Photograph(String caption, String filename, String dateTaken, int rating) {
		this.caption = caption;
		this.filename = filename;
		if (datecheck(dateTaken)) {
			this.dateTaken = dateTaken;
		} else {
			this.dateTaken = "1901-01-01";
		}
		if (rating >= 0 && rating <= 5) {
			this.rating = rating;
		} else {
			this.rating = 0;
		}
	}

	// Accessors
	public String getCaption() {
		return caption;
	}

	public String getFilename() {
		return filename;
	}

	public String getDateTaken() {
		return dateTaken;
	}

	public int getRating() {
		return rating;
	}
	
	public BufferedImage getImageData() {
		return imageData;
	}
	// Mutators
	public void setFileName(String newname) {
			this.filename = newname;
	}
	
	
	public void setDateTaken(String newdate) {
		if (datecheck(newdate)) {
			this.dateTaken = newdate;
		}
	}

	public void setRating(int newrating) {
		if (newrating >= 0 && newrating <= 5) {
			this.rating = newrating;
		} else {
			rating = 0;
		}
	}

	public void setCaption(String caption1) {
		this.caption = caption1;
	}
	
	public void setImageData(String filename) {
		try 
		{
		    imageData = ImageIO.read(new File(filename));
		    setFileName(filename);
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		
	}

	private static boolean datecheck(String date) {
		boolean checkFormat = false;

		if (date.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
			if (date.charAt(4) == '-' && date.charAt(7) == '-' && date.length() == "1901-01-01".length()
					&& Integer.parseInt(date.substring(5, 7)) <= 12 && Integer.parseInt(date.substring(8, 10)) <= 31) {
				checkFormat = true;
			}
		}
		return checkFormat;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Photograph) {
			Photograph photo = (Photograph) obj;
			return (photo.caption.equals(caption) && photo.filename.equals(filename));
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Photo caption: " + caption + "\nFile name: " + filename + "\nDate Taken:" + dateTaken;
	}

	@Override
	public int hashCode() {

		return (int) filename.hashCode();

	}

	@Override
	public int compareTo(Photograph p) {
		if (year(p) > year(this)) {
			return -1;
		} else if (year(p) < year(this)) {
			return 1;
		}

		else {

			if (month(p) > month(this)) {
				return -1;
			} else if (month(p) < month(p)) {
				return 1;
			} else {
				if (day(p) > day(this)) {
					return -1;
				} else if (day(p) < day(this)) {
					return 1;
				} else {
					//return this.getCaption().compareTo(p.getCaption());
					return 0;
				}
			}
		}

	}
	
	//loads the Image data from the file and stores it into theimageData
	public boolean loadImageData(String filename) {
		try 
		{
		    imageData = ImageIO.read(new File(filename));
		    setFileName(filename);
		    return true;
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		    return false;
		}
		
	}
	
	// helper method - finds day of a photo
	private int day(Photograph v) {
		return Integer.parseInt(v.getDateTaken().substring(8));
	}

	// helper method - finds month of a photo
	private int month(Photograph v) {
		return Integer.parseInt(v.getDateTaken().substring(5, 7));
	}

	// helper method - finds year of a photo
	private int year(Photograph v) {
		return Integer.parseInt(v.getDateTaken().substring(0, 4));
	}

}