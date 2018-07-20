package com.newer.spider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ImgDownload implements Runnable{
	private Film film;
	
	

	public ImgDownload(Film film) {
		super();
		this.film = film;
	}



	@Override
	public void run() {
		
		
		File path =new File("pic");
		if(!path.exists())
			path.mkdirs();
		String name=String.format("%03s_%s.jpg",film.getId(),film.getTitle().split(" ")[0]);
		
		try (BufferedOutputStream out=
				new BufferedOutputStream(new FileOutputStream(new File(path,name)));){
			
			byte[] data=new OkHttpClient.Builder()
					.connectTimeout(60, TimeUnit.SECONDS)
					.readTimeout(60, TimeUnit.SECONDS)
					.writeTimeout(60, TimeUnit.SECONDS)
					.build()
					.newCall(
							new Request.Builder().url(film.getPoster()).build()
					).execute()
					.body()
					.bytes();
			
			out.write(data);
			out.close();
			System.out.println(Thread.currentThread().getName()+"下载"+name);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
