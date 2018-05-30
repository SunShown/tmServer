package com.lilei.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lilei.dao.CourseInfo;
import com.lilei.entity.Question;
import com.lilei.entity.Qwork;
import com.lilei.entity.Work;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 
 * @author djzhao
 * @time 2017年5月2日 下午10:00:56
 */
public class WorkServlet extends BaseMobileServlet {

	private static final long serialVersionUID = 4495375668511646732L;

	/**
	 * 打卡
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */

	// 获得打卡记录
	public String getWorkList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		List<Work> worksList = workDao.getWorkList();
		Gson gson = new Gson();
		String json = gson.toJson(worksList);
		if (json == null) {
			return ERROR;
		} else {
			return json;
		}
	}

	//
	public String getWorkDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String workId = request.getParameter("workId");
		List<Question> worksList = workDao.getWorkdetail(workId);
		Gson gson = new Gson();
		String json = gson.toJson(worksList);
		if (json == null) {
			return ERROR;
		} else {
			return json;
		}
	}



	public String commitWork(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String workStatus = request.getParameter("workDetail");
		Gson gson = new Gson();
		List<Qwork> qworks = gson.fromJson(workStatus, new TypeToken<ArrayList<Qwork>>() {
		}.getType());
		int isSuccess = workDao.commitWork(qworks);
		return isSuccess+"";
	}


	//布置作业
	public String releaseWork(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String workStatus = request.getParameter("content");
		Gson gson = new Gson();
		Work work = gson.fromJson(workStatus, Work.class);
		String isSuccess = workDao.releaseWork(work);
		return isSuccess+"";
	}


	//提交课程
	public String commitCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String course = request.getParameter("content");
		Gson gson = new Gson();
		CourseInfo work = gson.fromJson(course, CourseInfo.class);
		String isSuccess = workDao.commitCourse(work);
		return isSuccess+"";
	}

	//获取课程信息
	public String getCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String week = request.getParameter("week");
		List<CourseInfo> couserInfo = workDao.getCourse(Integer.parseInt(week));
		Gson  gson = new Gson();
		return  gson.toJson(couserInfo);
	}

	//获取课程信息
	public String deleteCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String courseId = request.getParameter("courseId");
		return workDao.deleteCourse(Integer.parseInt(courseId));
	}
}
