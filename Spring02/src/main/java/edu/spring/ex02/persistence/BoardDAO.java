package edu.spring.ex02.persistence;

import java.util.List;

import edu.spring.ex02.domain.BoardVO;
import edu.spring.ex02.pageutil.PageCriteria;

public interface BoardDAO {
	int insert(BoardVO vo); // 등록
	List<BoardVO> select(); // 전체 출력
	BoardVO select(int boardId); // 상세 출력
	int update(BoardVO vo); // 수정
	int delete(int boardId); // 삭제
	List<BoardVO> select(PageCriteria criteria); // 페이징 처리
	int getTotalCounts(); // 총 게시글 수 
	List<BoardVO> select(String memberId); // 작성자로 검색
	List<BoardVO> selectByTitleOrContent(String keyword); // 제목이랑 내용으로 검색
}
