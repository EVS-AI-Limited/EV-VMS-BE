package com.evision.tms.service;

import com.evision.tms.dto.UserBreakDetailsDTO;

import java.text.ParseException;
import java.util.List;

public interface UserBreakDetailsService {
    List<UserBreakDetailsDTO> getAllBreak();
    UserBreakDetailsDTO getOneBreak(long id);
    UserBreakDetailsDTO createBreak(int userBreakId, UserBreakDetailsDTO userBreakDetailsDTO) throws Exception;
    UserBreakDetailsDTO updateBreak(long id, UserBreakDetailsDTO userBreakDetailsDTO) throws ParseException;

}
