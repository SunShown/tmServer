package com.lilei.dao;

import com.lilei.entity.*;
import com.lilei.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author djzhao
 * @time
 */
public class WorkDao {

	/** sql语句 */
	private String sql = "";
	
	/** QueryRunner */
	private QueryRunner queryRunner = JdbcUtils.getQueryRunnner();
	public List<Work> getWorkList() {
		List<Work> works = new ArrayList<>();
		try {
			sql= "SELECT * FROM work ORDER BY time DESC";
			works = queryRunner.query(sql, new BeanListHandler<Work>(Work.class));
			for (int i = 0; i < works.size(); i++) {
				sql ="SELECT * FROM user where userId = ?";
				User user = queryRunner.query(sql,works.get(i).getUserId(),new BeanHandler<>(User.class));
				works.get(i).setUser(user);
			}
		} catch (Exception e) {

		}
		return works;
	}
	public List<Question> getWorkdetail(String workDetail){
		try {
			sql= "SELECT * FROM `work` a, question b WHERE a.workId = b.workId AND a.workId = ? ORDER BY a.time DESC;";
			return queryRunner.query(sql, new BeanListHandler<Question>(Question.class),workDetail);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int commitWork(List<Qwork> qworks){
		int update = 0;
		try {

			for (Qwork qwork : qworks) {
				sql = "INSERT INTO qwork (userId, questionId, `select`, isRight) VALUES (?, ?, ?,? )";
				update +=queryRunner.update(sql, qwork.userId, qwork.questionId, qwork.select, qwork.isRight);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}


	public List<Comments> getCommentsByNewsId(String newsId) {
		try {
			sql= "SELECT commentId, username, replyUser, `comment`, commentTime FROM `user` a, comments b WHERE a.userId = b.userId AND newsId = ? ORDER BY commentTime DESC;";
			return queryRunner.query(sql, new BeanListHandler<Comments>(Comments.class), newsId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean addNewComment(String userId, String newsId, String comment, String replyUser) {
		try {
			sql= "INSERT INTO comments (newsId, userId, comment, replyUser) VALUES (?, ?, ?, ?);";
			return queryRunner.update(sql, newsId, userId, comment, replyUser) > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public String releaseWork(Work work){
		BigInteger insertId = new BigInteger("0") ;
		int result = 0;
		sql = "INSERT INTO `work` (workTitle,userId) VALUES(?,?)";
		try {
			int i = queryRunner.update(sql, work.getWorkTitle(), work.getUserId());
			if (i == 1) {
				BigInteger query = queryRunner.query("SELECT LAST_INSERT_ID()",new ScalarHandler<BigInteger>());
				insertId =  query;
			}
			for (int j = 0; j < work.getWorkList().size(); j++) {
				Question question = work.getWorkList().get(j);
				sql = "INSERT INTO question (title,qOne,qTwo,qThree,qFour,answer,workId) VALUES(?,?,?,?,?,?,?)";
				result +=queryRunner.update(sql, question.title, question.qOne,question.qTwo,question.qThree,question.qFour,question.answer,insertId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == 0){
			return "error";
		}else {
			return "success";
		}

	}
	
}
