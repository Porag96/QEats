
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;
  @Autowired
  private RestaurantRepositoryService restaurantRepositoryService;


  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(
      GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {

      List<Restaurant> restaurants = new ArrayList<>();

      if (isPeakHour(currentTime)){
          restaurants = restaurantRepositoryService.findAllRestaurantsCloseBy(getRestaurantsRequest.getLatitude(), getRestaurantsRequest.getLongitude(), currentTime, peakHoursServingRadiusInKms);
      } else {
        restaurants = restaurantRepositoryService.findAllRestaurantsCloseBy(getRestaurantsRequest.getLatitude(), getRestaurantsRequest.getLongitude(), currentTime, normalHoursServingRadiusInKms);
      }
      GetRestaurantsResponse getRestaurantsResponse = new GetRestaurantsResponse(restaurants);
     return getRestaurantsResponse;
  }


  private boolean isPeakHour(LocalTime currentTime) {

    if(currentTime.compareTo(LocalTime.of(8, 0)) >= 0 && currentTime.compareTo(LocalTime.of(10, 0)) <= 0 || 
      currentTime.compareTo(LocalTime.of(13, 0)) >= 0 && currentTime.compareTo(LocalTime.of(15, 0)) <= 0 || 
      currentTime.compareTo(LocalTime.of(19, 0)) >= 0 && currentTime.compareTo(LocalTime.of(21, 0)) <= 0){
        return true;
    }
    return false;
  }


}

