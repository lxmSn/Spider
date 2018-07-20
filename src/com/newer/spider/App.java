package com.newer.spider;

import java.io.FileWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

public class App {

	public static void main(String[] args) {
		
		String url = "https://movie.douban.com/top250";
		List<Film> linkList = Collections.synchronizedList(new LinkedList<>());

		ExecutorService pool = Executors.newCachedThreadPool();
		pool.execute(new Spider1(url, linkList));

		for (int i = 0; i < 10; i++) {

			url = String.format("https://movie.douban.com/top250?start=%d", 25 * i);
			pool.execute(new Spider1(url, linkList));

		}

		pool.shutdown();

		while (true) {
			if (pool.isTerminated()) {
				writeData(linkList);
				break;
			} else {
				try {
					Thread.sleep(1_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}
	
	
	private static void writeData(List<Film> linkList) {
		System.out.println(linkList.size());
		Collections.sort(linkList);
		
		for (Film film : linkList) {
			System.out.println(film);
		}
		
		String json=new Gson().toJson(linkList);
		try (FileWriter out =new FileWriter("Top250.json")){
			out.write(json);
			System.out.println("JSON  完成");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		ExecutorService pool=Executors.newFixedThreadPool(8);
		
		for (Film film : linkList) {
			pool.execute(new ImgDownload(film));
		}
		pool.shutdown();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
