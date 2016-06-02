import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.Test;
import com.epam.*;

public class BinderManagerTest {

	@Test
	public void testNotPage() {
		BinderManager bm = new BinderManager("bash.im", 80);
		
		try {
			bm.getHeadPage("fdsfsd");
		} catch (UnknownHostException e) {
			Assert.assertTrue(true);
		} catch (IOException e) {
		}
	}
}
