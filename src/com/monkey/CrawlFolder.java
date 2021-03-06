package com.monkey;

import java.io.File;

import com.monkey.util.CalculateSpace;
import com.monkey.util.CalculateTime;

/**
 * 
 * @author monkey
 * @since 2015-6-24
 */
public class CrawlFolder 
{
	private static String fileStr = "E:\\";
	
	// 访问过的文件夹的集合
	private static VisitedQueue visitedQueue = new VisitedQueue();
	
	// 待访问的文件夹的队列
	private static FileQueue fileQueue = new FileQueue();
	
	/**
	 * 遍历文件夹
	 */
	public void TraversalFolders()
	{	
		File root = new File(fileStr); 
		
		if(root.isDirectory())
		{
			// 文件的绝对路径
			System.out.println(root.getAbsolutePath());
			
			// 判断文件的大小
			System.out.println(CalculateSpace.CalculateSpace(root.getTotalSpace(), root));
			
			fileQueue.AddFileListToQueue(root.listFiles());			
			
			
			while(!fileQueue.IsEmpty())
			{
				File file = fileQueue.RemoveFromQueue();  
				
				
				// 已经遍历过的文件直接跳过
				if(visitedQueue.Iscontainment(file))
				{
					return;
				}	
				
				showFolderInfo(file);
				
				if(file.isDirectory() && !visitedQueue.Iscontainment(file))
				{
					fileQueue.AddFileListToQueue(file.listFiles());
				}
				
				visitedQueue.addVisitedFolder(file);  
				
				
			}  
			
		}
	}
	
	
	/**
	 * 输出文件夹相关的信息
	 */
	public void showFolderInfo(File file)
	{
		// 输出文件名称
		System.out.print(file.getName());
		
		// 输出文件是否为隐藏的
		if(file.isHidden())
		{
			System.out.print(" - 隐藏");
		}
		else
		{
			System.out.print(" - 可见");
		}
		
		//  输出文件的大小
		System.out.println(" - " + CalculateSpace.CalculateSpace(file.length(), file));
	}
	
	public static void main(String[] args)
	{
		// 记录开始遍历的时间
		long starttime = System.currentTimeMillis();
		
		CrawlFolder crawlfolder = new CrawlFolder();
		crawlfolder.TraversalFolders();
		
		long endtime = System.currentTimeMillis();
		
		System.out.println(CalculateTime.CalculateTime(endtime-starttime));
		
	}

}
