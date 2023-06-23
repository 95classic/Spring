package edu.spring.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.ex02.domain.BoardVO;
import edu.spring.ex02.pageutil.PageCriteria;
import edu.spring.ex02.persistence.BoardDAO;
import oracle.jdbc.OracleDriver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class BoardDAOTest {
	private static final Logger logger = 
			LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Autowired
	private BoardDAO dao;
	
	@Test
	public void testDAO() {
//		testInsert();
//		testDAOselect();
//		testSelectByBoardId();
//		testUpdate();
//		testDelete();
//		testSelectPaging();
//		testTotalCounts();
		testSelectSearch();
	}

	


	private void testSelectSearch() {
		//List<BoardVO> list = dao.select("t");
		List<BoardVO> list = dao.selectByTitleOrContent("t");
		for(BoardVO vo : list) {
			logger.info(vo.toString());
		}
		
	} // end testSelectSearch



	private void testTotalCounts() {
		int totalCounts = dao.getTotalCounts();
		logger.info("총 게시글 수 : " + totalCounts);
		
	} // end TotalCounts

	private void testSelectPaging() {
		PageCriteria criteria = new PageCriteria(1, 3);
		List<BoardVO> list = dao.select(criteria);
		for(BoardVO vo : list) {
			logger.info(vo.toString());
		}
		 
	} // end Selectpaging


	private void testDelete() {
		int result = dao.delete(2);
		if(result == 1) {
			logger.info("delete 성공");
		} else {
			logger.info("delete 실패");
		}
		
	} // end Delete()



	private void testUpdate() {
		BoardVO vo = new BoardVO(2, "update", "update", null, null, 0);
		int result = dao.update(vo);
		if(result == 1) {
			logger.info("udpate 성공");
		} else { 
			logger.info("update 실패");
		}
		
	} // end Update()



	private void testSelectByBoardId() {
		BoardVO vo = dao.select(2);
		logger.info(vo.toString());
	} // end selectbyboardId()



	private void testDAOselect() {
		List<BoardVO> list = dao.select();
		for(BoardVO vo : list);
		
	} // end selectAll()

	private void testInsert() {
		BoardVO vo = new BoardVO(0, "안녕", "금요일입니다", "mok", null, 0);
		int result = dao.insert(vo);
		if(result == 1) {
			logger.info("insert 성공");
		} else {
			logger.info("insert 실패");
		}
		
	} // end testInsert()
	
	
	
} // end BoardDAOTest