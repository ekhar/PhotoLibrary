import java.util.Comparator;

public class CompareByRating implements Comparator<Photograph> {

	@Override
	// if photo1 rating is equal to photo2 rating, compare captions. Otherwise
	// compare ratings
	public int compare(Photograph p1, Photograph p2) {
		if (p1.getRating() == p2.getRating()) {
			return p1.getCaption().compareTo(p2.getCaption());
		}

		else {
			return p2.getRating() - p1.getRating();
		}

	}
}