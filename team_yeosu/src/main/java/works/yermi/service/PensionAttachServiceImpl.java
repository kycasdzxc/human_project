package works.yermi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import works.yermi.domain.PensionAttachVO;
import works.yermi.mapper.PensionAttachMapper;

@Service @AllArgsConstructor
public class PensionAttachServiceImpl implements PensionAttachService{
	private PensionAttachMapper mapper;
	@Override
	public List<PensionAttachVO> getList(Long pensionid) {
		return mapper.findBy(pensionid);
	}
	@Override
	public int register(PensionAttachVO vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}
	@Override
	public boolean remove(String uuid) {
		// TODO Auto-generated method stub
		return mapper.delete(uuid) > 0;
	}
	@Override
	public boolean removeAll(Long pensionid) {
		// TODO Auto-generated method stub
		return mapper.deleteAll(pensionid) > 0;
	}
	
	
}
