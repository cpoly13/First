package a5.cpoly13;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a6jedi.*;

public class A6JediTest {

	static public String[] getTestNames() {
		String[] test_names = new String[1];

		test_names[0] = "exampleTest";

		return test_names;
	}

	@Test
	public void exampleTest() {
	}

	@Test
	public void zigzagIteratorTester() {
		Picture testPic = new PictureImpl(3, 3);
		Iterator<Pixel> zigzags = testPic.zigzag();

		for (int i = 0; i < testPic.getWidth(); i++) {

			for (int n = 0; n < testPic.getHeight(); n++) {
				Pixel random = new GrayPixel(Math.random());
				Coordinate xy = new Coordinate(i, n);
				testPic.setPixel(xy, random);
			}
		}

		

			assertEquals("Zigzag is zigzaging wrong", zigzags.next().getChar(),
					testPic.getPixel(0, 0).getChar());
			
			assertEquals("Zigzag is zigzaging wrong", zigzags.next().getChar(),
					testPic.getPixel(1, 0).getChar());
			assertEquals("Zigzag is zigzaging wrong", zigzags.next().getChar(),
					testPic.getPixel(0, 1).getChar());
			assertEquals("Zigzag is zigzaging wrong", zigzags.next().getChar(),
					testPic.getPixel(0, 2).getChar());
			assertEquals("Zigzag is zigzaging wrong", zigzags.next().getChar(),
					testPic.getPixel(1, 1).getChar());
			assertEquals("Zigzag is zigzaging wrong", zigzags.next().getChar(),
					testPic.getPixel(2, 0).getChar());
			assertEquals("Zigzag is zigzaging wrong", zigzags.next().getChar(),
					testPic.getPixel(2, 1).getChar());
			assertEquals("Zigzag is zigzaging wrong", zigzags.next().getChar(),
					testPic.getPixel(1, 2).getChar());
			assertEquals("Zigzag is zigzaging wrong", zigzags.next().getChar(),
					testPic.getPixel(2, 2).getChar());
			

		
		

	}
}
