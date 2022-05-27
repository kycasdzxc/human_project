package domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDto {
	private static final int PAGE_COUNT = 10;
	
	private int start;	// 시작 숫자
	private int end;	// 종료 숫자
	private int total;	// 게시글 총 개수
	private boolean next;	// 다음 페이지 가능여부
	private boolean prev;	// 이전 페이지 가능여부
	private Criteria cri;	// pageNum, amount

	public PageDto(int total, Criteria cri) {
		this.total = total;
		this.cri = cri;
		
		end = (cri.getPageNum() + (PAGE_COUNT - 1)) / PAGE_COUNT * PAGE_COUNT;
		start = end - PAGE_COUNT + 1;
		
		int realEnd = (total + (cri.getAmount()-1)) / cri.getAmount();
		if(realEnd < end) {
			end = realEnd;
		}
		prev = cri.getPageNum() > 1;
		next = cri.getPageNum() < realEnd;
	}
}