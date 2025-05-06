package com.evision.tms.service;

import com.evision.tms.dto.SearchDTO;

import java.util.List;

public interface SearchService {
  List<SearchDTO> getSearchDetail(SearchDTO searchDTO);

}
