package works.yermi.mapper;

import java.util.List;
import works.yermi.domain.NoteVO;

public interface Point_HistoryMapper {
		// 포인트 적립 내역 조회
		public List<NoteVO> getList();
		// 포인트 사용 내역 조회
		public List<NoteVO> getUseList();
		
}
