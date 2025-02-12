package com.edu.springboot.jpaboard;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	// DAO 역할의 리포지토리 인터페이스에 대한 빈 주입
	@Autowired
	private BoardRepository boardRepository;
	
	// 목록(페이징 없음)
	public List<BoardEntity> selectList(){
		Sort sort = Sort.by(Sort.Direction.DESC, "idx");
		
		return boardRepository.findAll(sort);
	}
	
//	쓰기
	public void insertPost(BoardEntity boardTable){
		boardRepository.save(boardTable);
	}
	
	// 열람
	public Optional<BoardEntity> selectPost(Long idx){
		// 게시물 인출
		Optional<BoardEntity> row = boardRepository.findById(idx);
		
		// 조회수 증가
		BoardEntity be = row.get();
		Long hits = (be.getHits()==null) ? 0 : be.getHits();
		
		be.setHits(hits+1);
		boardRepository.save(be);
		
		return row;
	}
	
	// 수정
	public void updatePost(BoardEntity boardEntity) {
		boardRepository.save(boardEntity);
	}
	
//	삭제
	public void deletePost(Long idx) {
		boardRepository.deleteById(idx);
	}

}
