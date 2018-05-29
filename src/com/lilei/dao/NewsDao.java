package com.lilei.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lilei.entity.News;
import com.lilei.entity.NewsDetail;
import com.lilei.entity.NewsListForFound;
import com.lilei.utils.JdbcUtils;

/**
 * 
 * 
 * @author djzhao
 * @time 2017年5月5日 下午7:56:57
 */
public class NewsDao {

	/** sql语句 */
	private String sql = "";

	/** QueryRunner */
	private QueryRunner queryRunner = JdbcUtils.getQueryRunnner();

	public boolean releaseNews(News news) {
		sql = "INSERT INTO news (userId, title, content, image) VALUES (?, ?, ?, ?);";
		try {
			return queryRunner.update(sql, news.getUserId(), news.getTitle(), news.getContent(), news.getImage()) > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<News> getNewsList(int rows) {
		sql = "SELECT * from news ORDER BY releaseTime DESC;";
		try {
			return queryRunner.query(sql, new BeanListHandler<News>(News.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public NewsDetail getNewsDetail(String newsId) {
		sql = "SELECT * FROM comments WHERE newsId = ?";
		try {
			return queryRunner.query(sql, new BeanHandler<NewsDetail>(NewsDetail.class), newsId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}
