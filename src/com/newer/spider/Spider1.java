package com.newer.spider;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider1 implements Runnable {

	private String url;
	private List<Film> linkList;

	public Spider1(String url, List<Film> linkList) {

		
		this.url = url;
		this.linkList = linkList;

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void run() {
		try {

			Document doc = Jsoup.connect(url).timeout(60_000).get();
			Elements items = doc.select(".grid_view .item");

			for (Element e : items) {

				Film film = new Film();
				// 排名
				String id = e.select("em").first().text();
				// System.out.println(id);
				film.setId(id);

				// 图片
				String poster = e.select("img").first().attr("src");
				// System.out.println(poster);
				film.setPoster(poster);

				// 电影名字
				String title = e.select(".info .title").first().text();
				// System.out.println(title);
				film.setTitle(title);

				// 评分
				String grade = e.select(".star .rating_num").text();
				// System.out.println(grade);
				film.setGrade(grade);

				// 观众人数
				String audience = e.select(".star span").last().text();
				// System.out.println(audience);
				film.setAudience(audience);

				// 评语
				String remark = e.select(".bd .quote").text();
				// System.out.println(remark);
				film.setRemark(remark);

				// 电影地址

				String filmAddress = e.select(".pic a").attr("href");
				try {
					Document docIn = Jsoup.connect(filmAddress).get();

					// 导演
					String director = docIn.select("#info .attrs").first().text();

					film.setDirector(director);

					// 主演
					String protagonist = docIn.select("#info .actor .attrs").text();
					film.setProtagonist(protagonist);

					// 类型
					String type = docIn.select("#info span[property$=v:genre]").text();
					film.setType(type);

					// 上映日期
					String showTime = docIn.select("#info span[property$=v:initialReleaseDate]").text();
					film.setShowTime(showTime);

					// 片长
					String time = docIn.select("#info span[property$=v:runtime]").text();
					film.setTime(time);

					linkList.add(film);
					System.out.println(Thread.currentThread().getName() + "抓取" + id);

				} catch (Exception exception) {
					System.out.println("网址失效");
				}

			}
			System.out.println("爬虫"+Thread.currentThread().getName()+"over");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
//
//	public String start() {
//
//		try {
//
//			// 发送HTTP GET 请求，获得 HTML 文档
//			Connection conn = Jsoup.connect(url);
//			conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//			conn.header("Accept-Encoding", "gzip, deflate, sdch");
//			conn.header("Accept-Language", "zh-CN,zh;q=0.8");
//			conn.header("User-Agent",
//					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
//			Document doc = conn
//					.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0")
//					.get();
//
//			// System.out.println(doc.title());
//			// System.out.println(doc.text());
//			// System.out.println(doc.html());
//
//			// grid_view 类别中的item
//			Elements items = doc.select(".grid_view .item");
//			// System.out.println(items.size());
//
//			// 遍历
//			for (int i = 0; i < items.size(); i++) {
//
//				// 一部影片
//				Element item = items.get(i);
//				// 创建电影对象
//				Film film = new Film();
//
//				// item.select("em").get(0);
//
//				// 排名
//				String id = item.select("em").first().text();
//				// System.out.println(id);
//				film.setId(id);
//
//				// 图片
//				String poster = item.select("img").first().attr("src");
//				// System.out.println(poster);
//				film.setPoster(poster);
//
//				// 电影名字
//				String title = item.select(".info .title").first().text();
//				// System.out.println(title);
//				film.setTitle(title);
//
//				// 评分
//				String grade = item.select(".star .rating_num").text();
//				// System.out.println(grade);
//				film.setGrade(grade);
//
//				// 观众人数
//				String audience = item.select(".star span").last().text();
//				// System.out.println(audience);
//				film.setAudience(audience);
//
//				// 评语
//				String remark = item.select(".bd .quote").text();
//				// System.out.println(remark);
//				film.setRemark(remark);
//
//				// 电影地址
//
//				String filmAddress = item.select(".pic a").attr("href");
//				try {
//					Document docIn = Jsoup.connect(filmAddress).get();
//
//					// 导演
//					String director = docIn.select("#info .attrs").first().text();
//
//					film.setDirector(director);
//
//					// 主演
//					String protagonist = docIn.select("#info .actor .attrs").text();
//					film.setProtagonist(protagonist);
//
//					// 类型
//					String type = docIn.select("#info span[property$=v:genre]").text();
//					film.setType(type);
//
//					// 上映日期
//					String showTime = docIn.select("#info span[property$=v:initialReleaseDate]").text();
//					film.setShowTime(showTime);
//
//					// 片长
//					String time = docIn.select("#info span[property$=v:runtime]").text();
//					film.setTime(time);
//
//					linkList.add(film);
//					System.out.println(film);
//
//				} catch (Exception e) {
//					System.out.println("网址失效");
//				}
//
//			}
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return ((LinkedList<Film>) linkList).getLast().getId();
//
//	}

}
