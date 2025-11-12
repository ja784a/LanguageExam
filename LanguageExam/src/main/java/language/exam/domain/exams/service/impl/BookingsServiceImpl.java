package language.exam.domain.exams.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import language.exam.domain.exams.model.Bookings;
import language.exam.domain.exams.service.BookingsService;
import language.exam.repository.BookingsMapper;

@Service
public class BookingsServiceImpl implements BookingsService {
	@Autowired
	private BookingsMapper mapper;
	
	public void addBooking(Bookings booking) {
		mapper.insertBooking(booking);
	}
	
	public List<Bookings> getBookings(Integer accountId) {
		List<Bookings> bookings = mapper.selectBookings(accountId);
		
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DATE, -1);
		Date yesterday = calendar.getTime();
		
		for (Bookings b : bookings) {
			if (b.getExamInfos().getUpdatedDateTime() == null) {
				b.getExamInfos().setUpdatedDateTime(yesterday);
			}
		}
		return bookings;
	}
	
	public boolean isNotDuplicatedDate(Date examDate, Integer accountId) {
		if (mapper.countBookings(examDate, accountId) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void deleteBooking(Integer accountId, Integer examId) {
		mapper.deleteBooking(accountId, examId);
	}
	
	public boolean isInCapacity(Integer examId) {
		if (mapper.countBookingsWithExamId(examId) < 5) {
			return true;
		} else {
			return false;
		}
	}
}