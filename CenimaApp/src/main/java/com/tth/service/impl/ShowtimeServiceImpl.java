/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.service.impl;

/**
 *
 * @author Admin
 */
import com.tth.dto.ShowtimeAdminDTO;
import com.tth.dto.ShowtimeStatisticResponse;
import com.tth.pojo.Showtimes;
import com.tth.repository.BookingRepository;
import com.tth.repository.ShowtimeRepository;
import com.tth.repository.TicketRepository;
import com.tth.service.ShowtimeService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShowtimeServiceImpl implements ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepo;

    @Autowired
    private TicketRepository ticketRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @Override
    public List<Showtimes> getShowtimes(Map<String, String> params) {
        return this.showtimeRepo.getShowtimes(params);
    }

    @Override
    public Showtimes getShowtimeById(int id) {
        return this.showtimeRepo.getShowtimeById(id);
    }

    @Override
    public void addOrUpdate(Showtimes showtime) {

        if (showtime.getId() != null) {

            boolean booked
                    = bookingRepo.hasBookingByShowtime(
                            showtime.getId());

            if (booked) {
                throw new RuntimeException(
                        "Không thể sửa suất chiếu đã phát sinh vé!");
            }
        }

        boolean busy
                = showtimeRepo.isRoomBusy(
                        showtime.getRoomId().getId(),
                        showtime.getStartTime(),
                        showtime.getEndTime(),
                        showtime.getId());

        if (busy) {
            throw new RuntimeException(
                    "Phòng đã có lịch chiếu trong thời gian này!");
        }

        showtimeRepo.addOrUpdate(showtime);
    }

    @Override
    public void deleteShowtime(int id) {

        boolean booked
                = bookingRepo.hasBookingByShowtime(id);

        if (booked) {
            throw new RuntimeException(
                    "Không thể xóa suất chiếu đã phát sinh vé!");
        }

        showtimeRepo.deleteShowtime(id);
    }

    @Override
    public boolean isRoomBusy(
            int roomId,
            java.util.Date start,
            java.util.Date end,
            Integer showtimeId) {

        return this.showtimeRepo.isRoomBusy(
                roomId,
                start,
                end,
                showtimeId
        );
    }

 @Override
public long countAvailableSeats(int showtimeId) {
    return showtimeRepo.countAvailableSeats(showtimeId);
}


    @Override
    public long countSoldTickets(Integer showtimeId) {
        return showtimeRepo.countSoldTickets(showtimeId);
    }

    @Override
    public double revenueByShowtime(Integer showtimeId) {
        return showtimeRepo.revenueByShowtime(showtimeId);
    }

    @Override
    public ShowtimeStatisticResponse getStatistic(Integer showtimeId) {

        return new ShowtimeStatisticResponse(
                showtimeId,
                showtimeRepo.countSoldTickets(showtimeId),
                showtimeRepo.revenueByShowtime(showtimeId)
        );
    }

    @Override
    public List<Showtimes> getUpcomingShowtimes() {
        return this.showtimeRepo.getUpcomingShowtimes();
    }

    @Override
    public List<Showtimes> getTodayShowtimes() {
        return this.showtimeRepo.getTodayShowtimes();
    }

    @Override
    public List<ShowtimeAdminDTO> getAdminShowtimes() {

        List<Showtimes> showtimes
                = showtimeRepo.getShowtimes(null);

        List<ShowtimeAdminDTO> result
                = new ArrayList<>();

        Date now = new Date();

        for (Showtimes st : showtimes) {

            int totalSeats
                    = st.getRoomId().getCapacity();

            long soldSeats
                    = ticketRepo.countTicketsByShowtime(
                            st.getId());

            long availableSeats
                    = totalSeats - soldSeats;

            double revenue
                    = bookingRepo.revenueByShowtime(
                            st.getId());

            double occupancy
                    = totalSeats == 0
                            ? 0
                            : soldSeats * 100.0 / totalSeats;

            String status;

            if (st.getEndTime().before(now)) {
                status = "COMPLETED";
            } else if (st.getStartTime().before(now)) {
                status = "RUNNING";
            } else {
                status = "UPCOMING";
            }

            result.add(
                    new ShowtimeAdminDTO(
                            st.getId(),
                            st.getMovieId().getMovieName(),
                            st.getRoomId().getName(),
                            totalSeats,
                            soldSeats,
                            availableSeats,
                            occupancy,
                            revenue,
                            status
                    )
            );
        }

        return result;
    }
}

