package edu.spring.ex02.persistence;

import java.util.List;

import edu.spring.ex02.domain.BoardVO;
import edu.spring.ex02.pageutil.PageCriteria;

public interface BoardDAO {
	int insert(BoardVO vo); // ���
	List<BoardVO> select(); // ��ü ���
	BoardVO select(int boardId); // �� ���
	int update(BoardVO vo); // ����
	int delete(int boardId); // ����
	List<BoardVO> select(PageCriteria criteria); // ����¡ ó��
	int getTotalCounts(); // �� �Խñ� �� 
	List<BoardVO> select(String memberId); // �ۼ��ڷ� �˻�
	List<BoardVO> selectByTitleOrContent(String keyword); // �����̶� �������� �˻�
}
