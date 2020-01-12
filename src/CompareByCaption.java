import java.util.Comparator;

public class CompareByCaption implements Comparator<Photograph> {

	@Override
	// if photo1 caption is equal to photo2 caption, compare ratings. Otherwise
	// compare captions
	public int compare(Photograph p1, Photograph p2) {
		int total = p1.getCaption().compareTo((p2.getCaption()));
		if (total == 0) {
			return p2.getRating() - p1.getRating();
		} else {
			return total;
		}
	}

}