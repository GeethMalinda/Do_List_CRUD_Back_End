package lk.misynergy.dolistcrud.controller;

import lk.misynergy.dolistcrud.advisor.ResourceNotFoundException;
import lk.misynergy.dolistcrud.entity.Shedule;
import lk.misynergy.dolistcrud.repo.SheduleRepo;
import lk.misynergy.dolistcrud.util.StandradResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin
public class Controller {

    @Autowired
    private SheduleRepo sheduleRepo;

    @GetMapping("/shedules")
    public ResponseEntity getAllShedules(){
        List<Shedule> allshedule = sheduleRepo.findAll();
        return new ResponseEntity(new StandradResponse(200,"Success",allshedule), HttpStatus.OK);
    }
    @PostMapping("/shedules")
    public ResponseEntity createShedule(@RequestBody Shedule shedule){
        sheduleRepo.save(shedule);
        StandradResponse response = new StandradResponse(200, "Success", null);
        return new ResponseEntity(response,HttpStatus.CREATED);
    }
    @PutMapping("/shedules/{id}")
    public  ResponseEntity updateShedule(@PathVariable Long id,@RequestBody Shedule sheduleDetails){
        Shedule shedule= sheduleRepo.findById(id).orElseThrow
                ((() -> new ResourceNotFoundException("Shedule Not Exists With Id " + id)));

        shedule.setTitle(sheduleDetails.getTitle());
        shedule.setDescription(sheduleDetails.getDescription());

        Shedule updateShedule = sheduleRepo.save(shedule);
        StandradResponse response = new StandradResponse(200, "Success", null);
        return  new ResponseEntity(response,HttpStatus.OK);

    }
    @DeleteMapping("/shedules/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteShedule(@PathVariable Long id){
        Shedule shedule= sheduleRepo.findById(id).orElseThrow
                ((() -> new ResourceNotFoundException("Shedule Not Exists With Id " + id)));

        sheduleRepo.delete(shedule);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/shedules/{id}")
    public ResponseEntity getSheduleById(@PathVariable Long id){
        Shedule shedule= sheduleRepo.findById(id).orElseThrow
                ((() -> new ResourceNotFoundException("Shedule Not Exists With Id " + id)));

        StandradResponse response = new StandradResponse(200, "Success", shedule);
        return new ResponseEntity(response,HttpStatus.OK);
    }


}
