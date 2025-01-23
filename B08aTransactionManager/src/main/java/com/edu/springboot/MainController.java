package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.jdbc.ITicketService;
import com.edu.springboot.jdbc.PayDTO;
import com.edu.springboot.jdbc.TicketDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	
	// Mybatis 사용을 위한 인터페이스 자동 주입
	@Autowired
	ITicketService dao;
	
	/* 
	 * 트랜잭션 처리를 위한 빈 자동주입. 스프링 컨테이너가 미리 생성해둔 것을 자동으로 주입받아 사용할 수 있다! 
	 */
	@Autowired
	PlatformTransactionManager transactionManager;
	@Autowired
	TransactionDefinition definition;
	
	@RequestMapping("/")
	public String home(){
		return "home";
	}	
	
	// 티켓 구매 - Get매핑 : 구매 페이지 이동
	@GetMapping(value="/buyTicket.do")
	public String buy1() {
		return "buy";
	}
	// 티켓 구매 - Post매핑 : 구매 처리
	@PostMapping(value="/buyTicket.do")
	public String buy2(TicketDTO ticketDTO, PayDTO payDTO, 
			HttpServletRequest req, Model model) {
		
		// 구매에 성공한 경우 포워드할 View 경로
		String viewPath = "success";
		
		/*
		 * 자동주입된 빈을 통해 status 인스턴스를 생성한다.
		 * 이를 통해 트랜잭션 처리를 하게된다.
		 */
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			/* 
			 * DB처리1 
			 * 	: 구매금액에 대한 입력처리. 구매장수*10000원 
			 */
			payDTO.setAmount(ticketDTO.getT_count()*10000);
			int result1 = dao.payInsert(payDTO);
			// insert 성공시 콘솔에 로그 출력
			if(result1==1) System.out.println("tansaction_pay 입력성공");
			
			/*
			 * 비즈니스 로직 처리(의도적 에러 발생)
			 * 구매페이지에서 체크박스에 체크하면 에러 발생
			 */
			String errFlag = req.getParameter("err_flag");
			if(errFlag!=null) {
				// NumberFormatException : '원'은 int로 변환 불가
				int money = Integer.parseInt("100원");
			}
			
			/*
			 * DB처리2 
			 *  : 구매한 티켓 매수에 대한 처리로 6장 이상이면 check제약조건 위배, DB에러
			 */
			int result2 = dao.ticketInsert(ticketDTO);
			if(result2 == 1) System.out.println("transaction_ticket 입력성공");
			
			/*
			 * DTO를 Model에 저장
			 */
			model.addAttribute("ticketDTO", ticketDTO);
			model.addAttribute("payDTO", payDTO);
			
			// 모든 작업이 정상 처리되었다면 commit
			transactionManager.commit(status);
		}
		catch(Exception e) {
			e.printStackTrace();
			/*
			 * 위 3개의 단위작업 중 (DB처리1, 비즈니스로직처리(의도적에러발생), DB처리2)
			 * 하나라도 오류가 발생하면 전체 작업을 rollback한 후 에러페이지로 포워드
			 */
			viewPath = "error";
			transactionManager.rollback(status);
		}
		return viewPath;
	}
}