package works.yermi.service;

import java.util.List;

import works.yermi.domain.Criteria;
import works.yermi.domain.CriteriaPension;
import works.yermi.domain.PensionVO;

public interface PensionService {
	
	/**
	 *  @author 이대석
	 */
	List<PensionVO> getList();
	
	/**
	 *  @author 이대석
	 */
	List<PensionVO> getListTopten();
	
	/**
	 *  @author 이대석
	 *  @param cri
	 *  @param vo
	 */
	List<PensionVO> getListWithFilter(CriteriaPension cri, PensionVO vo);
	
	/**
	 *  @author 이대석
	 */
	List<PensionVO> getListWithAd();
	
	/**
	 *  @author 이대석
	 * 	@param cri	
	 */
	List<PensionVO> getListWithNotAd(Criteria cri);
	
	/**
	 *  @author 이대석
	 * 	@param cri	
	 */
	List<PensionVO> ListWithAd(Criteria cri);
	
	/**
	 *  @author 이대석
	 * 	@param cri	
	 */
	public int getTotalCount(Criteria cri);
	
	/**
	 *  @author 이대석
	 * 	@param pensionid	
	 */
	PensionVO get(Long pensionid);
	
	/**
	 *  @author 이대석
	 * 	@param vo	
	 */
	int register(PensionVO vo);
	
	/**
	 *  @author 이대석
	 * 	@param vo	
	 */
	int registerPublic(PensionVO vo);
	
	/**
	 *  @author 이대석
	 * 	@param vo	
	 */
	int registerInternal(PensionVO vo);
	
	/**
	 *  @author 이대석
	 * 	@param vo	
	 */
	int registerOther(PensionVO vo);
	
	/**
	 *  @author 이대석
	 * 	@param vo	
	 */
	boolean modifyPension(PensionVO vo);
	
	/**
	 *  @author 이대석
	 * 	@param vo	
	 */
	boolean modifyPublic(PensionVO vo);
	
	/**
	 *  @author 이대석
	 * 	@param vo	
	 */
	boolean modifyInternal(PensionVO vo);
	
	/**
	 *  @author 이대석
	 * 	@param vo	
	 */
	boolean modifyOther(PensionVO vo);
	
	/**
	 *  @author 이대석
	 * 	@param pensionid	
	 */
	boolean removePension(Long pensionid);
	/**
	 *  @author 이대석
	 * 	@param pensionid	
	 */
	boolean removePublic(Long pensionid);
	/**
	 *  @author 이대석
	 * 	@param pensionid	
	 */
	boolean removeInternal(Long pensionid);
	/**
	 *  @author 이대석
	 * 	@param pensionid	
	 */
	boolean removeOther(Long pensionid);
	/**
	 *  @author 이대석
	 * 	@param pensionid	
	 */
	boolean modifyAd(Long pensionid);
	/**
	 *  @author 이대석
	 * 	@param pensionid	
	 */
	boolean removeAd(Long pensionid);
	/**
	 *  @author 이대석
	 * 	@param vo	
	 */
	PensionVO findPension(PensionVO vo);
	
	/**
	 *  @author 이대석
	 * 	@param userid	
	 */
	List<PensionVO> getPensionidByUserid(String userid);

	List<PensionVO> getListWithPaging(Criteria cri);

}
