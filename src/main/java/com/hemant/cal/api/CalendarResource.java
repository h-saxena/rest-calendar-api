package com.hemant.cal.api;

import com.hemant.cal.model.AppCal;
import com.hemant.cal.repo.AppCalRepo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class CalendarResource {

    @Autowired
    AppCalRepo calRepo;

    @GetMapping("/cals")
    @ApiOperation(value = "Get all Calendars")
    public List<AppCal> retrieveAllCalendars() {
        return calRepo.findAll();
    }

    @GetMapping("/cals/{id}")
    public AppCal retrieveCalendar(@PathVariable("id") final Long id, final HttpServletResponse response) {
        AppCal cal = null;

        Optional res = calRepo.findById(id);
        if(res.isPresent()) {
            cal = (AppCal) res.get();
        }
        return cal;
    }

    @DeleteMapping("/cals/{id}")
    public void deleteCalendar(@PathVariable long id) {
        calRepo.deleteById(id);
    }


    @PutMapping("/cals/{id}")
    public AppCal updateCalendar(@RequestBody AppCal reqCal, @PathVariable long id) {

        AppCal cal = null;

        Optional res = calRepo.findById(id);
        if(res.isPresent()) {
            cal = (AppCal) res.get();
            reqCal.setId(cal.getId());
            cal = calRepo.save(reqCal);
        }

        return cal;
    }

    @PostMapping("/cals")
    public AppCal createCalendar(@RequestBody AppCal reqCal) {
        return calRepo.save(reqCal);
    }

}