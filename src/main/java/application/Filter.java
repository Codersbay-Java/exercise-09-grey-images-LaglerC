package application;

import java.awt.Color;

import picture.Picture;

public class Filter {
	private Picture picture;

	public Filter(Picture picture) {
		this.picture = picture;
	}

	/**
	 * The input picture should be converted to a grey scale. To achieve a grey
	 * image we need to sum up the red, green and blue value and divide it by 3.
	 * 
	 * @return converted picture
	 */
	public Picture greyScaleFilter() {
		int x = picture.width();
		int y = picture.height();

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int red = picture.get(j, i).getRed();
				int blue = picture.get(j, i).getBlue();
				int green = picture.get(j, i).getGreen();
				int grey = (red + blue + green) / 3;
				picture.set(j, i, new Color(grey, grey, grey));
			}

		}
		return picture;
	}

	/**
	 * As we have a range until 255 inclusive getting the inverted image is easy.
	 * Just subtract the color value from 255.
	 * 
	 * @return converted picture
	 */
	public Picture revertColorFilter() {
		int x = picture.width();
		int y = picture.height();

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int red = 255 - picture.get(j, i).getRed();
				int blue = 255 - picture.get(j, i).getBlue();
				int green = 255 - picture.get(j, i).getGreen();
				picture.set(j, i, new Color(red, green, blue));
			}

		}
		return picture;
	}

	//@formatter:off
	/**
	 * A sepia filter is a little more difficult to calculate.
	 * to get the
	 * 
	 * sepia red: (current red * 0.393) + (current green * 0.769) + (current blue * 0.189)
	 * sepia green: (current red * 0.349) + (current green * 0.686) + (current blue * 0.168)
	 * sepia blue: (current red * 0.292) + (current green * 0.534) + (current blue * 0.131)
	 * 
	 * the new value is then Math.min(sepia red, 255) and so on.
	 * 
	 * @return converted picture
	 */
	//@formatter:on

	public Picture sepiaFilter() {

		int x = picture.width();
		int y = picture.height();

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int red = (int) Math.min(((picture.get(j, i).getRed() * 0.393) + (picture.get(j, i).getGreen() * 0.769)
						+ (picture.get(j, i).getBlue() * 0.189)), 255);
				int green = (int) Math.min(((picture.get(j, i).getRed() * 0.349)
						+ (picture.get(j, i).getGreen() * 0.686) + (picture.get(j, i).getBlue() * 0.168)), 255);
				int blue = (int) Math.min(((picture.get(j, i).getRed() * 0.292) + (picture.get(j, i).getGreen() * 0.534)
						+ (picture.get(j, i).getBlue() * 0.131)), 255);
				picture.set(j, i, new Color(red, green, blue));
			}

		}
		return picture;
	}
}
