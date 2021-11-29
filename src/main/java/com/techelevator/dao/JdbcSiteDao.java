package com.techelevator.dao;

import com.techelevator.model.Site;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcSiteDao implements SiteDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcSiteDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Site> getSitesThatAllowRVs(int parkId) {
        String sql = "SELECT s.* FROM site s\n" +
                "JOIN campground c ON c.campground_id = s.campground_id\n" +
                "JOIN park p ON p.park_id = c.park_id\n" +
                "WHERE p.park_id = ? AND max_rv_length > 0;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkId);

        List<Site> sites = new ArrayList<>();

        while (result.next()) {
            Site newSite = mapRowToSite(result);
            sites.add(newSite);
        }

        return sites;
    }

    @Override
    public List<Site> getAvailableSites(int parkId) {
        String sql = "SELECT * FROM site s\n" +
                "LEFT JOIN reservation r ON r.site_id = s.site_id\n" +
                "JOIN campground c ON c.campground_id = s.campground_id\n" +
                "JOIN park p ON p.park_id = c.park_id\n" +
                "WHERE r.reservation_id IS NULL AND p.park_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);

        List<Site> availableSites = new ArrayList<>();

        while (results.next()) {
            Site availableSite = mapRowToSite(results);
            availableSites.add(availableSite);
        }

        return availableSites;

    }

    private Site mapRowToSite(SqlRowSet results) {
        Site site = new Site();
        site.setSiteId(results.getInt("site_id"));
        site.setCampgroundId(results.getInt("campground_id"));
        site.setSiteNumber(results.getInt("site_number"));
        site.setMaxOccupancy(results.getInt("max_occupancy"));
        site.setAccessible(results.getBoolean("accessible"));
        site.setMaxRvLength(results.getInt("max_rv_length"));
        site.setUtilities(results.getBoolean("utilities"));
        return site;
    }
}
