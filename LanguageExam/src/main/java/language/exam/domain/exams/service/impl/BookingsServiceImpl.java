package language.exam.domain.exams.service.impl;

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
		return mapper.selectBookings(accountId);
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