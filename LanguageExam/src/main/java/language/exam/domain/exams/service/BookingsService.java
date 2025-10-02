package language.exam.domain.exams.service;

import java.util.Date;
import java.util.List;

import language.exam.domain.exams.model.Bookings;

public interface BookingsService {
	public void addBooking(Bookings bookings);
	
	public List<Bookings> getBookings(Integer accountId);
	
	public boolean isNotDuplicatedDate(Date examDate, Integer accountid);
	
	public void deleteBooking(Integer accountId, Integer examId);
	
	public boolean isInCapacity(Integer examId);
}