import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class PhotoViewer {

	// The PhotographContainer
	private PhotographContainer imageLibrary;

	// display index
	private int disIndex;

	// Radio buttons
	private JRadioButton rate1;
	private JRadioButton rate2;
	private JRadioButton rate3;
	private JRadioButton rate4;
	private JRadioButton rate5;
	private ButtonGroup group;

	// Buttons
	private JButton sortRating;
	private JButton sortCaption;
	private JButton sortDate;
	private JButton previous;
	private JButton next;

	// Panels
	private JPanel PnlOne;
	private JPanel PnlTwo;
	private JPanel PnlThree;
	private JPanel PnlFour;
	private JFrame frame;

	// Thumbnail Labels
	private JLabel tpicLabel1;
	private JLabel tpicLabel2;
	private JLabel tpicLabel3;
	private JLabel tpicLabel4;
	private JLabel tpicLabel5;

	// Display Label
	private JLabel picLabelDisplay;

	// Photos
	private Photograph p1;
	private Photograph p2;
	private Photograph p3;
	private Photograph p4;
	private Photograph p5;

	// main method
	public static void main(String[] args) {
		PhotoViewer P = new PhotoViewer();
		P.Gui(P.getImageLibrary());

	}

	// Image Library getter
	public PhotographContainer getImageLibrary() {
		return this.imageLibrary;
	}

	// Image Library setter
	public void setImageLibrary(PhotographContainer il) {
		this.imageLibrary = il;
	}

	// constructor
	public PhotoViewer() {
		frame = new JFrame("CS HW4");

		p1 = new Photograph("8pokemon", "images/8pokemon", "1905-01-01", 5);
		p1.loadImageData(p1.getFilename());
		p2 = new Photograph("3pokemon", "images/3pokemon", "1904-01-01", 4);
		p2.loadImageData(p2.getFilename());
		p3 = new Photograph("venasaur", "images/venasaur", "1903-01-01", 3);
		p3.loadImageData(p3.getFilename());
		p4 = new Photograph("bulbasaur", "images/bulbasaur", "1901-01-01", 2);
		p4.loadImageData(p4.getFilename());
		p5 = new Photograph("ivysaur", "images/ivysaur", "1902-01-01", 1);
		p5.loadImageData(p5.getFilename());

		Photograph[] photos_array = new Photograph[] { p1, p2, p3, p4, p5 };

		// creating and adding images to imageLibrary
		imageLibrary = new PhotoLibrary("container", 1);
		for (Photograph p : photos_array) {
			imageLibrary.addPhoto(p);
		}
		imageLibrary = sortByDate(imageLibrary);

	}

	// Resets Labels in accordance with order of imageLibrary
	public void setLabels(PhotographContainer p) {
		clearRadioButtons();

		tpicLabel1.setIcon(new ImageIcon(thumbnail(getImageDataFromImageLibrary(0, imageLibrary))));
		thumnailtext(tpicLabel1, 0);

		tpicLabel2.setIcon(new ImageIcon(thumbnail(getImageDataFromImageLibrary(1, imageLibrary))));
		thumnailtext(tpicLabel2, 1);

		tpicLabel3.setIcon(new ImageIcon(thumbnail(getImageDataFromImageLibrary(2, imageLibrary))));
		thumnailtext(tpicLabel3, 2);

		tpicLabel4.setIcon(new ImageIcon(thumbnail(getImageDataFromImageLibrary(3, imageLibrary))));
		thumnailtext(tpicLabel4, 3);

		tpicLabel5.setIcon(new ImageIcon(thumbnail(getImageDataFromImageLibrary(4, imageLibrary))));
		thumnailtext(tpicLabel5, 4);

		picLabelDisplay.setIcon(new ImageIcon(displayer(getImageDataFromImageLibrary(disIndex, imageLibrary))));

	}

	public void clearRadioButtons() {
		group.clearSelection();
	}

	// Runs the gui
	public void Gui(PhotographContainer imgLibrary) {

		// initialize all the labels for the display and thumbnail images
		tpicLabel1 = new JLabel(new ImageIcon(thumbnail(getImageDataFromImageLibrary(0, imageLibrary))));
		thumnailtext(tpicLabel1, 0);

		tpicLabel2 = new JLabel(new ImageIcon(thumbnail(getImageDataFromImageLibrary(1, imageLibrary))));
		thumnailtext(tpicLabel2, 1);

		tpicLabel3 = new JLabel(new ImageIcon(thumbnail(getImageDataFromImageLibrary(2, imageLibrary))));
		thumnailtext(tpicLabel3, 2);

		tpicLabel4 = new JLabel(new ImageIcon(thumbnail(getImageDataFromImageLibrary(3, imageLibrary))));
		thumnailtext(tpicLabel4, 3);

		tpicLabel5 = new JLabel(new ImageIcon(thumbnail(getImageDataFromImageLibrary(4, imageLibrary))));
		thumnailtext(tpicLabel5, 4);

		disIndex = 0;
		picLabelDisplay = setDisplay(disIndex, imageLibrary);

		// initialize panels
		PnlOne = new JPanel();
		PnlOne.setLayout(new BoxLayout(PnlOne, BoxLayout.PAGE_AXIS));
		PnlTwo = new JPanel(new FlowLayout());
		PnlThree = new JPanel(new FlowLayout());
		PnlFour = new JPanel(new FlowLayout());

		// Adding the Display Picture to PnlFour
		PnlFour.add(picLabelDisplay);

		// initialize buttons
		previous = new JButton("Previous");
		sortRating = new JButton("Sort by Rating");
		sortCaption = new JButton("Sort by Caption");
		sortDate = new JButton("Sort by Date");
		next = new JButton("Next");

		// initialize the main frame
		frame.setLayout(new BorderLayout());
		frame.add(PnlOne, BorderLayout.WEST);
		frame.add(PnlTwo, BorderLayout.CENTER);
		frame.add(PnlThree, BorderLayout.SOUTH);
		frame.add(PnlFour, BorderLayout.EAST);

		// actionListener for sortRating button
		// resets disIndex to 0 and orders imageLibrary by rating
		sortRating.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imageLibrary = sortByRating(imageLibrary);
				disIndex = 0;
				// load up new labels
				setLabels(imageLibrary);
			}
		});

		// actionListener for sortCaption button
		// resets disIndex to 0 and orders imageLibrary by Caption
		sortCaption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imageLibrary = sortByCaption(imageLibrary);
				disIndex = 0;
				// load up new labels
				setLabels(imageLibrary);
			}
		});

		// actionListener for sortDate button
		// resets disIndex to 0 and orders imageLibrary by Date
		sortDate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imageLibrary = sortByDate(imageLibrary);
				disIndex = 0;
				// load up new labels
				setLabels(imageLibrary);
			}
		});

		// actionListener for previous button
		// subtract 1 from disIndex until 0 and then reset back to 4
		previous.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (disIndex > 0) {
					disIndex--;
				} else {
					disIndex = 4;
				}
				// load up new labels
				setLabels(imageLibrary);

			}
		});

		// actionListener for previous button
		// add 1 to disIndex until 4 and then reset back to 0
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (disIndex < 4) {
					disIndex++;
				} else {
					disIndex = 0;
				}
				// load up new labels
				setLabels(imageLibrary);

			}
		});

		// Adding buttons to PnlTwo
		PnlTwo.add(previous);
		PnlTwo.add(sortRating);
		PnlTwo.add(sortCaption);
		PnlTwo.add(sortDate);
		PnlTwo.add(next);

		// Listener to tpic thumbnail
		tpicLabel1.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent me) {
			}

			public void mouseReleased(MouseEvent me) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
				if (me.getButton() == MouseEvent.BUTTON1) {
					// swaps the display image and thumbnail image
					disIndex = 0;
					picLabelDisplay
							.setIcon(new ImageIcon(displayer(getImageDataFromImageLibrary(disIndex, imageLibrary))));
				}
			}
		});

		// Listener to tpic thumbnail
		tpicLabel2.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent me) {
			}

			public void mouseReleased(MouseEvent me) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
				if (me.getButton() == MouseEvent.BUTTON1) {
					// swaps the display image and thumbnail image
					disIndex = 1;
					picLabelDisplay.setIcon(new ImageIcon(displayer(getImageDataFromImageLibrary(1, imageLibrary))));
				}
			}
		});

		// Listener to tpic thumbnail
		tpicLabel3.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent me) {
			}

			public void mouseReleased(MouseEvent me) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
				if (me.getButton() == MouseEvent.BUTTON1) {
					// swaps the display image and thumbnail image
					disIndex = 2;
					picLabelDisplay.setIcon(new ImageIcon(displayer(getImageDataFromImageLibrary(2, imageLibrary))));
				}
			}
		});

		// Listener to tpic thumbnail
		tpicLabel4.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent me) {
			}

			public void mouseReleased(MouseEvent me) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
				if (me.getButton() == MouseEvent.BUTTON1) {
					// swaps the display image and thumbnail image
					disIndex = 3;
					picLabelDisplay.setIcon(new ImageIcon(displayer(getImageDataFromImageLibrary(3, imageLibrary))));
				}
			}
		});

		// Listener to tpic thumbnail
		tpicLabel5.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent me) {
			}

			public void mouseReleased(MouseEvent me) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mouseExited(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
				if (me.getButton() == MouseEvent.BUTTON1) {
					// swaps the display image and thumbnail image
					disIndex = 4;
					picLabelDisplay.setIcon(new ImageIcon(displayer(getImageDataFromImageLibrary(4, imageLibrary))));
				}
			}
		});

		// Adds the thumbnails to panel one
		PnlOne.add(tpicLabel1);
		PnlOne.add(tpicLabel2);
		PnlOne.add(tpicLabel3);
		PnlOne.add(tpicLabel4);
		PnlOne.add(tpicLabel5);

		// initializes radio button
		rate1 = new JRadioButton("Set Rating to 1");
		rate1.setMnemonic(KeyEvent.VK_B);
		rate1.setActionCommand("Set Rating to 1");
		rate1.setSelected(false);

		// initializes radio button
		rate2 = new JRadioButton("Set Rating to 2");
		rate2.setMnemonic(KeyEvent.VK_B);
		rate2.setActionCommand("Set Rating to 2");
		rate2.setSelected(false);

		// initializes radio button
		rate3 = new JRadioButton("Set Rating to 3");
		rate3.setMnemonic(KeyEvent.VK_B);
		rate3.setActionCommand("Set Rating to 3");
		rate3.setSelected(false);

		// initializes radio button
		rate4 = new JRadioButton("Set Rating to 4");
		rate4.setMnemonic(KeyEvent.VK_B);
		rate4.setActionCommand("Set Rating to 4");
		rate4.setSelected(false);

		// initializes radio button
		rate5 = new JRadioButton("Set Rating to 5");
		rate5.setMnemonic(KeyEvent.VK_B);
		rate5.setActionCommand("Set Rating to 5");
		rate5.setSelected(false);

		// initializes ButtonGroup to only have 1 radio button selected at a time
		group = new ButtonGroup();
		group.add(rate1);
		group.add(rate2);
		group.add(rate3);
		group.add(rate4);
		group.add(rate5);

		// radio button1
		rate1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create new photo identical to current display. Then set rating according
				// to radio button. Then replace the old photo with the new temp photo.
				// reload the pics
				Photograph temp = imageLibrary.getPhotos().get(disIndex);
				temp.setRating(1);
				imageLibrary.getPhotos().remove(disIndex);
				imageLibrary.getPhotos().add(disIndex, temp);
				setLabels(imageLibrary);

			}
		});

		rate2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// create new photo identical to current display. Then set rating according
				// to radio button. Then replace the old photo with the new temp photo.
				// reload the pics
				Photograph temp = imageLibrary.getPhotos().get(disIndex);
				temp.setRating(2);
				imageLibrary.getPhotos().remove(disIndex);
				imageLibrary.getPhotos().add(disIndex, temp);
				setLabels(imageLibrary);

			}
		});

		rate3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// create new photo identical to current display. Then set rating according
				// to radio button. Then replace the old photo with the new temp photo.
				// reload the pics
				Photograph temp = imageLibrary.getPhotos().get(disIndex);
				temp.setRating(3);
				imageLibrary.getPhotos().remove(disIndex);
				imageLibrary.getPhotos().add(disIndex, temp);
				setLabels(imageLibrary);

			}
		});

		rate4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// create new photo identical to current display. Then set rating according
				// to radio button. Then replace the old photo with the new temp photo.
				// reload the pics
				Photograph temp = imageLibrary.getPhotos().get(disIndex);
				temp.setRating(4);
				imageLibrary.getPhotos().remove(disIndex);
				imageLibrary.getPhotos().add(disIndex, temp);
				setLabels(imageLibrary);

			}
		});

		rate5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// create new photo identical to current display. Then set rating according
				// to radio button. Then replace the old photo with the new temp photo.
				// reload the pics
				Photograph temp = imageLibrary.getPhotos().get(disIndex);
				temp.setRating(5);
				imageLibrary.getPhotos().remove(disIndex);
				imageLibrary.getPhotos().add(disIndex, temp);
				setLabels(imageLibrary);

			}
		});

		// add the radio buttons to panel 3
		PnlThree.add(rate1);
		PnlThree.add(rate2);
		PnlThree.add(rate3);
		PnlThree.add(rate4);
		PnlThree.add(rate5);

		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	// helper methods
	private String getNameFromImageLibrary(int index, PhotographContainer p) {
		return p.getPhotos().get(index).getFilename();
	}

	private String getDateFromImageLibrary(int index, PhotographContainer p) {
		return p.getPhotos().get(index).getDateTaken();
	}

	private int getRatingFromImageLibrary(int index, PhotographContainer p) {
		return p.getPhotos().get(index).getRating();
	}

	private BufferedImage getImageDataFromImageLibrary(int index, PhotographContainer p) {
		return p.getPhotos().get(index).getImageData();
	}

	// uses selection sort to sort by ratings
	private PhotographContainer sortByRating(PhotographContainer p) {
		PhotographContainer lib = new PhotoLibrary(p.getName(), 1);
		Photograph[] pics = new Photograph[p.getPhotos().size()];
		for (int i = 0; i < p.getPhotos().size(); i++) {
			pics[i] = p.getPhotos().get(i);
		}
		int n = pics.length;
		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n - 1; i++) {
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i + 1; j < n; j++)
				if (pics[j].getRating() > pics[min_idx].getRating())
					min_idx = j;

			// Swap the found minimum element with the first
			// element
			Photograph temp = pics[min_idx];
			pics[min_idx] = pics[i];
			pics[i] = temp;
		}

		for (int i = 0; i < pics.length; i++) {
			lib.addPhoto(pics[i]);
		}

		return lib;

	}

	// uses selection sort to sort by dates
	private PhotographContainer sortByDate(PhotographContainer p) {
		PhotographContainer lib = new PhotoLibrary(p.getName(), 1);
		Photograph[] pics = new Photograph[p.getPhotos().size()];
		for (int i = 0; i < p.getPhotos().size(); i++) {
			pics[i] = p.getPhotos().get(i);
		}
		int n = pics.length;
		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n - 1; i++) {
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i + 1; j < n; j++)
				if (pics[j].compareTo(pics[min_idx]) > 0)
					min_idx = j;

			// Swap the found minimum element with the first
			// element
			Photograph temp = pics[min_idx];
			pics[min_idx] = pics[i];
			pics[i] = temp;
		}

		for (int i = 0; i < pics.length; i++) {
			lib.addPhoto(pics[i]);
		}

		return lib;

	}

	// uses selection sort to sort by captions
	private PhotographContainer sortByCaption(PhotographContainer p) {
		PhotographContainer lib = new PhotoLibrary(p.getName(), 1);
		Photograph[] pics = new Photograph[p.getPhotos().size()];
		for (int i = 0; i < p.getPhotos().size(); i++) {
			pics[i] = p.getPhotos().get(i);
		}
		int n = pics.length;
		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n - 1; i++) {
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i + 1; j < n; j++)
				if (pics[j].getCaption().compareTo(pics[min_idx].getCaption()) > 0)
					min_idx = j;

			// Swap the found minimum element with the first
			// element
			Photograph temp = pics[min_idx];
			pics[min_idx] = pics[i];
			pics[i] = temp;
		}

		for (int i = 0; i < pics.length; i++) {
			lib.addPhoto(pics[i]);
		}

		return lib;

	}

	// Makes an image ready to be displayed as the main picture
	private BufferedImage displayer(BufferedImage img) {
		Image tmp = img.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(350, 350, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	// Makes an image ready to be displayed as a thumbnail picture
	private BufferedImage thumbnail(BufferedImage img) {
		Image tmp = img.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(90, 90, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	// Makes text go right next to thumbnail picture
	private void thumnailtext(JLabel label, int i) {
		label.setText("<html> Caption: " + getNameFromImageLibrary(i, imageLibrary) + "<br>Date: "
				+ getDateFromImageLibrary(i, imageLibrary) + "<br>Rating: " + getRatingFromImageLibrary(i, imageLibrary)
				+ "</html>");

	}

	// creates a new JLabel that is for the main picture
	private JLabel setDisplay(int i, PhotographContainer P) {
		picLabelDisplay = new JLabel(new ImageIcon(displayer(getImageDataFromImageLibrary(i, P))));
		return picLabelDisplay;
	}

}

//sources used
//https://www.geeksforgeeks.org/selection-sort/
//https://docs.oracle.com/javase/7/docs/api/javax/swing/ButtonGroup.html
//https://docs.oracle.com/javase/7/docs/api/javax/swing/JLabel.html
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/RadioButtonDemoProject/src/components/RadioButtonDemo.java
