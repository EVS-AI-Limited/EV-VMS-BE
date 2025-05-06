package com.evision.tms.service.impl;

import com.evision.tms.dto.TimeTrackerDTO;
import com.evision.tms.entity.TimeTrackerEntity;
import com.evision.tms.repository.TimeTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeTrackerService {

        private static final String des = null;

        @Autowired
        TimeTrackerRepository timeTrackerRepository;


        public TimeTrackerEntity addDetail(TimeTrackerDTO timeTrackerDTO)throws SQLException {
            TimeTrackerEntity tsd=new TimeTrackerEntity();
            tsd.setProjectId(timeTrackerDTO.getProjectId());
            tsd.setDescription(timeTrackerDTO.getDescription());
            tsd.setProjectName(timeTrackerDTO.getProjectName());

            timeTrackerRepository.save(tsd);
            return tsd;
        }
        public TimeTrackerEntity startTime(TimeTrackerDTO timeTrackerDTO)throws SQLException {
            TimeTrackerEntity tsd=new TimeTrackerEntity();
            List<TimeTrackerEntity> tdl=new ArrayList<>();
            List<Integer> list1=new ArrayList<>();
            tdl=timeTrackerRepository.findAll();
            list1= tdl.stream().map(x->x.getProjectId()).collect(Collectors.toList());
            Integer pid=timeTrackerDTO.getProjectId();

            tsd.setProjectId(timeTrackerDTO.getProjectId());
            tsd.setDescription(timeTrackerDTO.getDescription());
            tsd.setProjectName(timeTrackerDTO.getProjectName());

            timeTrackerRepository.save(tsd);
            return tsd;

        }
        public TimeTrackerEntity stopTime(TimeTrackerDTO timeTrackerDTO)throws SQLException {

            TimeTrackerEntity tsd=new TimeTrackerEntity();
            List<TimeTrackerEntity> tdl=new ArrayList<>();
            List<Integer> list1=new ArrayList<>();
            List<String> list2=new ArrayList<>();
            tdl=timeTrackerRepository.findAll();
            list1= tdl.stream().map(x->x.getProjectId()).collect(Collectors.toList());
            list2= tdl.stream().map(x->x.getStopStatus()).collect(Collectors.toList());
            Integer pid=timeTrackerDTO.getProjectId();

            if(list1.contains(pid))
            {
                for(TimeTrackerEntity ts:tdl) {
                    boolean b=ts.getProjectId().equals(pid) && ts.getStopStatus()=="Updated";
                    if(ts.getProjectId().equals(pid) && ts.getStopStatus()=="Updated") {
                        tsd.setProjectId(timeTrackerDTO.getProjectId());
                        tsd.setDescription(timeTrackerDTO.getDescription());
                        tsd.setProjectName(timeTrackerDTO.getProjectName());
                        tsd.setStartTime(timeTrackerDTO.getStartTime());
                        tsd.setStopTime(timeTrackerDTO.getStopTime());
                        timeTrackerRepository.save(tsd);
                        return tsd;

                    }
                    else  {
                        ts.setStopTime(timeTrackerDTO.getStopTime().now());
                        ts.setStopStatus("Updated");
                    }
                    timeTrackerRepository.save(ts);
                }
            }

            return tsd;
        }
    }


