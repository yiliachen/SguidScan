package com.example.xmlpaser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.model.FileEntry;
import com.example.model.FileEntrySum;
import com.example.utils.SpringUtil;

public class xmlParser {
	@SuppressWarnings("rawtypes")
	private CopyOnWriteArraySet fes = null;

	@Autowired
	BeanFactory beanFactory;
	
	@SuppressWarnings("rawtypes")

	public Set Process(String[] args) {
		if (args[2].equals("file")) {
			fes = new CopyOnWriteArraySet<FileEntrySum>();
		} else {
			fes = new CopyOnWriteArraySet<FileEntry>();
		}
		if (args.length != 4) {
			System.err.println("Usages:xmlParser xmlfile threadcount processtype");
			System.exit(2);
		}

		Integer lthreadCount = 10;
		String lfileName = args[0];
		String lstartPath = args[3];

		try {
			lthreadCount = Integer.parseInt(args[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		File file = new File(lfileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String lLine = null;
			ExecutorService lThreadPool = Executors.newFixedThreadPool(lthreadCount);
			while ((lLine = reader.readLine()) != null) {
				if (args[2].equals("file")) {
					FileProcessor fp = new FileProcessor(lstartPath + "/" + lLine, fes);
					fp.setStartPath(lstartPath);
					lThreadPool.execute(fp);
				} else {
					AttributeProcessor ap = new AttributeProcessor(lstartPath + "/" + lLine, fes);
					ap.setStartPath(lstartPath);
					lThreadPool.execute(ap);
				}
			}
			lThreadPool.shutdown();
			try {
				lThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return fes;
	}

	public Set<FileEntry> Process(String pfileName) {
		// TODO Auto-generated method stub
		CopyOnWriteArraySet<FileEntry> fes = new CopyOnWriteArraySet<FileEntry>();

		Integer lthreadCount = 10;

		try {
			// Read one line at one time util null encountered
			ExecutorService lThreadPool = Executors.newFixedThreadPool(lthreadCount);

			lThreadPool.execute(new AttributeProcessor(pfileName, fes));
			lThreadPool.shutdown();
			try {
				lThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fes;
	}

	public void Process(String[] __args, String __proc) {
		fes = new CopyOnWriteArraySet();

		Integer lthreadCount = 10;
		String lfileName = __args[0];
		String lstartPath = __args[1];

		File file = new File(lfileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String lLine = null;
			// Read one line at one time util null encountered
			ExecutorService lThreadPool = Executors.newFixedThreadPool(lthreadCount);
			while ((lLine = reader.readLine()) != null) {
				ProcessorBase _proc = (ProcessorBase)SpringUtil.getBean(__proc);
				_proc.setFes(fes);
				_proc.setmEntryName(lLine);
				
				lThreadPool.execute(_proc);
			}
			lThreadPool.shutdown();
			try {
				lThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}
}
