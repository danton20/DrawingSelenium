package script;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Driver;

public class Main extends Driver {

	@Before
	public void beforeTest() {
		startDriver();
	}

	@Test
	public void script() throws Exception {
		acessar("file:///C:/DrawingPage/html5-canvas-drawing-app.html");
		esperarPagina();
		Thread.sleep(5000);
		arrastar(185, 200, 500, 550);
		Thread.sleep(10000);
	}

	@After
	public void afterTest() {
		driver.close();
	}
}
