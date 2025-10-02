package language.exam.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import language.exam.domain.exams.model.Bookings;

@Mapper
public interface BookingsMapper {
	public void insertBooking(Bookings booking);
	
	public List<Bookings> selectBookings(Integer accountId);
	
	public int countBookings(Date examDate, Integer accountId);
	
	public void deleteBooking(Integer accountId, Integer examId);
	
	public int countBookingsWithExamId(Integer examId);
}