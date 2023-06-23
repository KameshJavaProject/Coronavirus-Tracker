package com.example.demo.services;

import java.util.List; 
import java.util.Optional;
import org.springframework.stereotype.Repository; 
import com.example.demo.model.LocationStates;
  
  @Repository 
  public class CoronaVirusDataServicesRepository {

	public Optional<LocationStates> findById(int countryid) {
		// TODO Auto-generated method stub
		return null;
	}

	public LocationStates findByCountry(String countryName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LocationStates> findBylatestTotalDeaths(int count) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveAll(List<LocationStates> allstates) {
		// TODO Auto-generated method stub
		
	}
	
  
  
  
  }
 
