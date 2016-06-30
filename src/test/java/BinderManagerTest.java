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
		String str = bm.executeQuery("GET", "-43");
		Assert.assertEquals(bm.executeQuery("GET", "-43"), "Невозможно подключиться");
	}
	
	@Test
	public void testGetPage() {
		BinderManager bm = new BinderManager("bas.im", 80);
		Assert.assertNotEquals(bm.executeQuery("GET", "80"), "Хоста не существует");
	}
}
