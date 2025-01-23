package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
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
	
	// 트랜잭션 처리를 위한 빈 자동 주입
	@Autowired
	TransactionTemplate transactionTemplate;
	
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
		
		/* 템플릿을 사용하면 기존 status인스턴스는 필요없으므로 삭제 */
		
		try {
			/*
			 * 템플릿 내 익명클래스를 통해 오버라이딩된 메서드에 모든 비즈니스 로직을 작성
			 * 템플릿을 사용하면 commit, rollback 등 트랜잭션이 자동 처리된다. 
			 */
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus status) {
					/* DB처리1 
					 * 	: 구매금액에 대한 입력처리. 구매장수*10000원 
					 */
					payDTO.setAmount(ticketDTO.getT_count()*10000);
					int result1 = dao.payInsert(payDTO);
					if(result1==1) System.out.println("tansaction_pay 입력성공");
					
					/* 비즈니스 로직 처리(의도적 에러 발생)
					 * 구매페이지에서 체크박스에 체크하면 에러 발생
					 */
					String errFlag = req.getParameter("err_flag");
					if(errFlag!=null) {
						int money = Integer.parseInt("100원");
					}
					
					/* DB처리2 
					 *  : 구매한 티켓 매수에 대한 처리로 6장 이상이면 check제약조건 위배, DB에러
					 */
					int result2 = dao.ticketInsert(ticketDTO);
					if(result2 == 1) System.out.println("transaction_ticket 입력성공");
					
					model.addAttribute("ticketDTO", ticketDTO);
					model.addAttribute("payDTO", payDTO);
				}
			});
		}
		catch(Exception e) {
			e.printStackTrace();
			viewPath = "error";
		}
		return viewPath;
	}
}