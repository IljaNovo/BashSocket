import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;

import com.epam.*;
import org.junit.Assert;
import org.junit.Test;

public class HeadQueryParserTest {

	@Test
	public void testParseCharset() {
		BinderManager bm = new BinderManager("bash.im", 80);
		HeadQueryParser hqp = new HeadQueryParser(bm.executeQuery("GET", "43"));
		Assert.assertEquals(hqp.getCharset(), "windows-1251");
		
	}
	
	@Test
	public void testParseContentType() {
		BinderManager bm = new BinderManager("bash.im", 80);
		HeadQueryParser hqp = new HeadQueryParser(bm.executeQuery("GET", "43"));
		Assert.assertEquals(hqp.getContentType(), "text/html");
		
	}
	
	@Test
	public void testParseStatusLoading() {
		BinderManager bm = new BinderManager("bash.im", 80);
		HeadQueryParser hqp = new HeadQueryParser(bm.executeQuery("GET", "43"));
		Assert.assertEquals(hqp.getStatusLoading(), "200");
	}
	
	@Test
	public void testParseTransferEncoding() {
		BinderManager bm = new BinderManager("bash.im", 80);
		HeadQueryParser hqp = new HeadQueryParser(bm.executeQuery("GET", "43"));
		Assert.assertEquals(hqp.getTransferEncoding(), "chunked");
	}
}
