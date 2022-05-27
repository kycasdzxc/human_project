package works.yermi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import works.yermi.domain.Criteria;
import works.yermi.domain.CriteriaPension;
import works.yermi.domain.PensionVO;
import works.yermi.mapper.PensionAttachMapper;
import works.yermi.mapper.PensionMapper;

@Service @AllArgsConstructor @Log4j
public class PensionServiceImpl implements PensionService {
	private PensionMapper mapper;
	private PensionAttachService service;
	
	@Override
	public PensionVO get(Long pensionid) {
		PensionVO vo = mapper.read(pensionid);
		log.info(service.getList(vo.getPensionid()));
		vo.setAttachs(service.getList(vo.getPensionid()));
		return vo;
	}

	@Override
	@Transactional
	public int register(PensionVO vo) {
		return mapper.insertSelectKey(vo);
	}

	@Override
	public boolean modifyPension(PensionVO vo) {
		service.removeAll(vo.getPensionid());
		
		vo.getAttachs().forEach(attach -> {
			attach.setPensionid(vo.getPensionid());
			service.register(attach);
		});
		return mapper.updatePension(vo) > 0;
	}

	@Override
	public boolean removePension(Long pensionid) {
		service.removeAll(pensionid);
		return mapper.deletePension(pensionid) > 0;
	}

	@Override
	public List<PensionVO> getListTopten() {
		List<PensionVO> list = mapper.getListTopten();
		list.forEach(pension -> {
			pension.setAttachs(service.getList(pension.getPensionid()));
		});
		return list;
	}

	@Override
	public List<PensionVO> getList() {
		return mapper.getList();
	}

	@Override
	@Transactional
	public int registerPublic(PensionVO vo) {
		return mapper.insertPublic(vo);
	}

	@Override
	@Transactional
	public int registerInternal(PensionVO vo) {
		return mapper.insertInternal(vo);
	}

	@Override
	@Transactional
	public int registerOther(PensionVO vo) {
		return mapper.insertOther(vo);
	}

	@Override
	public List<PensionVO> getListWithFilter(CriteriaPension cri, PensionVO vo) {
		List<PensionVO> list = mapper.getListWithFilter(cri, vo);
		list.forEach(pension -> {
			pension.setAttachs(service.getList(pension.getPensionid()));
		});
		return list;
	}
	
	@Override
	public List<PensionVO> getListWithAd() {
		List<PensionVO> list = mapper.getListWithAd();
		list.forEach(pension -> {
			pension.setAttachs(service.getList(pension.getPensionid()));
		});
		return list;
	}

	@Override
	public PensionVO findPension(PensionVO vo) {
		PensionVO pensionVO = mapper.findPension(vo);
		return pensionVO;
	}

	@Override
	@Transactional
	public boolean modifyPublic(PensionVO vo) {
		return mapper.updatePublic(vo) > 0;
	}

	@Override
	@Transactional
	public boolean modifyInternal(PensionVO vo) {
		return mapper.updateInternal(vo) > 0;
	}

	@Override
	@Transactional
	public boolean modifyOther(PensionVO vo) {
		return mapper.updateOther(vo) > 0; 
	}

	@Override
	@Transactional
	public boolean removePublic(Long pensionid) {
		return mapper.deletePublic(pensionid) > 0;
	}

	@Override
	@Transactional
	public boolean removeInternal(Long pensionid) {
		return mapper.deleteInternal(pensionid) > 0;
	}

	@Override
	@Transactional
	public boolean removeOther(Long pensionid) {
		return mapper.deleteOther(pensionid) > 0;
	}

	@Override
	public List<PensionVO> getPensionidByUserid(String userid) {
		return mapper.getPensionidByUserid(userid);
	}

	@Override
	public List<PensionVO> getListWithNotAd(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getListWithNotAd(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		return mapper.getTotalCount(cri);
	}

	@Override
	public boolean modifyAd(Long pensionid) {
		return mapper.updateAd(pensionid) > 0;
	}

	@Override
	public List<PensionVO> getListWithPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getListWithPaging(cri);
	}

	@Override
	public List<PensionVO> ListWithAd(Criteria cri) {
		
		return mapper.ListWithAd(cri);
	}

	@Override
	public boolean removeAd(Long pensionid) {
		// TODO Auto-generated method stub
		return mapper.deleteAd(pensionid) > 0;
	}
	
}
