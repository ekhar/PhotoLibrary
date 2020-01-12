import java.util.ArrayList;
import java.util.HashSet;

//Create a class Photo library
public class PhotoLibrary extends PhotographContainer {
	// Instance variables
	private final int id;
	private HashSet<Album> albums;

	// Constructor
	public PhotoLibrary(String name, int id) {
		super(name);
		this.id = id;
		this.albums = new HashSet<Album>();
	}

	// Accessors
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public HashSet<Album> getAlbums() {
		return albums;
	}

	// makes an album
	public boolean createAlbum(String albumName) {
		Album album = new Album(albumName);
		return albums.add(album);
	}

	// removes an album
	public boolean removeAlbum(String albumName) {
		for (Album album : albums) {
			if (album.getName().equals(albumName)) {
				return albums.remove(album);
			}
		}
		return false;
	}

	public boolean addPhotoToAlbum(Photograph p, String albumName) {
		for (Album album : albums) {
			if (album.getName().equals(albumName) && photos.contains(p)) {
				return album.addPhoto(p);
			}
		}
		return false;
	}

	public boolean removePhotoFromAlbum(Photograph p, String albumName) {
		for (Album album : albums) {
			if (album.getName().equals(albumName)) {
				return album.removePhoto(p);
			}
		}
		return false;
	}

	private Album getAlbumByName(String albumName) {
		for (Album album : albums) {
			if (album.getName().equals(albumName)) {
				return album;
			}
		}
		return null;
	}

	// Override equals() to see if 2 PhotoLibrary instances are the same
	public boolean equals(Object obj) {
		if (obj instanceof PhotoLibrary) {
			PhotoLibrary photoLib = (PhotoLibrary) obj;
			return photoLib.id == id;
		} else {
			return false;
		}
	}

	// Method to remove a pic from the library
	public boolean removePhoto(Photograph p) {
		boolean temp = false;
		for (Album album : albums) {
			if (album.hasPhoto(p)) {
				album.removePhoto(p);
				temp = true;
			}
		}
		for (Photograph photo : photos) {
			if (photo.equals(p)) {
				photos.remove(photo);
				temp = true;
			}
		}
		return temp;
	}

	// Override toString()
	public String toString() {
		String str = "Name of the photo library: " + name;
		str += "\nId of the library: ";
		str += id;
		str += "\nPhotos in the library:";
		int count = 1;
		for (Photograph photo : photos) {
			str += "\nPhoto ";
			str += count;
			str += "\n" + photo;
			count++;
		}
		for (Album album : albums) {
			str += "\nAlbum";
			str += album.getName();
			str += "\n";

		}
		return str;
	}

	// Method to calculate percent of equal photos between 2 Libraries
	public static double similarity(PhotoLibrary a, PhotoLibrary b) {
		ArrayList<Photograph> temp = PhotoLibrary.commonPhotos(a, b);
		int size = 0;
		if (temp.size() == 0) {
			return 0.0;
		} else {
			if (a.photos.size() < b.photos.size()) // assigns size to the biggest length object
				size = a.photos.size();
			else
				size = b.photos.size();
			return Double.valueOf(temp.size()) / size;
		}
	}

	// Method to return shared photos
	public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b) {
		ArrayList<Photograph> aPhotos = a.getPhotos();
		ArrayList<Photograph> bPhotos = b.getPhotos();
		ArrayList<Photograph> temp = new ArrayList<Photograph>();
		for (Photograph photoA : aPhotos) {
			for (Photograph photoB : bPhotos) {
				if (photoA.equals(photoB)) {
					temp.add(photoA);
				}
			}
		}
		return temp;
	}
}
