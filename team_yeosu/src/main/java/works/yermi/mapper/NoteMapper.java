package works.yermi.mapper;

import java.util.List;
	
import works.yermi.domain.NoteVO;

public interface NoteMapper {
	// 문의글 전체목록 조회
	public List<NoteVO> getList();
	// 문의글 단일조회
	public NoteVO read(int noteNum);
	// 문의글 등록
	public int insert(NoteVO vo);
	// 문의글 수정
	public int update(NoteVO vo);
	// 문의글 삭제
	public int delete(int noteNum);
	
}
