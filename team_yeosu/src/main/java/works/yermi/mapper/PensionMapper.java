package works.yermi.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import works.yermi.domain.Criteria;
import works.yermi.domain.CriteriaPension;
import works.yermi.domain.PensionVO;

public interface PensionMapper {
	
	public List<PensionVO> getList();
	
	public List<PensionVO> getListTopten();
	
	public List<PensionVO> getListWithFilter(@Param("cri") CriteriaPension cri, @Param("pension")PensionVO vo);
	
	public List<PensionVO> getListWithAd();
	
	public List<PensionVO> getListWithNotAd(Criteria cri);
	
	public List<PensionVO> ListWithAd(Criteria cri);
	
	public int getTotalCount(Criteria cri);
	
	public PensionVO read(Long pensionid);
	
	public PensionVO findPension(PensionVO vo);
	
	public int insertSelectKey(PensionVO vo);
	
	public int insertPublic(PensionVO vo);
	
	public int insertInternal(PensionVO vo);
	
	public int insertOther(PensionVO vo);
	
	public int updatePension(PensionVO vo);
	
	public int updatePublic(PensionVO vo);
	
	public int updateInternal(PensionVO vo);
	
	public int updateOther(PensionVO vo);
	
	public int deletePension(Long pensionid);
	
	public int deletePublic(Long pensionid);
	
	public int deleteInternal(Long pensionid);
	
	public int deleteOther(Long pensionid);
	
	public List<PensionVO> getPensionidByUserid(String userid);
	
	public int updateReplyCnt(Long pensionid);
	
	public int updateStarRate(Long pensionid);
	
	public int updatePrice(Long pensionid);
	
	public int updateAd(Long pensionid);

	public int updateEnabled(PensionVO vo);

	public List<PensionVO> getListWithPaging(Criteria cri);
	
	public int deleteAd(Long pensionid);
	
//	@Select("SELECT PENSIONID, MIN_PRICE FROM TBL_PENSION JOIN (SELECT PENSIONID, MIN(PRICE) MIN_PRICE FROM TBL_ROOM T GROUP BY PENSIONID) USING(PENSIONID)")
//	public List<Map<String, Long>> test1();
//	
//	@Update("UPDATE TBL_PENSION SET PRICE = #{MIN_PRICE} where pensionid = #{PENSIONID}")
//	public int test2(Map<String, Long> map);
//	
//	@Select("SELECT PENSIONID, AVG_STARRATE FROM TBL_PENSION JOIN(SELECT PENSIONID, ROUND(AVG(STARRATE), 1) AVG_STARRATE FROM TBL_REPLY GROUP BY PENSIONID)USING (PENSIONID)")
//	public List<Map<String, Long>> test3();
//	
//	@Update("UPDATE TBL_PENSION SET STARRATE = #{AVG_STARRATE} where pensionid = #{PENSIONID}")
//	public int test4(Map<String, Long>map);
//	
//	@Select("SELECT PENSIONID, TOTAL_REPLYCNT FROM TBL_PENSION JOIN (SELECT PENSIONID, COUNT(*) TOTAL_REPLYCNT FROM TBL_REPLY GROUP BY PENSIONID) USING(PENSIONID)")
//	public List<Map<String, Long>> test5();
//	
//	@Update("UPDATE TBL_PENSION SET REPLYCNT = #{TOTAL_REPLYCNT} where pensionid = #{PENSIONID}")
//	public int test6(Map<String, Long>map);

}
