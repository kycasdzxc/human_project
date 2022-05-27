package works.yermi.mapper;

import works.yermi.domain.AuthVO;

public interface AuthMapper {
	// 권한 생성
	public int insertSelectKey(AuthVO authVO);
	// 권한 조회 
	AuthVO read(String auth);
	// 권한 수정
	int update(AuthVO vo);
	// 권한 삭제
	int delete(AuthVO vo);

}
