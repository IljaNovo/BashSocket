import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.Test;
import com.epam.*;

public class BinderManagerTest {

	@Test
	public void testNotPage() {
		BinderManager bm = new BinderManager("bas.im", 80);
		
		try {
			bm.executeQuery("GET", "-43");
		} catch (UnknownHostException e) {
			Assert.assertTrue(true);
		} catch (IOException e) {
		}
	}
	
	@Test
	public void testGetPage() {
		BinderManager bm = new BinderManager("bas.im", 80);
		
		try {
			bm.executeQuery("GET", "80");
			Assert.assertTrue(true);
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		}
	}
}
