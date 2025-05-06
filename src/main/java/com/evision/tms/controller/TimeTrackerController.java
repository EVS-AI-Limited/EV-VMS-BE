package com.evision.tms.controller;

import com.evision.tms.dto.TimeTrackerDTO;
import com.evision.tms.entity.TimeTrackerEntity;
import com.evision.tms.repository.TimeTrackerRepository;
import com.evision.tms.service.impl.TimeTrackerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/timetracker")
public class TimeTrackerController {

//    public class TimeSheetTracker {

        TimeTrackerRepository timeSheetDetailsRepository;
        TimeTrackerService timeTrackerService;



//        Logger log = LogManager.getLogger(TimeSheetTracker.class);
//        @PostMapping(value = "/addEmployee", consumes = "application/json")
//        public ResponseEntity<EmpEntity> saveTimeDetails(@RequestBody EmpEntity employee) throws SQLException{
//            //employee.getStopTimeDTO().setStopTime(null);
//
//            EmpEntity emp=	employeeService.addEmployee(employee);
//
//            return new ResponseEntity<>(emp, HttpStatus.OK);
//        }
//        @PutMapping(value = "/startEvent/{id}", consumes = "application/json")
//        public ResponseEntity<EmpEntity> updateStartTimeDetails(@RequestBody EmpEntity empEntity ,@PathVariable("id")Integer id) throws SQLException{
//
//            List<Integer> list1=new ArrayList<>();
//            List<Integer> list2=new ArrayList<>();
//            List<Integer> list5=new ArrayList<>();
//            List list3=new ArrayList<>();
//            List list4=new ArrayList<>();
//            List<EmpEntityDetails> empEntityDetailsListFilter=new ArrayList<>();
//            List<EmpEntityDetails> empEntityDetailsList=new ArrayList<>();
//            EmpEntityDetails  empEntityDetail =new EmpEntityDetails();
//            Optional<EmpEntity> empEntityList ;
//
//            //list3=	empEntity.getEmpEntityDetails().stream().map(x->x.getDescription()).collect(Collectors.toList());
//            empEntityList=employeeRepository.findById(id);
//            list5=empEntity.getEmpEntityDetails().stream().map(x->x.getProjectId()).collect(Collectors.toList());
//            empEntityDetailsListFilter=empEntityDetailRepo.findAll();
//            list1=empEntityDetailsListFilter.stream().map(x->x.getProjectId()).collect(Collectors.toList());
//            list3=empEntityDetailsListFilter.stream().map(x->x.getDescription()).collect(Collectors.toList());
//            list2=empEntityList.get().getEmpEntityDetails().stream().map(x->x.getProjectId()).collect(Collectors.toList());
//            Stream<Object> ide=empEntityList.get().getEmpEntityDetails().stream().map(x->x.getProjectId());
//            list4=empEntityList.get().getEmpEntityDetails().stream().map(x->x.getDescription()).collect(Collectors.toList());
//            //Integer	pid=list5.forEach(x->x.intValue());
//            boolean b=list1.containsAll(list2)&& list3.containsAll(list4);
//            if(list1.containsAll(list5)&& list3.containsAll(list4) ) {
//                for(EmpEntityDetails e:empEntityDetailsListFilter){
//                    boolean c=e.getProjectId().equals(list5.get(0));
//                    if(e.getProjectId().equals(list5.get(0))) {
//                        //	EmpEntityDetails emp=	(EmpEntityDetails) empEntityDetailRepo.details(e);
//                        empEntityList.stream().map(x->x.getEmpEntityDetails().stream().map(y->y.getStartTime().now()));
//                        e.setStartTime(e.getStartTime().now());
//
//                        //empEntityDetailRepo.save(e);
//                    }
//
//                    // empEntityList.stream().map(x->x.getEmpEntityDetails().stream().map(y->y.getStopTime().now()));
//                    empEntityList.get();
//
//                } employeeService.addEmployee(empEntityList.get());
//                return new ResponseEntity<>( empEntityList.get(), HttpStatus.OK);
//            }
//            else {
//
//                employeeService.updateStartTime(empEntity);
//                return new ResponseEntity<>( empEntityList.get(), HttpStatus.OK);
//            }
//
//        }
//
//
//
//
//
//        @GetMapping(value = "/get")
//        public List<EmpEntityDetails> get() throws SQLException{
//            List<EmpEntityDetails> empEntityDetails=empEntityDetailRepo.findAll();
////		for(EmpEntityDetails emp:empEntityDetails) {
////			emp.getId();
////			emp.getProjectId();
////		}
//            return empEntityDetails;
//        }
//        @PutMapping(value = "/stopEve/{id}", consumes = "application/json")
//        public ResponseEntity<EmpEntity> updateStopTime(@RequestBody EmpEntity empEntity,@PathVariable("id") Integer id ) throws SQLException{
//            List<Integer> list1=new ArrayList<>();
//            List<Integer> list2=new ArrayList<>();
//            List<Integer> list5=new ArrayList<>();
//            List list3=new ArrayList<>();
//            List list4=new ArrayList<>();
//            List<EmpEntityDetails> empEntityDetailsListFilter=new ArrayList<>();
//            List<EmpEntityDetails> empEntityDetailsList=new ArrayList<>();
//            EmpEntityDetails  empEntityDetail =new EmpEntityDetails();
//            Optional<EmpEntity> empEntityList ;
//
//            //list3=	empEntity.getEmpEntityDetails().stream().map(x->x.getDescription()).collect(Collectors.toList());
//            empEntityList=employeeRepository.findById(id);
//            list5=empEntity.getEmpEntityDetails().stream().map(x->x.getProjectId()).collect(Collectors.toList());
//            empEntityDetailsListFilter=empEntityDetailRepo.findAll();
//            list1=empEntityDetailsListFilter.stream().map(x->x.getProjectId()).collect(Collectors.toList());
//            list3=empEntityDetailsListFilter.stream().map(x->x.getDescription()).collect(Collectors.toList());
//            list2=empEntityList.get().getEmpEntityDetails().stream().map(x->x.getProjectId()).collect(Collectors.toList());
//            Stream<Object> ide=empEntityList.get().getEmpEntityDetails().stream().map(x->x.getProjectId());
//            list4=empEntityList.get().getEmpEntityDetails().stream().map(x->x.getDescription()).collect(Collectors.toList());
//            //Integer	pid=list5.forEach(x->x.intValue());
//            boolean b=list1.containsAll(list2)&& list3.containsAll(list5);
//            if(list1.containsAll(list5)&& list3.containsAll(list4) ) {
//                for(EmpEntityDetails e:empEntityDetailsListFilter){
//                    boolean c=e.getProjectId().equals(list5.get(0));
//                    if(e.getProjectId().equals(list5.get(0))) {
//                        //	EmpEntityDetails emp=	(EmpEntityDetails) empEntityDetailRepo.details(e);
//                        empEntityList.stream().map(x->x.getEmpEntityDetails().stream().map(y->y.getStopTime().now()));
//                        e.setStopTime(e.getStopTime().now());
//                        e.setStopStatus("Updated");
//                    }
//
//                    // empEntityList.stream().map(x->x.getEmpEntityDetails().stream().map(y->y.getStopTime().now()));
//                    empEntityList.get();
//
//                } employeeService.addEmployee(empEntityList.get());
//                return new ResponseEntity<>( empEntityList.get(), HttpStatus.OK);
//            }
//            else {
//
//                employeeService.addEmployee(empEntity);
//                return new ResponseEntity<>( empEntityList.get(), HttpStatus.OK);
//            }



//        }
        @PostMapping( "/addProject")
        public ResponseEntity<TimeTrackerEntity> saveProject(@RequestBody TimeTrackerDTO timeTrackerDTO) throws SQLException{
         TimeTrackerEntity  tsd=	timeTrackerService.addDetail(timeTrackerDTO);
         return new ResponseEntity<>(tsd, HttpStatus.OK);
        }
        @PutMapping(value = "/startProject", consumes = "application/json")
        public ResponseEntity<TimeTrackerEntity> start(@RequestBody TimeTrackerDTO timeTrackerDTO) throws SQLException {
        TimeTrackerEntity tsd=	timeTrackerService.addDetail(timeTrackerDTO);


            return new ResponseEntity<>(tsd, HttpStatus.OK);
        }
        @PutMapping(value = "/stopProject", consumes = "application/json")
        public ResponseEntity<TimeTrackerEntity> stop(@RequestBody TimeTrackerDTO timeTrackerDTO) throws SQLException{
            TimeTrackerEntity ts=timeTrackerService.stopTime(timeTrackerDTO);
            return new ResponseEntity<>(ts, HttpStatus.OK);
        }
//    }



}
