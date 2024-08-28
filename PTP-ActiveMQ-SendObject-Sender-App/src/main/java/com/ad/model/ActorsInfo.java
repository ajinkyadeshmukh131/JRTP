package com.ad.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorsInfo implements Serializable {
	
   private Integer aid;
   private String actorName;
   private String actorAddrs;
	
	
}
