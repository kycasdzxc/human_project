package works.yermi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import works.yermi.domain.CustomUser;
import works.yermi.domain.MemberVO;
import works.yermi.mapper.MemberMapper;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired @Setter
	private MemberMapper mapper;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.warn(username);
		 // MemberVO 객체 생성
		 MemberVO vo = mapper.read(username); // mapper내 read method에 인자값으로 username을 받아서 return 결과(한명의 회원정보)를 vo에 담는다 
//		if(!vo.isDelStatus()) // MmeberVO에 delStatus값(기본값 0:false)이 false 일때 > delStatus가 true > 회원탈퇴상태
		 UserDetails details = vo == null || vo.isDelStatus() ? null : new CustomUser(vo); // db에 정보가 없으면 null, null 이 아니면 객체생성
		 
		 // vo에 담겨있는 회원정보가 null 이면 (회원정보를 못불러온 상태) security 내 회원정보를 담당하는 userdetails 에 회원정보를 null값으로 하고, 그렇지 않으면 vo에 담긴 user 정보를 userDetails에 담는다
		log.warn(details); // warn level 로그 출력으로 details에 담긴 user정보를 log 로 확인한다
		return details; // details에 담긴 user 정보를 리턴한다 // return 한 userDetails 가 null 이거나 authentication 이 invalid 할 때 login을 막는다
	}
}
