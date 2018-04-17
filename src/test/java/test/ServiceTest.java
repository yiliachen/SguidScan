package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.DemoApplication;
import com.example.service.FileEntryService;
import com.example.service.FileEntrySumService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { DemoApplication.class })
public class ServiceTest {
	
	@Autowired
	FileEntrySumService fileEntrySumServiceImpl;
	
	
	@Test
	public void testFindbypath(){
		System.out.println(fileEntrySumServiceImpl.getPathFilteredEntry("/Users/ming.c.chen"));
	}
}
