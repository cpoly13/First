package a5.cpoly13;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a6adept.*;

public class A6AdeptTest {

	static public String[] getTestNames() {
		String[] test_names = new String[5];

		test_names[0] = "exampleTest";
		test_names[1] = "testIteratorSample";
		test_names[2] = "testIteratorWindow";
		test_names[3] = "testIteratorTile";
		test_names[4] = "testExceptions";

		return test_names;
	}

	@Test
	public void exampleTest() {
	}
	
	@Test
	public void testExceptions(){
		Picture testPic=new PictureImpl(1920,1080);
		try {
			Iterator <Pixel> pixels = testPic.sample(2000, -3, 4, 5);
			fail("Expected IllegalArgument Exception for init x,y out of bounds");
		}
		catch(IllegalArgumentException e){
		}
		catch (RuntimeException e){
			fail("Expected IllegalArgument Exception but was RuntimeException");
		}
		
		try{
			Iterator <Pixel> pixels=testPic.sample(0, 0, -2, -500);
			fail("Expected IllegalArgument Exception for negative dx/dy");
		}
		catch(IllegalArgumentException e){
		}
		catch (RuntimeException e){
			fail("Expected IllegalArgument Exception but was RuntimeException");
		}
		
		try{
			Iterator <SubPicture> windows= testPic.window(-7, -400);
			fail("Expected IllegalArgument Exception for negative window width/heigh");
		}
		catch(IllegalArgumentException e){
		}
		
		catch (RuntimeException e){
			fail("Expected IllegalArgument Exception but was RuntimeException");
		}
		
		try{
			Iterator <SubPicture> windows= testPic.window(1922, 5000);
			fail("Expected IllegalArgument Exception for being over limit of window width/heigh");
		}
		catch(IllegalArgumentException e){
		}
		
		catch (RuntimeException e){
			fail("Expected IllegalArgument Exception but was RuntimeException");
		}
		
		try{
			Iterator <SubPicture> tiles= testPic.tile(-7, -400);
			fail("Expected IllegalArgument Exception for negative tile width/heigh");
		}
		catch(IllegalArgumentException e){
		}
		
		catch (RuntimeException e){
			fail("Expected IllegalArgument Exception but was RuntimeException");
		}
		
		try{
			Iterator <SubPicture> tiles= testPic.tile(1922, 5000);
			fail("Expected IllegalArgument Exception for being over limit of tile width/heigh");
		}
		catch(IllegalArgumentException e){
		}
		
		catch (RuntimeException e){
			fail("Expected IllegalArgument Exception but was RuntimeException");
		}
	}

	@Test
	public void testIteratorSample() {
		Picture testPic = new PictureImpl(1920, 1080);
		Iterator<Pixel> pixels = testPic.sample(100, 300, 2, 3);
		int x = 100;
		int y = 300;

		for (int i = 0; i < testPic.getWidth(); i++) {
			for (int n = 0; n < testPic.getHeight(); n++) {
				Pixel random = new GrayPixel(Math.random());
				Coordinate xy = new Coordinate(i, n);
				testPic.setPixel(xy, random);
			}

		}

		while (pixels.hasNext()) {
			assertEquals("Sample Iterator method not working", testPic.getPixel(x, y).getChar(),
					pixels.next().getChar());

			if (x + 2 < testPic.getWidth() - 1) {
				x += 2;
			} else {
				x = 100;
				y += 3;
			}

		}

	}

	@Test
	public void testIteratorWindow() {
		Picture testPic = new PictureImpl(1920, 1080);
		Iterator<SubPicture> windows = testPic.window(3, 2);
		int x = 0;
		int y = 0;

		for (int i = 0; i < testPic.getWidth(); i++) {

			for (int n = 0; n < testPic.getHeight(); n++) {
				Pixel random = new GrayPixel(Math.random());
				Coordinate xy = new Coordinate(i, n);
				testPic.setPixel(xy, random);
			}

		}
		
		while (windows.hasNext()){
			assertEquals("SubPictures don't match iterator windows",testPic.extract(x, y, 3, 2).getPixel(1, 1).getChar(),
					windows.next().getPixel(1,1).getChar());
			
			if(x+3<testPic.getWidth()){
				x++;
			}
			
			else{
				x=0;
				y++;
			}
		}

	}
	
	@Test
	public void testIteratorTile() {
		Picture testPic = new PictureImpl(1920, 1080);
		Iterator<SubPicture> tiles = testPic.tile(2,2);
		int x = 0;
		int y = 0;

		for (int i = 0; i < testPic.getWidth(); i++) {

			for (int n = 0; n < testPic.getHeight(); n++) {
				Pixel random = new GrayPixel(Math.random());
				Coordinate xy = new Coordinate(i, n);
				testPic.setPixel(xy, random);
			}

		}
		
		while (tiles.hasNext()){
			assertEquals("SubPictures don't match iterator tiles",testPic.extract(x, y, 2, 2).getPixel(1, 1).getChar(),
					tiles.next().getPixel(1,1).getChar());
			
			if(x+2<testPic.getWidth()){
				x+=2;
			}
			
			else{
				x=0;
				y+=2;
			}
		}

	}
}
