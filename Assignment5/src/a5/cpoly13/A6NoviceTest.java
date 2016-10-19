package a5.cpoly13;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a6novice.*;

/**
 * @author Chris
 *
 */
public class A6NoviceTest {
	/*
	 * Stores and returns names of test methods
	 */
	static public String[] getTestNames() {
		String[] test_names = new String[7];

		test_names[0] = "exampleTest";
		test_names[1] = "testPictureDimensions";
		test_names[2] = "testBadValues";
		test_names[3] = "testCoordinate";
		test_names[4] = "testPixelCoordinateGettersAndSetters";
		test_names[5] = "subPictureExtractTest";
		test_names[6] = "pixelIteratorTest";

		return test_names;
	}

	@Test
	public void exampleTest() {
	}

	/*
	 * Test general picture dimensions Control variables: width, height Test
	 * variable: Picture Result:If test variable equals control variable
	 */
	@Test
	public void testPictureDimensions() {
		Picture testPic = new PictureImpl(1080, 1920);
		int width = 1080;
		int height = 1920;
		assertEquals("Width doesn't match", width, testPic.getWidth());
		assertEquals("Height doesn't match", height, testPic.getHeight());
	}

	@Test
	public void testBadValues() {
		try {
			Picture nullTest = new PictureImpl(-2, 99);
			fail("Expected runtime exception");
		}

		catch (RuntimeException c) {
		}
	}

	@Test
	public void testCoordinate() {
		Coordinate test = new Coordinate(2, 3);
		int x = 2;
		int y = 3;

		assertEquals("X component doesn't match", test.getX(), x);
		assertEquals("Y component doesn't match", test.getY(), y);

		test = new Coordinate(499, 0);
		x = 499;
		y = 0;

		assertEquals("X component doesn't match", test.getX(), x);
		assertEquals("Y component doesn't match", test.getY(), y);

		test = new Coordinate(23, 333);
		x = 23;
		y = 333;

		assertEquals("X component doesn't match", test.getX(), x);
		assertEquals("Y component doesn't match", test.getY(), y);

		test = new Coordinate(-9999, 9999);
		x = -9999;
		y = 9999;

		assertEquals("X component doesn't match", test.getX(), x);
		assertEquals("Y component doesn't match", test.getY(), y);
	}

	@Test
	public void testPixelCoordinateGettersAndSetters() {
		Picture pTest = new PictureImpl(1920, 1080);
		Coordinate cTest = new Coordinate(13, 25);
		Pixel toInsert = new GrayPixel(0.5);

		pTest.setPixel(cTest, toInsert);

		assertEquals("Get Pixel doesn't match set pixel", toInsert, pTest.getPixel(cTest));

		pTest = new PictureImpl(1, 1);
		cTest = new Coordinate(0, 0);
		toInsert = new GrayPixel(0.8);

		pTest.setPixel(cTest, toInsert);

		assertEquals("Get Pixel doesn't match set pixel", toInsert, pTest.getPixel(cTest));

		try {
			pTest = new PictureImpl(1, 1);
			cTest = new Coordinate(0, 1);
			toInsert = new GrayPixel(1.0);

			pTest.setPixel(cTest, toInsert);

			assertEquals("Get Pixel doesn't match set pixel", toInsert, pTest.getPixel(cTest));
			fail("Should be out of bounds");
		} catch (RuntimeException e) {

		}

		pTest = new PictureImpl(888, 57);
		cTest = new Coordinate(887, 56);
		toInsert = new GrayPixel(0.0);

		pTest.setPixel(cTest, toInsert);

		assertEquals("Get Pixel doesn't match set pixel", toInsert, pTest.getPixel(cTest));
	}

	@Test
	public void subPictureExtractTest() {
		Picture toTest = new PictureImpl(1920, 1080);

		for (int i = 0; i < toTest.getWidth(); i++) {
			for (int n = 0; n < toTest.getHeight(); n++) {
				Pixel random = new GrayPixel(Math.random());
				Coordinate xy = new Coordinate(i, n);
				toTest.setPixel(xy, random);
			}
		}
		SubPicture sub = toTest.extract(12, 100, 500, 900);
		Coordinate a = new Coordinate(12, 100);
		Coordinate b = new Coordinate(512, 1000);
		SubPicture subToTest = toTest.extract(a, b);

		for (int i = 0; i < sub.getWidth(); i++) {
			for (int n = 0; n < sub.getHeight(); n++) {
				assertEquals("SubPictures don't match", +sub.getPixel(i, n).getChar(),
						+subToTest.getPixel(i, n).getChar());
			}
		}

	}

	@Test
	public void pixelIteratorTest() {
		Picture toTest = new PictureImpl(1920, 1080);
		Iterator<Pixel> pixels = toTest.iterator();
		int x = 0;
		int y = 0;
		

		for (int i = 0; i < toTest.getWidth(); i++) {
			for (int n = 0; n < toTest.getHeight(); n++) {
				Pixel random = new GrayPixel(Math.random());
				Coordinate xy = new Coordinate(i, n);
				toTest.setPixel(xy, random);
			}
		}

		
		while (pixels.hasNext()) {

			assertEquals("Iterator is not iterating correctly", pixels.next().getChar(),
					toTest.getPixel(x, y).getChar());

			if (x < toTest.getWidth() - 1) {
				x++;
			}

			else {
				x = 0;
				y++;
			}

		}

	}

}
