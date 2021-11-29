package com.techelevator.dao;

import com.techelevator.model.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcReservationDao implements ReservationDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcReservationDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int createReservation(int siteId, String name, LocalDate fromDate, LocalDate toDate) {

        String sql = "INSERT INTO reservation VALUES (DEFAULT, ?, ?, ?, ?, CURRENT_DATE) RETURNING reservation_id;";

        int newId = jdbcTemplate.queryForObject(sql, int.class, siteId, name,
                fromDate, toDate);

        return newId;
    }

    @Override
    public List<Reservation> getUpcomingReservationByPark(int parkId) {

        List<Reservation> reservations = new ArrayList<>();

        String sql = "SELECT * FROM reservation r\n" +
                "JOIN site s ON s.site_id = r.site_id\n" +
                "JOIN campground c ON c.campground_id = s.campground_id\n" +
                "JOIN park p ON p.park_id = c.park_id\n" +
                "WHERE from_date BETWEEN CURRENT_DATE AND (CURRENT_DATE + 30) AND p.park_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);

        while (results.next()) {
            reservations.add(mapRowToReservation(results));
        }

        return reservations;
    }

    private Reservation mapRowToReservation(SqlRowSet results) {
        Reservation r = new Reservation();
        r.setReservationId(results.getInt("reservation_id"));
        r.setSiteId(results.getInt("site_id"));
        r.setName(results.getString("name"));
        r.setFromDate(results.getDate("from_date").toLocalDate());
        r.setToDate(results.getDate("to_date").toLocalDate());
        r.setCreateDate(results.getDate("create_date").toLocalDate());
        return r;
    }


}
