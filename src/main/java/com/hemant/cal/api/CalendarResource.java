package com.hemant.cal.api;

import com.hemant.cal.model.AppCal;
import com.hemant.cal.repo.AppCalRepo;
import com.hemant.cal.security.Secured;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class CalendarResource {

    @Autowired
    AppCalRepo calRepo;

    @Secured
    @GetMapping("/{clientId}/cals")
    @ApiOperation(value = "Get all Calendars")
    public List<AppCal> retrieveAllCalendars() {
        return calRepo.findAll();
    }

    @Secured
    @GetMapping("/{clientId}/cals/{id}")
    public AppCal retrieveCalendar(@PathVariable("clientId") final String clientId, @PathVariable("id") final Long id, final HttpServletResponse response) {
        AppCal cal = null;

        Optional res = calRepo.findById(id);
        if(res.isPresent()) {
            cal = (AppCal) res.get();
        }
        return cal;
    }

    @Secured
    @DeleteMapping("/{clientId}/cals/{id}")
    public void deleteCalendar(@PathVariable("clientId") final String clientId,@PathVariable long id) {
        calRepo.deleteById(id);
    }


    @Secured
    @PutMapping("/{clientId}/cals/{id}")
    public AppCal updateCalendar(@PathVariable("clientId") final String clientId, @RequestBody AppCal reqCal, @PathVariable long id) {

        AppCal cal = null;

        Optional res = calRepo.findById(id);
        if(res.isPresent()) {
            cal = (AppCal) res.get();
            reqCal.setId(cal.getId());
            cal = calRepo.save(reqCal);
        }

        return cal;
    }

    @Secured
    @PostMapping("/{clientId}/cals")
    public AppCal createCalendar(@PathVariable("clientId") final String clientId, @RequestBody AppCal reqCal) {
        return calRepo.save(reqCal);
    }

}